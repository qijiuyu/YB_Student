package com.zxdc.utils.library.http;

import android.os.Handler;
import android.text.TextUtils;
import com.zxdc.utils.library.bean.AboutBean;
import com.zxdc.utils.library.bean.ActivityNum;
import com.zxdc.utils.library.bean.ApplyBean;
import com.zxdc.utils.library.bean.AuditBean;
import com.zxdc.utils.library.bean.BankBaseBean;
import com.zxdc.utils.library.bean.BankProgress;
import com.zxdc.utils.library.bean.BannerBean;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.BatchBean;
import com.zxdc.utils.library.bean.BatchDetails;
import com.zxdc.utils.library.bean.CollMoneyBean;
import com.zxdc.utils.library.bean.DeclareBean;
import com.zxdc.utils.library.bean.DeclareDetailsBean;
import com.zxdc.utils.library.bean.DeliveryBean;
import com.zxdc.utils.library.bean.DictBean;
import com.zxdc.utils.library.bean.DonationBean;
import com.zxdc.utils.library.bean.DownLoad;
import com.zxdc.utils.library.bean.EconomicBean;
import com.zxdc.utils.library.bean.EducationBean;
import com.zxdc.utils.library.bean.FacultyBean;
import com.zxdc.utils.library.bean.FamilyBean;
import com.zxdc.utils.library.bean.FileBean;
import com.zxdc.utils.library.bean.ForgetPwd;
import com.zxdc.utils.library.bean.HistoryBankBean;
import com.zxdc.utils.library.bean.InSchoolBean;
import com.zxdc.utils.library.bean.InSchoolDetailsBean;
import com.zxdc.utils.library.bean.IssueRecordBean;
import com.zxdc.utils.library.bean.LeaveBean;
import com.zxdc.utils.library.bean.LeaveDetailsBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.NewsBean;
import com.zxdc.utils.library.bean.NewsDetailsBean;
import com.zxdc.utils.library.bean.NewsListBean;
import com.zxdc.utils.library.bean.NewsSingle;
import com.zxdc.utils.library.bean.NewsTitleBean;
import com.zxdc.utils.library.bean.PageParam;
import com.zxdc.utils.library.bean.ProvinceBean;
import com.zxdc.utils.library.bean.ReceivablesheadBean;
import com.zxdc.utils.library.bean.Register;
import com.zxdc.utils.library.bean.ReissueAuditBean;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.bean.ResumePostion;
import com.zxdc.utils.library.bean.SchoolBean;
import com.zxdc.utils.library.bean.SurveyBean;
import com.zxdc.utils.library.bean.SurveyDetails;
import com.zxdc.utils.library.bean.TempleteBean;
import com.zxdc.utils.library.bean.UploadFile;
import com.zxdc.utils.library.bean.UploadPic;
import com.zxdc.utils.library.bean.UploadResumeFile;
import com.zxdc.utils.library.bean.UserInfo;
import com.zxdc.utils.library.bean.parameter.AddResumeEducation;
import com.zxdc.utils.library.bean.parameter.AddSchoolHonor;
import com.zxdc.utils.library.bean.parameter.AddSchoolPosition;
import com.zxdc.utils.library.bean.parameter.AddSpecialtyP;
import com.zxdc.utils.library.bean.parameter.GetDeliveryRecord;
import com.zxdc.utils.library.bean.parameter.JobIntention;
import com.zxdc.utils.library.bean.parameter.Position;
import com.zxdc.utils.library.bean.parameter.ResumeBase;
import com.zxdc.utils.library.bean.parameter.ResumeCertificate;
import com.zxdc.utils.library.http.base.BaseRequst;
import com.zxdc.utils.library.http.base.Http;
import com.zxdc.utils.library.util.DialogUtil;
import com.zxdc.utils.library.util.JsonUtil;
import com.zxdc.utils.library.util.LogUtils;
import com.zxdc.utils.library.util.ToastUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HttpMethod extends BaseRequst {

    private static String size="10";
    public static int pageSize=10;


    /**
     * 获取所有省集合
     */
    public static void getProvince(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getProvince().enqueue(new Callback<ProvinceBean>() {
            public void onResponse(Call<ProvinceBean> call, Response<ProvinceBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ProvinceBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 根据省代码获取市集合
     */
    public static void getCityByProvince(String code,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getCityByProvince(code).enqueue(new Callback<ProvinceBean>() {
            public void onResponse(Call<ProvinceBean> call, Response<ProvinceBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ProvinceBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 根据市代码获取区集合
     */
    public static void getAreaByCity(String code,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getAreaByCity(code).enqueue(new Callback<ProvinceBean>() {
            public void onResponse(Call<ProvinceBean> call, Response<ProvinceBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ProvinceBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 注册第一步
     */
    public static void register1(String code, String idcardno, String phone, String pwd, List<FileBean> list,final NetCallBack netCallBack){
        Map<String,String> map=new HashMap<>();
        map.put("code",code);
        map.put("idcardno",idcardno);
        map.put("phone",phone);
        map.put("pwd",pwd);
        Http.upLoadFile("api/user/student/register", list, map, new okhttp3.Callback() {
            public void onResponse(okhttp3.Call call, okhttp3.Response response){
                DialogUtil.closeProgress();
                try {
                    String str = response.body().string();
                    LogUtils.e("+++++++++++++++++"+str);
                    final Register userInfo= (Register) JsonUtil.stringToObject(str, Register.class);
                    netCallBack.onSuccess(userInfo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(okhttp3.Call call, IOException e) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(e.getMessage());
            }
        });
    }


    /**
     * 保存用户基本信息
     */
    public static void saveUser(String ucphone,String address,String qq,String residenceaddress,String uctel,int uid,String wechat,final NetCallBack netCallBack) {
        Map<String ,String> map=new HashMap<>();
        map.put("ucphone",ucphone);
        map.put("address",address);
        map.put("qq",qq);
        map.put("residenceaddress",residenceaddress);
        map.put("uctel",uctel);
        map.put("uid",uid+"");
        map.put("wechat",wechat);
        Http.getRetrofit().create(HttpApi.class).saveUser(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 发送email验证信息
     */
    public static void sendbindemail(String type,String email,final NetCallBack netCallBack) {
        Map<String ,String> map=new HashMap<>();
        map.put("type",type);
        map.put("email",email);
        Http.getRetrofit().create(HttpApi.class).sendbindemail(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 发送email验证信息(根据身份证号码)
     */
    public static void sendbindemailByNum(String idnum,String type,final NetCallBack netCallBack) {
        Map<String ,String> map=new HashMap<>();
        map.put("idnum",idnum);
        map.put("type",type);
        Http.getRetrofit().create(HttpApi.class).sendbindemailByNum(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 学生注册第三步
     */
    public static void bindingEmail(String code,String email,int uid,final NetCallBack netCallBack) {
        Map<String ,String> map=new HashMap<>();
        map.put("code",code);
        map.put("email",email);
        map.put("uid",uid+"");
        Http.getRetrofit().create(HttpApi.class).bindingEmail(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 登录
     */
    public static void login(String name,String pwd,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).login(name,pwd).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 找回密码第一步
     */
    public static void findfirstpwd(String idnum,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).findfirstpwd(idnum).enqueue(new Callback<ForgetPwd>() {
            public void onResponse(Call<ForgetPwd> call, Response<ForgetPwd> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ForgetPwd> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 设置新登录密码
     */
    public static void findpwd(String idnum,String code,String pwd,String rpwd,int type,final NetCallBack netCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("idnum",idnum);
        map.put("code",code);
        map.put("pwd",pwd);
        map.put("rpwd",rpwd);
        map.put("type",type+"");

        Http.getRetrofit().create(HttpApi.class).findpwd(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 获取学生基本信息
     */
    public static void getbaseinfo(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getbaseinfo().enqueue(new Callback<UserInfo>() {
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<UserInfo> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 修改用户头像
     */
    public static void updatephotoimg(List<FileBean> list,final NetCallBack netCallBack){
        Map<String,String> map=new HashMap<>();
        Http.upLoadFile("api/user/student/updatephotoimg", list, map, new okhttp3.Callback() {
            public void onResponse(okhttp3.Call call, okhttp3.Response response){
                DialogUtil.closeProgress();
                try {
                    String str = response.body().string();
                    LogUtils.e("+++++++++++++++++"+str);
                    final UploadPic uploadPic= (UploadPic) JsonUtil.stringToObject(str, UploadPic.class);
                    netCallBack.onSuccess(uploadPic);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(okhttp3.Call call, IOException e) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(e.getMessage());
            }
        });
    }



    /**
     * 添加家庭成员
     */
    public static void addFamily(String familymembers,final NetCallBack netCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("familymembers",familymembers);
        Http.getRetrofit().create(HttpApi.class).addFamily(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 修改个人信息
     */
    public static void updateUserInfo(String ucphone,String address,String qq,String uctel,String wechat,final NetCallBack netCallBack) {
        Map<String ,String> map=new HashMap<>();
        map.put("ucphone",ucphone);
        map.put("address",address);
        map.put("qq",qq);
        map.put("uctel",uctel);
        map.put("wechat",wechat);
        Http.getRetrofit().create(HttpApi.class).updateUserInfo(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 查询家庭成员
     */
    public static void getFamilyList(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getFamilyList().enqueue(new Callback<FamilyBean>() {
            public void onResponse(Call<FamilyBean> call, Response<FamilyBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<FamilyBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 删除家庭成员
     */
    public static void deleteFamily(int id,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).deleteFamily(id).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 修改家庭成员
     */
    public static void updateFamily(int id,String company,String incomesource,String name,String occupation,int relation,String relationname,int whethersupport,final NetCallBack netCallBack) {
        Map<String ,String> map=new HashMap<>();
        map.put("id",id+"");
        map.put("company",company);
        map.put("incomesource",incomesource);
        map.put("name",name);
        map.put("occupation",occupation);
        map.put("relation",relation+"");
        map.put("relationname",relationname);
        map.put("whethersupport",whethersupport+"");
        Http.getRetrofit().create(HttpApi.class).updateFamily(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 获取所有学校
     */
    public static void getSchoolList(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getSchoolList().enqueue(new Callback<SchoolBean>() {
            public void onResponse(Call<SchoolBean> call, Response<SchoolBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<SchoolBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 获取所有院系
     */
    public static void getFacultyList(final NetCallBack netCallBack) {
        Map<String,String> map=new HashMap<>();
        Http.getRetrofit().create(HttpApi.class).getFacultyList(map).enqueue(new Callback<FacultyBean>() {
            public void onResponse(Call<FacultyBean> call, Response<FacultyBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<FacultyBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取所有专业
     */
    public static void getSpecialtyList(final NetCallBack netCallBack) {
        Map<String,String> map=new HashMap<>();
        Http.getRetrofit().create(HttpApi.class).getSpecialtyList(map).enqueue(new Callback<FacultyBean>() {
            public void onResponse(Call<FacultyBean> call, Response<FacultyBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<FacultyBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 添加教育经历
     */
    public static void updateEducation(String admissiontime,int education,int facultyid,String grades,int id,int majorid,String region,int sid,int type,final NetCallBack netCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("admissiontime",admissiontime);
        map.put("education",education+"");
        if(facultyid!=0){
            map.put("facultyid",facultyid+"");
        }
        if(!TextUtils.isEmpty(grades)){
            map.put("grades",grades);
        }
        map.put("id",id+"");
        if(majorid!=0){
            map.put("majorid",majorid+"");
        }
        map.put("region",region);
        map.put("sid",sid+"");
        map.put("type",type+"");
        map.put("ftype","1");
        map.put("mtype","1");
        LogUtils.e(admissiontime+"++++++++++"+education+"++++++"+facultyid+"+++++++"+grades+"+++++++"+id+"++++++++"+majorid+"+++++++"+region+"++++++"+sid);

        Http.getRetrofit().create(HttpApi.class).updateEducation(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 修改教育经历
     */
    public static void addEducation(String learningexperiences,final NetCallBack netCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("learningexperiences",learningexperiences);
        Http.getRetrofit().create(HttpApi.class).addEducation(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取学习经历集合
     */
    public static void getEducationList(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getEducationList().enqueue(new Callback<EducationBean>() {
            public void onResponse(Call<EducationBean> call, Response<EducationBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<EducationBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 学生获取申报记录
     */
    public static void getDeclareList(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getDeclareList().enqueue(new Callback<DeclareBean>() {
            public void onResponse(Call<DeclareBean> call, Response<DeclareBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<DeclareBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 学生获取可申报批次
     */
    public static void getBatch(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getBatch().enqueue(new Callback<BatchBean>() {
            public void onResponse(Call<BatchBean> call, Response<BatchBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BatchBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 学生获取可申报批次详情
     */
    public static void getBatchDetailed(int bid,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getBatchDetailed(bid).enqueue(new Callback<BatchDetails>() {
            public void onResponse(Call<BatchDetails> call, Response<BatchDetails> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BatchDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 判断学生是否可以申报（学号）
     */
    public static void checkdeclareno(int bid,String num,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).checkdeclareno(bid,num).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取所有资助批次经济情况
     */
    public static void getEconomicList(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getEconomicList().enqueue(new Callback<EconomicBean>() {
            public void onResponse(Call<EconomicBean> call, Response<EconomicBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<EconomicBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 上传文件
     */
    public static void uploadFile(int relationtype,List<FileBean> list,final NetCallBack netCallBack){
        Map<String,String> map=new HashMap<>();
        map.put("iskdr","0");
        map.put("relationtype",relationtype+"");
        Http.upLoadFile("api/sys/upload", list, map, new okhttp3.Callback() {
            public void onResponse(okhttp3.Call call, okhttp3.Response response){
                DialogUtil.closeProgress();
                try {
                    String str = response.body().string();
                    LogUtils.e("+++++++++++++++++"+str);
                    final UploadFile uploadFile= (UploadFile) JsonUtil.stringToObject(str, UploadFile.class);
                    netCallBack.onSuccess(uploadFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(okhttp3.Call call, IOException e) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(e.getMessage());
            }
        });
    }



    /**
     * 关于我们
     */
    public static void getAbout(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getAbout().enqueue(new Callback<AboutBean>() {
            public void onResponse(Call<AboutBean> call, Response<AboutBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<AboutBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取消息列表
     */
    public static void getNewsList(int ctype,int page,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getNewsList(ctype,page,pageSize).enqueue(new Callback<NewsBean>() {
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<NewsBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取消息列表
     */
    public static void getMyLeave(int page,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getMyLeave(page,pageSize).enqueue(new Callback<LeaveBean>() {
            public void onResponse(Call<LeaveBean> call, Response<LeaveBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<LeaveBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 回复留言
     */
    public static void reply(int id,String content,final NetCallBack netCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("id",id+"");
        map.put("content",content);
        Http.getRetrofit().create(HttpApi.class).reply(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取银行卡基本信息
     */
    public static void getbankinfo(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getbankinfo().enqueue(new Callback<BankBaseBean>() {
            public void onResponse(Call<BankBaseBean> call, Response<BankBaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BankBaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取银行卡历史信息
     */
    public static void getBankHistory(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getBankHistory().enqueue(new Callback<HistoryBankBean>() {
            public void onResponse(Call<HistoryBankBean> call, Response<HistoryBankBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<HistoryBankBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 变更银行卡
     */
    public static void updateBank(String num,List<FileBean> list,final NetCallBack netCallBack){
        Map<String,String> map=new HashMap<>();
        map.put("num",num);
        Http.upLoadFile2("api/user/bk/changebankinfo", list, map, new okhttp3.Callback() {
            public void onResponse(okhttp3.Call call, okhttp3.Response response){
                DialogUtil.closeProgress();
                try {
                    String str = response.body().string();
                    LogUtils.e("+++++++++++++++++"+str);
                    final BaseBean baseBean= (BaseBean) JsonUtil.stringToObject(str, BaseBean.class);
                    netCallBack.onSuccess(baseBean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(okhttp3.Call call, IOException e) {
                DialogUtil.closeProgress();
                ToastUtil.showLong(e.getMessage());
            }
        });
    }



    /**
     * 获得在校情况列表
     */
    public static void getInSchoolList(int page,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getInSchoolList(page,pageSize).enqueue(new Callback<InSchoolBean>() {
            public void onResponse(Call<InSchoolBean> call, Response<InSchoolBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<InSchoolBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 获取问卷列表
     */
    public static void getSurveyList(int page,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getSurveyList(page,pageSize).enqueue(new Callback<SurveyBean>() {
            public void onResponse(Call<SurveyBean> call, Response<SurveyBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<SurveyBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取问卷详情
     */
    public static void getSurveyDetails(int id,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getSurveyDetails(id).enqueue(new Callback<SurveyDetails>() {
            public void onResponse(Call<SurveyDetails> call, Response<SurveyDetails> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<SurveyDetails> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 答题
     */
    public static void solvevoucher(int id,String paramter,final NetCallBack netCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("id",id+"");
        map.put("paramter",paramter);

        Http.getRetrofit().create(HttpApi.class).solvevoucher(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 学生申报批次
     */
    public static void applyDeclare(int bid,String jkids,String idpositive,String idback,String householder,String oneself,String acceptanceletter,String relevantdoc,String num,final NetCallBack netCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("bid",String.valueOf(bid));
        map.put("jkids",jkids);
        if(!TextUtils.isEmpty(idpositive)){
            map.put("idpositive",idpositive);
        }
        if(!TextUtils.isEmpty(idback)){
            map.put("idback",idback);
        }
        if(!TextUtils.isEmpty(householder)){
            map.put("householder",householder);
        }
        if(!TextUtils.isEmpty(oneself)){
            map.put("oneself",oneself);
        }
        if(!TextUtils.isEmpty(acceptanceletter)){
            map.put("acceptanceletter",acceptanceletter);
        }
        if(!TextUtils.isEmpty(relevantdoc)){
            map.put("relevantdoc",relevantdoc);
        }
        if(!TextUtils.isEmpty(num)){
            map.put("num",num);
        }
        LogUtils.e("+++++++++"+JsonUtil.objectToString(map));
        Http.getRetrofit().create(HttpApi.class).applyDeclare(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 学生重新申报批次
     */
    public static void againdeclare(int did,String jkids,String idpositive,String idback,String householder,String oneself,String acceptanceletter,String relevantdoc,final NetCallBack netCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("did",String.valueOf(did));
        map.put("jkids",jkids);
        if(!TextUtils.isEmpty(idpositive)){
            map.put("idpositive",idpositive);
        }
        if(!TextUtils.isEmpty(idback)){
            map.put("idback",idback);
        }
        if(!TextUtils.isEmpty(householder)){
            map.put("householder",householder);
        }
        if(!TextUtils.isEmpty(oneself)){
            map.put("oneself",oneself);
        }
        if(!TextUtils.isEmpty(acceptanceletter)){
            map.put("acceptanceletter",acceptanceletter);
        }
        if(!TextUtils.isEmpty(relevantdoc)){
            map.put("relevantdoc",relevantdoc);
        }
        LogUtils.e("+++++++++"+JsonUtil.objectToString(map));
        Http.getRetrofit().create(HttpApi.class).againdeclare(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 删除教育经历
     */
    public static void deleteEducation(int id,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).deleteEducation(id).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 查询我的简历
     */
    public static void getMyResume(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getMyResume().enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                DialogUtil.closeProgress();
                try {
                    netCallBack.onSuccess(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 提交在校情况说明
     */
    public static void addInSchool(int status,String schoolreport,String descriptionfile, String content,int ruleid,final NetCallBack netCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("status",status+"");
        map.put("schoolreport",schoolreport);
        map.put("descriptionfile",descriptionfile);
        map.put("content",content);
        map.put("ruleid",String.valueOf(ruleid));

        Http.getRetrofit().create(HttpApi.class).addInSchool(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 编辑在校情况说明
     */
    public static void updateInSchool(int did,int status,String schoolreport,String descriptionfile,final String content,final NetCallBack netCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("did",did+"");
        map.put("status",status+"");
        map.put("schoolreport",schoolreport);
        map.put("descriptionfile",descriptionfile);
        map.put("content",content);

        Http.getRetrofit().create(HttpApi.class).updateInSchool(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 我的社团活动数量
     */
    public static void getOwnActivityNum(PageParam pageParam, final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getOwnActivityNum(pageParam).enqueue(new Callback<ActivityNum>() {
            public void onResponse(Call<ActivityNum> call, Response<ActivityNum> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ActivityNum> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 新增或编辑简历信息(简历证书)
     */
    public static void SaveOrUpdateCertificates(ResumeCertificate resumeCertificate, final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).SaveOrUpdateCertificates(resumeCertificate).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * -查询职位类型信息
     */
    public static void getResumePostion(Position position, final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getResumePostion(position).enqueue(new Callback<ResumePostion>() {
            public void onResponse(Call<ResumePostion> call, Response<ResumePostion> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ResumePostion> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 收款信息
     */
    public static void getCollMoneyList(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getCollMoneyList().enqueue(new Callback<CollMoneyBean>() {
            public void onResponse(Call<CollMoneyBean> call, Response<CollMoneyBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<CollMoneyBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 根据type获取字典集合
     */
    public static void getDict(int type,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getDict(type).enqueue(new Callback<DictBean>() {
            public void onResponse(Call<DictBean> call, Response<DictBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<DictBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 新增或编辑简历信息(简历特长)
     */
    public static void saveOrUpdateSpeciality(AddSpecialtyP addSpecialtyP, final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).saveOrUpdateSpeciality(addSpecialtyP).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 编辑简历附件
     */
    public static void uploadResumeFile(UploadResumeFile uploadResumeFile, final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).uploadResumeFile(uploadResumeFile).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 获取银行卡办里进度
     */
    public static void getBankProgress(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getBankProgress().enqueue(new Callback<BankProgress>() {
            public void onResponse(Call<BankProgress> call, Response<BankProgress> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BankProgress> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 验证银行卡
     */
    public static void verBank(String num,final NetCallBack netCallBack) {
        Map<String,String> map=new HashMap<>();
        LogUtils.e(num+"+++++++++++++++num");
        map.put("num",num);
        Http.getRetrofit().create(HttpApi.class).verBank(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 查询投递记录
     */
    public static void getDeliveryRecord(GetDeliveryRecord getDeliveryRecord,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getDeliveryRecord(getDeliveryRecord).enqueue(new Callback<DeliveryBean>() {
            public void onResponse(Call<DeliveryBean> call, Response<DeliveryBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<DeliveryBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 新增或编辑简历信息(在校荣誉)
     */
    public static void saveOrUpdateInSchoolHonor(AddSchoolHonor addSchoolHonor, final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).saveOrUpdateInSchoolHonor(addSchoolHonor).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 新增或编辑简历信息(校内职务)
     */
    public static void saveOrUpdateSchoolDuties(AddSchoolPosition addSchoolPosition, final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).saveOrUpdateSchoolDuties(addSchoolPosition).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 新新增或编辑简历学习经历
     */
    public static void saveOrUpdateLearnings(AddResumeEducation addResumeEducation, final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).saveOrUpdateLearnings(addResumeEducation).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 新增或编辑简历求职意向
     */
    public static void saveOrUpdateJobIdea(JobIntention jobIntention, final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).saveOrUpdateJobIdea(jobIntention).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 新增或编辑简历基本信息
     */
    public static void saveOrUpdateResumePerson(ResumeBase resumeBase, final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).saveOrUpdateResumePerson(resumeBase).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 获取学生申报或查看申报基本信息
     */
    public static void getUserInfoByApply(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getUserInfoByApply().enqueue(new Callback<UserInfo>() {
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<UserInfo> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取留言详细
     */
    public static void getLeaveDetails(int id,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getLeaveDetails(id).enqueue(new Callback<LeaveDetailsBean>() {
            public void onResponse(Call<LeaveDetailsBean> call, Response<LeaveDetailsBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<LeaveDetailsBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取短信验证码
     * type：  0学生注册，  1学生登录   2变更手机号    3忘记密码
     */
    public static void getSmsCode(String code,String phone,String type,final NetCallBack netCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("code",code);
        map.put("phone",phone);
        map.put("type",type);
        Http.getRetrofit().create(HttpApi.class).getSmsCode(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 发送短信(根据身份证号码)
     */
    public static void getSmsCodeByNum(String idnum,String type,final NetCallBack netCallBack) {
        Map<String,String> map=new HashMap<>();
        map.put("idnum",idnum);
        map.put("type",type);
        Http.getRetrofit().create(HttpApi.class).getSmsCodeByNum(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取申报基本信息
     */
    public static void getDeclareDetails(int did,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getDeclareDetails(did).enqueue(new Callback<DeclareDetailsBean>() {
            public void onResponse(Call<DeclareDetailsBean> call, Response<DeclareDetailsBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<DeclareDetailsBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 资金发放明细
     */
    public static void getIssueRecord(int did,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getIssueRecord(did,-1).enqueue(new Callback<IssueRecordBean>() {
            public void onResponse(Call<IssueRecordBean> call, Response<IssueRecordBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<IssueRecordBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取申请记录
     */
    public static void getApplyList(int page,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getApplyList(page,pageSize).enqueue(new Callback<ApplyBean>() {
            public void onResponse(Call<ApplyBean> call, Response<ApplyBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ApplyBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取审核信息
     */
    public static void getAudit(int did,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getAudit(did).enqueue(new Callback<AuditBean>() {
            public void onResponse(Call<AuditBean> call, Response<AuditBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<AuditBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 文件下载
     * @param handler
     */
    public static void download(final DownLoad downLoad, final Handler handler) {
        Http.dowload(downLoad.getDownPath(), downLoad.getSavePath(),handler, new okhttp3.Callback() {
            public void onFailure(okhttp3.Call call, IOException e) {
            }
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if(response.isSuccessful()){
                    sendMessage(handler, HandlerConstant.DOWNLOAD_SUCCESS, downLoad);
                }
            }
        });
    }


    /**
     * 获取在校情况说明模板
     */
    public static void getSchoolTemplete(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getSchoolTemplete().enqueue(new Callback<TempleteBean>() {
            public void onResponse(Call<TempleteBean> call, Response<TempleteBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<TempleteBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 获取Banner列表信息
     * 所在页面 0 首页banner 1 交流合作banner 2 爱心社banner 可按顺序增加
     */
    public static void getBanner(int pageindex,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getBanner(pageindex).enqueue(new Callback<BannerBean>() {
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BannerBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取网站新闻标题列表
     * 网站导航类型 201 机构文化 202 捐赠项目 203 合作交流 204 燕宝人 205 基金会动态
     */
    public static void getNewsTitle(int ctype,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getNewsTitle(ctype).enqueue(new Callback<NewsTitleBean>() {
            public void onResponse(Call<NewsTitleBean> call, Response<NewsTitleBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<NewsTitleBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取网站新闻详细
     */
    public static void getNewsDetails(int id,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getNewsDetails(id).enqueue(new Callback<NewsDetailsBean>() {
            public void onResponse(Call<NewsDetailsBean> call, Response<NewsDetailsBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<NewsDetailsBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取网站项目捐赠列表
     */
    public static void getDonation(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getDonation().enqueue(new Callback<DonationBean>() {
            public void onResponse(Call<DonationBean> call, Response<DonationBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<DonationBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取网站新闻列表
     */
    public static void getNewsList(int cid,int ctype,int page,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getNewsList(cid,ctype,page,pageSize).enqueue(new Callback<NewsListBean>() {
            public void onResponse(Call<NewsListBean> call, Response<NewsListBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<NewsListBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取网站新闻单页
     */
    public static void getNewsSingle(int cid,int ctype,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getNewsSingle(cid,ctype).enqueue(new Callback<NewsSingle>() {
            public void onResponse(Call<NewsSingle> call, Response<NewsSingle> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<NewsSingle> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取网站首页新闻列表
     */
    public static void getMainNews(int ctype,int top,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getMainNews(ctype,top).enqueue(new Callback<NewsListBean>() {
            public void onResponse(Call<NewsListBean> call, Response<NewsListBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<NewsListBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 查看在校情况提交的详情
     */
    public static void getInSchoolDetails(int did,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getInSchoolDetails(did).enqueue(new Callback<InSchoolDetailsBean>() {
            public void onResponse(Call<InSchoolDetailsBean> call, Response<InSchoolDetailsBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<InSchoolDetailsBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }



    /**
     * 财务记录明细信息头部
     */
    public static void getReceivableshead(int fid,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getReceivableshead(fid).enqueue(new Callback<ReceivablesheadBean>() {
            public void onResponse(Call<ReceivablesheadBean> call, Response<ReceivablesheadBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ReceivablesheadBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取申请补发模板
     */
    public static void getReissueTemplate(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getReissueTemplate().enqueue(new Callback<TempleteBean>() {
            public void onResponse(Call<TempleteBean> call, Response<TempleteBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<TempleteBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取申请退还奖学金模板
     */
    public static void getReturnTemplate(final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getReturnTemplate().enqueue(new Callback<TempleteBean>() {
            public void onResponse(Call<TempleteBean> call, Response<TempleteBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<TempleteBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 变更手机号
     */
    public static void updatePhone(String code,String ecode,String phone,final NetCallBack netCallBack) {
        Map<String ,String> map=new HashMap<>();
        map.put("code",code);
        map.put("ecode",ecode);
        map.put("phone",phone);
        Http.getRetrofit().create(HttpApi.class).updatePhone(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 财务补发
     */
    public static void financialreissue(int fid,List<FileBean> list,String remarks,final NetCallBack netCallBack){
        Map<String,String> map=new HashMap<>();
        map.put("fid",String.valueOf(fid));
        if(!TextUtils.isEmpty(remarks)){
            map.put("remarks",remarks);
        }
        map.put("type","1");
        Http.upLoadFile("api/sys/applyrecord/financialreissue", list, map, new okhttp3.Callback() {
            public void onResponse(okhttp3.Call call, okhttp3.Response response){
                DialogUtil.closeProgress();
                try {
                    String str = response.body().string();
                    LogUtils.e("+++++++++++++++++"+str);
                    final BaseBean baseBean= (BaseBean) JsonUtil.stringToObject(str, BaseBean.class);
                    netCallBack.onSuccess(baseBean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(okhttp3.Call call, IOException e) {
                DialogUtil.closeProgress();
                netCallBack.onFail();
            }
        });
    }


    /**
     * 申请退还奖学金
     */
    public static void applyreturn(int fid,List<FileBean> list,String remarks,final NetCallBack netCallBack){
        Map<String,String> map=new HashMap<>();
        map.put("fid",String.valueOf(fid));
        if(!TextUtils.isEmpty(remarks)){
            map.put("remarks",remarks);
        }
        Http.upLoadFile("api/sys/applyrecord/applyreturn", list, map, new okhttp3.Callback() {
            public void onResponse(okhttp3.Call call, okhttp3.Response response){
                DialogUtil.closeProgress();
                try {
                    String str = response.body().string();
                    LogUtils.e("+++++++++++++++++"+str);
                    final BaseBean baseBean= (BaseBean) JsonUtil.stringToObject(str, BaseBean.class);
                    netCallBack.onSuccess(baseBean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(okhttp3.Call call, IOException e) {
                DialogUtil.closeProgress();
                netCallBack.onFail();
            }
        });
    }


    /**
     * 修改密码
     */
    public static void updatePwd(String pwd,String rpwd,String ypwd,final NetCallBack netCallBack) {
        Map<String ,String> map=new HashMap<>();
        map.put("pwd",pwd);
        map.put("rpwd",rpwd);
        map.put("ypwd",ypwd);
        Http.getRetrofit().create(HttpApi.class).updatePwd(map).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 意见反馈
     */
    public static void addFeedBack(String content,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).addFeedBack(content).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 获取申请记录详情（财务或项目补发用）
     */
    public static void getReissueAudit(int id,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).getReissueAudit(id).enqueue(new Callback<ReissueAuditBean>() {
            public void onResponse(Call<ReissueAuditBean> call, Response<ReissueAuditBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<ReissueAuditBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }


    /**
     * 学生登录验证短信验证码
     */
    public static void loginByCode(String code,String phone,final NetCallBack netCallBack) {
        Http.getRetrofit().create(HttpApi.class).loginByCode(code,phone).enqueue(new Callback<BaseBean>() {
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                DialogUtil.closeProgress();
                netCallBack.onSuccess(response.body());
            }
            public void onFailure(Call<BaseBean> call, Throwable t) {
                DialogUtil.closeProgress();
                ToastUtil.showLong("网络异常，请检查网络后重试");
            }
        });
    }
}
