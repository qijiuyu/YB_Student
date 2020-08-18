package com.zxdc.utils.library.http;



import com.zxdc.utils.library.bean.BaseBean;
import com.zxdc.utils.library.bean.ForgetPwd;
import com.zxdc.utils.library.bean.ProvinceBean;

import java.util.Map;

import retrofit2.Call;
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
    Call<BaseBean> getbaseinfo();

    @FormUrlEncoded
    @PUT(HttpConstant.IP+"api/user/login/findpwd")
    Call<BaseBean> findpwd(@FieldMap Map<String, String> map);


}
