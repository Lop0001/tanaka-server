package edu.cibertec.tanaka.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.cibertec.tanaka.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByNameLike(String name);
	List<Product> findByDescriptionLike(String text);
	List<Product> findByState(boolean state);
	
	@Query(value="select * from tanaka.tbl_products where state=1;", nativeQuery=true)
	List<Product> findAllCustomSql();
	
	@Query(value="select p from Product p where p.state=true")
	List<Product> findAllCustomJpql();
}
