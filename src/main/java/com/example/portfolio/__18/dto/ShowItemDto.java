package com.example.portfolio.__18.dto;


import com.example.portfolio.__18.constant.ItemPosition;
import com.example.portfolio.__18.constant.ItemSellStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowItemDto {
    
    private Long id;
    
    private String itemNm;
    
    private String itemDetail;
    
    private String imgUrl;
    
    private Integer price;

    private Long sale;

    private ItemPosition itemPosition;

    private ItemSellStatus itemSellStatus;
    //@QueryProjection 을 선언하여 Querydsl로 결과 조회 시 ShowDto객체로 바로 받아 오도록 설정
    @QueryProjection
    public ShowItemDto(Long id, String itemNm, String itemDetail, String imgUrl, Integer price, Long sale, ItemPosition itemPosition, ItemSellStatus itemSellStatus){
        this.id = id;
        this.itemNm = itemNm;
        this.itemDetail = itemDetail;
        this.imgUrl = imgUrl;
        this.price = price;
        this.sale = sale;
        this.itemPosition = itemPosition;
        this.itemSellStatus = itemSellStatus;
    }
}
