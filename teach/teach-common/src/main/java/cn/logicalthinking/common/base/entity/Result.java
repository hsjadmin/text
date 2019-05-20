package cn.logicalthinking.common.base.entity;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "结果集",description="结果集") 
public class Result extends JSONObject{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "状态码")
	public static final String KEY_CODE = "code";
	@ApiModelProperty(value = "消息提示")
	public static final String KEY_MSG = "msg";  
	@ApiModelProperty(value = "总条数")
	public static final String KEY_TOTAL = "total";
	@ApiModelProperty(value = "结果集")
	public static final String KEY_RESULT = "result";
	

	/**
	 * 结果集
	 * @param success
	 * 			成功   true  失败  false
	 * @param data
	 * 			结果集数据
	 * @param total
	 * 			总条数
	 * @return Result
	 * @date 2018年1月2日
	 *
	 */
	public static Result jsonData(Object code, Object data, long total) {
		Result result = new Result();
		result.put(KEY_CODE, code);
		result.put(KEY_RESULT, data);
		result.put(KEY_TOTAL, total);
		return result;
	}
	/**
	 * 结果集
	 * @param success
	 * 			成功   true  失败  false
	 * @param data
	 * 			结果集数据
	 * @return Result
	 * @date 2018年1月2日
	 *
	 */
	public static Result jsonData(String code, Object data) {
		Result result = new Result();
		result.put(KEY_CODE, code);
		result.put(KEY_RESULT, data);
		return result;
	}

	/**
	 * 提示信息
	 * @param success
	 * 			成功   true  失败  false
	 * @param message 
	 * 			消息
	 * @return Result
	 * @date 2017年8月10日
	 *
	 */
	public static Result jsonMsg(String code, String message) {
		Result result = new Result();
		result.put(KEY_CODE, code);
		result.put(KEY_MSG, message);
		return result;
	}
	
	public static Result jsonMsg(String code, String message,String key,String value) {
		Result result = new Result();
		result.put(KEY_CODE, code);
		result.put(KEY_MSG, message);
		result.put(key, value);
		return result;
	}
}
