package kr.ko.DBshop.mapper;

import kr.ko.DBshop.dto.Role;
import kr.ko.DBshop.dto.UsersDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper {

    public void signUp(UsersDto userDto);
    public void insertUserRole(Integer userId, int roleId);
    public String selectUserEmailByEmail(String email);
    public UsersDto selectUserByEmail(String username);
    public List<Role> selectRolesByUserId(Integer id);
    public void updateUser(UsersDto userDto);
    public void deleteUserByEmail(String email);

}
