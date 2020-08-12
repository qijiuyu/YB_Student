package com.ylean.yb.student.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.declare.DeclareActivity;
import com.ylean.yb.student.activity.love.LoveActivity;
import com.ylean.yb.student.activity.main.MainActivity;
import com.ylean.yb.student.activity.planning.PlanningActivity;
import com.ylean.yb.student.activity.user.UserActivity;
import com.ylean.yb.student.utils.PermissionUtil;
import com.zxdc.utils.library.util.ActivitysLifecycle;
import com.zxdc.utils.library.util.ToastUtil;
import com.zxdc.utils.library.util.error.CockroachUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabActivity extends android.app.TabActivity {

    @BindView(R.id.img_main)
    ImageView imgMain;
    @BindView(R.id.tv_main)
    TextView tvMain;
    @BindView(R.id.img_declare)
    ImageView imgDeclare;
    @BindView(R.id.tv_declare)
    TextView tvDeclare;
    @BindView(R.id.img_love)
    ImageView imgLove;
    @BindView(R.id.tv_love)
    TextView tvLove;
    @BindView(R.id.img_planning)
    ImageView imgPlanning;
    @BindView(R.id.tv_planning)
    TextView tvPlanning;
    @BindView(R.id.img_user)
    ImageView imgUser;
    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(android.R.id.tabhost)
    TabHost tabhost;
    private int[] notClick = new int[]{R.mipmap.tab_1_false, R.mipmap.tab_2_false, R.mipmap.tab_3_false, R.mipmap.tab_4_false,R.mipmap.tab_5_false};
    private int[] yesClick = new int[]{R.mipmap.tab_1_true, R.mipmap.tab_2_true, R.mipmap.tab_3_true, R.mipmap.tab_4_true,R.mipmap.tab_5_true};
    private List<TextView> tvList;
    private List<ImageView> imgList;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);
        initView();

        //android 7.0系统解决拍照的问题
        PermissionUtil.initPhotoError();
    }

    /**
     * 初始化
     */
    private void initView(){
        tvList = new ArrayList<TextView>(){{add(tvMain);add(tvDeclare);add(tvLove);add(tvPlanning);add(tvUser);}};
        imgList = new ArrayList<ImageView>(){{add(imgMain);add(imgDeclare);add(imgLove);add(imgPlanning);add(imgUser);}};
        tabhost = this.getTabHost();
        TabHost.TabSpec spec;
        spec = tabhost.newTabSpec("首页").setIndicator("首页").setContent(new Intent(this, MainActivity.class));
        tabhost.addTab(spec);
        spec = tabhost.newTabSpec("批次申报").setIndicator("批次申报").setContent(new Intent(this, DeclareActivity.class));
        tabhost.addTab(spec);
        spec = tabhost.newTabSpec("爱心社").setIndicator("爱心社").setContent(new Intent(this, LoveActivity.class));
        tabhost.addTab(spec);
        spec = tabhost.newTabSpec("职业规划").setIndicator("职业规划").setContent(new Intent(this, PlanningActivity.class));
        tabhost.addTab(spec);
        spec = tabhost.newTabSpec("我的").setIndicator("我的").setContent(new Intent(this, UserActivity.class));
        tabhost.addTab(spec);
        tabhost.setCurrentTab(0);
    }

    @OnClick({R.id.lin_main, R.id.lin_declare, R.id.lin_love, R.id.lin_planning, R.id.lin_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_main:
                updateTag(0);
                break;
            case R.id.lin_declare:
                updateTag(1);
                break;
            case R.id.lin_love:
                updateTag(2);
                break;
            case R.id.lin_planning:
                updateTag(3);
                break;
            case R.id.lin_user:
                updateTag(4);
                break;
            default:
                break;
        }
    }


    /**
     * 切换tab时，更新图片和文字颜色
     */
    private void updateTag(int type) {
        for (int i = 0; i < 5; i++) {
            if (i == type) {
                imgList.get(i).setImageDrawable(getResources().getDrawable(yesClick[i]));
                tvList.get(i).setTextColor(getResources().getColor(R.color.color_333333));
            } else {
                imgList.get(i).setImageDrawable(getResources().getDrawable(notClick[i]));
                tvList.get(i).setTextColor(getResources().getColor(R.color.color_999999));
            }
        }
        tabhost.setCurrentTab(type);
    }


    /**
     * 连续点击两次返回退出
     */
    protected long exitTime = 0;
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.showLong("再按一次退出程序!");
                exitTime = System.currentTimeMillis();
            } else {
                //关闭小强
                CockroachUtil.clear();
                ActivitysLifecycle.getInstance().exit();
            }
            return false;
        }
        return super.dispatchKeyEvent(event);
    }

}
