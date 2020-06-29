package com.shophere.book.domain.shops;

import com.shophere.book.api.dto.shops.ShopSearchCondition;
import com.shophere.book.api.dto.shops.ShopsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShopsRepositoryCustom {

    Page<ShopsResponseDto> search(ShopSearchCondition condition, Pageable pageable);
}
