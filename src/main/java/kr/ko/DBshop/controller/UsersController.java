package kr.ko.DBshop.controller;

import kr.ko.DBshop.dto.CartItemProductDto;
import kr.ko.DBshop.dto.OrderItemsDto;
import kr.ko.DBshop.dto.OrdersDto;
import kr.ko.DBshop.dto.UsersDto;
import kr.ko.DBshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    ProductsService productsService;

    @Autowired
    CartItemsService cartItemsService;

    @Autowired
    OrderItemsService orderItemsService;

    @Autowired
    OrdersService ordersService;

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
        return "/admin/admin";
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


    @PostMapping("/my/cart")
    public String addMyCart(@RequestParam("productId") Integer productId){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = usersService.getUserByEmail(email).getUserId();

        cartItemsService.addCartItemByUserIdAndProductId(userId, productId);

        return "redirect:/my/cart";

    }

    @GetMapping("/my/cart")
    public String showMyCart(Model model){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = usersService.getUserByEmail(email).getUserId();
        List<CartItemProductDto> cartItems = cartItemsService.getCartItemsWithProductByUserId(userId);
        model.addAttribute("cartItems",cartItems);

        return "/users/mycart";
    }

    @GetMapping("/my/checkout")
    public String chechout(Model model){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = usersService.getUserByEmail(email).getUserId();
        List<CartItemProductDto> cartItems = cartItemsService.getCartItemsWithProductByUserId(userId);

        long totalAmount = 0;

        for (CartItemProductDto item : cartItems) {
            totalAmount += (long) (item.getPrice()) * item.getQuantity();
        }

        model.addAttribute("totalPrice", totalAmount);
        model.addAttribute("cartItems",cartItems);

        return "/users/checkout";
    }

    @PostMapping("/my/process-payment")
    public String payment(@RequestParam List<String> productNames,
                               @RequestParam List<Integer> quantities,
                               @RequestParam String totalPrice,
                               @RequestParam String address){

        long totalAmount = Long.parseLong(totalPrice);

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = usersService.getUserByEmail(email).getUserId();

        OrdersDto ordersDto = new OrdersDto(userId,"배송 준비 중", totalAmount,address);


        ordersService.createOrder(ordersDto);

        orderItemsService.createOrderItem(ordersDto.getOrderId(),productNames,quantities);

        cartItemsService.deleteCartItemByUserId(userId);

        return "/users/payment-success";
    }
}
