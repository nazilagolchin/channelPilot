package com.channelpilot;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonHelper {

	/**
	 * parses a string, removes unwanted backslashes and returns a json object
	 * 
	 * @param data
	 * @return
	 */
	public static JSONObject parse(String data) {
		JSONObject result = new JSONObject();
		try {
			result = new JSONObject(data.replace("\\\"", "\""));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * tries getting a string from a json object. If key can not be found an empty
	 * string will be returned
	 * 
	 * @param key
	 * @param obj
	 * @return
	 */
	public static String tryGetString(String key, JSONObject obj) {
		String value = "";
		try {
			value = (obj.has(key) ? obj.getString(key) : "");
		} catch (Exception e) {
			value = tryGetObject(key, obj, new String()).toString();
		}
		if (value.toLowerCase().trim().equals("null")) {
			return "";
		}
		return value;
	}

	/**
	 * returns an object from a json object by its key if it's existing
	 * 
	 * @param key
	 * @param obj
	 * @param defaultObj
	 * @return
	 */
	public static Object tryGetObject(String key, JSONObject obj, Object defaultObj) {
		try {
			return (obj.has(key) ? obj.get(key) : defaultObj);
		} catch (Exception e) {
			return defaultObj;
		}
	}
}
