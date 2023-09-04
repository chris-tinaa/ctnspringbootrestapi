package com.juaracoding.cspringbootrestapi.core;

import java.util.List;

public interface IService<T> {

    public void save(T t);//001-0010
    public void update(T t);//001-0010
    public void delete(T t);//001-0010
    public void saveBatch(List<T> lt);//001-0010


}
