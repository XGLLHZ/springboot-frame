package org.huangzi.main.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author: XGLLHZ
 * @date: 2019/11/7 15:02
 * @description: redis 配置文件
 * 重新配置 RedisTemplate 的原因：1、springboot 自动生成的 RedisTemplate 的泛型是 <Object, Object>，
 *                                  而我们需要一个 <String, Object> 这样的泛型
 *                              2、springboot 自动生成的 RedisTemplate 在存数据时没有对 key 序列化
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        //配置连接工厂
        template.setConnectionFactory(factory);

        //使用 Jackson 来序列化和反序列化 redis 的 value 值（默认使用 JDK 的序列化方法）
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        //使用 String 来序列化 redis 的 key 值
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        ObjectMapper objectMapper = new ObjectMapper();
        //指定序列化的域，field、get、set，以及修饰范围，ANY 是都包括
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //指定序列化输入的类型，必须是 final 修饰的
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jsonRedisSerializer.setObjectMapper(objectMapper);

        //序列化
        //序列化 key
        template.setKeySerializer(stringRedisSerializer);
        //序列化 value
        template.setValueSerializer(jsonRedisSerializer);
        //序列化 hash 的 key
        template.setHashKeySerializer(stringRedisSerializer);
        //序列化 hash 的 key
        template.setHashValueSerializer(jsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }

}
