package com.mtool.toolslib.base.view.adapter;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * Created by buck on 2017/10/4.
 */

public interface DataSet<T> {

    interface Filter<T> {
        boolean onComare(Object obj, T item);
    }

    void setData(Collection<T> list);

    void setDataEx(Collection<T> list);

    void clearDate();

    void addData(int index, Collection<T> list);

    void addData(Collection<T> list);

    void addData(int index, T obj);

    void addData(T obj);

    void removeData(T obj);

    void update(T obj);

    void updateAndChangeToPosition(T obj, int position);

    void addDataEx(Collection<T> list);

    void addDataEx(T obj);

    void removeDataEx(T obj);

    void sort(Comparator<T> comparator);

    void filter(Object obj, Filter<T> filter);

    void reStore();

    int getCount();

    List<T> getData();
}
