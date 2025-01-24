package kr.ko.DBshop.service;

import kr.ko.DBshop.dto.CategoriesDto;
import kr.ko.DBshop.dto.PageDto;
import kr.ko.DBshop.mapper.CategoriesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    CategoriesMapper categoriesMapper;

    public void createCategories(CategoriesDto categoriesDto){


        categoriesMapper.insertCategory(categoriesDto.getCategoryName());
    }

    public PageDto<CategoriesDto> getCategoriesForList(int page,int size){

        int offset = (page-1)*size;
        List<CategoriesDto> categoriesDto = categoriesMapper.selectCategoriesForList(size,offset);
        int numOfAllCategories = categoriesMapper.countAllCategories();
        PageDto<CategoriesDto> pageDto = new PageDto<>(page, size, numOfAllCategories, categoriesDto);
        return pageDto;

    }
}
