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
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Provinces
{


    String updateTime;//更新时间
    ArrayList<Province> provinces;//行政区列表
    public Provinces()
    {

        /*
        //1.打开浏览器，创建httpClient对象
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //2.输入网址
        HttpGet httpGet=new HttpGet("https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5");
        //3.按回车，发送请求，返回响应
        CloseableHttpResponse response=null;
        try{
            response=httpClient.execute(httpGet);
            //4.解析响应
            String content=new String();
            if(response.getStatusLine().getStatusCode()==200)
            {
                HttpEntity httpEntity=response.getEntity();
                content = EntityUtils.toString(httpEntity,"utf8");
                System.out.println(content);


            }
        }catch(IOException e)
        {
            System.out.println(e.getMessage());
        }finally
        {
            //关闭response
            try
            {
                response.close();
            }catch(IOException e)
            {
                e.printStackTrace();
            }
            //关闭client
            try
            {
                httpClient.close();
            }catch(IOException e)
            {
                e.printStackTrace();
            }


        }

         */

        /*
        //解析url地址
        Document doc = Jsoup.parse(new URL("https://news.qq.com/zt2020/page/feiyan.htm#/?pool=bj&nojump=1"),1000);
        //使用标签选择器
        String title=doc.getElementsByTag("title").first().text();
        System.out.println(title);
        System.out.println("信息是"+doc.getElementsByTag("span").first().text());

         */















    }

}
