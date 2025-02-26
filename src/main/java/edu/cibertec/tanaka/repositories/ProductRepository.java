package edu.cibertec.tanaka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cibertec.tanaka.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
