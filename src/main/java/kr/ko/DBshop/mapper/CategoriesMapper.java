package kr.ko.DBshop.mapper;

import kr.ko.DBshop.dto.CategoriesDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoriesMapper {

    public void insertCategory(String categoryName);
    public List<CategoriesDto> selectCategoriesForList(int limit,int offset);
    public int countAllCategories();
}
