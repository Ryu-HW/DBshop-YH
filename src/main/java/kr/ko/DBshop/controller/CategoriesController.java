package kr.ko.DBshop.controller;


import kr.ko.DBshop.dto.CategoriesDto;
import kr.ko.DBshop.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @GetMapping("/create")
    public String getCreatePage(){
        return "/categories/form";
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute CategoriesDto categoriesDto){
        categoriesService.createCategories(categoriesDto);
        return "/categories/list";
    }

}
