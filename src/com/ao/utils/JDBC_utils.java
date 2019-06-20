package com.ao.utils;



import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC Utils use Durid
 */
public class JDBC_utils {
    private static DataSource ds;



    static{
        //1.load properties;加载配置文件
        Properties pro = new Properties();
            //use class loader;获取字节输入流
        InputStream resourceAsStream = JDBC_utils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            pro.load(resourceAsStream);
            //2.initialize ds;
            ds=DruidDataSourceFactory.createDataSource(pro);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get object connection pool
    public static DataSource getDataSource(){
        return  ds;
    }

    //get connection object

    public static Connection getConnection() throws SQLException {

        return  ds.getConnection();
    }

}
