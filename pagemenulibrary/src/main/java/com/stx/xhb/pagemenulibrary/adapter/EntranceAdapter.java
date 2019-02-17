package com.stx.xhb.pagemenulibrary.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stx.xhb.pagemenulibrary.holder.AbstractHolder;
import com.stx.xhb.pagemenulibrary.holder.PageMenuViewHolderCreator;

import java.util.List;

/**
 * @author xiao.haibin
 * mail:xhb_199409@163.com
 * github:https://github.com/xiaohaibin
 * describe: 分页菜单项列表适配器
 */
public class EntranceAdapter<T> extends RecyclerView.Adapter<AbstractHolder> {

    private List<T> mDatas;

    /**
     * 页数下标,从0开始(通俗讲第几页)
     */
    private int mIndex;

    /**
     * 每页显示最大条目个数
     */
    private int mPageSize;
    private PageMenuViewHolderCreator mPageMenuViewHolderCreator;

    public EntranceAdapter(PageMenuViewHolderCreator holderCreator, List<T> datas, int index, int pageSize) {
        this.mDatas = datas;
        this.mPageSize = pageSize;
        this.mIndex = index;
        this.mPageMenuViewHolderCreator = holderCreator;
    }

    @Override
    public int getItemCount() {
        return mDatas.size() > (mIndex + 1) * mPageSize ? mPageSize : (mDatas.size() - mIndex * mPageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + mIndex * mPageSize;
    }


    @NonNull
    @Override
    public AbstractHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = mPageMenuViewHolderCreator.getLayoutId();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return mPageMenuViewHolderCreator.createHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AbstractHolder holder, int position) {
        final int pos = position + mIndex * mPageSize;
        holder.bindView(holder,mDatas.get(pos),pos);
    }

}
