package com.weapon.shop.service;

import com.weapon.shop.dto.OrderDto;
import com.weapon.shop.entity.Item;
import com.weapon.shop.entity.Member;
import com.weapon.shop.entity.Order;
import com.weapon.shop.entity.OrderItem;
import com.weapon.shop.repository.ItemImgRepository;
import com.weapon.shop.repository.ItemRepository;
import com.weapon.shop.repository.MemberRepository;
import com.weapon.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemImgRepository itemImgRepository;

    //구매하기 버튼 클릭시 구매 이력으로 저장
    public Long order(OrderDto orderDto, String email){
        Item item = itemRepository.findById(orderDto.getItemId()).get();//구매 상품객체

        Member member = memberRepository.findByEmail(email); // 구매자

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderIem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderIem);
        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }


    // 구매이력 메뉴 클릭시 로그인한 회원의 구매이력 가져오기


    //  구매 취소


}







