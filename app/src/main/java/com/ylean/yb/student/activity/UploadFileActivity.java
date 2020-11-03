package com.ylean.yb.student.activity;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.ylean.yb.student.base.BaseActivity;
import com.zxdc.utils.library.bean.DownLoad;
import com.zxdc.utils.library.http.HandlerConstant;
import com.zxdc.utils.library.http.HttpMethod;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 下载文件
 */
public class UploadFileActivity extends BaseActivity{
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.lin_dialog)
    LinearLayout linDialog;
    //文件下载地址
    private String fileUrl="https://www.baidu.com/img/bdlogo.png";
    //文件保存地址
    private String savePath;

    /**
     * 加载布局
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_file;
    }


    /**
     * 初始化
     */
    @Override
    protected void initData() {
        super.initData();
        fileUrl=getIntent().getStringExtra("fileUrl");
        String[] str = fileUrl.split("/");
        savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + str[str.length - 1];

        //开始下载
        startUpload();
    }

    @OnClick(R.id.img_close)
    public void onViewClicked() {
        finish();
    }


    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                //下载进度
                case HandlerConstant.DOWNLOAD_PRORESS:
                    String progress = (String) msg.obj;
                    if (!TextUtils.isEmpty(progress)) {
                        tvProgress.setText(progress);
                    }
                    break;
                //下载完成
                case HandlerConstant.DOWNLOAD_SUCCESS:
                    //展示完成提示框
                    linDialog.setVisibility(View.VISIBLE);
                    tvContent.setText("文件位置："+savePath);
                    break;
                default:
                    break;
            }
            return false;
        }
    });


    /**
     * 开始下载
     */
    private void startUpload(){
        DownLoad d = new DownLoad();
        d.setDownPath(fileUrl);
        d.setSavePath(savePath);
        HttpMethod.download(d, handler);
    }
}
