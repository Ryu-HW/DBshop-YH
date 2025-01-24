package kr.ko.DBshop.controller;


import kr.ko.DBshop.dto.CategoriesDto;
import kr.ko.DBshop.dto.PageDto;
import kr.ko.DBshop.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @GetMapping("/create")
    public String getCreatePage(){
        return "/categories/form";
    }

    @GetMapping("/list")
    public String getCategoriesLists(@RequestParam(name ="page", defaultValue = "1")int page,
                                     @RequestParam(name ="size", defaultValue = "5")int size,
                                     Model model){

        PageDto<CategoriesDto> pageDto = categoriesService.getCategoriesForList(page,size);
        model.addAttribute("pageDto",pageDto);
        return "/categories/list";
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute CategoriesDto categoriesDto){
        categoriesService.createCategories(categoriesDto);
        return "redirect:/categories/create";
    }





}
