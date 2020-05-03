package com.shophere.book.service.shops;

import com.shophere.book.domain.shops.Shops;
import com.shophere.book.domain.shops.ShopsRepository;
import com.shophere.book.api.dto.shops.ShopsListResponseDto;
import com.shophere.book.api.dto.shops.ShopsResponseDto;
import com.shophere.book.api.dto.shops.ShopsSaveRequestDto;
import com.shophere.book.api.dto.shops.ShopsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ShopsService {
    private final ShopsRepository shopsRepository;

    @Transactional
    public Long save(ShopsSaveRequestDto requestDto) {
        return shopsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ShopsUpdateRequestDto requestDto) {
        Shops shops = shopsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
        shops.update(requestDto.getTitle(), requestDto.getOverView(), requestDto.getContent());

        return shops.getId();
    }

    public ShopsResponseDto findById(Long id) {
        Shops shops = shopsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new ShopsResponseDto(shops);
    }

    @Transactional(readOnly = true)
    public List<ShopsListResponseDto> findAllDesc() {
        return shopsRepository.findAllDesc().stream()
                .map(ShopsListResponseDto::new)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        Shops shops = shopsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
        shopsRepository.delete(shops);
    }
}
