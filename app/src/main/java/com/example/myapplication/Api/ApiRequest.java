package com.example.myapplication.Api;

import com.example.myapplication.Model.ResponseAnakModel;
import com.example.myapplication.Model.ResponseApiModel;
import com.example.myapplication.Model.ResponseBiodataModel;
import com.example.myapplication.Model.ResponseDelete;
import com.example.myapplication.Model.ResponseImageModel;
import com.example.myapplication.Model.ResponseLimitModel;
import com.example.myapplication.Model.ResponseModel;
import com.example.myapplication.Model.ResponseOrtuModel;
import com.example.myapplication.Model.ResponseReadModel;
import com.example.myapplication.Model.ResponseSekolahModel;
import com.example.myapplication.Model.ResponseUpdateBiodataModel;
import com.example.myapplication.Model.ResponseUpdateModel;
import com.example.myapplication.Model.ResponseUpdatepinModel;
import com.example.myapplication.ResponseeModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("masuk.php")
    Call<ResponseApiModel> login (@Field("telp_ortu") String username,
                                  @Field("password_login") String password);
    @FormUrlEncoded
    @POST("registrasi.php")
    Call<ResponseModel> sendBiodata(@Field("id_customer") String id_customer,
                                    @Field("nama_customer") String nama_customer,
                                    @Field("jk_customer") String jk_customer,
                                    @Field("id_sekolah") String id_sekolah,
                                    @Field("kelas") String kelas,
                                    @Field("kode_template") String kode_template,
                                    @Field("nama_ortu") String nama_ortu,
                                    @Field("jk_ortu") String jk_ortu,
                                    @Field("telp_ortu") String telp_ortu,
                                    @Field("email_ortu") String email_ortu,
                                    @Field("password_login") String password_login,
                                    @Field("c_pass") String c_pass);
    @FormUrlEncoded
    @POST("readbiodata.php")
    Call<ResponseBiodataModel> readBiodata (@Field("id_keluarga") String id_keluarga);
    @FormUrlEncoded
    @POST("updatebiodata.php")
    Call<ResponseUpdateBiodataModel> sendUpdateBiodata(@Field("id_keluarga") String id_keluarga,
                                                       @Field("telp_ortu") String telp_ortu,
                                                       @Field("telp_alternatif") String telp_alternatif,
                                                       @Field("email_ortu") String email_ortu);
    @FormUrlEncoded
    @POST("readua.php")
    Call<ResponseReadModel> getRead(@Field("id_keluarga") String id_keluarga);
    @FormUrlEncoded
    @POST("topup.php")
    Call<ResponseReadModel> sendTopup(@Field("id_topup") String id_topup,
                                      @Field("id_customer") String id_customer,
                                      @Field("total") String total,
                                      @Field("unik") String unik);

//    @Multipart
//    @POST("uploadimage.php")
//    Call<ResponseImageModel> postImage(@Part MultipartBody.Part image, @Part("name") RequestBody name, @Part("id_topup") RequestBody id_topup);
    @FormUrlEncoded
    @POST("uploadimagelo.php")
    Call<ResponseImageModel> uploadImage(@Field("id_topup") String title,
                                         @Field("image") String image);
    @FormUrlEncoded
    @POST("read.php")
    Call<ResponseReadModel> getRiwayat(@Field("id_customer") String id_customer);
    @FormUrlEncoded
    @POST("readselesai.php")
    Call<ResponseReadModel> getRiwayatA(@Field("id_customer") String id_customer);
    @FormUrlEncoded
    @POST("limit.php")
    Call<ResponseLimitModel> updateLimit(@Field("id_customer") String id_customer,
                                         @Field("limit_kartu") String limit_kartu);
    @FormUrlEncoded
    @POST("updatepass.php")
    Call<ResponseUpdateModel> sendUpdate(@Field("id_keluarga") String id_keluarga,
                                         @Field("password_login") String password_login,
                                         @Field("old_pass") String old_pass,
                                         @Field("new_pass") String new_pass,
                                         @Field("c_pass") String c_pass);
    @FormUrlEncoded
    @POST("updatepin.php")
    Call<ResponseUpdatepinModel> sendUpdatepin(@Field("id_customer") String id_customer,
                                               @Field("kode_template") String kode_template,
                                               @Field("old_pin") String old_pin,
                                               @Field("new_pin") String new_pin,
                                               @Field("c_pin") String c_pin);
//
//    @GET("bacaadapter.php")
//    Call<ResponseeModel> getBaca();

    @FormUrlEncoded
    @POST("bacaadapter.php")
    Call<ResponseeModel> getBaca(@Field("id_keluarga") String id_keluarga);
    @FormUrlEncoded
    @POST("daftaranak.php")
    Call<ResponseAnakModel> sendAnak(@Field("id_keluarga") String id_keluarga,
                                     @Field("nama_customer") String nama_customer,
                                     @Field("jk_customer") String jk_customer,
                                     @Field("id_sekolah") String id_sekolah,
                                     @Field("kelas") String kelas,
                                     @Field("kode_template") String kode_template,
                                     @Field("c_pass") String c_pass);
    @FormUrlEncoded
    @POST("ceksekolah.php")
    Call<ResponseSekolahModel> getCek(@Field("id_sekolah") String id_sekolah);

    @FormUrlEncoded
    @POST("tambahortu.php")
    Call<ResponseOrtuModel> sendOrtu(@Field("nama_ortu") String nama_ortu,
                                     @Field("telp_ortu") String telp_ortu,
                                     @Field("email_ortu") String email_ortu,
                                     @Field("alamat") String alamat,
                                     @Field("ktp") String ktp,
                                     @Field("password_login") String password_login);

    @FormUrlEncoded
    @POST("deletetopup.php")
    Call<ResponseDelete> delete(@Field("id_topup") String id_topup);
}
