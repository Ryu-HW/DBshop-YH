package kr.ko.DBshop.service;


import kr.ko.DBshop.dto.CustomUserDetails;
import kr.ko.DBshop.dto.Role;
import kr.ko.DBshop.dto.UsersDto;
import kr.ko.DBshop.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//로그인시(config에 설정된 루트) UserDetailsService를 상속받는 class실행
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsersDto userDto = usersMapper.selectUserByEmail(username);

        if(userDto != null){
            List<Role> rolesDto = usersMapper.selectRolesByUserId(userDto.getUserId());

            //역할 이름만 추출해서 roles 리스트로 반환.
            List<String> roles = rolesDto.stream()
                    .map(Role::getRole)//각Role객체의 getRole매서드를 이용해서 값 추출
                    .collect(Collectors.toList()); //모아서 list로 리턴

//            List<String> roles = new ArrayList<>();
//
//            for (int i = 0; i < rolesDto.size(); i++) {
//                roles.add(rolesDto.get(i).getRole());
//            }

            return new CustomUserDetails(userDto,roles);
        }

        return null; //가입한 회원정보가 없음.
    }

}