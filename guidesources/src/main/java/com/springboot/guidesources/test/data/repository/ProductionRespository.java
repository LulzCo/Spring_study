package com.springboot.guidesources.test.data.repository;

import com.springboot.guidesources.test.data.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionRespository extends JpaRepository<Production, Long> {

}
