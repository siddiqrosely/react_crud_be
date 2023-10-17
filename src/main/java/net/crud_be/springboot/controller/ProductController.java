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
		
//	@GetMapping("/product/{id}")
//	public Product getProductById(@PathVariable Long id){	
//		return productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("not found"));		
//	}

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
	
//	@PutMapping("/product/{id}")
//	public Product updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
//		return productRepository.findById(id)
//				.map(product ->{
//					product.setProductCode(newProduct.getProductCode());					
//					product.setProductName(newProduct.getProductName());					
//					product.setProductCategory(newProduct.getProductCategory());					
//					product.setProductBrand(newProduct.getProductBrand());
//					product.setProductType(newProduct.getProductType());
//					product.setProductDesc(newProduct.getProductDesc());
//					return productRepository.save(product);
//				}).orElseThrow(()-> new ResourceNotFoundException("Id not found"));
//	}
	
	@PutMapping("/product/{productCode}")
	public Product updateProductByProductCode(@RequestBody Product newProduct, @PathVariable String productCode) {

		Product existingProduct = productRepository.findByProductCode(productCode);
	    if (existingProduct == null) {
	        throw new ResourceNotFoundException("Product with productCode " + productCode + " not found");
	    }
	    
	    existingProduct.setProductCode(newProduct.getProductCode());
	    existingProduct.setProductName(newProduct.getProductName());
	    existingProduct.setProductCategory(newProduct.getProductCategory());
	    existingProduct.setProductBrand(newProduct.getProductBrand());
	    existingProduct.setProductType(newProduct.getProductType());
	    existingProduct.setProductDesc(newProduct.getProductDesc());

	    return productRepository.save(existingProduct);
	}

	
//	@DeleteMapping("/product/{id}")
//	public String deleteEmployee(@PathVariable Long id) {
//		if(!productRepository.existsById(id)) {
//			throw new ResourceNotFoundException("Id not found");
//		}		
//		productRepository.deleteById(id);
//		return "Product with id "+id+" has been removed.";		
//	}	
	
	
	@DeleteMapping("/product/{productCode}")
	public String deleteProductByProductCode(@PathVariable String productCode) {
	    Product existingProduct = productRepository.findByProductCode(productCode);
	    if (existingProduct == null) {
	        throw new ResourceNotFoundException("Product with productCode " + productCode + " not found");
	    }
	    
	    productRepository.delete(existingProduct);
	    return "Product with productCode " + productCode + " has been removed.";
	}

	
}
