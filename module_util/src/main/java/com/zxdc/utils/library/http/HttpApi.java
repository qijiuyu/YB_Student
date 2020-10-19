package com.zxdc.utils.library.http;


import com.zxdc.utils.library.bean.AboutBean;
import com.zxdc.utils.library.bean.ActivityNum;
import com.zxdc.utils.library.bean.ApplyBean;
import com.zxdc.utils.library.bean.AuditBean;
import com.zxdc.utils.library.bean.BankBaseBean;
import com.zxdc.utils.library.bean.BankProgress;
import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.BatchBean;
import com.zxdc.utils.library.bean.BatchDetails;
import com.zxdc.utils.library.bean.CollMoneyBean;
import com.zxdc.utils.library.bean.DeclareBean;
import com.zxdc.utils.library.bean.DeclareDetailsBean;
import com.zxdc.utils.library.bean.DeliveryBean;
import com.zxdc.utils.library.bean.DictBean;
import com.zxdc.utils.library.bean.EconomicBean;
import com.zxdc.utils.library.bean.EducationBean;
import com.zxdc.utils.library.bean.FacultyBean;
import com.zxdc.utils.library.bean.FamilyBean;
import com.zxdc.utils.library.bean.ForgetPwd;
import com.zxdc.utils.library.bean.HistoryBankBean;
import com.zxdc.utils.library.bean.InSchoolBean;
import com.zxdc.utils.library.bean.IssueRecordBean;
import com.zxdc.utils.library.bean.LeaveBean;
import com.zxdc.utils.library.bean.LeaveDetailsBean;
import com.zxdc.utils.library.bean.NewsBean;
import com.zxdc.utils.library.bean.PageParam;
import com.zxdc.utils.library.bean.ProvinceBean;
import com.zxdc.utils.library.bean.ResumeBean;
import com.zxdc.utils.library.bean.ResumePostion;
import com.zxdc.utils.library.bean.SchoolBean;
import com.zxdc.utils.library.bean.SurveyBean;
import com.zxdc.utils.library.bean.SurveyDetails;
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
import com.zxdc.utils.library.util.SPUtil;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
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

    @FormUrlEncoded
    @PUT(HttpConstant.IP+"api/user/le/update")
    Call<BaseBean> updateEducation(@FieldMap Map<String, String> map);

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
    Call<HistoryBankBean> getBankHistory();

    @GET(HttpConstant.IP+"api/sys/schoolsituation/getschoolsituationlist")
    Call<InSchoolBean> getInSchoolList(@Query("page") int page, @Query("size") int size);

    @GET(HttpConstant.IP+"api/news/question/getvoucherlist")
    Call<SurveyBean> getSurveyList(@Query("page") int page, @Query("size") int size);

    @GET(HttpConstant.IP+"api/news/question/getvoucherdetail")
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

    @GET(HttpConstant.IP+"api/sys/student/finace/receivablesinfo")
    Call<CollMoneyBean> getCollMoneyList();

    @GET(HttpConstant.IP+"api/sys/dic/getTypeAllList")
    Call<DictBean> getDict(@Query("type") int type);

    @GET(HttpConstant.IP+"api/user/bk/getbankspeedinfo")
    Call<BankProgress> getBankProgress();

    @POST(HttpConstant.IP+"api/syn/resumeInfo/saveOrUpdateSpeciality")
    Call<BaseBean> saveOrUpdateSpeciality(@Body AddSpecialtyP addSpecialtyP);

    @POST(HttpConstant.IP+"api/syn/positionType/findPostionTypeByPage")
    Call<ResumePostion> getResumePostion(@Body Position position);

    @POST(HttpConstant.IP+"api/syn/resumeInfo/saveOrUpdateCertificates")
    Call<BaseBean> SaveOrUpdateCertificates(@Body ResumeCertificate resumeCertificate);

    @POST(HttpConstant.IP+"api/syn/publicWelfare/getOwnActivityNum")
    Call<ActivityNum> getOwnActivityNum(@Body PageParam pageParam);

    @FormUrlEncoded
    @PUT(HttpConstant.IP+"api/user/bk/verification")
    Call<BaseBean> verBank(@FieldMap Map<String, String> map);

    @POST(HttpConstant.IP+"api/syn/positionInfo/findApplyPositionInfoByPage")
    Call<DeliveryBean> getDeliveryRecord(@Body GetDeliveryRecord getDeliveryRecord);

    @POST(HttpConstant.IP+"api/syn/resumeInfo/saveOrUpdateInSchoolHonor")
    Call<BaseBean> saveOrUpdateInSchoolHonor(@Body AddSchoolHonor addSchoolHonor);

    @POST(HttpConstant.IP+"api/syn/resumeInfo/saveOrUpdateSchoolDuties")
    Call<BaseBean> saveOrUpdateSchoolDuties(@Body AddSchoolPosition addSchoolPosition);

    @POST(HttpConstant.IP+"api/syn/resumeInfo/saveOrUpdateLearnings")
    Call<BaseBean> saveOrUpdateLearnings(@Body AddResumeEducation addResumeEducation);

    @POST(HttpConstant.IP+"api/syn/resumeInfo/saveOrUpdateJobIdea")
    Call<BaseBean> saveOrUpdateJobIdea(@Body JobIntention jobIntention);

    @POST(HttpConstant.IP+"api/syn/resumeInfo/saveOrUpdateResumePerson")
    Call<BaseBean> saveOrUpdateResumePerson(@Body ResumeBase resumeBase);

    @GET(HttpConstant.IP+"api/user/student/getdeclarebaseinfo")
    Call<UserInfo> getUserInfoByApply();

    @GET(HttpConstant.IP+"api/sys/msg/lm/getdetailed")
    Call<LeaveDetailsBean> getLeaveDetails(@Query("id") int id);

    @FormUrlEncoded
    @POST(HttpConstant.IP+"api/sys/sms/send")
    Call<BaseBean> getSmsCode(@FieldMap Map<String, String> map);

    @GET(HttpConstant.IP+"api/sys/declare/pub/getdbaseinfo")
    Call<DeclareDetailsBean> getDeclareDetails(@Query("did") int did);

    @GET(HttpConstant.IP+"api/sys/student/finace/financialrecords")
    Call<IssueRecordBean> getIssueRecord(@Query("bid") int bid);

    @GET(HttpConstant.IP+"ybsysserver/api/sys/applyrecord/list")
    Call<ApplyBean> getApplyList(@Query("page") int page, @Query("size") int size);

    @GET(HttpConstant.IP+"api/sys/declare/pub/gettjinfo")
    Call<AuditBean> getAudit(@Query("did") int did);


}
