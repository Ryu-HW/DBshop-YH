package kr.ko.DBshop.service;

import kr.ko.DBshop.dto.CategoriesDto;
import kr.ko.DBshop.mapper.CategoriesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesService {

    @Autowired
    CategoriesMapper categoriesMapper;

    public void createCategories(CategoriesDto categoriesDto){

        categoriesMapper.insertProduct(categoriesDto.getCategoryName());
    }
}
