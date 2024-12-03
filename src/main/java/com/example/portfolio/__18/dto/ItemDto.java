package com.example.portfolio.__18.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemDto {

    private Long id;

    private String itemNm;

    private Integer price;

    private Integer sale;

    private String itemDetail;

    private String sellStatCd;

    private String itemPosition;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}
