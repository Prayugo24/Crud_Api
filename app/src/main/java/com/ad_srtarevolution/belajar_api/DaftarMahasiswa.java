package com.ad_srtarevolution.belajar_api;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ad_srtarevolution.belajar_api.Api.ApiService;
import com.ad_srtarevolution.belajar_api.Model.ModelData;
import  com.ad_srtarevolution.belajar_api.Adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DaftarMahasiswa extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<ModelData> datamahasiswa=new ArrayList<ModelData>();
    ListView listview;
    ListAdapter adapter;

    LinearLayout layout_loading;
    TextView text_load;
    ImageView icon_load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_mahasiswa);

        layout_loading=(LinearLayout)findViewById(R.id.layout_loading);
        text_load=(TextView)findViewById(R.id.text_load);
        icon_load=(ImageView)findViewById(R.id.icon_load);

        listview=(ListView)findViewById(R.id.listMhs);
        listview.setOnItemClickListener((AdapterView.OnItemClickListener) DaftarMahasiswa.this);
        listview.setDividerHeight(0);
        setup();


    }

    public void setup(){
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(MainActivity.ROOt_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service=retrofit.create(ApiService.class);
        Call<List<ModelData>> call=service.getSemuaData();

        call.enqueue(new Callback<List<ModelData>>() {
            @Override
            public void onResponse(Call<List<ModelData>> call, Response<List<ModelData>> response) {

                datamahasiswa.clear();
                if (response.isSuccessful()){
                    int jumlah= response.body().size();

                    for (int i=0;i <jumlah;i++){
                        ModelData data =new ModelData(
                                response.body().get(i).getIdMahasiswa(),
                                response.body().get(i).getNamaMahasiswa(),
                                response.body().get(i).getKelasMahasiswa());
                        datamahasiswa.add(data);
                        Log.d("Respon","onResponse : " +response.body().get(i).getIdMahasiswa());
                    }
                    listview.setVisibility(View.VISIBLE);
                    adapter =new ListAdapter(DaftarMahasiswa.this,R.layout.row_mahasiswa,datamahasiswa);
                    listview.setAdapter(adapter);

                    if (adapter.getCount()<1){
                        layout_loading.setVisibility(View.VISIBLE);
                        String error="daftar mahasiswa kosong";
                        text_load.setText(error);
                        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.ic_data_kosong);
                        icon_load.setImageBitmap(icon);
                    }else {
                        layout_loading.setVisibility(View.GONE);
                    }
                }else {
                    String error="Error Retrive data form server !!!";
                    text_load.setText(error);
                    Bitmap icon=BitmapFactory.decodeResource(getResources(),R.drawable.ic_network);
                    icon_load.setImageBitmap(icon);
                }

            }

            @Override
            public void onFailure(Call<List<ModelData>> call, Throwable t) {
                String error="Error Retrive Data from Server "+t.getMessage();
                text_load.setText(error);
                Bitmap icon=BitmapFactory.decodeResource(getResources(),R.drawable.ic_network);
                icon_load.setImageBitmap(icon);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            adapter.clear();
            setup();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        TextView ids=(TextView) view.findViewById(R.id.listID2);
        Intent intent=new Intent(DaftarMahasiswa.this,MainEdit.class);
        intent.putExtra(ModelData.id_mahasiswa,ids.getText().toString());
        startActivityForResult(intent,1);
    }
}
