package com.ylean.yb.student.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.ylean.yb.student.R;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.FileUtils;

import java.io.File;

public class SelectPhotoUtil {


    //相册
    public static final int CODE_GALLERY_REQUEST = 0xa3;
    //拍照
    public static final int CODE_CAMERA_REQUEST = 0xa4;
    //裁剪
    public static final int CODE_RESULT_REQUEST = 0xa5;
    public static final String pai = FileUtils.getSdcardPath() + "pictures.jpg";
    //裁剪后的图片路径
    public static String crop;

    /**
     * 选择照片
     */
    public static  void selectPhoto(Activity activity,int type){
        if(type==1){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(pai)));
            activity.startActivityForResult(intent, CODE_CAMERA_REQUEST);
        }else{
            Intent intent = new Intent(Intent.ACTION_PICK);
            // 设置文件类型
            intent.setType("image/*");
            activity.startActivityForResult(intent, CODE_GALLERY_REQUEST);
        }

    }


    /**
     * 裁剪原始的图片
     */
    public static void cropRawPhoto(Uri uri,Activity activity) {
        crop = FileUtils.getSdcardPath()+System.currentTimeMillis()+".jpg";
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("output", Uri.fromFile(new File(crop)));
        // 设置裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", false);
        activity.startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /**
     * 选择照片
     */
    public static void SelectPhoto(final Activity activity,final int maxNum){
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_photo, null);
        final PopupWindow popupWindow = DialogUtil.showPopWindow(view);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        //拍照
        view.findViewById(R.id.tv_picture).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
                selectPhoto(activity,1);
            }
        });
        //从相册选择
        view.findViewById(R.id.tv_photo).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
                PictureSelector.create(activity)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(maxNum)
                        .minSelectNum(1)
                        .imageSpanCount(3)
                        .selectionMode(PictureConfig.MULTIPLE)
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });
        view.findViewById(R.id.tv_cancle).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
