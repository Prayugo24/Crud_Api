package com.ad_srtarevolution.belajar_api.Adapter;



import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ad_srtarevolution.belajar_api.Model.ModelData;
import com.ad_srtarevolution.belajar_api.R;
import android.content.Context;


import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 14/09/2017.
 */

public class ListAdapter extends ArrayAdapter<ModelData> {

    private ArrayList<ModelData> list;
    private LayoutInflater inflater;
    private int res;

    public ListAdapter(Context context,int resources,ArrayList<ModelData>list){
        super (context,resources,list);
        this.list=list;
        this.inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.res=resources;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        MyHolder holder=null;

        if (convertView==null){
            convertView=inflater.inflate(res,parent,false);
            holder=new MyHolder();

            holder.ID=(TextView)convertView.findViewById(R.id.listID1);
            holder.IdPush=(TextView)convertView.findViewById(R.id.listID2);
            holder.Nama=(TextView)convertView.findViewById(R.id.listNamaMhs);
            holder.jenis=(TextView)convertView.findViewById(R.id.listJnsMhs);
            convertView.setTag(holder);
        }else {
            holder = (MyHolder) convertView.getTag();
        }
        holder.ID.setText("Id Mahasiswa : "+list.get(position).getIdMahasiswa());
        holder.IdPush.setText(list.get(position).getIdMahasiswa());
        holder.Nama.setText("Nama Mahasiswa : "+list.get(position).getNamaMahasiswa());
        holder.jenis.setText("Kelas Mahasiswa : "+list.get(position).getKelasMahasiswa());

        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void remove( ModelData object) {
        super.remove(object);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    static  class MyHolder{
        TextView ID;
        TextView IdPush;
        TextView Nama;
        TextView jenis;
    }
}
