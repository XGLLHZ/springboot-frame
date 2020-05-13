package org.huangzi.main.common.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author: XGLLHZ
 * @date: 2020/5/13 下午4:53
 * @description: 时间戳序列化工具类
 */
public class TimeSerialize extends JsonSerializer<Timestamp> {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void serialize(Timestamp timestamp, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(sdf.format(timestamp));
    }

}
