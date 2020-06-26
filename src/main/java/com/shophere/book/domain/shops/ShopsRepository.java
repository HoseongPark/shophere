package com.shophere.book.domain.shops;

import com.shophere.book.api.dto.shops.ShopSearchCondition;
import com.shophere.book.api.dto.shops.ShopsResponseDto;
import com.shophere.book.domain.book.Books;
import com.shophere.book.domain.user.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopsRepository extends JpaRepository<Shops, Long>, ShopsRepositoryCustom{

    @Query("SELECT s FROM Shops s ORDER BY s.id")
    List<Shops> findAllDesc();

    @Query("SELECT s FROM Shops s WHERE s.users =:user")
    List<Shops> findByUser(@Param("user") Users user);

    @Override
    Page<ShopsResponseDto> search(ShopSearchCondition condition, Pageable pageable);
}
