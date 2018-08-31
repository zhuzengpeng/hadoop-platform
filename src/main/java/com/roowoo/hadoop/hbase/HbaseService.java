package com.roowoo.hadoop.hbase;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.NavigableMap;

/**
 * @author zhuzengpeng
 * @date 2018/8/31 10:12
 */
@Service
public class HbaseService {

    private Connection connection = null;

    @Autowired
    private HbaseTemplate hbaseTemplate;


    /**
     * 创建HBASE表
     * @param tableName 表名
     * @param family 列族数组
     */
    public void creatTable(String tableName, String[] family) {
        try {
            init(hbaseTemplate);
            Admin admin = connection.getAdmin();
            HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(tableName));
            for (int i = 0; i < family.length; i++) {
                desc.addFamily(new HColumnDescriptor(family[i]));
            }
            if (admin.tableExists(TableName.valueOf(tableName))) {
                System.out.println("table Exists!");
                return;
            } else {
                admin.createTable(desc);
                System.out.println("create table Success!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加数据 ---这是测试用的，不作为正式的API调用
     * @param rowKey
     * @param tableName
     * @param column1
     * @param value1
     * @param column2
     * @param value2
     */
    public void addData(String rowKey, String tableName, String[] column1, String[] value1, String[] column2, String[] value2) {
        try {
            // 设置rowkey
            Put put = new Put(Bytes.toBytes(rowKey));
            Table table;
            table = connection.getTable(TableName.valueOf(tableName));
            // HTabel负责跟记录相关的操作如增删改查等//
            // 获取表
            // 获取所有的列族
            HColumnDescriptor[] columnFamilies = table.getTableDescriptor()
                    .getColumnFamilies();
            for (int i = 0; i < columnFamilies.length; i++) {
                // 获取列族名
                String familyName = columnFamilies[i].getNameAsString();
                // article列族put数据
                if (familyName.equals("article")) {
                    for (int j = 0; j < column1.length; j++) {
                        put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(column1[j]), Bytes.toBytes(value1[j]));
                    }
                }
                // author列族put数据
                if (familyName.equals("author")) {
                    for (int j = 0; j < column2.length; j++) {
                        put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(column2[j]), Bytes.toBytes(value2[j]));
                    }
                }
            }
            table.put(put);
            System.out.println("add data Success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param rowKey 行健
     * @param tableName 表名
     * @param familyName 列族名
     * @param column 列名数组
     * @param value 值数组
     */
    public void addData(String rowKey, String tableName, String familyName, String[] column, String[] value) {
        try {
            // 设置rowkey
            Put put = new Put(Bytes.toBytes(rowKey));
            Table table = connection.getTable(TableName.valueOf(tableName));
            // HTabel负责跟记录相关的操作如增删改查等//
            for (int i = 0; i < column.length; i++) {
                put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(column[i]), Bytes.toBytes(value[i]));
            }
            table.put(put);
            System.out.println("add data Success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init(HbaseTemplate htemplate) throws Exception {
        if (connection == null) {
            connection = ConnectionFactory.createConnection(hbaseTemplate.getConfiguration());
        }
        if (this.hbaseTemplate == null) {
            this.hbaseTemplate = htemplate;
        }
    }

    /**
     * 根据行键、列族、列名查询数据
     * @param tableName
     * @param rowKey
     * @param family
     * @param qualifier
     * @return
     */
    public String getData(String tableName, String rowKey, String family, String qualifier) {
        String value = hbaseTemplate.get(tableName, rowKey, family, qualifier, (result, rowNum) -> {
            byte[] bytes = result.getValue(Bytes.toBytes(family), Bytes.toBytes(qualifier));
            if (bytes != null) {
                return new String(bytes);
            } else {
                return null;
            }
        });
        return value;
    }

    /**
     * 根据表名、行健、列族查询数据
     * @param table 表名
     * @param rowKey 行健
     * @param family 列族
     * @return 查出来的值转成JSON字符串
     */
    public String getData(String table, final String rowKey, final String family) {
        String valueJson = hbaseTemplate.get(table, rowKey, family, (result, rowNum) -> {
            Map<String, Object> valueMap = Maps.newHashMap();
            valueMap.put("rowKey", rowKey);
            NavigableMap<byte[], byte[]> map = result.getFamilyMap(Bytes.toBytes(family));
            if(map == null) {
                return "";
            }
            map.forEach((k, v) -> valueMap.put(Bytes.toString(k), Bytes.toString(v)));
            return JSON.toJSONString(valueMap);
        });
        return valueJson;
    }
}
