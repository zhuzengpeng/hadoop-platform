package com.roowoo.hadoop;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.hive.HiveClientCallback;
import org.springframework.data.hadoop.hive.HiveScript;
import org.springframework.data.hadoop.hive.HiveTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author zhuzengpeng
 */
public class HiveTest {

    private ApplicationContext ctx;
    private HiveTemplate hiveTemplate;

    @Before
    public void setUp() {
        ctx = new ClassPathXmlApplicationContext("spring/hive.xml");
        this.hiveTemplate = ctx.getBean(HiveTemplate.class);
    }

    @Test
    public void test1() {
        List<String> list = hiveTemplate.query("show tables");
        for(String s: list) {
            System.out.println(s);
        }
    }

    @Test
    public void test2() {
        List<String> list = hiveTemplate.query("select no from student  order by no desc");
        list.forEach(s-> {
            System.out.println(s);
        });
    }

    @Test
    public void test3() {
        List<String> list = hiveTemplate.query("select merchant_id,sum(real_amount) from hgo_order_info group by merchant_id");
        list.forEach(s-> System.out.println(s));
    }

    @Test
    public void test4() {
        List<String> list = hiveTemplate.query("select count(*) from hgo_order_info");
        list.forEach(s-> System.out.println(s));
    }
}
