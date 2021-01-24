package com.channelpilot.productconnect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.channelpilot.productconnect.model.ProductEntity;
import com.channelpilot.productconnect.repository.ProductRepository;

@Service("v2")
public class ProductServiceV2Impl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public ProductEntity save(ProductEntity product) {
		return productRepository.save(product);
	}

}
