package com.zxdc.utils.library.http;



import com.zxdc.utils.library.bean.ProvinceBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HttpApi {

    @GET(HttpConstant.IP+"api/sys/region/getPAllList")
    Call<ProvinceBean> getProvince();

    @GET(HttpConstant.IP+"api/sys/region/getCList")
    Call<ProvinceBean> getCityByProvince(@Query("code") String code);

    @GET(HttpConstant.IP+"api/sys/region/getAList")
    Call<ProvinceBean> getAreaByCity(@Query("code") String code);

}
