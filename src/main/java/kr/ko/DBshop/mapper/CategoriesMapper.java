package kr.ko.DBshop.mapper;

import kr.ko.DBshop.dto.CategoriesDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoriesMapper {

    public void insertProduct(String categoryName);
}
