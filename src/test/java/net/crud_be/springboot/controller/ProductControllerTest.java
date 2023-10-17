package net.crud_be.springboot.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.crud_be.springboot.model.Product;
import net.crud_be.springboot.repository.ProductRepository;

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMVC;
	
	@MockBean
	private ProductRepository productRepository;
	
	@Test
    public void testGetAllProduct() throws Exception {
        // Mock data
        Product product1 = new Product("code1", "name1", "category1", "brand1", "type1", "desc1");
        Product product2 = new Product("code2", "name2", "category2", "brand2", "type2", "desc2");
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        when(productRepository.findAll()).thenReturn(productList);

        // Perform GET request
        MockHttpServletResponse response = mockMVC.perform(MockMvcRequestBuilders.get("/api/v1/product")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();

        // Validate response
        assertThat(response.getContentAsString()).isEqualTo("[{\"id\":0,\"productCode\":\"code1\",\"productName\":\"name1\",\"productCategory\":\"category1\",\"productBrand\":\"brand1\",\"productType\":\"type1\",\"productDesc\":\"desc1\"},{\"id\":0,\"productCode\":\"code2\",\"productName\":\"name2\",\"productCategory\":\"category2\",\"productBrand\":\"brand2\",\"productType\":\"type2\",\"productDesc\":\"desc2\"}]");
    }
	
	
	 public static String writeToJSON(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	@Test
	public void testCreateProduct() throws Exception {
	    Product newProduct = new Product("newCode", "newName", "newCategory", "newBrand", "newType", "newDesc");
	    when(productRepository.save(newProduct)).thenReturn(newProduct);
	    MockHttpServletResponse response = mockMVC.perform(MockMvcRequestBuilders.post("/api/v1/product")
	            .content(writeToJSON(newProduct))
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andReturn().getResponse();

	    System.out.println("--> "+writeToJSON(newProduct));
	    System.out.println("--> "+response.getContentAsString());
	    
//	    assertThat(response.getContentAsString()).isEqualTo(writeToJSON(newProduct));
	    ObjectMapper objectMapper = new ObjectMapper();
	    Product responseProduct = objectMapper.readValue(response.getContentAsString(), Product.class);
	    System.out.println("==> "+responseProduct.toString());
//	    assertThat(responseProduct.getProductCode()).isEqualTo("newCode");
//	    assertThat(responseProduct.getProductName()).isEqualTo("newName");
//	    assertThat(responseProduct.getProductCategory()).isEqualTo("newCategory");
//	    assertThat(responseProduct.getProductBrand()).isEqualTo("newBrand");
//	    assertThat(responseProduct.getProductType()).isEqualTo("newType");
//	    assertThat(responseProduct.getProductDesc()).isEqualTo("newDesc");
	    
	    
	}


}
