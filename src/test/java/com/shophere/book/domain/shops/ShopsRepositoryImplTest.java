package com.shophere.book.domain.shops;

import com.shophere.book.api.dto.shops.ShopSearchCondition;
import com.shophere.book.api.dto.shops.ShopsResponseDto;
import com.shophere.book.api.dto.shops.ShopsSaveRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopsRepositoryImplTest {

    @Autowired
    ShopsRepository shopsRepository;

    @Before
    public void setup() {
        ShopsSaveRequestDto requestDto = new ShopsSaveRequestDto().builder()
                .title("hoseong")
                .author("hoseong")
                .category("cafe")
                .content("test content")
                .overView("test overView")
                .price(1000)
                .build();

        shopsRepository.save(requestDto.toEntity());

        ShopsSaveRequestDto requestDto2 = new ShopsSaveRequestDto().builder()
                .title("hugh")
                .author("hoseong")
                .category("zoo")
                .content("test content2")
                .overView("test overView2")
                .price(6000)
                .build();

        shopsRepository.save(requestDto2.toEntity());
    }

    @After
    public void tearDown() {
        shopsRepository.deleteAll();
    }

    @Test
    public void searchByCondition() throws Exception {
        //Given
        ShopSearchCondition condition = new ShopSearchCondition("hoseong", "cafe", null, null);

        //When
        List<ShopsResponseDto> search = shopsRepository.search(condition);

        //Then
        assertThat(search.get(0).getTitle()).isEqualTo("hoseong");
        assertThat(search.get(0).getCategory()).isEqualTo("cafe");
        assertThat(search.size()).isEqualTo(1);
    }
}