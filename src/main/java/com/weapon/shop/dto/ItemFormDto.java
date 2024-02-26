package com.weapon.shop.dto;

import com.weapon.shop.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Getter  @Setter
public class ItemFormDto {
    private Long id;
    private String itemNm;
    private Integer price;
    private String itemDetail;
    private String sellStatCd;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;

    private static ModelMapper mapper = new ModelMapper();

    public Item createItem(){  //  ItemFormDto객체의 데이터를  ->  Item 객체에 저장
        return mapper.map(this,Item.class);
    }

    public static ItemFormDto of(Item item){  // Item객체의 데이터를 -> ItemFormDto객체에 저장
        return mapper.map(item, ItemFormDto.class);
    }
}
