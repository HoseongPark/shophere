package com.shophere.book.service.shops;

import com.shophere.book.api.dto.shops.*;
import com.shophere.book.domain.shops.Shops;
import com.shophere.book.domain.shops.ShopsRepository;
import com.shophere.book.domain.user.UserRepository;
import com.shophere.book.domain.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ShopsService {
    private final ShopsRepository shopsRepository;
    private final UserRepository userRepository;

    @Transactional
    public String save(ShopsSaveRequestDto requestDto) {
        Users user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));
        Shops shop = Shops.createShop(requestDto, user);
        shopsRepository.save(shop);
        return "Success Create Shop";
    }

    @Transactional
    public Long update(Long id, ShopsUpdateRequestDto requestDto) {
        Shops shops = shopsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 가맹점이 없습니다."));
        shops.update(requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getOverView(),
                requestDto.getPrice(),
                requestDto.getCategory());

        return shops.getId();
    }

    public ShopsResponseDto findById(Long id) {
        Shops shops = shopsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 가맹점이 없습니다. id=" + id));
        return new ShopsResponseDto(shops);
    }

    public List<ShopsListResponseDto> findAllDesc() {
        return shopsRepository.findAllDesc().stream()
                .map(ShopsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Shops shops = shopsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 가맹점이 없습니다."));
        shopsRepository.delete(shops);
    }

    public Page<ShopsResponseDto> findByCondition(ShopSearchCondition shopSearchCondition, Pageable pageable) {
        return shopsRepository.search(shopSearchCondition, pageable);
    }
}
