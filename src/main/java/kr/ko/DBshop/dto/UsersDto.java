package kr.ko.DBshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsersDto {

    private Integer userId;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String createdAt;

}
