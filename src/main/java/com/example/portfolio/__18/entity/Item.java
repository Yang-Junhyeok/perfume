package com.example.portfolio.__18.entity;

import com.example.portfolio.__18.constant.ItemPosition;
import com.example.portfolio.__18.exception.OutOfStockException;
import com.example.portfolio.__18.constant.ItemSellStatus;
import com.example.portfolio.__18.dto.ItemFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "item")
@ToString
@Getter
@Setter
public class Item extends BaseEntity{

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //상품명 nullable = null값의 허용 여부설정. false설정시 DDL생성 시에 not null 제약조건 추가
    @Column(nullable = false, length = 50)
    private String itemNm;
    //가격
    @Column(name = "price",nullable = false)
    private int price;
    //재고수량
    @Column(nullable = false)
    private int stockNumber;

    @OneToMany(mappedBy = "item")
    private List<ItemImg> itemImg;

    private long sale;
    //상품상세설명, Lob = BLOB, CLOB 타입매핑
    //BLOB = 바이너리 데이터를 DB 외부에 저장하기 위한 타입, 이미지, 사운드, 비디오 같은 멀티미디어 데이터를 다룰 때 사용
    //CLOB = 사이즈가 큰 데이터를 외부 파일로 저장하기 위한 데이터 타입, 문자형 대용량 파일을 저장 하는데 사용하는 데이터 타입
    @Lob
    @Column(nullable = false)
    private String itemDetail;
    //상품판매상태 Enumerated = enum타입 매핑ㄴ
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    @Enumerated(EnumType.STRING)
    private ItemPosition itemPosition;

    public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        if(itemFormDto.getStockNumber() < 1){
            this.itemSellStatus = ItemSellStatus.SOLD_OUT;
        }else{
            this.itemSellStatus = ItemSellStatus.SELL;
        }
        this.itemPosition = itemFormDto.getItemPosition();
    }

    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber;
        if (restStock<0){
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량 : " + this.stockNumber +"개입니다.)");
        }
        this.stockNumber = restStock;
        if (this.stockNumber < 1){
            this.itemSellStatus = ItemSellStatus.SOLD_OUT;
        }else {
            this.itemSellStatus = ItemSellStatus.SELL;
        }
    }

    public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }
}
