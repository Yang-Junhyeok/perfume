package com.example.portfolio.__18.repository;

import com.example.portfolio.__18.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart , Long> {
    Cart findByMemberId(Long memberId);
}
