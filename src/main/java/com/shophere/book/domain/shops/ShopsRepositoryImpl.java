package com.shophere.book.domain.shops;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shophere.book.api.dto.shops.ShopSearchCondition;
import com.shophere.book.api.dto.shops.ShopsResponseDto;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.shophere.book.domain.shops.QShops.*;

@RequiredArgsConstructor
public class ShopsRepositoryImpl implements ShopsRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public ShopsRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ShopsResponseDto> search(ShopSearchCondition condition) {
        queryFactory
                .select(new ShopsResponseDto(
                        shops.id,
                        shops.title,
                        shops.overView,
                        shops.content,
                        shops.author
                ))

    }
}
