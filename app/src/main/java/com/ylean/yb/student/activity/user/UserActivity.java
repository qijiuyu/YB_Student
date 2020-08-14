package com.ylean.yb.student.activity.user;

import android.view.View;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.init.LoginActivity;
import com.ylean.yb.student.activity.user.activity.MyActivity;
import com.ylean.yb.student.activity.user.apply.ApplyRecordActivity;
import com.ylean.yb.student.activity.user.resume.MyResumeActivity;
import com.ylean.yb.student.activity.user.school.InSchoolActivity;
import com.ylean.yb.student.activity.user.setting.SettingActivity;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.view.CircleImageView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的
 */
public class UserActivity extends BaseActivity {
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }


    @OnClick({R.id.img_news, R.id.img_setting,R.id.img_head, R.id.rel_sqjl, R.id.rel_zxjk, R.id.rel_st, R.id.rel_bank, R.id.rel_activity, R.id.rel_zx, R.id.rel_share, R.id.rel_resume, R.id.rel_ly, R.id.rel_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //消息
            case R.id.img_news:
                setClass(LoginActivity.class);
                break;
            //设置
            case R.id.img_setting:
                 setClass(SettingActivity.class);
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
                break;
            //关于我们
            case R.id.rel_about:
                break;
            default:
                break;
        }
    }
}
