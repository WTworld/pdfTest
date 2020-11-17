package com.u_anywhere.base.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface SelectMapMapper<T> {

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param example
     * @return
     */
    @SelectProvider(type = MySelectProvider.class, method = "dynamicSQL")
    List<Map<String, Object>> selectMapByExample(Object example);

    @SelectProvider(type = MySelectProvider.class, method = "dynamicSQL")
    List<Map<String, Object>> selectMap(Object example);

    @InsertProvider(type = MySelectProvider.class, method = "dynamicSQL")
    int insertBatch(List<T> var1);


    @SelectProvider(type = MySelectProvider.class, method = "dynamicSQL")
    List<T> selectByMap(@Param("params") Map<String, Object> params);

    @SelectProvider(type = MySelectProvider.class, method = "dynamicSQL")
    List<T> selectByKey(@Param("key") String key, @Param("value") Object value);
}
