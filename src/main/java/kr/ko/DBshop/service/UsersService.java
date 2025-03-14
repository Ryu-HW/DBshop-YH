package kr.ko.DBshop.service;

import kr.ko.DBshop.dto.Role;
import kr.ko.DBshop.dto.UsersDto;
import kr.ko.DBshop.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    UsersMapper usersMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void signUp(UsersDto userDto){

        if(usersMapper.selectUserEmailByEmail(userDto.getEmail()) == null){
            //페스워드 암호화
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            //회원데이터 저장
            usersMapper.signUp(userDto);

            //저장된 객체를 참조하여 id값 추출
            Integer user_id = userDto.getUserId();
            //잘 추출되면 1(ROLE_USER) 권한 추가
            if(user_id == null){
                throw new IllegalStateException("회원가입이 제대로 되지 않았습니다.");
            }else {
                usersMapper.insertUserRole(user_id,1);
            }
        }else {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

    }

    public UsersDto getUserByEmail(String email){
        return usersMapper.selectUserByEmail(email);
    }

    public void updateUser(UsersDto usersDto){
        usersMapper.updateUser(usersDto);
    }

    public void deleteUserByEmail(String email){
        usersMapper.deleteUserByEmail(email);
    }

    public List<Role> getRolesByUserId(Integer id){
        return usersMapper.selectRolesByUserId(id);
    }
}
