package com.weapon.shop.service;

import com.weapon.shop.dto.ItemFormDto;
import com.weapon.shop.entity.Item;
import com.weapon.shop.entity.ItemImg;
import com.weapon.shop.repository.ItemImgRepository;
import com.weapon.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;
    private final ItemImgService itemImgService;

    public Long saveItem(ItemFormDto itemFormDto,
                         List<MultipartFile> multipartFileList) throws Exception{
        //상품 정보 데이터베이스에 저장
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        //이미지 업로드및 데이터베이스 저장
        for(int i=0; i<multipartFileList.size(); i++){
            ItemImg itemImg=new ItemImg();
            itemImg.setItem(item); // 이미지에 상품 번호(item_id)가 같이 저장된다.

            if(i==0)  // 대표이미지 설정 - 무조건 첫번째 이미지를 대표이지미로 사용한다.
                itemImg.setRepImgYn("Y");
            else
                itemImg.setRepImgYn("N");
            // 업로드및 데이터베이스저장 하기위해 service클래스의 메서드 호출
            itemImgService.saveItemImg(itemImg, multipartFileList.get(i) );
        }
        return item.getId();
    }
}









