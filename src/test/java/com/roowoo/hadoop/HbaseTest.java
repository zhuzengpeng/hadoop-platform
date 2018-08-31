package com.roowoo.hadoop;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

import java.io.IOException;
import java.util.*;

import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;

/**
 * @author zhuzengpeng
 */
public class HbaseTest {

    private ApplicationContext ctx;
    private HbaseTemplate hbaseTemplate;

    String TABLE_NAME = "hgo_order_info";
    String orderNo = "20180829140313150000001437";
    String TABLE_COLUMN_FAMILY = "orderinfo";

    @Before
    public void setUp() {
        ctx = new ClassPathXmlApplicationContext("spring/hbase.xml");
        this.hbaseTemplate = ctx.getBean(HbaseTemplate.class);
    }

    @Test
    public void test1() {
        OrderInfoDTO orderInfoDTO = hbaseTemplate.get("hgo_order_info", "20180829140313150000001437", new OrderInfoRowMapper());
        System.out.println(JSON.toJSONString(orderInfoDTO));
    }

    @Test
    public void test2() {
        String cf_name = "orderinfo";
        byte[] cf_bytes = Bytes.toBytes(cf_name);
        Scan scan = new Scan();
        scan.setCaching(10);//全表扫描设置cache
        scan.addFamily(cf_name.getBytes());
        List<Object> entities = hbaseTemplate.find("hgo_order_info", scan, results -> {
            List<Object> objectList = new ArrayList<>();
            Iterator<Result> iterator = results.iterator(); //这个迭代器只能获取一次
            while (iterator.hasNext()) {
                //迭代获取数据
//                        SimpleLocationEntity temp = new SimpleLocationEntity();
                Result next = iterator.next();
                next.getFamilyMap(cf_bytes).forEach((bytes, values) -> {
                    byte[] row = next.getRow();
                    System.out.println("rowKey:" + Bytes.toString(next.getRow(), 0, 7) + Bytes.toIntUnsafe(row, 7));
                    System.out.println(values.toString());
                });
                objectList.add(next);
            }
            return objectList;
        });
        System.out.println(entities.size());
    }

    @Test
    public void test3() {
        List<String> list = hbaseTemplate.get(TABLE_NAME, orderNo, (result, rowNum) -> {
            List<Cell> ceList = result.listCells();
            List<String> applications = new ArrayList();
            if (ceList != null && ceList.size() > 0) {
                for (Cell cell : ceList) {
                    applications.add(Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
                }
            }
            return applications;
        });
        System.out.println(JSON.toJSONString(list));
    }


    @Test
    public void test4() {
        String[] family = {"loginfo"};
        creatTable("sys_log", family);

    }

    private void creatTable(String tableName, String[] family) {
        Connection connection = null;
        try {
            //
            if (connection == null) {
                connection = ConnectionFactory.createConnection(hbaseTemplate.getConfiguration());
            }

            Admin admin;
            admin = connection.getAdmin();

            HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(tableName));
            for (int i = 0; i < family.length; i++) {
                desc.addFamily(new HColumnDescriptor(family[i]));
            }
            if (admin.tableExists(TableName.valueOf(tableName))) {
                System.out.println("table Exists!");
                System.exit(0);
            } else {
                admin.createTable(desc);
                System.out.println("create table Success!");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 查询特定列的值
     */
    @Test
    public void test5() {
        String tableName = "sys_log";
        String rowKey = "00992c75-8997-4f68-ae60-56ac36bab83b";
        String family = "loginfo";
        String qualifier = "title";
        String value = hbaseTemplate.get(tableName, rowKey, family, qualifier, new RowMapper<String>() {
            @Override
            public String mapRow(Result result, int rowNum) {
                byte[] bytes = result.getValue(Bytes.toBytes(family), Bytes.toBytes(qualifier));
                if (bytes != null) {
                    return new String(bytes);
                } else {
                    return null;
                }
            }
        });
        System.out.println(value);
    }

    @Test
    public void test6() {
        String tableName = "sys_log";
        String rowKey = "00992c75-8997-4f68-ae60-56ac36bab83b";
        String family = "loginfo";
        List<String> list = hbaseTemplate.find(tableName, family, results -> {
            int ii = 0;
            List<String> values = new ArrayList();
            for (Result result : results) {
                System.out.println(Bytes.toString(result.getValue(Bytes.toBytes(family), Bytes.toBytes("title"))));
                System.out.println(ii++);
                if (Bytes.equals(result.getRow(), Bytes.toBytes(rowKey))) {
                    for (Cell cell : result.listCells()) {
                        String value = Bytes.toString(cell.getValueArray());
                        values.add(value);
                    }
                    break;
                }
            }
            return values;
        });
        System.out.println(JSON.toJSONString(list));
    }

    /**
     * 根据行健及列族查询此行健列族下的所有值
     */
    @Test
    public void test7() {
        String tableName = "sys_log";
        String rowKey = "00992c75-8997-4f68-ae60-56ac36bab83b11";
        String family = "loginfo";
        String valueJson = hbaseTemplate.get(tableName, rowKey, family, (result, rowNum) -> {
            Map<String, Object> valueMap = Maps.newHashMap();
            valueMap.put("rowKey", rowKey);
            NavigableMap<byte[], byte[]> map = result.getFamilyMap(Bytes.toBytes(family));
            if(map == null) {
                return "";
            }
            map.forEach((k, v) -> valueMap.put(Bytes.toString(k), Bytes.toString(v)));
            return JSON.toJSONString(valueMap);
        });
        System.out.println(valueJson);
    }

    @Test
    public void test8() {
        String tableName = "sys_log";
        String rowKey = "00992c75-8997-4f68-ae60-56ac36bab83b1";
        String family = "loginfo";
        hbaseTemplate.delete(tableName, rowKey, family);
    }
}
