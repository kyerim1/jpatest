package com.weapon.shop.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
@Entity
@Getter @Setter
@ToString
public class Cart  extends BaseEntity{

    private Long id;
    private Member member;
    public static Cart createCart(Member member){
        Cart cart = new Cart();
        cart.setMember(member);
        return cart;
    }
}
