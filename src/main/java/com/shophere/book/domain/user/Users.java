package com.shophere.book.domain.user;

import com.shophere.book.api.dto.users.UserUpdateDto;


import com.shophere.book.domain.BaseTimeEntity;
import com.shophere.book.domain.book.Books;
import com.shophere.book.domain.shops.Shops;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.shophere.book.domain.user.Role.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Users extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private String password;

    private String picture;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "users")
    @Builder.Default
    private List<Books> books = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Shops> shops = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> collectonSimple = new ArrayList<>();
        String key = this.role.getKey();
        SimpleGrantedAuthority simple = new SimpleGrantedAuthority(key);

        collectonSimple.add(simple);
        return collectonSimple;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void update(UserUpdateDto updateDto) {
        this.password = updateDto.getPassword();
    }

    public void updateOwner() {
        this.role = OWNER;
    }

//    public String getRoleKey() {
//        return this.role.getKey();
//    }

}
