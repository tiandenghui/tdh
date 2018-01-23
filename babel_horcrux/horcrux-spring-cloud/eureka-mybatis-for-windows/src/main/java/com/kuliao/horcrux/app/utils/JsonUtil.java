package com.kuliao.horcrux.app.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 本类是基于jackson的JSON工具类
 *
 * @author zc
 */
public class JsonUtil {

	/**
	 * 创建ObjectMapper对象
	 *
	 * @return mapper实例
	 */
	private static final ObjectMapper mapper = new ObjectMapper();

	private JsonUtil() {
	}

	public static ObjectMapper getMapperInstance() {
		return mapper;
	}

	/**
	 * object 转换成 json
	 *
	 * @param obj
	 * @return json字符串
	 */
	public static String ObjectToJson(Object obj) {
		ObjectMapper mapper = JsonUtil.getMapperInstance();
		String str = null;
		try {
			str = mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return str;
	}

	/**
	 * bean 转换成 json
	 *
	 * @param obj
	 * @return json字符串
	 */
	public static String beanToJson(Object obj) {
		ObjectMapper mapper = JsonUtil.getMapperInstance();
		StringWriter writer = new StringWriter();
		JsonGenerator gen = null;
		String json = null;
		try {
			gen = new JsonFactory().createJsonGenerator(writer);
			mapper.writeValue(gen, obj);

			json = writer.toString();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				gen.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return json;
	}

	/**
	 * Json 转换成bean
	 *
	 * @return
	 */
	public static Object jsonToBean(String json, Class<?> cls) {
		Object vo = null;
		try {
			vo = mapper.readValue(json, cls);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return vo;
	}

	public static Map<String, Object> toSuccessJson() {

		return toSuccessJson(null, null);
	}

	public static Map<String, Object> toSuccessJson(String objName, Object obj) {

		return toJson(objName, obj, MsgCode.STATUS_CODE_TRUE, null, null);
	}

	// add by xiongzhe
	public static Map<String, Object> toSuccessJson(Map<String, Object> bodyMap) {
		// json Map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// header Map
		Map<String, Object> headerMap = new HashMap<String, Object>();
		// exception code Map
		Map<String, Object> errorMsg = new HashMap<String, Object>();
		// String[] errorCodeArray = errorCode.split(",");
		errorMsg.put("errorCode", null);
		errorMsg.put("errorDesc", null);
		// 填充 header
		headerMap.put("statusCode", MsgCode.BODY_RETURN_TRUE);
		headerMap.put("errorMsg", errorMsg);

		if (null == bodyMap) {
			bodyMap = new HashedMap();
		}
		// 填充json map
		jsonMap.put("header", headerMap);
		jsonMap.put("body", bodyMap);
		return jsonMap;
	}

	public static Map<String, Object> toFailJson(String errorCode, String msg) {

		return toJson(null, null, MsgCode.STATUS_CODE_FALSE, errorCode, msg);
	}

	public static Map<String, Object> toFailJson(MessageCode messageCode) {

		return toJson(null, null, MsgCode.STATUS_CODE_FALSE,
				messageCode.getCode(), messageCode.getMessage());
	}

	/**
	 * 对象转JSON
	 *
	 * @param objName
	 *            ：对象名
	 * @param obj
	 *            ：对象
	 * @param statusCode
	 *            ：状态码
	 * @return JSON字符串
	 */
	public static Map<String, Object> toJson(String objName, Object obj,
			String statusCode, String code, String msg) {

		// json Map
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// header Map
		Map<String, Object> headerMap = new HashMap<String, Object>();

		// exception code Map
		Map<String, Object> errorMsg = new HashMap<String, Object>();
		// String[] errorCodeArray = errorCode.split(",");
		errorMsg.put("errorCode", code);
		errorMsg.put("errorDesc", msg);

		// 填充 header
		headerMap.put("statusCode", statusCode);
		headerMap.put("errorMsg", errorMsg);

		// body Map
		Map<String, Object> bodyMap = new HashMap<String, Object>();

		if (null != objName && null != obj) {
			bodyMap.put(objName, obj);
		}

		// 填充json map
		jsonMap.put("header", headerMap);
		jsonMap.put("body", bodyMap);

		return jsonMap;
	}

	/**
	 * 对象转JSON
	 *
	 * @param objName
	 *            ：对象名
	 * @param pageInfo
	 *            ：分页信息
	 * @param obj
	 *            ：对象
	 * @param statusCode
	 *            ：状态码
	 * @return JSON字符串
	 */
	public static Map<String, Object> toJsonPage(String objName, Object obj,
			Object pageInfo, String statusCode, String code, String msg) {

		// json Map
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// header Map
		Map<String, Object> headerMap = new HashMap<String, Object>();

		// exception code Map
		Map<String, Object> errorMsg = new HashMap<String, Object>();
		errorMsg.put("errorCode", code);
		errorMsg.put("errorDesc", msg);

		// 填充 header
		headerMap.put("statusCode", statusCode);
		headerMap.put("errorMsg", errorMsg);

		// body Map
		Map<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put(objName, obj);
		bodyMap.put("pageInfo", pageInfo);

		// 填充json map
		jsonMap.put("header", headerMap);
		jsonMap.put("body", bodyMap);

		return jsonMap;
	}

	/**
	 * 对象转JSON
	 *
	 * @param objName
	 *            ：对象名
	 * @param pageInfo
	 *            ：分页信息
	 * @param obj
	 *            ：对象
	 * @param statusCode
	 *            ：状态码
	 * @return JSON字符串
	 */
	public static Map<String, Object> toJsonPage(String objName, Object obj,
			Object pageInfo, String statusCode) {

		return toJsonPage(objName, obj, pageInfo, statusCode, null, null);
	}

	// 参数传递错误JSON
	public static Map<String, Object> paramsError() {
		return JsonUtil.toJson("参数错误", null, MsgCode.STATUS_CODE_FALSE,
				MsgCode.PARAMS_ERROR, MsgCode.MSG_PARAMS_ERROR);
	}

	/**
	 * Json转化为List
	 *
	 * @param json
	 * @return
	 */
	public static List<Map<String, Object>> jsonToList(String json) {
		List<Map<String, Object>> listMap = JSON.parseObject(json,
				new TypeReference<List<Map<String, Object>>>() {
				});
		return listMap;

	}

	/**
	 * Json转化为List<String>
	 *
	 * @param json
	 * @return
	 */
	public static List<String> jsonToListOfString(String json) {
		List<String> listString = JSON.parseArray(json, String.class);
		return listString;

	}

	/**
	 * @author pengwq 将集合转换为json格式字符串
	 * @param list
	 *            集合
	 */
	public static String listToJson(List<?> list) {
		JSONArray jsonArray = new JSONArray();
		for (Object object : list) {
			JSONObject jsonObject = JSONObject.fromObject(object);
			jsonArray.add(jsonObject);
		}
		return jsonArray.toString();
	}
	//
	// public static void main(String[] args) {
	//
	// Map<String, Object> operater1 = new HashMap<String, Object>();
	// operater1.put("open", 1);
	// operater1.put("month", 12);
	//
	// Map<String, Object> operater2 = new HashMap<String, Object>();
	// operater2.put("open", 1);
	// operater2.put("month", 1);
	//
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("xsdp", operater1);
	// map.put("dptk", operater2);
	//
	// System.out.println(JsonUtil.beanToJson(map));
	// }

}
