package com.u_anywhere.base.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Collection;
import java.util.List;

public interface BatchIdMapper<T> {


    @SelectProvider(type = MySelectProvider.class, method = "dynamicSQL")
    List<T> selectByIdBatch(@Param("ids") Collection<String> ids);

    @DeleteProvider(
            type = MySelectProvider.class,
            method = "dynamicSQL"
    )
    int deleteByIdBatch(@Param("ids") Collection<String> ids);

}
