package com.ncu.papersystem;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xiongwb
 * @date 2021/5/9 14:09
 */

@Data
@NoArgsConstructor
public class Page<T> {

    //页面大小
    private int pageSize = 10;

    //当前页码
    private int currentPage = 1;

    public Page(int currentPage){
        this.currentPage = currentPage;
    }

    public Page(int currentPage, int pageSize){
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

}
