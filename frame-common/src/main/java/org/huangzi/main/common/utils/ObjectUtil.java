package org.huangzi.main.common.utils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author: XGLLHZ
 * @date: 2020/2/8 下午6:39
 * @description: object 工具类
 */
public class ObjectUtil {

    /**
     * entity 与 dto 的转换
     * @param entity
     * @param dto
     * @param <T1>
     * @param <T2>
     * @return
     */
    public static <T1, T2> T1 beanConvert(T1 entity, T2 dto) {
        List<Map<String, Object>> entityFieldList = getFieldInfo(entity);
        List<Map<String, Object>> dtoFieldList = getFieldInfo(dto);
        for (Map mapEntity : entityFieldList) {
            for (Map mapDto : dtoFieldList) {
                if (mapEntity.get("type").toString().equals(mapDto.get("type").toString())
                    && mapEntity.get("name").toString().equals(mapDto.get("name").toString())) {
                    try {
                        Field field = entity.getClass().getDeclaredField(mapEntity.get("name").toString());
                        field.setAccessible(true);
                        field.set(entity, mapDto.get("value"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return entity;
    }

    //数组和集合的区别：两者都是Java中的容器
    //    ：数组大小是固定的，集合大小是动态变化的
    //    ：数组中的只能放一种类型的数据，集合中可以放多种类型的数据
    //数组与集合间的相互转化：
    //    ：数组 => 集合：Arrays.asList(String[] string)
    //    ：集合 => 数组：list.toArray()
    //集合中add()与addAll()的区别：
    //    ：add()：添加一条item
    //    ：addAll()：添加一条或多条item(添加量由被添加者决定)
    /**
     * 获取对象属性，包括属性类型、属性值、属性名
     * @param object
     * @return List<Map>
     */
    private static List<Map<String, Object>> getFieldInfo(Object object) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Field> list1 = new ArrayList<>();
        //获取对象中的属性名并放入 list
        list1.addAll(Arrays.asList(object.getClass().getDeclaredFields()));
        for (Field field : list1) {
            Map<String, Object> map = new HashMap<>();
            map.put("type", field.getType());
            map.put("name", field.getName());
            map.put("value", getFieldValueByName(field.getName(), object));
            list.add(map);
        }
        return list;
    }

    /**
     * 通过属性名来获取属性值
     * @param fieldName
     * @param object
     * @return
     */
    private static Object getFieldValueByName(String fieldName, Object object) {
        try {
            //通过反射机制来获取对象中的属性
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，确保 private 属性能够被访问
            field.setAccessible(true);
            //获取属性值
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
