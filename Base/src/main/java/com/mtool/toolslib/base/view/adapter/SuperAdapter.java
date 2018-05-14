package com.mtool.toolslib.base.view.adapter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by buck on 2017/10/4.
 */

public abstract class SuperAdapter<T> extends Adapter<SuperAdapter.Holder> implements DataSet<T> {

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    private boolean containHeaderOrFooter = false;
    private static final int TYPE_NORMAL = 0;

    private static final int HEADER_VIEW_TYPE_START = -1000;
    private static final int FOOTER_VIEW_TYPE_START = -2000;

    protected List<T> mData = new ArrayList<T>();
    protected List<T> mCopy = new ArrayList<T>();
    protected LayoutInflater mInflater;
    protected Activity mCtx;

    private OnItemClickListener mItemClickListener;

    public SuperAdapter(Activity ctx) {
        mInflater = LayoutInflater.from(ctx);
        mCtx = ctx;
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        mItemClickListener = l;
    }

    @Override
    public void setData(Collection<T> list) {
        mData.clear();
        if (list != null) {
            mData.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void setData(ArrayList<T> arr) {
        mData.clear();
        if (arr != null) {
            for (T i : arr) {
                mData.add(i);
            }
        }
        notifyDataSetChanged();
    }


    public void setData(T[] arr) {
        mData.clear();
        if (arr != null) {
            for (T i : arr) {
                mData.add(i);
            }
        }
        notifyDataSetChanged();
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
    public void filter(Object obj, DataSet.Filter<T> filter) {
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

    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType <= FOOTER_VIEW_TYPE_START) {
            return new Holder(mFooterViews.get(FOOTER_VIEW_TYPE_START - viewType), false);
        } else if (viewType <= HEADER_VIEW_TYPE_START) {
            return new Holder(mHeaderViews.get(HEADER_VIEW_TYPE_START - viewType), false);
        }
        return new Holder(onCreateItemView(parent, viewType), true);
    }

    @Override
    public void onBindViewHolder(SuperAdapter.Holder holder, int position) {
        if (position >= mHeaderViews.size() && position < (mHeaderViews.size() + getCount())) {
            int contentPosition = position - mHeaderViews.size();
            onBindItemView(holder.itemView, getItemViewType(position), contentPosition);
        }
    }


    @Override
    public int getItemViewType(int position) {
        final int withoutFooter = mHeaderViews.size() + getCount();

        if (position < mHeaderViews.size()) {
            return HEADER_VIEW_TYPE_START - position;
        } else if (position >= (withoutFooter)) {
            return FOOTER_VIEW_TYPE_START - (position - withoutFooter);
        }
        return getViewType(position - mHeaderViews.size());
//        return getViewType(position);
    }

    public int getViewType(int position) {
        return TYPE_NORMAL;
    }

    protected abstract View onCreateItemView(ViewGroup parent, int viewType);

    protected abstract void onBindItemView(View view, int viewType, int position);


    @Override
    public int getItemCount() {
        return mData.size() + mHeaderViews.size() + mFooterViews.size();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public Holder(View itemView, boolean enableClick) {
            super(itemView);
            if (enableClick) {
                itemView.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getAdapterPosition() - mHeaderViews.size());
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == 0
                            ? 1 : gridManager.getSpanCount();
                }
            });
        }
    }

    public void addHeaderView(View headerView) {
        mHeaderViews.add(headerView);
        notifyDataSetChanged();
    }


    public void cleanHeaderView() {
        mHeaderViews.clear();
        notifyDataSetChanged();
    }

    public void addFooterView(View footerView) {
        mFooterViews.add(footerView);
        notifyDataSetChanged();
    }

    public void cleanFooterView() {
        mFooterViews.clear();
        notifyDataSetChanged();
    }


    protected List<View> mHeaderViews = new ArrayList<>();
    protected List<View> mFooterViews = new ArrayList<>();
}
