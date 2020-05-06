package com.shophere.book.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books, Long> {

    @Query("SELECT b FROM Books b JOIN FETCH b.user u")
    List<Books> findAllUserId(Long id);

}
