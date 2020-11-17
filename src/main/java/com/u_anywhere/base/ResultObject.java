package com.u_anywhere.base;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description= "返回响应数据")
public class ResultObject  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "是否成功")
	private boolean success=true;
    @ApiModelProperty(value = "返回对象")
	private Object result=null;
    @ApiModelProperty(value = "错误编号")
	private String stateCode=null;
	private Long total;//总条数
	public static ResultObject createErrorMessage(String msg) {
		return createErrorMessage(msg,null);
	}
	
	public static ResultObject createErrorMessage(Object msg,String errorcode) {
		ResultObject ro=new ResultObject();
		ro.setSuccess(false);
		if (errorcode!=null)ro.setStateCode(errorcode);
		ro.setResult(msg);
		return ro;
	}
		
	public static ResultObject createSuccessMessage(String msg) {
		return createSuccessMessage(msg,null);
	}
	public static ResultObject createSuccessMessage(Object object,String successcode) {
		ResultObject ro = new ResultObject();
		ro.setSuccess(true);
		if (object!=null)ro.setResult(object);
		if (successcode!=null)ro.setStateCode(successcode);
		return ro;
	}
	public static ResultObject createSuccess() {
		return createSuccessMessage(null);
	}
	
}
