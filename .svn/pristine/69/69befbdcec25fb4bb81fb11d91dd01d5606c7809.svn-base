package com.mtool.toolslib.base.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by buck on 2017/9/28.
 */
public abstract class SuperBaseAdapter<T> extends BaseAdapter implements DataSet<T> {

    protected List<T> mData = new ArrayList<T>();
    protected List<T> mCopy = new ArrayList<T>();
    protected LayoutInflater mInflater;
    protected Activity mCtx;

    public SuperBaseAdapter(Activity ctx) {
        mInflater = LayoutInflater.from(ctx);
        mCtx = ctx;
    }

    @Override
    public void setData(Collection<T> list) {
        mData.clear();
        if (list != null) {
            mData.addAll(list);
        }
        notifyDataSetInvalidated();
    }

    @Override
    public void setDataEx(Collection<T> list) {
        mData.clear();
        if (list != null) mData.addAll(list);
    }

    @Override
    public void clearDate() {
        mData.clear();
        notifyDataSetChanged();
    }

    @Override
    public void addData(int index, Collection<T> list) {
        if (list != null) {
            mData.addAll(index, list);
        }
        notifyDataSetChanged();
    }

    @Override
    public void addData(Collection<T> list) {
        if (list != null) {
            mData.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public void addData(int index, T obj) {
        mData.add(index, obj);
        notifyDataSetChanged();
    }

    @Override
    public void addData(T obj) {
        mData.add(obj);
        notifyDataSetChanged();
    }

    @Override
    public void removeData(T obj) {
        mData.remove(obj);
        notifyDataSetChanged();
    }

    @Override
    public void update(T obj) {
        int index = mData.indexOf(obj);
        if (index != -1) {
            mData.remove(index);
            mData.add(index, obj);
            notifyDataSetChanged();
        } else {
            addData(obj);
        }
    }

    @Override
    public void updateAndChangeToPosition(T obj, int position) {
        int index = mData.indexOf(obj);
        if (index != -1) {
            mData.remove(index);
            mData.add(position, obj);
            notifyDataSetChanged();
        } else {
            addData(obj);
        }
    }

    @Override
    public void addDataEx(Collection<T> list) {
        if (list != null) {
            mData.addAll(list);
        }
    }

    @Override
    public void addDataEx(T obj) {
        mData.add(obj);
    }

    @Override
    public void removeDataEx(T obj) {
        mData.remove(obj);
    }

    @Override
    public void sort(Comparator<T> comparator) {
        if (mCopy.isEmpty()) {
            mCopy.addAll(mData);
        }
        Collections.sort(mData, comparator);
        notifyDataSetChanged();
    }

    @Override
    public void filter(Object obj, Filter<T> filter) {
        if (mCopy.isEmpty()) {
            mCopy.addAll(mData);
        }
        mData.clear();
        mData.addAll(mCopy);

        int count = mData.size();
        for (int i = count - 1; i >= 0; i--) {
            if (!filter.onComare(obj, mData.get(i))) {
                mData.remove(i);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void reStore() {
        if (!mCopy.isEmpty()) {
            mData.clear();
            mData.addAll(mCopy);
            mCopy.clear();
        } else {
            mCopy.clear();
        }
        notifyDataSetChanged();
    }

    @Override
    public List<T> getData() {
        return mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
