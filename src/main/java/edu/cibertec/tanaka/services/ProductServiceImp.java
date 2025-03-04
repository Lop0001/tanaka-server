package edu.cibertec.tanaka.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cibertec.tanaka.entities.Product;
import edu.cibertec.tanaka.repositories.ProductRepository;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private ProductRepository productRepository; 
	
	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return productRepository.findByState(true);
	}

}
