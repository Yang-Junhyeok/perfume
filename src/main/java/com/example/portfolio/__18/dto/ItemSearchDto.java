package com.example.portfolio.__18.dto;

import com.example.portfolio.__18.constant.ItemPosition;
import com.example.portfolio.__18.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {
    private String searchDateType;

    private ItemSellStatus searchSellStatus;

    private ItemPosition itemPosition;

    private String searchBy;

    private String searchQuery = "";
}
