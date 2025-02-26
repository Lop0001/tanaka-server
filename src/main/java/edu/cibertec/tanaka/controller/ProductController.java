package edu.cibertec.tanaka.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.cibertec.tanaka.entities.Product;
import edu.cibertec.tanaka.repositories.ProductRepository;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

  @Autowired
  private ProductRepository productRepository;
  
  @GetMapping
  public List<Product> findAll(){
	  return productRepository.findAll();
  }
  
  @GetMapping("/{id}")
  public Optional<Product> findById(@PathVariable Long id) {
	  return productRepository.findById(id);
  }
   
  @PostMapping
  public Product save(@RequestBody Product product) {
	  return productRepository.save(product);
  }
}
