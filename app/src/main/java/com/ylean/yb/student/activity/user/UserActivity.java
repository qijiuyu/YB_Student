package com.ylean.yb.student.activity.user;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.user.activity.MyActivity;
import com.ylean.yb.student.activity.user.apply.ApplyRecordActivity;
import com.ylean.yb.student.activity.user.bank.MyBankActivity;
import com.ylean.yb.student.activity.user.leave.MyLeaveActivity;
import com.ylean.yb.student.activity.user.min.UserInfoActivity;
import com.ylean.yb.student.activity.user.news.NewsActivity;
import com.ylean.yb.student.activity.user.resume.MyResumeActivity;
import com.ylean.yb.student.activity.user.school.InSchoolActivity;
import com.ylean.yb.student.activity.user.setting.SettingActivity;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.user.UserP;
import com.zxdc.utils.library.bean.ActivityNum;
import com.zxdc.utils.library.bean.PageParam;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.util.SPUtil;
import com.zxdc.utils.library.view.CircleImageView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 */
public class UserActivity extends BaseActivity implements UserP.Face {
    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_nickName)
    TextView tvNickName;
    @BindView(R.id.tv_credential)
    TextView tvCredential;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_total_activity)
    TextView tvTotalActivity;
    @BindView(R.id.tv_total_time)
    TextView tvTotalTime;

    //用户信息对象
    private UserInfo userInfo;
    private UserP userP;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        userP=new UserP(this,this);
    }


    @OnClick({R.id.img_news, R.id.img_setting,R.id.img_head, R.id.rel_sqjl, R.id.rel_zxjk, R.id.rel_st, R.id.rel_bank, R.id.rel_activity, R.id.rel_zx, R.id.rel_share, R.id.rel_resume, R.id.rel_ly, R.id.rel_about})
    public void onViewClicked(View view) {
        Intent intent=new Intent();
        switch (view.getId()) {
            //消息
            case R.id.img_news:
                setClass(NewsActivity.class);
//                setClass(LoginActivity.class);
                break;
            //设置
            case R.id.img_setting:
                 intent.setClass(this,SettingActivity.class);
                 intent.putExtra("userInfo",userInfo);
                 startActivity(intent);
                break;
            //个人档案
            case R.id.img_head:
                 setClass(UserInfoActivity.class);
                 break;
            //申请记录
            case R.id.rel_sqjl:
                setClass(ApplyRecordActivity.class);
                break;
            //在校情况
            case R.id.rel_zxjk:
                setClass(InSchoolActivity.class);
                break;
            //我的社团
            case R.id.rel_st:
                break;
            //我的银行卡
            case R.id.rel_bank:
                setClass(MyBankActivity.class);
                break;
            //我的活动
            case R.id.rel_activity:
                setClass(MyActivity.class);
                break;
            //导师咨询
            case R.id.rel_zx:
                break;
            //创业分享
            case R.id.rel_share:
                break;
            //我的简历
            case R.id.rel_resume:
                setClass(MyResumeActivity.class);
                break;
            //我的留言
            case R.id.rel_ly:
                setClass(MyLeaveActivity.class);
                break;
            //关于我们
            case R.id.rel_about:
                setClass(AboutActivity.class);
                break;
            default:
                break;
        }
    }


    /**
     * 获取用户基本信息
     */
    @Override
    public void getbaseinfo(UserInfo userInfo) {
        this.userInfo=userInfo;
        if(!TextUtils.isEmpty(userInfo.getData().getPhoto())){
            Glide.with(this).load(userInfo.getData().getPhoto()).into(imgHead);
        }
        tvNickName.setText(userInfo.getData().getName());
        tvCredential.setText("身份证号:"+userInfo.getData().getIdnum());
        Drawable drawable;
        if(userInfo.getData().getSex().equals("男")){
            drawable = getResources().getDrawable(R.mipmap.male);
        }else{
            drawable = getResources().getDrawable(R.mipmap.female);
        }
        tvNickName.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);

        //存储用户信息
        SPUtil.getInstance(this).addObject(SPUtil.USER_BASE_INFO,userInfo);
    }


    /**
     * 我的社团活动数量
     * @param numBean
     */
    @Override
    public void getOwnActivityNum(final ActivityNum.NumBean numBean) {
        tvTotalActivity.setText(numBean.getActivityNum()+"");
        tvTotalTime.setText(numBean.getTotalPublicWelfare()+"");
    }


    @Override
    public void onResume() {
        super.onResume();
        final String token= SPUtil.getInstance(this).getString(SPUtil.TOKEN);
        if(!TextUtils.isEmpty(token)){
            //获取学生基本信息
            userP.getbaseinfo();

            //我的社团活动数量
            userP.getOwnActivityNum(new PageParam(1,10));
        }
    }
}
