package com.itheima.test;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleTypes;
import org.junit.Test;

import java.sql.*;

public class TestOracle {


    /*
        测试连接Oracle进行查询
     */
    @Test
    public void testOracle() throws Exception {
        //注册驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //创建连接
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.80.88:1521:orcl", "scott", "itcast");
        //进行预编译
        PreparedStatement pre = conn.prepareStatement("select * from emp where empno=?");
        pre.setObject(1, 7788);
        //执行sql
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getObject("sal"));
        }
    }


    /*
        测试连接Oracle调用存储 函数  查询员工年薪
     */
    @Test
    public void testCallProcedure() throws Exception {
        //注册驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //创建连接
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.80.88:1521:orcl", "scott", "itcast");
        //进行预编译
        CallableStatement cst = conn.prepareCall("{call p_yearsal(?,?)}");
        cst.setObject(1, 7788);
        cst.registerOutParameter(2, OracleTypes.NUMBER);
        //执行sql
        cst.execute();
        System.out.println(cst.getObject(2));
    }
    /*
       测试连接Oracle调用存储 过程  查询员工年薪
    */
    @Test
    public void testCallFunction() throws Exception {
        //注册驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //创建连接
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.80.88:1521:orcl", "scott", "itcast");
        //进行预编译
        CallableStatement cst = conn.prepareCall("{?=call f_yearsal(?)}");
        cst.setObject(2, 7788);
        cst.registerOutParameter(1, OracleTypes.NUMBER);
        //执行sql
        cst.execute();
        System.out.println(cst.getObject(1));
    }
}

