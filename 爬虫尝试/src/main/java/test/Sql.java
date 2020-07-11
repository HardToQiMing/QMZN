package test;

import java.sql.*;


public class Sql
{

    public static Connection getConnection()
    {
        Connection conn = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cov2019?serverTimezone=UTC", "root", "yy20001209");
            System.out.println("创建连接");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }



    public static void close(Connection conn, PreparedStatement ps,ResultSet rs)
    {//关闭连接
        if (rs != null)
        {
            try
            {
                rs.close();
            } catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (ps != null)
        {
            try
            {
                ps.close();
            } catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (conn != null)
        {
            try
            {
                conn.close();
            } catch (SQLException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public int add(Connection con,Province province) throws SQLException
    {//向表中插入数据
        String sql="insert into cov2019.provinces values(?,?)";
        PreparedStatement pstm=con.prepareStatement(sql);
        pstm.setString(1,province.getPro_Name());
        pstm.setInt(2,province.getConfirmedNum());
        int result=pstm.executeUpdate();
        pstm.close();
        return result;
    }

    public void create(Connection con)
    {//创建表
        if(validateTableNameExist("provinces"))//如果表已存在则不用创建
        {
            System.out.println("数据库关系表已存在");
        }else
        {
            //建表
            Connection conn = null;
            Statement stmt = null;
            String creatsql = "create table cov2019.provinces(pro_name char(10) primary key,confirmed_num int(10))";
            try {

                System.out.println("创建表");
                stmt = con.createStatement();
                if (0 == stmt.executeLargeUpdate(creatsql))
                {
                    System.out.println("成功创建表！");
                    //return 0;
                }
                else
                {
                    System.out.println("创建表失败！");
                    //return (int)stmt.executeLargeUpdate(creatsql);
                }
                stmt.close();
                System.out.println("关闭资源");
            } catch (Exception e)
            {
                System.out.println("创建表失败！");
                e.printStackTrace();
            }
        }

    }





    public static boolean validateTableNameExist(String tableName)
    {//https://blog.csdn.net/lmb55/article/details/54837719

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cov2019?serverTimezone=UTC", "root", "yy20001209");
            ResultSet rs = con.getMetaData().getTables(null, null, tableName, null);
            if (rs.next()) {
                return true;
            }else {
                return false;
            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }





}

