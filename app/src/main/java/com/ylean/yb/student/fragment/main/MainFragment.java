package com.ylean.yb.student.fragment.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.main.MainActivity;
import com.ylean.yb.student.activity.webview.MainWebViewActivity;
import com.ylean.yb.student.adapter.main.MainDTAdapter;
import com.ylean.yb.student.adapter.main.MainJLAdapter;
import com.ylean.yb.student.adapter.main.MainJZAdapter;
import com.ylean.yb.student.base.BaseFragment;
import com.ylean.yb.student.persenter.main.BannerP;
import com.ylean.yb.student.persenter.main.DonationP;
import com.ylean.yb.student.persenter.main.MainP;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.zxdc.utils.library.bean.BannerBean;
import com.zxdc.utils.library.bean.DonationBean;
import com.zxdc.utils.library.bean.NewsListBean;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2020/3/29.
 */
public class MainFragment extends BaseFragment implements BannerP.Face, DonationP.Face, MainP.Face {


    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.list_jl)
    RecyclerView listJl;
    @BindView(R.id.list_jz)
    RecyclerView listJz;
    @BindView(R.id.list_dt)
    RecyclerView listDt;

    private BannerP bannerP;
    private DonationP donationP;
    private MainP mainP;

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
        donationP=new DonationP(activity,this);
        mainP=new MainP(activity,this);

        //获取Banner列表信息
        bannerP.getBanner(0);

        //获取网站项目捐赠列表
        donationP.getDonation();

        //获取交流合作数据
        mainP.getMainNews(203,6);

        //获取首页动态数据
        mainP.getMainNews(205,6);

    }


    @OnClick({R.id.tv_jz, R.id.tv_school, R.id.tv_jyj, R.id.tv_ds, R.id.tv_qy, R.id.tv_more_jl})
    public void onViewClicked(View view) {
        Intent intent=new Intent(activity, MainWebViewActivity.class);
        switch (view.getId()) {
            //捐赠管理系统
            case R.id.tv_jz:
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            case R.id.tv_school:
                intent.putExtra("type",2);
                startActivity(intent);
                break;
            case R.id.tv_jyj:
                intent.putExtra("type",3);
                startActivity(intent);
                break;
            case R.id.tv_ds:
                intent.putExtra("type",4);
                startActivity(intent);
                break;
            case R.id.tv_qy:
                intent.putExtra("type",5);
                startActivity(intent);
                break;
            //交流合作-更多
            case R.id.tv_more_jl:
                ((MainActivity)activity).updatePager(3);
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


    /**
     * 显示捐赠项目
     * @param list
     */
    @Override
    public void getDonation(List<DonationBean.ListBean> list) {
        listJz.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        listJz.setAdapter(new MainJZAdapter(activity,list));
    }


    /**
     * 显示动态数据
     * @param list
     */
    @Override
    public void getMainNews(List<NewsListBean.ListBean> list,int ctype) {
        if(ctype==203){
            listJl.setLayoutManager(new GridLayoutManager(activity, 2));
            listJl.setAdapter(new MainJLAdapter(activity,list));
        }else{
            listDt.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
            listDt.setAdapter(new MainDTAdapter(activity,list));
        }
    }
}
