package kr.ko.DBshop.controller;


import jakarta.validation.Valid;
import kr.ko.DBshop.dto.CategoriesDto;
import kr.ko.DBshop.dto.PageDto;
import kr.ko.DBshop.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicBoolean;

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
    //@Valid 는, categoriesDto내의 유효성검사 어노테이션을 통한 결과값을 bindingResult에 삽입.
    public String createCategory(@Valid @ModelAttribute CategoriesDto categoriesDto, BindingResult bindingResult,Model model){

        //에러의 필드와 메시지를 담을 Map생성, | Map사용하지 않고 에러메시지 유무만 사용함 |
        //Map<String, List<String>> errorMap = new HashMap<>();

        //에러가 있으면,
        if(bindingResult.hasErrors()){

            //bindingResilt의 FieldErrors() 는, 해당필드와 메시지를 갖고있는 배열객체이다.
            bindingResult.getFieldErrors().forEach(error->{
                //있다면 여기서는 categoryName일 것,
                String field = error.getField();
                //위 필드의 에러메시지.(필드에 적혀있음)
                String message = error.getDefaultMessage();

                //computeIfAbsent는 같은 필드명이 있을 때, 해당 필드명의 Map의 객체배열값에 객체추가.
                //errorMap.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
            });
            model.addAttribute("errorExist",true);
            return "/categories/form";

        }else {
            categoriesService.createCategories(categoriesDto);
            return "redirect:/categories/list";
        }

    }

    @GetMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") int categoryId){
        categoriesService.deleteCategoryById(categoryId);
        return "redirect:/categories/list";
    }

    @GetMapping("/detail/{categoryId}")
    public String getCategoriesLists(@PathVariable("categoryId") int categoryId,
                                     Model model){

        //ProductMapper나 ProductService를 연결해서 categoryId를 포린키로 갖고 있는 products들을 배열로 받아서 model.addAttribute한 후 "/products/list"로 리턴
        //model.addAttribute("productDto",productDto);

        return "/products/list";
    }

}
