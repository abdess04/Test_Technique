package com.test.test_technique_api.Repository;

import com.test.test_technique_api.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {

}
