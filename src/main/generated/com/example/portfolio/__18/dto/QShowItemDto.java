package com.example.portfolio.__18.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.example.portfolio.__18.dto.QShowItemDto is a Querydsl Projection type for ShowItemDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QShowItemDto extends ConstructorExpression<ShowItemDto> {

    private static final long serialVersionUID = -352525456L;

    public QShowItemDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> itemNm, com.querydsl.core.types.Expression<String> itemDetail, com.querydsl.core.types.Expression<String> imgUrl, com.querydsl.core.types.Expression<Integer> price, com.querydsl.core.types.Expression<Long> sale, com.querydsl.core.types.Expression<com.example.portfolio.__18.constant.ItemPosition> itemPosition, com.querydsl.core.types.Expression<com.example.portfolio.__18.constant.ItemSellStatus> itemSellStatus) {
        super(ShowItemDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, int.class, long.class, com.example.portfolio.__18.constant.ItemPosition.class, com.example.portfolio.__18.constant.ItemSellStatus.class}, id, itemNm, itemDetail, imgUrl, price, sale, itemPosition, itemSellStatus);
    }

}

