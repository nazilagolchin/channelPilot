package com.channelpilot.productconnect.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.channelpilot.productconnect.model.ProductEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ChannelpilotConnectApplicationTests {

	private static final String API_V2_ADD_PRODUCT = "/api/v2/addProduct";
	private static final String AUTHORIZATION = "Authorization";
	private static final String API_V1_ADD_PRODUCT = "/api/v1/addProduct";
	private static final String TOKEN = "token";
	private static final String USER = "user";
	private static final String PASSWORD2 = "password";
	private static final String API_USER = "/api/user";
	@Autowired
	private MockMvc mockMvc;

	/**
	 * call /api/user and get token
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	private String obtainAccessToken(String username, String password) throws Exception {
		ResultActions result = mockMvc.perform(post(API_USER).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
				.param(USER, username).param(PASSWORD2, password));
		String resultString = result.andReturn().getResponse().getContentAsString();
		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resultString).get(TOKEN).toString();
	}

	@Test
	public void giveAccessTokenByUserAndPass() {
		try {
			mockMvc.perform(post(API_USER).contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
					.param(USER, "nazila").param(PASSWORD2, "golchin")).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenNoToken_thenForbiddenForAddProduct() {
		ProductEntity productEntity = new ProductEntity();
		ObjectMapper mapper = new ObjectMapper();
		String inputJson;
		try {
			inputJson = mapper.writeValueAsString(productEntity);
			String url = API_V1_ADD_PRODUCT;
			mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(inputJson))
					.andExpect(status().isForbidden());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldAddProductWithAccessTokenAndMandatory_V1() throws Exception {
		try {
			String accessToken = obtainAccessToken("nazila", "golchin");
			ProductEntity productEntity = new ProductEntity();
			productEntity.setx("x1");
			productEntity.setY("y1");
			productEntity.setZ("z1");
			ObjectMapper mapper = new ObjectMapper();
			String inputJson = mapper.writeValueAsString(productEntity);
			mockMvc.perform(post(API_V1_ADD_PRODUCT).contentType(MediaType.APPLICATION_JSON)
					.header(AUTHORIZATION, accessToken).content(inputJson)).andExpect(status().isCreated());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldAddProductWithAccessTokenAndMandatory_V2() throws Exception {
		try {
			String accessToken = obtainAccessToken("nazila", "golchin");
			ProductEntity productEntity = new ProductEntity();
			productEntity.setx("x2");
			productEntity.setY("y2");
			productEntity.setZ("z2");
			productEntity.setA("a2");
			productEntity.setB("b2");
			ObjectMapper mapper = new ObjectMapper();
			String inputJson = mapper.writeValueAsString(productEntity);
			mockMvc.perform(post(API_V2_ADD_PRODUCT).contentType(MediaType.APPLICATION_JSON)
					.header(AUTHORIZATION, accessToken).content(inputJson)).andExpect(status().isCreated());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldNotAddProductWithAccessTokenAndNotMandatory_V1() throws Exception {
		try {
			String accessToken = obtainAccessToken("nazila", "golchin");
			ProductEntity productEntity = new ProductEntity();
			productEntity.setx("x1");
			productEntity.setY("y1");
			ObjectMapper mapper = new ObjectMapper();
			String inputJson = mapper.writeValueAsString(productEntity);
			mockMvc.perform(post(API_V1_ADD_PRODUCT).contentType(MediaType.APPLICATION_JSON)
					.header(AUTHORIZATION, accessToken).content(inputJson)).andExpect(status().isBadRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldNotAddProductWithAccessTokenAndNotMandatory_V2() throws Exception {
		try {
			String accessToken = obtainAccessToken("nazila", "golchin");
			ProductEntity productEntity = new ProductEntity();
			productEntity.setx("x1");
			productEntity.setY("y1");
			productEntity.setZ("z1");
			productEntity.setA("a1");
			ObjectMapper mapper = new ObjectMapper();
			String inputJson = mapper.writeValueAsString(productEntity);
			mockMvc.perform(post(API_V2_ADD_PRODUCT).contentType(MediaType.APPLICATION_JSON)
					.header(AUTHORIZATION, accessToken).content(inputJson)).andExpect(status().isBadRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
