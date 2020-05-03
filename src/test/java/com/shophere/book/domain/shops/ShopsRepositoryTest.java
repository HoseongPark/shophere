package com.shophere.book.domain.shops;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopsRepositoryTest {

    @Autowired
    ShopsRepository shopsRepository;

    @After
    public void cleanup() {
        shopsRepository.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        shopsRepository.save(Shops.builder()
                .title(title)
                .content(content)
                .author("hoseong@gmail.com")
                .build());

        List<Shops> shopsList = shopsRepository.findAll();

        Shops shops = shopsList.get(0);
        assertThat(shops.getTitle()).isEqualTo(title);
        assertThat(shops.getContent()).isEqualTo(content);
    }

    @Test
    public void baseTimeEntityAddTest() throws Exception {
        //given
        LocalDateTime now = LocalDateTime.now();
        shopsRepository.save(Shops.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Shops> shopsList = shopsRepository.findAll();
        Shops shops = shopsList.get(0);

        //then
        System.out.println(">>>>>>> CreateDate= " + shops.getCreateDate() + ", ModiFiedDate= " + shops.getModifiedDate());

        assertThat(shops.getCreateDate()).isAfter(now);
        assertThat(shops.getModifiedDate()).isAfter(now);
    }

}