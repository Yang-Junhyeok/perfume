package com.example.portfolio.__18.repository;

import com.example.portfolio.__18.dto.CartDetailDto;
import com.example.portfolio.__18.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    @Query("select new com.example.portfolio.__18.dto.CartDetailDto(ci.id, i.itemNm, i.price, ci.count, im.imgUrl) " +
            "from CartItem ci " +
            "join ci.item i " + // CartItem과 Item을 조인
            "join i.itemImg im " + // Item과 연결된 ItemImg를 조인
            "where ci.cart.id = :cartId " + // 주어진 cartId로 장바구니 항목을 필터링
            "and im.repimgYn = 'Y' " + // 대표 이미지만 필터링
            "order by ci.regTime desc") // 장바구니 항목의 등록 시간을 내림차순으로 정렬

    List<CartDetailDto> findCartDetailDtoList(Long cartId);
}
