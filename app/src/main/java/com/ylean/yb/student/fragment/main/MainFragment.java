package com.ylean.yb.student.fragment.main;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.ylean.yb.student.R;
import com.ylean.yb.student.adapter.main.MainDTAdapter;
import com.ylean.yb.student.adapter.main.MainJLAdapter;
import com.ylean.yb.student.adapter.main.MainJZAdapter;
import com.ylean.yb.student.base.BaseFragment;
import com.ylean.yb.student.persenter.main.BannerP;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zxdc.utils.library.bean.BannerBean;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2020/3/29.
 */
public class MainFragment extends BaseFragment implements BannerP.Face {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.list_jl)
    RecyclerView listJl;
    @BindView(R.id.list_jz)
    RecyclerView listJz;
    @BindView(R.id.list_dt)
    RecyclerView listDt;

    private BannerP bannerP;

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
        bannerP=new BannerP(activity,this);

        //获取Banner列表信息
        bannerP.getBanner(0);


        listJl.setLayoutManager(new GridLayoutManager(activity, 2));
//        listJl.setAdapter(new MainJLAdapter(activity));


        listJz.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        listJz.setAdapter(new MainJZAdapter(activity));

        listDt.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        listDt.setAdapter(new MainDTAdapter(activity));
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
     * 获取banner图
     * @param list
     */
    @Override
    public void getBanner(List<BannerBean.ListBean> list) {
        if(list==null){
            return;
        }
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
            final BannerBean.ListBean listBean= (BannerBean.ListBean) path;
            Glide.with(context).load(listBean.getImgurl()).into(imageView);
        }
    }
}
