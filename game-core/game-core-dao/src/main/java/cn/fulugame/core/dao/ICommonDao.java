package cn.fulugame.core.dao;

import java.util.List;

public interface ICommonDao<T,K> {

    List<T> findAll();
    T findById(K id);
    int deleteById(K id);
    int create(T t);
    int update(T t);

}
