package com.example.portfolio.__18.repository;

import com.example.portfolio.__18.dto.ItemSearchDto;
import com.example.portfolio.__18.dto.ShowItemDto;
import com.example.portfolio.__18.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    //상품 조회 조건을 담고있는 itemSearchDto객체와 페이징 정보를 담고 있는 pageable객체를 파라미터로 받는 getAdminItemPage메소드를 정의. 반한데이터로 Page<Item> 객체를 반환
    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<ShowItemDto> getShowItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
