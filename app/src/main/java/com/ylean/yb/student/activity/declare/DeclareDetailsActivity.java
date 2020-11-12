package com.ylean.yb.student.activity.declare;

import android.app.Dialog;
import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ylean.yb.student.R;
import com.ylean.yb.student.activity.user.min.UserInfoActivity;
import com.ylean.yb.student.base.BaseActivity;
import com.ylean.yb.student.persenter.declare.DeclareP;
import com.zxdc.utils.library.bean.BatchDetails;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.ToastUtil;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 批次申报详情
 */
public class DeclareDetailsActivity extends BaseActivity implements DeclareP.Face2,DeclareP.Face3 {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_valid_time)
    TextView tvValidTime;
    @BindView(R.id.tv_send_time)
    TextView tvSendTime;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv_school)
    TextView tvSchool;
    @BindView(R.id.tv_html)
    HtmlTextView tvHtml;
    //批次id
    private int id;
    private DeclareP declareP=new DeclareP(this);

    /**
     * 加载布局
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_declare_details;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        declareP.setFace2(this);
        declareP.setFace3(this);
        tvTitle.setText("批次申报详情");
        tvDes.setText(Html.fromHtml("注：未能展示出符合实际申报的批次，请从个人档案中正确维护教育经历！<font color=\"#FA4D4F\">去维护></font>"));

        //获取批次id
        id=getIntent().getIntExtra("id",0);
        declareP.getBatchDetailed(id);
    }


    @OnClick({R.id.lin_back, R.id.tv_des,R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_back:
                 finish();
                break;
            case R.id.tv_des:
                 setClass(UserInfoActivity.class);
                 break;
            case R.id.tv_submit:
                 if(batch==null){
                     return;
                 }
                 if(batch.getType()==4 || batch.getType()==5 || batch.getType()==6){
                     showApplyDialog();
                 }else {
                     checkdeclareno();
                 }
                break;
            default:
                break;
        }
    }


    /**
     * 去申请
     */
    private String num;
    private void showApplyDialog(){
        View view= LayoutInflater.from(this).inflate(R.layout.dialog_apply_declare,null);
        final Dialog dialog= DialogUtil.getDialog(this,view);
        final EditText etCode=view.findViewById(R.id.et_code);
        view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num=etCode.getText().toString().trim();
                if(TextUtils.isEmpty(num)){
                    ToastUtil.showLong("请输入考号");
                    return;
                }
                declareP.checkdeclareno(id,num);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.img_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    /**
     * 返回详情
     * @param batch
     */
    private BatchDetails.Batch batch;
    @Override
    public void getBatchDetailed(BatchDetails.Batch batch) {
        this.batch=batch;
        Glide.with(this).load(batch.getImg()).into(imgHead);
        tvName.setText(batch.getName());
        tvContent.setText(batch.getFactor());
        tvNum.setText("已有："+batch.getApplynum()+"人 进行申请");
        tvValidTime.setText("有效时间："+batch.getStarttime().split(" ")[0]+"一"+batch.getEndtime().split(" ")[0]);
        tvSendTime.setText("发布时间："+batch.getCreatetime());
        tvHtml.setHtml(batch.getRemarks(), new HtmlHttpImageGetter(tvHtml));
        switch (batch.getType()){
            case 0:
                tvSchool.setText("高中");
                break;
            case 1:
                tvSchool.setText("中职");
                break;
            case 2:
                tvSchool.setText("高职");
                break;
            case 4:
                tvSchool.setText("大学");
                break;
            case 5:
                tvSchool.setText("硕士");
                break;
            case 6:
                tvSchool.setText("博士");
                break;
            default:
                break;
        }

        switch (batch.getStatus()){
            case 0:
                tvStatus.setText("未开始");
                break;
            case 1:
                tvStatus.setText("已开始");
                break;
            case 2:
                tvStatus.setText("已结束");
                break;
            default:
                break;
        }
    }


    /**
     * 考号验证通过
     */
    @Override
    public void checkdeclareno() {
        Intent intent=new Intent(this,AddDeclareActivity.class);
        intent.putExtra("batchId",batch.getId());
        intent.putExtra("num",num);
        startActivity(intent);
    }
}
