package com.zxdc.utils.library.http;



import com.zxdc.utils.library.bean.AboutBean;
import com.zxdc.utils.library.bean.BankBaseBean;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.BatchBean;
import com.zxdc.utils.library.bean.BatchDetails;
import com.zxdc.utils.library.bean.DeclareBean;
import com.zxdc.utils.library.bean.EconomicBean;
import com.zxdc.utils.library.bean.EducationBean;
import com.zxdc.utils.library.bean.FacultyBean;
import com.zxdc.utils.library.bean.FamilyBean;
import com.zxdc.utils.library.bean.ForgetPwd;
import com.zxdc.utils.library.bean.InSchoolBean;
import com.zxdc.utils.library.bean.LeaveBean;
import com.zxdc.utils.library.bean.NewsBean;
import com.zxdc.utils.library.bean.ProvinceBean;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.bean.SchoolBean;
import com.zxdc.utils.library.bean.SurveyBean;
import com.zxdc.utils.library.bean.SurveyDetails;
import com.zxdc.utils.library.bean.UserInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface HttpApi {

    @GET(HttpConstant.IP+"api/sys/region/getPAllList")
    Call<ProvinceBean> getProvince();

    @GET(HttpConstant.IP+"api/sys/region/getCList")
    Call<ProvinceBean> getCityByProvince(@Query("code") String code);

    @GET(HttpConstant.IP+"api/sys/region/getAList")
    Call<ProvinceBean> getAreaByCity(@Query("code") String code);

    @FormUrlEncoded
    @POST(HttpConstant.IP+"api/user/student/second")
    Call<BaseBean> saveUser(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @PUT(HttpConstant.IP+"api/user/student/three")
    Call<BaseBean> bindingEmail(@FieldMap Map<String, String> map);

    @GET(HttpConstant.IP+"api/user/login/xlogin")
    Call<BaseBean> login(@Query("name") String name, @Query("pwd") String pwd);

    @GET(HttpConstant.IP+"api/user/login/findfirstpwd")
    Call<ForgetPwd> findfirstpwd(@Query("idnum") String idnum);

    @FormUrlEncoded
    @PUT(HttpConstant.IP+"api/sys/email/sendbindemail")
    Call<BaseBean> sendbindemail(@FieldMap Map<String, String> map);

    @GET(HttpConstant.IP+"api/user/student/getbaseinfo")
    Call<UserInfo> getbaseinfo();

    @FormUrlEncoded
    @PUT(HttpConstant.IP+"api/user/login/findpwd")
    Call<BaseBean> findpwd(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.IP+"api/user/fm/add")
    Call<BaseBean> addFamily(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @PUT(HttpConstant.IP+"api/user/student/savebaseinfo")
    Call<BaseBean> updateUserInfo(@FieldMap Map<String, String> map);

    @GET(HttpConstant.IP+"api/user/fm/getList")
    Call<FamilyBean> getFamilyList();

    @DELETE(HttpConstant.IP+"api/user/fm/delete")
    Call<BaseBean> deleteFamily(@Query("id") int id);

    @FormUrlEncoded
    @PUT(HttpConstant.IP+"api/user/fm/update")
    Call<BaseBean> updateFamily(@FieldMap Map<String, String> map);

    @GET(HttpConstant.IP+"api/sys/school/getInAllList")
    Call<SchoolBean> getSchoolList();

    @FormUrlEncoded
    @POST(HttpConstant.IP+"api/sys/faculty/getalllist")
    Call<FacultyBean> getFacultyList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.IP+"api/sys/major/getalllist")
    Call<FacultyBean> getSpecialtyList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(HttpConstant.IP+"api/user/le/add")
    Call<BaseBean> addEducation(@FieldMap Map<String, String> map);

    @GET(HttpConstant.IP+"api/user/le/getList")
    Call<EducationBean> getEducationList();

    @GET(HttpConstant.IP+"api/sys/declare/getBatchHistory")
    Call<DeclareBean> getDeclareList();

    @GET(HttpConstant.IP+"api/sys/declare/getBatch")
    Call<BatchBean> getBatch();

    @GET(HttpConstant.IP+"api/sys/declare/getBatchDetailed")
    Call<BatchDetails> getBatchDetailed(@Query("bid") int bid);

    @GET(HttpConstant.IP+"api/sys/declare/checkdeclare")
    Call<BaseBean> checkdeclareno(@Query("bid") int bid,@Query("num") String num);

    @GET(HttpConstant.IP+"api/sys/economic/getAllList")
    Call<EconomicBean> getEconomicList();

    @GET(HttpConstant.IP+"api/news/about/getabout")
    Call<AboutBean> getAbout();

    @GET(HttpConstant.IP+"api/sys/user/msg/getlist")
    Call<NewsBean> getNewsList(@Query("ctype") int ctype, @Query("page") int page, @Query("size") int size);

    @GET(HttpConstant.IP+"api/sys/msg/lm/getslist")
    Call<LeaveBean> getMyLeave(@Query("page") int page, @Query("size") int size);

    @FormUrlEncoded
    @POST(HttpConstant.IP+"api/sys/msg/lm/reply")
    Call<BaseBean> reply(@FieldMap Map<String, String> map);

    @GET(HttpConstant.IP+"api/user/bk/getbankinfo")
    Call<BankBaseBean> getbankinfo();

    @GET(HttpConstant.IP+"api/user/bk/getbankhistoryinfos")
    Call<BankBaseBean> getBankHistory();

    @GET(HttpConstant.IP+"api/sys/schoolsituation/getschoolsituationlist")
    Call<InSchoolBean> getInSchoolList(@Query("page") int page, @Query("size") int size);

    @GET(HttpConstant.IP+"api/news/question/voucherlist")
    Call<SurveyBean> getSurveyList(@Query("page") int page, @Query("size") int size);

    @GET(HttpConstant.IP+"api/news/question/voucherdetail")
    Call<SurveyDetails> getSurveyDetails(@Query("id") int id);

    @FormUrlEncoded
    @POST(HttpConstant.IP+"api/news/question/solvevoucher")
    Call<BaseBean> solvevoucher(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @PUT(HttpConstant.IP+"api/sys/declare/declare")
    Call<BaseBean> applyDeclare(@FieldMap Map<String, String> map);

    @DELETE(HttpConstant.IP+"api/user/le/delete")
    Call<BaseBean> deleteEducation(@Query("id") int id);

    @GET(HttpConstant.IP+"api/syn/resumeInfo/getResumeInfoByToken")
    Call<ResumeBean> getMyResume();

    @FormUrlEncoded
    @POST(HttpConstant.IP+"api/sys/schoolsituation/updstudentapply")
    Call<BaseBean> updateInSchool(@FieldMap Map<String, String> map);

}
