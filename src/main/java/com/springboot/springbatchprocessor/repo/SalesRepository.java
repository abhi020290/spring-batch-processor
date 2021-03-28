package com.springboot.springbatchprocessor.repo;

import com.springboot.springbatchprocessor.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales,Long> {
}
