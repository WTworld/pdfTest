package com.u_anywhere.base;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;

@Data
public class BaseVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @Transient
    @ApiModelProperty(position = 1)
    private String userToken;
    @JsonIgnore
    @Transient
    @ApiModelProperty(hidden=true)
    private Integer page = 0;
    @JsonIgnore
    @Transient
    @ApiModelProperty(hidden=true)
    private Integer pageSize = 0;//每页显示记录条数
    @JsonIgnore
    @Transient
    @ApiModelProperty(hidden=true)
    private Integer startSize;
    public Integer siteStart() {
        if (pageSize == null || pageSize == 0) {
            return 0;
        }
       return startSize = (page - 1 < 0 ? 0 : page - 1) * pageSize;
    }
}
