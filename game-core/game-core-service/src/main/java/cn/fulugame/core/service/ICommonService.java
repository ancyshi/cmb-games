package cn.fulugame.core.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * shijiaoyun 2019-01-03 
 * @param <T>
 * @param <K>
 */
public interface ICommonService<T,K> {
    List<T> findAll();
    PageInfo<T> find(int pageNum, int pageSize);
    T findById(K id);
    int deleteById(K id);
    int create(T t);
    int update(T t);
}
