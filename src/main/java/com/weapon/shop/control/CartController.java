package com.weapon.shop.control;

import com.weapon.shop.dto.CartItemDto;
import com.weapon.shop.dto.CartOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CartController {


    @PostMapping("/cart")
    public @ResponseBody ResponseEntity cartPut(@RequestBody @Valid CartItemDto cartItemDto,
                                                BindingResult bindingResult, Principal principal) {
//장바구니 버튼클릭시
        return new ResponseEntity<Long>(1L, HttpStatus.OK);
    }

    @GetMapping(value = "/cart")
    public String orderHist(Principal principal, Model model) {
        //장바구니 메뉴 클릭시

    }

    @PatchMapping(value = "/cartItem/{cartItemId}")
    public @ResponseBody ResponseEntity updateCartItem(@PathVariable("cartItemId") Long cartItemId, int count, Principal principal) {
        // 상품 수량 변경
    }
    @DeleteMapping(value = "/cartItem/{cartItemId}")
    public @ResponseBody ResponseEntity deleteCartItem(@PathVariable("cartItemId") Long cartItemId, Principal principal) {
         //장바구니 상품 삭제
    }

    @PostMapping(value = "/cart/orders")
    public @ResponseBody ResponseEntity orderCartItem(@RequestBody CartOrderDto cartOrderDto, Principal principal){
        // 장바구니 에서 상품 주문
    }
}