package com.weapon.shop.service;

import com.weapon.shop.dto.CartDetailDto;
import com.weapon.shop.dto.CartItemDto;
import com.weapon.shop.dto.CartOrderDto;
import com.weapon.shop.entity.Cart;
import com.weapon.shop.entity.CartItem;
import com.weapon.shop.entity.Item;
import com.weapon.shop.entity.Member;
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
        Item item = itemRepository.findById(cartItemDto.getItemId()).get();
        Member member = memberRepository.findByEmail(email);

        //회원마다 하나씩 카트를 가져야 한다.  카트를 가지고있는지 없는지 확인하여 생성여부를 할 필요가있다.
        Cart cart = cartRepository.findByMemberId( member.getId() );
        if(cart == null){
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        CartItem cartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());
        //전에 동일한 상품을 장바구니에 담았다면 수량 증가를 하면 되고  장바구니에없는경우는 새롭게 저장

        if(cartItem != null){ // 담겨있는 상품이라면
            cartItem.addCount(cartItemDto.getCount());
            return cartItem.getId();
        }else{ // 새로운 상품 장바구니에 담는경우
            CartItem newCartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(newCartItem);
            return newCartItem.getId();
        }

    }
//
//    @Transactional(readOnly = true)
//    public List<CartDetailDto> getCartList(String email){
//        //장바구니 메뉴 클릭시
//    }
//
//    public void updateCartItemCount(Long cartItemId, int count){
//        //장바구니 상품 수량 변경
//    }
//
//    public void deleteCartItem(Long cartItemId) {
//        //장바구니 상품 삭제
//    }
//    public Long orderCartItem(List<CartOrderDto> cartOrderDtoList, String email){
//        //장바구니 상품 구매하기
//    }
}
