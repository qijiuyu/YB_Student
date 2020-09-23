package com.ylean.yb.student.fragment.main;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.main.MainJLAdapter;
import com.ylean.yb.student.adapter.main.MainJZAdapter;
import com.ylean.yb.student.base.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2020/3/29.
 */
public class MainFragment extends BaseFragment {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.list_jl)
    RecyclerView listJl;
    @BindView(R.id.list_jz)
    RecyclerView listJz;

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();

        showBanner();


        listJl.setLayoutManager(new GridLayoutManager(activity, 2));
        listJl.setAdapter(new MainJLAdapter(activity));


        listJz.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        listJz.setAdapter(new MainJZAdapter(activity));
    }


    @OnClick({R.id.tv_jz, R.id.tv_school, R.id.tv_jyj, R.id.tv_ds, R.id.tv_qy, R.id.tv_more_jl,R.id.tv_more_jz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_jz:
                break;
            case R.id.tv_school:
                break;
            case R.id.tv_jyj:
                break;
            case R.id.tv_ds:
                break;
            case R.id.tv_qy:
                break;
            case R.id.tv_more_jl:
                break;
            case R.id.tv_more_jz:
                 break;
            default:
                break;
        }
    }


    /**
     * 显示轮播图
     */
    private void showBanner(){
        List<String> list=new ArrayList<>();
        list.add("http://app2020ad.dyrs.com.cn/upload/image/20200910/e0792e45-ae58-4e29-8f26-8f7f32ed3454.jpg");
        list.add("http://app2020ad.dyrs.com.cn/upload/image/20200910/e0792e45-ae58-4e29-8f26-8f7f32ed3454.jpg");
        list.add("http://app2020ad.dyrs.com.cn/upload/image/20200910/e0792e45-ae58-4e29-8f26-8f7f32ed3454.jpg");
        try {
            banner.setVisibility(View.VISIBLE);
            //设置样式，里面有很多种样式可以自己都看看效果
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
            banner.setBannerAnimation(Transformer.Default);
            //设置图片加载器，图片加载器在下方
            banner.setImageLoader(new ABImageLoader());
            //设置图片集合
            banner.setImages(list);
            //设置轮播间隔时间
            banner.setDelayTime(3000);
            //设置是否为自动轮播，默认是true
            banner.isAutoPlay(true);
            //设置指示器的位置，小点点，居中显示
            banner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public class ABImageLoader extends ImageLoader {
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String)path).into(imageView);
        }
    }
}
