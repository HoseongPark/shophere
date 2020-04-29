package com.shophere.book.domain.book;

import com.shophere.book.domain.posts.Posts;
import com.shophere.book.domain.user.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Users user;

    @Column
    private LocalDate bookData;

    @Column
    private LocalTime bookTime;

    @Column
    private int bookPeople;

    @Column
    private List<Posts> posts;
}
