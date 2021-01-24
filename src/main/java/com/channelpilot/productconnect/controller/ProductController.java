package com.channelpilot.productconnect.controller;

import java.net.URI;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.channelpilot.productconnect.model.ProductEntity;
import com.channelpilot.productconnect.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/")
@Api(value = "/Products", tags = "Products", description = " Add Product By JSON payload")
public class ProductController {

	@Autowired
	@Qualifier("v1")
	ProductService productserviceV1;

	@Autowired
	@Qualifier("v2")
	ProductService productserviceV2;

	/**
	 * adds a new product with mandatory fields x,y,z
	 * 
	 * @param payload product JSONOBJECT
	 * @return
	 */
	@RequestMapping(value = "/v1/addProduct", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ApiOperation("Add Product with Mandatory field x,y,z.")
	public ResponseEntity<String> addProductV1(@RequestBody String payload) {
		JSONObject result = new JSONObject();
		try {
			ProductEntity productEntity = ProductConvertor.transmitProduct(payload);
			StringBuilder errorMsg = checkConditions(productEntity, 1);
			if (errorMsg.length() > 1) {
				result.put("reason", errorMsg);
				return failedResult(result, HttpStatus.BAD_REQUEST);
			}
			productserviceV1.save(productEntity);
			return successResult(result, productEntity);
		} catch (Exception e) {
			return failedResult(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * adds a new product with mandatory fields x,y,z,a,b
	 * 
	 * @param payload product JSONOBJECT
	 * @return
	 */
	@RequestMapping(value = "/v2/addProduct", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ApiOperation("Add Product with Mandatory field x,y,z,a,b.")
	public ResponseEntity<String> addProductV2(@RequestBody String payload) {
		JSONObject result = new JSONObject();
		try {
			ProductEntity productEntity = ProductConvertor.transmitProduct(payload);
			StringBuilder errorMsg = checkConditions(productEntity, 2);
			if (errorMsg.length() > 1) {
				result.put("reason", errorMsg);
				return failedResult(result, HttpStatus.BAD_REQUEST);
			}
			productserviceV1.save(productEntity);
			return successResult(result, productEntity);
		} catch (Exception e) {
			return failedResult(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * create fail response
	 * 
	 * @param result
	 * @param status
	 * @return
	 */
	private ResponseEntity<String> failedResult(JSONObject result, HttpStatus status) {
		try {
			result.put("success", false);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(status).body(result.toString());
	}

	/**
	 * create success response
	 * 
	 * @param result
	 * @param productEntity
	 * @return
	 */
	private ResponseEntity<String> successResult(JSONObject result, ProductEntity productEntity) {
		try {
			result.put("success", true);
			result.put("payload", productEntity.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ResponseEntity.created(URI.create(String.format("/product/%s", productEntity.getId())))
				.body(result.toString());
	}

	/**
	 * check productEntity for mandatory field regarding to version number
	 * 
	 * @param productEntity
	 * @param Version
	 * @return
	 */
	private StringBuilder checkConditions(ProductEntity productEntity, int Version) {
		StringBuilder errorMsg = new StringBuilder();
		errorMsg.append(((productEntity.getx().equals("")) ? "x " : ""));
		errorMsg.append(((productEntity.getY().equals("")) ? "y " : ""));
		errorMsg.append(((productEntity.getZ().equals("")) ? "z " : ""));
		if (Version == 2) {
			errorMsg.append(((productEntity.getA().equals("")) ? "a " : ""));
			errorMsg.append(((productEntity.getB().equals("")) ? "b " : ""));
		}
		if (errorMsg.length() > 0) {
			errorMsg.insert(0, "fill all mandatory fields: ");
		}
		return errorMsg;
	}

}
