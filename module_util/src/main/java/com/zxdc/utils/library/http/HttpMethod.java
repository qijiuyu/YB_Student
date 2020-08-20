package com.zxdc.utils.library.http;

import com.zxdc.utils.library.bean.AboutBean;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.BatchBean;
import com.zxdc.utils.library.bean.BatchDetails;
import com.zxdc.utils.library.bean.DeclareBean;
import com.zxdc.utils.library.bean.EconomicBean;
import com.zxdc.utils.library.bean.FacultyBean;
import com.zxdc.utils.library.bean.FamilyBean;
import com.zxdc.utils.library.bean.FileBean;
import com.zxdc.utils.library.bean.ForgetPwd;
import com.zxdc.utils.library.bean.InSchoolBean;
import com.zxdc.utils.library.bean.LeaveBean;
import com.zxdc.utils.library.bean.NetCallBack;
import com.zxdc.utils.library.bean.NewsBean;
import com.zxdc.utils.library.bean.ProvinceBean;
import com.zxdc.utils.library.bean.Register;
import com.zxdc.utils.library.bean.SchoolBean;
import com.zxdc.utils.library.bean.SurveyBean;
import com.zxdc.utils.library.bean.UploadFile;
import com.zxdc.utils.library.bean.UserInfo;
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
    public static void updateUserInfo(String ucphone,String address,String qq,String residenceaddress,String uctel,String wechat,final NetCallBack netCallBack) {
        Map<String ,String> map=new HashMap<>();
        map.put("ucphone",ucphone);
        map.put("address",address);
        map.put("qq",qq);
        map.put("residenceaddress",residenceaddress);
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
        Http.getRetrofit().create(HttpApi.class).getEducationList().enqueue(new Callback<BaseBean>() {
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
        Http.getRetrofit().create(HttpApi.class).getbankinfo().enqueue(new Callback<BaseBean>() {
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
        Http.getRetrofit().create(HttpApi.class).getSurveyDetails(id).enqueue(new Callback<SurveyBean>() {
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


}
