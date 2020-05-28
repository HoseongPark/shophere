package com.shophere.book.domain.shops;

import com.shophere.book.api.dto.shops.ShopSearchCondition;
import com.shophere.book.api.dto.shops.ShopsResponseDto;

import java.util.List;

public interface ShopsRepositoryCustom {

    List<ShopsResponseDto> search(ShopSearchCondition condition);
}
