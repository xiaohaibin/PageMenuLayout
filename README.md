前段时间公司移动端App新增一个模块，类似美团团购的功能，首页有个类似美团的分页菜单的功能，用过美团和饿了么的app的童鞋应该清楚这一功能。首页菜单可以分页切换，类似我们的banner广告切换效果，只不过只能手动切换。所以整个分页效果，我们可以采用Viewpager实现，里面的菜单项我们则可以采用RecyclerView实现，动态改变里面的菜单项，以后产品汪要改需求也是一两行代码能搞定的事，是不是很机智。所以今天我们这个首页分页菜单效果，可以采用ViewPager+RecyclerView实现，思路既然已经有了，那我们就开整吧。首先我们先看下实现的效果图。

![最终实现效果](http://upload-images.jianshu.io/upload_images/1956769-e357d3d1f829a1a9.gif?imageMogr2/auto-orient/strip)

![美团首页分页菜单](http://upload-images.jianshu.io/upload_images/1956769-a5488ad88da53f28.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/300)

![饿了么首页分页菜单](http://upload-images.jianshu.io/upload_images/1956769-9b21714999ddd994.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/300)


#### 布局中使用
activity_main.xml
```
<?xml version="1.0" encoding="utf-8"?>
    <com.stx.xhb.pagemenulibrary.PageMenuLayout
        android:id="@+id/pagemenu"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:pagemenu_row_count="2"
        app:pagemenu_span_count="4"/>

```
#### 代码中使用
```
  private void initView() {
        entranceIndicatorView = findViewById(R.id.main_home_entrance_indicator);
        mPageMenuLayout = findViewById(R.id.pagemenu);
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
        mPageMenuLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) ((float) ScreenUtil.getScreenWidth() / 2.0f)));
        mPageMenuLayout.setPageDatas(homeEntrances, new PageMenuViewHolderCreator() {
            @Override
            public AbstractHolder createHolder(View itemView) {
                return new AbstractHolder<ModelHomeEntrance>(itemView) {
                    private TextView entranceNameTextView;
                    private ImageView entranceIconImageView;

                    @Override
                    protected void initView(View itemView) {
                        entranceIconImageView = itemView.findViewById(R.id.entrance_image);
                        entranceNameTextView = itemView.findViewById(R.id.entrance_name);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) ((float) ScreenUtil.getScreenWidth() / 4.0f));
                        itemView.setLayoutParams(layoutParams);
                    }

                    @Override
                    public void bindView(RecyclerView.ViewHolder holder, final ModelHomeEntrance data, int pos) {
                        entranceNameTextView.setText(data.getName());
                        entranceIconImageView.setImageResource(data.getImage());
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, data.getName(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                };
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_home_entrance;
            }
        });
    }
```
以上就是实现首页分页菜单效果的主要实现代码，这种分页菜单效果在我们的应用中也比较常见，说不定啥时候公司产品汪就拿手机过来让你照着美团之类实现这种效果，demo地址如下，如果帮到了你，可以点个start，支持一下~~~~~~~

