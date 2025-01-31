package kr.ko.DBshop.controller;

import kr.ko.DBshop.service.CategoriesService;
import kr.ko.DBshop.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
//모든 Controller에 해당 코드 적용
public class GlovalControllerAdvice {

    @Autowired
    UsersService usersService;

    @Autowired
    CategoriesService categoriesService;

    @ModelAttribute
    public void addLoginInfo(Model model){

        //모든 URL에 Modelattribute하는 코드
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("userInfo", usersService.getUserByEmail(email));

        //모든카테고리 불러오기
        model.addAttribute("categories",categoriesService.getAllCategories());
    }

}
