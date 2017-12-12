package com.ad_srtarevolution.belajar_api.Api;

import com.ad_srtarevolution.belajar_api.Model.ModelData;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

        @GET("lihat_data_semua.php")
        Call<List<ModelData>> getSemuaData();

        @GET("lihat_single_data.php")
        Call<List<ModelData>> getSingleData(@Query("id")String id);

        @FormUrlEncoded
        @POST("hapus_data.php")
        Call<ResponseBody> hapusData(@Field("id_mhs")String id_mhs);

        @FormUrlEncoded
        @POST("edit_data.php")
        Call<ResponseBody> editData(@Field("id_mhs") String id, @Field("nama_mhs")String nama, @Field("kelas_mhs")String kelas);
}
