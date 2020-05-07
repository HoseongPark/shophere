package com.shophere.book.domain.book;

import com.shophere.book.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books, Long> {

    @Query("SELECT b FROM Books b WHERE b.users =:user")
    List<Books> findByUser(@Param("user") Users user);

}