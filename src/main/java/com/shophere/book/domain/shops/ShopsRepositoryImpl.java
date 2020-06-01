package com.shophere.book.domain.shops;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shophere.book.api.dto.shops.ShopSearchCondition;
import com.shophere.book.api.dto.shops.ShopsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.shophere.book.domain.shops.QShops.shops;

public class ShopsRepositoryImpl implements ShopsRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public ShopsRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ShopsResponseDto> search(ShopSearchCondition condition, Pageable pageable) {
        QueryResults<ShopsResponseDto> result = queryFactory
                .select(Projections.fields(ShopsResponseDto.class,
                        shops.id,
                        shops.title,
                        shops.overView,
                        shops.content,
                        shops.author,
                        shops.price,
                        shops.category
                ))
                .from(shops)
                .where(titleEq(condition.getTitle()),
                        categoryEq(condition.getCategory()),
                        priceGoe(condition.getPriceGoe()),
                        priceLoe(condition.getPriceLoe()))
                .fetchResults();

        List<ShopsResponseDto> data = result.getResults();
        long total = result.getTotal();

        return new PageImpl<>(data, pageable, total);
    }

    private BooleanExpression priceLoe(Integer priceLoe) {
        if (priceLoe != null) {
            return shops.price.loe(priceLoe);
        }
        return null;
    }

    private BooleanExpression categoryEq(String category) {
        if (category != null) {
            return shops.category.eq(category);
        }
        return null;
    }

    private BooleanExpression priceGoe(Integer priceGoe) {
        if (priceGoe != null) {
            return shops.price.goe(priceGoe);
        }
        return null;
    }

    private BooleanExpression titleEq(String title) {
        if (title != null) {
            return shops.title.eq(title);
        }
        return null;
    }
}
