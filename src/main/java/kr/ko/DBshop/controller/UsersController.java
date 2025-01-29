package kr.ko.DBshop.controller;

import kr.ko.DBshop.dto.UsersDto;
import kr.ko.DBshop.service.CategoriesService;
import kr.ko.DBshop.service.ProductsService;
import kr.ko.DBshop.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    ProductsService productsService;

    @GetMapping("/")
    public String mainPage(Model model){

        //유저 이메일을 가져와서 룰리스트반환
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        //반환값이 널이아니면(로그인상태면)
        if(!email.equals("anonymousUser")){
            model.addAttribute("roles",usersService.getRolesByUserId(usersService.getUserByEmail(email).getUserId()));
        }

        model.addAttribute("products",productsService.getAllproducts());
        return "/users/main";
    }

    @GetMapping("/login")
    public String loginPage(){

        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        if(id != "anonymousUser"){
            return "/users/logined";
        }else {
            return "/users/login";
        }
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "/users/admin";
    }

    @GetMapping("/signup")
    public String signup(){
        return "/users/signup";
    }

    @PostMapping("/update/profile")
    public String updateUser(@ModelAttribute UsersDto usersDto){
        usersService.updateUser(usersDto);
        return "redirect:/";
    }


    @GetMapping("/my/page")
    public String mypage(){

        return "/users/myinfo";
    }

    @GetMapping("/delete/profile")
    public String delprofile(){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        usersService.deleteUserByEmail(email);
        return "redirect:/";
    }

    @PostMapping("/signup")
    public String signupForm(@ModelAttribute UsersDto userDto){
        
        //받은 UserDto정보를 받아 서비스로 토스
        usersService.signUp(userDto);

        return "redirect:/login";
    }

    @GetMapping("/my/info")
    public String myInfo(){
        return "/users/myinfo";
    }


}
