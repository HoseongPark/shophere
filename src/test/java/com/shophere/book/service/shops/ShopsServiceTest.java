package com.shophere.book.service.shops;

import com.shophere.book.api.dto.shops.ShopsListResponseDto;
import com.shophere.book.api.dto.shops.ShopsResponseDto;
import com.shophere.book.api.dto.shops.ShopsSaveRequestDto;
import com.shophere.book.api.dto.shops.ShopsUpdateRequestDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopsServiceTest {

    @Autowired
    ShopsService shopsService;

    @Before
    public void init() {
        ShopsSaveRequestDto requestDto = ShopsSaveRequestDto.builder()
                .author("hoseong")
                .content("test")
                .title("test")
                .overView("test")
                .build();

        ShopsSaveRequestDto requestDto2 = ShopsSaveRequestDto.builder()
                .author("hoseong2")
                .content("test2")
                .title("test2")
                .overView("test2")
                .build();

        shopsService.save(requestDto);
        shopsService.save(requestDto2);
    }

    @Test
    public void save() throws Exception{
        //Given
        ShopsSaveRequestDto requestDto = ShopsSaveRequestDto.builder()
                .author("hoseong")
                .content("test")
                .title("test")
                .overView("test")
                .build();

        //When
        Long result = shopsService.save(requestDto);

        //Then
        assertThat(result).isEqualTo(1L);
    }

    @Test
    public void update() throws Exception {
        //Given
        
        // 가맹점 생성
        ShopsSaveRequestDto requestDto = ShopsSaveRequestDto.builder()
                .author("hoseong")
                .content("test")
                .title("test")
                .overView("test")
                .build();

        shopsService.save(requestDto);

        ShopsUpdateRequestDto updateRequestDto = ShopsUpdateRequestDto.builder()
                .content("hoseong content")
                .overView("hoseong overView")
                .title("hoseong title")
                .build();

        //When
        shopsService.update(1L, updateRequestDto);

        //Then
        ShopsResponseDto findShop = shopsService.findById(1L);

        assertThat(findShop.getContent()).isEqualTo("hoseong content");
        assertThat(findShop.getTitle()).isEqualTo("hoseong title");
        assertThat(findShop.getOverView()).isEqualTo("hoseong overView");
    }

    @Test
    public void findById() throws Exception {
        //Given


        //When
        ShopsResponseDto findShop = shopsService.findById(1L);

        //Then
        assertThat(findShop.getTitle()).isEqualTo("test");
        assertThat(findShop.getOverView()).isEqualTo("test");
        assertThat(findShop.getAuthor()).isEqualTo("hoseong");
        assertThat(findShop.getContent()).isEqualTo("test");
    }

    @Test
    public void findAllShop() throws Exception {
        //Given

        //When
        List<ShopsListResponseDto> findShops = shopsService.findAllDesc();

        //Then
        assertThat(findShops.size()).isEqualTo(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void delete() throws Exception {
        //Given

        //When
        shopsService.delete(1L);

        //Then
        shopsService.findById(1L);
    }

}