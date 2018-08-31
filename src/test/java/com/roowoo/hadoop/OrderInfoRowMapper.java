package com.roowoo.hadoop;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.data.hadoop.hbase.RowMapper;

/**
 * @author zhuzengpeng
 * @date 2018/8/30 14:07
 */
public class OrderInfoRowMapper implements RowMapper<OrderInfoDTO> {

    private static byte[] COLUMNFAMILY = "orderinfo".getBytes();
    private static byte[] MERCHANTID = "merchantId".getBytes();
    private static byte[] MERCHANTNAME = "merchantName".getBytes();
    private static byte[] HOPETIME = "hopeTime".getBytes();
    private static byte[] DELIVERADDRESS = "deliverAddress".getBytes();
    private static byte[] LONGITUDE = "longitude".getBytes();
    private static byte[] LATITUDE = "latitude".getBytes();

    @Override
    public OrderInfoDTO mapRow(Result result, int i) throws Exception {
        OrderInfoDTO dto = new OrderInfoDTO();
//        Long merchantId = Bytes.toLong(result.getValue(COLUMNFAMILY, MERCHANTID));
        String merchantName = Bytes.toString(result.getValue(COLUMNFAMILY, MERCHANTNAME));
        String hopeTime = Bytes.toString(result.getValue(COLUMNFAMILY, HOPETIME));
        String deliverAddress = Bytes.toString(result.getValue(COLUMNFAMILY, DELIVERADDRESS));
//        BigDecimal longitude = Bytes.toBigDecimal(result.getValue(COLUMNFAMILY, LONGITUDE));
//        BigDecimal latitude = Bytes.toBigDecimal(result.getValue(COLUMNFAMILY, LATITUDE));
//        dto.setMerchantId(merchantId);
        dto.setMerchantName(merchantName);
        dto.setHopeTime(hopeTime);
        dto.setDeliverAddress(deliverAddress);
//        dto.setLongitude(longitude);
//        dto.setLatitude(latitude);
        return dto;
    }
}
