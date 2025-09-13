package com.zee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zee.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

}
