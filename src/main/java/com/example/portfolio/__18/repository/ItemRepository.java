package com.example.portfolio.__18.repository;

import com.example.portfolio.__18.dto.ItemFormDto;
import com.example.portfolio.__18.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>,ItemRepositoryCustom {
    //상품명을 조건으로 조회하는 쿼리 메서드
    List<Item> findByItemNm(String itemNm);

    //상품을 상품명과 상품 상세 설명을 OR조건을 이용하여 조회하는 쿼리 메소드
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    //파라미터로 넘어온 price 변수보다 값이 작은 상품 데이터를 가격이 높은 순으로 조회하는 메서드
    List<Item> findByPriceLessThanOrderByPriceDesc(int price);

    List<Item> findByRegTimeOrderByRegTime(LocalDateTime regTime);

    @Query(value = "select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);


}
