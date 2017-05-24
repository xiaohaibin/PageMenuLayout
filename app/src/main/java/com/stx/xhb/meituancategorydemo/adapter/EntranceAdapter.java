package com.stx.xhb.meituancategorydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stx.xhb.meituancategorydemo.R;
import com.stx.xhb.meituancategorydemo.model.ModelHomeEntrance;
import com.stx.xhb.meituancategorydemo.utils.ScreenUtil;

import java.util.List;

/**
 * Author: Mr.xiao on 2017/5/23
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe: 首页分页菜单项列表适配器
 */
public class EntranceAdapter extends RecyclerView.Adapter<EntranceAdapter.EntranceViewHolder> {

    private List<ModelHomeEntrance> mDatas;

    /**
     * 页数下标,从0开始(通俗讲第几页)
     */
    private int mIndex;

    /**
     * 每页显示最大条目个数
     */
    private int mPageSize;

    private Context mContext;

    private final LayoutInflater mLayoutInflater;

    private List<ModelHomeEntrance> homeEntrances;

    public EntranceAdapter(Context context, List<ModelHomeEntrance> datas, int index, int pageSize) {
        this.mContext = context;
        this.homeEntrances = datas;
        mPageSize = pageSize;
        mDatas = datas;
        mIndex = index;
        mLayoutInflater = LayoutInflater.from(context);

    }

    @Override
    public EntranceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EntranceViewHolder(mLayoutInflater.inflate(R.layout.item_home_entrance, null));
    }

    @Override
    public void onBindViewHolder(EntranceViewHolder holder, final int position) {
        /**
         * 在给View绑定显示的数据时，计算正确的position = position + mIndex * mPageSize，
         */
        final int pos = position + mIndex * mPageSize;
        holder.entranceNameTextView.setText(homeEntrances.get(pos).getName());
        holder.entranceIconImageView.setImageResource(homeEntrances.get(pos).getImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelHomeEntrance entrance = homeEntrances.get(pos);
                // TODO: 2017/5/24 点击事件
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size() > (mIndex + 1) * mPageSize ? mPageSize : (mDatas.size() - mIndex * mPageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + mIndex * mPageSize;
    }

    class EntranceViewHolder extends RecyclerView.ViewHolder {

        private TextView entranceNameTextView;
        private ImageView entranceIconImageView;

        public EntranceViewHolder(View itemView) {
            super(itemView);
            entranceIconImageView = (ImageView) itemView.findViewById(R.id.entrance_image);
            entranceNameTextView = (TextView) itemView.findViewById(R.id.entrance_name);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) ((float) ScreenUtil.getScreenWidth() / 4.0f));
            itemView.setLayoutParams(layoutParams);
        }
    }
}
