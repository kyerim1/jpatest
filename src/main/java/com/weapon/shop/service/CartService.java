package com.weapon.shop.service;

import com.weapon.shop.dto.CartDetailDto;
import com.weapon.shop.dto.CartItemDto;
import com.weapon.shop.dto.CartOrderDto;
import com.weapon.shop.repository.ItemRepository;
import com.weapon.shop.repository.MemberRepository;
import com.weapon.shop.repository.CartRepository;
import com.weapon.shop.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;

    public Long addCart(CartItemDto cartItemDto, String email){
        //장바구니 버튼 클릭시
    }

    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String email){
        //장바구니 메뉴 클릭시
    }

    public void updateCartItemCount(Long cartItemId, int count){
        //장바구니 상품 수량 변경
    }

    public void deleteCartItem(Long cartItemId) {
        //장바구니 상품 삭제
    }
    public Long orderCartItem(List<CartOrderDto> cartOrderDtoList, String email){
        //장바구니 상품 구매하기
    }
}
