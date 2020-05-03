package com.shophere.book.web.controller;

import com.shophere.book.config.auth.LoginUser;
import com.shophere.book.config.auth.dto.SessionUser;
import com.shophere.book.service.shops.ShopsService;
import com.shophere.book.api.dto.shops.ShopsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final ShopsService shopsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("shops", shopsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    @GetMapping("/shops/save")
    public String shopsSave() {
        return "posts-save";
    }

    @GetMapping("/shops/update/{id}")
    public String shopsUpdate(@PathVariable Long id, Model model) {
        ShopsResponseDto responseDto = shopsService.findById(id);
        model.addAttribute("shops", responseDto);

        return "posts-update";
    }

    @GetMapping("/docs")
    public String swagger() {
        return "redirect:/swagger-ui.html";
    }
}
