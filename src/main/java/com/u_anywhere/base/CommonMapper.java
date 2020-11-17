package com.u_anywhere.base;

import com.u_anywhere.base.mapper.BatchIdMapper;

import tk.mybatis.mapper.common.IdsMapper;

/**
 * @ Description：配置通用mapper的接口
 * @ Modified By：
 * @Version: $version$
 */
public interface CommonMapper<T> extends IdsMapper<T>, BatchIdMapper<T>, MultipleIdMapper<T> {


}
