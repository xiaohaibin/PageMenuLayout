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
