package kr.ko.DBshop.service;

import kr.ko.DBshop.dto.CategoriesDto;
import kr.ko.DBshop.dto.PageDto;
import kr.ko.DBshop.dto.Role;
import kr.ko.DBshop.mapper.CategoriesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    CategoriesMapper categoriesMapper;

    public void createCategories(CategoriesDto categoriesDto){

        categoriesMapper.insertCategory(categoriesDto);
    }

    public PageDto<CategoriesDto> getCategoriesForList(int page,int size){

        int offset = (page-1)*size;
        List<CategoriesDto> categoriesDto = categoriesMapper.selectCategoriesForList(size,offset);
        int numOfAllCategories = categoriesMapper.countAllCategories();
        PageDto<CategoriesDto> pageDto = new PageDto<>(page, size, numOfAllCategories, categoriesDto);
        return pageDto;
    }

    public void deleteCategoryById(int categoryId){
        //리턴값을 int로 받고 있지만 값을 원하지 않는다면 void처럼 사용 가능
        categoriesMapper.deleteCategoryById(categoryId);
    }

    public List<CategoriesDto> getAllCategories(){
        return categoriesMapper.selectAllCategories();
    }


}
