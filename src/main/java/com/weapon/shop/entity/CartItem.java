package com.weapon.shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class CartItem extends BaseEntity{
    private Long id;
    private Cart cart;
    private Item item;
    private int count;

    public static CartItem createCartItem(Cart cart, Item item, int count) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);
        return cartItem;
    }

    public void addCount(int count){ //수량 증가
        this.count += count;
    }

    public void updateCount(int count){  //증가된수량 업데이트
        this.count = count;
    }
}
