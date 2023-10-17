package net.crud_be.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.crud_be.springboot.exception.ResourceNotFoundException;
import net.crud_be.springboot.model.Product;
import net.crud_be.springboot.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	//get all employee
	@GetMapping("/product")
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
		

	@GetMapping("/product/{productCode}")
	public Product getProductByProductCode(@PathVariable String productCode) {
	    Product product = productRepository.findByProductCode(productCode);
	    if (product == null) {
	        throw new ResourceNotFoundException("Product not found for productCode: " + productCode);
	    }
	    return product;
	}
	
	
	@PostMapping("/product")
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@PutMapping("/product/{productCode}")
	public Product updateProductByProductCode(@RequestBody Product newProduct, @PathVariable String productCode) {

		Product currentProduct = productRepository.findByProductCode(productCode);
	    if (currentProduct == null) {
	        throw new ResourceNotFoundException("Product with productCode " + productCode + " not found");
	    }
	    
	    currentProduct.setProductCode(newProduct.getProductCode());
	    currentProduct.setProductName(newProduct.getProductName());
	    currentProduct.setProductCategory(newProduct.getProductCategory());
	    currentProduct.setProductBrand(newProduct.getProductBrand());
	    currentProduct.setProductType(newProduct.getProductType());
	    currentProduct.setProductDesc(newProduct.getProductDesc());

	    return productRepository.save(currentProduct);
	}
	
	@DeleteMapping("/product/{productCode}")
	public String deleteProductByProductCode(@PathVariable String productCode) {
	    Product currentProduct = productRepository.findByProductCode(productCode);
	    if (currentProduct == null) {
	        throw new ResourceNotFoundException("Product with productCode " + productCode + " not found");
	    }
	    
	    productRepository.delete(currentProduct);
	    return "Product with productCode " + productCode + " has been removed.";
	}

	
}
