package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import java.sql.Connection;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class CrawlerFirst
{
    //定义几个常量防止反爬虫
    public static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:49.0) Gecko/20100101 Firefox/49.0";
    public static String HOST = "i.snssdk.com";
    public static String REFERER = "https://i.snssdk.com/feoffline/hot_list/template/hot_list/forum_tab.html?activeWidget=1";

    public static void main(String[] args)
    {
        /*
        //1.打开浏览器，创建httpClient对象
        CloseableHttpClient httpClient=HttpClients.createDefault();
        //2.输入网址
        HttpGet httpGet=new HttpGet("https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5");
        //3.按回车，发送请求，返回响应
        try{
            CloseableHttpResponse response=httpClient.execute(httpGet);
            //4.解析响应
            if(response.getStatusLine().getStatusCode()==200)
            {
                HttpEntity httpEntity=response.getEntity();
                String content=EntityUtils.toString(httpEntity,"utf8");
                System.out.println(content);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        //CloseableHttpResponse response=httpClient.execute(httpGet);
        */
        /*
        try
        {
            Provinces provinces = new Provinces();
        }catch(Exception e)
        {
            System.out.println(e.toString());
        }


         */
        String url = "https://i.snssdk.com/forum/home/v1/info/?activeWidget=1&forum_id=1656784762444839";
        try
        {
            String resultBody = Jsoup.connect(url).

                    userAgent(USER_AGENT).header("Host", HOST).header("Referer", REFERER).execute().body();
            com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(resultBody);
            String ncovStringList = jsonObject.getJSONObject("forum").getJSONObject("extra").getString("ncov_string_list");
            com.alibaba.fastjson.JSONObject ncovListObj = JSON.parseObject(ncovStringList);
            JSONArray nationwides=ncovListObj.getJSONArray("nationwide");
            //获取今日数据
            JSONObject currentDateData=nationwides.getJSONObject(0);
            //获取昨日数据
            JSONObject lastDayData = nationwides.getJSONObject(1);
            //System.out.println(currentDateData);
            //System.out.println(lastDayData);
            //System.out.println(currentDateData.getIntValue("confirmedNum"));
            //获取各省数据
            JSONArray provinces=ncovListObj.getJSONArray("provinces");
            JSONObject currentProvincesData=provinces.getJSONObject(0);
            //System.out.println(currentProvincesData);
            //System.out.println(currentProvincesData.getString("name")+"累积确诊患者"+currentProvincesData.getIntValue("confirmedNum"));
            Sql sql=new Sql();
            Connection con=Sql.getConnection();
            //创建privinces表
            sql.create(con);
            //用迭代器来遍历
            for (Iterator<Object> iterator = provinces.iterator(); iterator.hasNext(); )
            {
                JSONObject next = (JSONObject) iterator.next();
                //System.out.println(next.getString("name")+"累积确诊患者"+next.getIntValue("confirmedNum"));
                //将数据存入数据库中
                sql.add(con,new Province(next.getString("name"),next.getIntValue("confirmedNum")));

            }
            System.out.println("获取数据，存入数据库");
            //关闭连接
            con.close();
            System.out.println("连接关闭");

        }catch(IOException e)
        {
            System.out.println(e.getMessage());
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }














    }

}
