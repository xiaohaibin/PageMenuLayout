前段时间公司移动端App新增一个模块，类似美团团购的功能，首页有个类似美团的分页菜单的功能，用过美团和饿了么的app的童鞋应该清楚这一功能。首页菜单可以分页切换，类似我们的banner广告切换效果，只不过只能手动切换。所以整个分页效果，我们可以采用Viewpager实现，里面的菜单项我们则可以采用RecyclerView实现，动态改变里面的菜单项，以后产品汪要改需求也是一两行代码能搞定的事，是不是很机智。所以今天我们这个首页分页菜单效果，可以采用ViewPager+RecyclerView实现，思路既然已经有了，那我们就开整吧。首先我们先看下实现的效果图。

![最终实现效果](http://upload-images.jianshu.io/upload_images/1956769-e357d3d1f829a1a9.gif?imageMogr2/auto-orient/strip)

![美团首页分页菜单](http://upload-images.jianshu.io/upload_images/1956769-a5488ad88da53f28.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/300)

![饿了么首页分页菜单](http://upload-images.jianshu.io/upload_images/1956769-9b21714999ddd994.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/300)


首页布局文件，分页指示器是单独封装的一个控件，后面会把代码贴出来
activity_main.xml
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:app="http://schemas.android.com/apk/res-auto"
              android:id="@+id/home_entrance"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:orientation="vertical">

    <android.support.v4.view.ViewPager
        android:id="@+id/main_home_entrance_vp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

    <com.stx.xhb.meituancategorydemo.widget.IndicatorView
        android:id="@+id/main_home_entrance_indicator"
        android:layout_width="match_parent"
        android:layout_height="32dp"
        android:layout_marginLeft="16dp"
        android:layout_gravity="bottom"
        android:layout_marginRight="16dp"
        app:gravity="0"
        app:indicatorColor="#668b8989"
        app:indicatorColorSelected="#FF5722"
        app:indicatorWidth="6"/>

</LinearLayout>

```
ViewPager中的子控件RecyclerView
item_home_entrance_vp.xml

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.v7.widget.RecyclerView xmlns:android="http://schemas.android.com/apk/res/android"
                                        android:layout_width="match_parent"
                                        android:layout_height="wrap_content"/>
```

接下来就是RecyclerView的菜单项的布局文件
item_home_entrance.xml

```
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
             android:layout_width="match_parent"
             android:layout_height="match_parent">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center_horizontal"
        android:orientation="vertical"
        android:padding="6dp">

        <ImageView
            android:id="@+id/entrance_image"
            android:layout_width="wrap_content"
            android:layout_height="0dp"
            android:layout_margin="2dp"
            android:layout_weight="1"
            android:scaleType="fitCenter"/>

        <TextView
            android:id="@+id/entrance_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="2dp"
            android:singleLine="true"
            android:textColor="#80000000"
            android:textSize="12dp"/>
    </LinearLayout>

    <View
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@drawable/selector_trans_divider"/>

</FrameLayout>

```

布局都创建好了，接下来我们一起来看看里面的具体实现代码了。由于我们的菜单项有一个icon和名称name，为了方便管理，我们可以创建一个菜单项实体类ModelHomeEntrance.class
```
/**
 * Author: Mr.xiao on 2017/5/23
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:菜单项实体类
 */
public class ModelHomeEntrance {
    private String name = "";
    private int image;

    public ModelHomeEntrance(String name, int image) {
        this.image = image;
        this.name = name;
    }


    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

}
```

由于我们分页效果是以ViewPager实现的，所以我们要创建一个ViewPager的适配器，CagegoryViewPagerAdapter.Class
```
package com.stx.xhb.meituancategorydemo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jxnk25 on 2016/9/21.
 *
 * @link https://xiaohaibin.github.io/
 * @email： xhb_199409@163.com
 * @github: https://github.com/xiaohaibin
 * @description：  首页分类ViewPager适配器
 */
public class CagegoryViewPagerAdapter extends PagerAdapter {

    private List<View> mViewList;
    public CagegoryViewPagerAdapter(List<View> mViewList) {
        this.mViewList = mViewList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewList.get(position));
        return (mViewList.get(position));
    }

    @Override
    public int getCount() {
        if (mViewList == null)
            return 0;
        return mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}

```

ViewPager的适配器有了，我们还得再创建一个RecyclerView的菜单项列表适配器，EntranceAdapter.Class

```
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
                // TODO: 2017/5/24 点击事件处理
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

```

最后就是我们的MainActivity的代码实现了，我们整体的思路其实就是需要根据首页菜单项的数据源进行分页显示，首页确定单页菜单显示数量，总数除以单页显示数量取整就是显示页数，我们再根据页数来创建RecyclerView将其添加到ViewPager的适配器中，下面就让我们一起来看看具体是如何的。

```
package com.stx.xhb.meituancategorydemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.stx.xhb.meituancategorydemo.adapter.CagegoryViewPagerAdapter;
import com.stx.xhb.meituancategorydemo.adapter.EntranceAdapter;
import com.stx.xhb.meituancategorydemo.model.ModelHomeEntrance;
import com.stx.xhb.meituancategorydemo.utils.ScreenUtil;
import com.stx.xhb.meituancategorydemo.widget.IndicatorView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int HOME_ENTRANCE_PAGE_SIZE = 10;//首页菜单单页显示数量
    private ViewPager entranceViewPager;
    private LinearLayout homeEntranceLayout;
    private List<ModelHomeEntrance> homeEntrances;
    private IndicatorView entranceIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        init();
    }


    private void initView() {
        homeEntranceLayout = (LinearLayout) findViewById(R.id.home_entrance);
        entranceViewPager = (ViewPager) findViewById(R.id.main_home_entrance_vp);
        entranceIndicatorView = (IndicatorView) findViewById(R.id.main_home_entrance_indicator);
    }


    private void initData() {
        homeEntrances = new ArrayList<>();
        homeEntrances.add(new ModelHomeEntrance("美食", R.mipmap.ic_category_0));
        homeEntrances.add(new ModelHomeEntrance("电影", R.mipmap.ic_category_1));
        homeEntrances.add(new ModelHomeEntrance("酒店住宿", R.mipmap.ic_category_2));
        homeEntrances.add(new ModelHomeEntrance("生活服务", R.mipmap.ic_category_3));
        homeEntrances.add(new ModelHomeEntrance("KTV", R.mipmap.ic_category_4));
        homeEntrances.add(new ModelHomeEntrance("旅游", R.mipmap.ic_category_5));
        homeEntrances.add(new ModelHomeEntrance("学习培训", R.mipmap.ic_category_6));
        homeEntrances.add(new ModelHomeEntrance("汽车服务", R.mipmap.ic_category_7));
        homeEntrances.add(new ModelHomeEntrance("摄影写真", R.mipmap.ic_category_8));
        homeEntrances.add(new ModelHomeEntrance("休闲娱乐", R.mipmap.ic_category_10));
        homeEntrances.add(new ModelHomeEntrance("丽人", R.mipmap.ic_category_11));
        homeEntrances.add(new ModelHomeEntrance("运动健身", R.mipmap.ic_category_12));
        homeEntrances.add(new ModelHomeEntrance("大保健", R.mipmap.ic_category_13));
        homeEntrances.add(new ModelHomeEntrance("团购", R.mipmap.ic_category_14));
        homeEntrances.add(new ModelHomeEntrance("景点", R.mipmap.ic_category_16));
        homeEntrances.add(new ModelHomeEntrance("全部分类", R.mipmap.ic_category_15));
    }

    private void init() {
        LinearLayout.LayoutParams layoutParams12 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) ((float) ScreenUtil.getScreenWidth() / 2.0f));

        //首页菜单分页
        FrameLayout.LayoutParams entrancelayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, (int) ((float) ScreenUtil.getScreenWidth() / 2.0f + 70));
        homeEntranceLayout.setLayoutParams(entrancelayoutParams);
        entranceViewPager.setLayoutParams(layoutParams12);
        LayoutInflater inflater = LayoutInflater.from(this);
        //将RecyclerView放至ViewPager中：
        int pageSize = HOME_ENTRANCE_PAGE_SIZE;
        //一共的页数等于 总数/每页数量，并取整。
        int pageCount = (int) Math.ceil(homeEntrances.size() * 1.0 / pageSize);
        List<View> viewList = new ArrayList<View>();
        for (int index = 0; index < pageCount; index++) {
            //每个页面都是inflate出一个新实例
            RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.item_home_entrance_vp, entranceViewPager, false);
            recyclerView.setLayoutParams(layoutParams12);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 5));
            EntranceAdapter entranceAdapter = new EntranceAdapter(MainActivity.this, homeEntrances, index, HOME_ENTRANCE_PAGE_SIZE);
            recyclerView.setAdapter(entranceAdapter);
            viewList.add(recyclerView);
        }
        CagegoryViewPagerAdapter adapter = new CagegoryViewPagerAdapter(viewList);
        entranceViewPager.setAdapter(adapter);
        entranceIndicatorView.setIndicatorCount(entranceViewPager.getAdapter().getCount());
        entranceIndicatorView.setCurrentIndicator(entranceViewPager.getCurrentItem());
        entranceViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                entranceIndicatorView.setCurrentIndicator(position);
            }
        });
    }
}

```

以上就是实现首页分页菜单效果的主要实现代码，这种分页菜单效果在我们的应用中也比较常见，说不定啥时候公司产品汪就拿手机过来让你照着美团之类实现这种效果，demo地址如下，如果帮到了你，可以点个start，支持一个哦~~~~~~~
https://github.com/xiaohaibin/MeiTuanCategaryDemo
