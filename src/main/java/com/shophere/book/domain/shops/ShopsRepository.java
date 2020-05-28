package com.shophere.book.domain.shops;

import com.shophere.book.api.dto.shops.ShopSearchCondition;
import com.shophere.book.api.dto.shops.ShopsResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopsRepository extends JpaRepository<Shops, Long>, ShopsRepositoryCustom{

    @Query("SELECT s FROM Shops s ORDER BY s.id")
    List<Shops> findAllDesc();

    @Override
    List<ShopsResponseDto> search(ShopSearchCondition condition);
}
