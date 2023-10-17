package net.crud_be.springboot.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.crud_be.springboot.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findByProductCode(String productCode);		
	
}
