package com.ylean.yb.student.activity.init;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.TabActivity;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.util.SPUtil;
import java.util.ArrayList;
import butterknife.BindView;

public class GuideActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    //用来存放导航图片实例
    private ArrayList<ImageView> imageViews;
    //导航页资源
    private int[] images = new int[]{
            R.mipmap.guide_1,
            R.mipmap.guide_2,
            R.mipmap.guide_3,
    };

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        return R.layout.activity_guide;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        SPUtil.getInstance(activity).addBoolean(SPUtil.IS_FIRST_OPEN, true);

        imageViews = new ArrayList<>();
        //初始化导航页面
        for (int i = 0; i < images.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setImageResource(images[i]);
            imageViews.add(iv);
            iv.setTag(i);
            iv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int index= (int) v.getTag();
                    if(index==2){
                        setClass(TabActivity.class);
                        finish();
                    }
                }
            });
        }
        //为ViewPager添加适配器
        viewPager.setAdapter(new MyAdapter());
    }


    //PagerAdapter有四个方法
    class MyAdapter extends PagerAdapter {
        //返回导航页的个数
        @Override
        public int getCount() {
            return images.length;
        }

        //判断是否由对象生成
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //加载页面
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = imageViews.get(position);
            container.addView(iv);
            return iv;
        }

        //移除页面
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
