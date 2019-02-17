[![](https://jitpack.io/v/xiaohaibin/PageMenuLayout.svg)](https://jitpack.io/#xiaohaibin/PageMenuLayout)

## PageMenuLayout
Android分页菜单控件，帮助你快速实现美团、饿了么、京东等分页菜单效果。

![最终实现效果](http://upload-images.jianshu.io/upload_images/1956769-e357d3d1f829a1a9.gif?imageMogr2/auto-orient/strip)


## 基本使用

#### 1.添加 Gradle 依赖

# Jitpack

Add it in your root build.gradle at the end of repositories:
```
allprojects {
     repositories {
	...
	maven { url 'https://jitpack.io' }
     }
}

```
Step 2. Add the dependency
```
dependencies {
   implementation 'com.github.xiaohaibin:PageMenuLayout:latestVersion'//将latestVersion替换成上面 jitpack 后面的版本号
}
```

#### 2.布局中使用
```
<?xml version="1.0" encoding="utf-8"?>
    <com.stx.xhb.pagemenulibrary.PageMenuLayout
        android:id="@+id/pagemenu"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:pagemenu_row_count="2"
        app:pagemenu_span_count="4"/>

```
#### 3.代码中使用
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

## 自定义属性说明

| 属性名 | 属性说明 | 属性值 | 
| ------------ | ------------- | ------------ |
| pagemenu_row_count| 菜单显示行数 | Integer类型，默认为2 |
| pagemenu_span_count| 菜单显示列数 | Integer类型，默认为5|


## 关于我

* **Email**: <xhb_199409@163.com>
* **Home**: <http://www.jxnk25.club>
* **掘金**: <https://juejin.im/user/56fcba0a71cfe4005ca1a57b>
* **简书**: <http://www.jianshu.com/users/42aed90cf5af/latest_articles>

### Contract

[QQ群:271127803](http://qm.qq.com/cgi-bin/qm/qr?k=cM-ytK5bbZZZ4v7S1fMrTDzkjlFT0C9K)

![欢迎关注“大话微信”公众号](http://upload-images.jianshu.io/upload_images/1956769-2f49dcb0dc5195b6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/400)


### 你的 Statr 是我最大的动力，谢谢~~~


License
--
    Copyright (C) 2016 xhb_199409@163.com

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

