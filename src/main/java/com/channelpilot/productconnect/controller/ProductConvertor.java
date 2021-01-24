package com.channelpilot.productconnect.controller;

import org.json.JSONObject;

import com.channelpilot.JsonHelper;
import com.channelpilot.productconnect.model.ProductEntity;

public class ProductConvertor {

	/**
	 * convert payload JSONOBJECT to productEntity
	 * 
	 * @param payload product JSONOBJECT
	 * @return
	 */
	public static ProductEntity transmitProduct(String payload) {
		JSONObject json = JsonHelper.parse(payload);
		String x = JsonHelper.tryGetString("x", json);
		String y = JsonHelper.tryGetString("y", json);
		String z = JsonHelper.tryGetString("z", json);
		String a = JsonHelper.tryGetString("a", json);
		String b = JsonHelper.tryGetString("b", json);
		ProductEntity productEntity = new ProductEntity();
		productEntity.setx(x);
		productEntity.setY(y);
		productEntity.setZ(z);
		productEntity.setA(a);
		productEntity.setB(b);
		return productEntity;
	}
}
