package com.juziwl.commonlibrary.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author 文庆
 * @date 2019/5/20.
 * @description 基本的分页bean
 */
public class BasePageBean<T> implements Serializable {
    public PageBean page;
    public List<T> records;

    public static class PageBean {

        public int page;
        public int size;
        public int total;
        public int totalPage;
    }

}
