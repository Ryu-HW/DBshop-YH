package kr.ko.DBshop.controller;

import kr.ko.DBshop.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    @Autowired
    ProductsService productsService;

    @GetMapping("products/{categoryId}")
    public String getProducts(@PathVariable("categoryId") int categoryId ,Model model){



        model.addAttribute("productList",productsService.getAllProductsByCategoryId(categoryId));

        return "/products/product";
    }
}
