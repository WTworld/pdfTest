package com.u_anywhere.base;

import com.u_anywhere.base.mapper.SelectMapMapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;


public interface MultipleIdMapper<T> extends Mapper<T>, InsertListMapper<T>,  SelectMapMapper<T> {
}
