package kr.ko.DBshop.mapper;

import kr.ko.DBshop.dto.CategoriesDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoriesMapper {

    public void insertCategory(CategoriesDto categoriesDto);

    public List<CategoriesDto> selectCategoriesForList(int limit,int offset);

    public int countAllCategories();

    //delete문의 return을 int로 하면 삭제된 데이터의 개수를 알 수 있음.
    public int deleteCategoryById(int categoryId);

    public List<CategoriesDto> selectAllCategories();
}
