package com.shophere.book.domain.shops;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopsRepository extends JpaRepository<Shops, Long> {

    @Query("SELECT s FROM Shops s ORDER BY s.id")
    List<Shops> findAllDesc();
}
