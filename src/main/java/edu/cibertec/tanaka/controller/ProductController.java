package edu.cibertec.tanaka.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.cibertec.tanaka.entities.Product;
import edu.cibertec.tanaka.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

  
  @Autowired
  private ProductService productService;
  
  @GetMapping
  public List<Product> findAll(){
	  //return productRepository.findAll();
	  //return productRepository.findByState(true);
	  //return productRepository.findAllCustomSql();
	  //return productRepository.findAllCustomJPQL();
	  return productService.findAll();
  }
  
  @GetMapping("/{id}")
  public Optional<Product> findById(@PathVariable Long id) {
	  return productService.findById(id);
  }
  
  @GetMapping("/findById")
  public Optional<Product> findByIdExtra(@RequestParam Long id){
	  //return productRepository.findById(id);
	  return productService.findById(id);
  }
  
  @GetMapping("/findByName")
  public List<Product> findByName(@RequestParam(defaultValue="empty") String name) {
	  return productService.findByNameLike("%" + name + "%");
  }
  
  @GetMapping("/findByDescription")
  public List<Product> findByDescription(@RequestParam String text){
	  return productService.findByDescriptionLike("%" + text + "%");
  }
  
  @PostMapping
  public Product save(@RequestBody Product product) {
	  return productService.save(product, true);
  }
  
  @PutMapping("/{id}")
  public Product update(@PathVariable Long id, @RequestBody Product product) {
	 product.setId(id);
	 //return productRepository.save(product);
	 return productService.save(product, true);
  }
  
  @PatchMapping("/{id}")
  public Product updateStock(@PathVariable Long id, @RequestBody Product product) {
	  //Optional<Product> optProduct = productRepository.findById(id);
	  Optional<Product> optProduct = productService.findById(id);
	  if(optProduct.isPresent()) {
		  Product oProduct = optProduct.get();
		  oProduct.setState(product.getState());
		  //return productRepository.save(oProduct);
		  return productService.save(product, true);
	  }
	  else {
		  throw new RuntimeException("Product not found");
	  }
  }
  
  //@DeleteMapping("/{id}")
  //public void hardDelete(@PathVariable Long id) {
	//  productRepository.deleteById(id);
    //  productService.deleteById(id);
  //}
  
  @DeleteMapping("/{id}")
  public void softDelete(Long id) {
//	  Optional<Product> optProduct = productRepository.findById(id);
	  Optional<Product> optProduct = productService.findById(id);

	  if(optProduct.isPresent()) {
		  Product oProduct = optProduct.get();
		  oProduct.setState(false);
//		  productRepository.save(oProduct);
		  productService.save(oProduct, false);

	  }else {
		  throw new RuntimeException("Product not found");
	  }
  }
}
