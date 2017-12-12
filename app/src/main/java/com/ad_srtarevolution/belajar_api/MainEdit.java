package com.ad_srtarevolution.belajar_api;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ad_srtarevolution.belajar_api.Api.ApiService;
import com.ad_srtarevolution.belajar_api.Model.ModelData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 17/09/2017.
 */

public class MainEdit extends AppCompatActivity {

    String ID_MAHASISWA;
    EditText et_id,et_nama,et_kelas;
    Button bt_ubah,bt_hapus,bt_batal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actifity_form);

        ID_MAHASISWA=getIntent().getStringExtra(ModelData.id_mahasiswa);

        et_id=(EditText)findViewById(R.id.id_form);
        et_nama=(EditText)findViewById(R.id.nama_form);
        et_kelas=(EditText)findViewById(R.id.kelas_form);
        bt_ubah=(Button)findViewById(R.id.bt_Ubah);
        bt_hapus=(Button)findViewById(R.id.bt_Hapus);
        bt_batal=(Button)findViewById(R.id.bt_Batal);

        binData();
        bt_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Sid=String.valueOf(et_id.getText());
                String Snama=String.valueOf(et_nama.getText());
                String Skelas=String.valueOf(et_kelas.getText());
                if(Sid.equals("")){
                    Toast.makeText(MainEdit.this,"jangan rubah id",Toast.LENGTH_SHORT).show();
                }else if(Snama.equals("")){
                    Toast.makeText(MainEdit.this,"nama tidak boleh kososng",Toast.LENGTH_SHORT).show();
                }else if(Skelas.equals("")){
                    Toast.makeText(MainEdit.this,"kelas tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }else{
                    editData(Sid,Snama,Skelas);
                }

            }
        });

        bt_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hapusData(ID_MAHASISWA);
            }
        });
        bt_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void binData(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(MainActivity.ROOt_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //et_id.setText(ID_MAHASISWA);
        ApiService service=retrofit.create(ApiService.class);

        Call<List<ModelData>> call=service.getSingleData(ID_MAHASISWA);
        call.enqueue(new Callback<List<ModelData>>() {
            @Override
            public void onResponse(Call<List<ModelData>> call, Response<List<ModelData>> response) {
                if(response.isSuccessful()){
                    for (int i=0; i<response.body().size();i++){
                        et_id.setText(response.body().get(i).getIdMahasiswa());
                        et_nama.setText(response.body().get(i).getNamaMahasiswa());
                        et_kelas.setText(response.body().get(i).getKelasMahasiswa());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ModelData>> call, Throwable t) {

            }
        });
    }
    public void editData(String id,String nama,String kelas){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(MainActivity.ROOt_URL)
                .addConverterFactory(new StringConverter())
                .build();

        ApiService service=retrofit.create(ApiService.class);

        Call<ResponseBody> call=service.editData(id,nama,kelas);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                BufferedReader reader=null;
                String respon="";

                try{
                    reader=new BufferedReader(new InputStreamReader(response.body().byteStream()));
                    respon=reader.readLine();
                }catch (IOException e){
                    e.printStackTrace();
                }

                Toast.makeText(MainEdit.this,respon,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void hapusData(String id){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(MainActivity.ROOt_URL)
                .addConverterFactory(new StringConverter())
                .build();

        ApiService service=retrofit.create(ApiService.class);

        Call<ResponseBody> call=service.hapusData(id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                BufferedReader reader=null;
                String respon="";

                try{
                    reader=new BufferedReader(new InputStreamReader(response.body().byteStream()));
                    respon=reader.readLine();
                }catch (IOException e){
                    e.printStackTrace();
                }

                Toast.makeText(MainEdit.this,respon,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
