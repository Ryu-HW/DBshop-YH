package kr.ko.DBshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PageDto<T> {
    private int currentPage;
    private int size;
    private int totalPageNum;
    private List<T> objects;

    public PageDto(int page, int size, int totalPageNum, List<T> objects) {
        this.currentPage = page;
        this.size = size;
        this.totalPageNum = (int) Math.ceil((double) totalPageNum/size);
        this.objects = objects;
    }


}
