package com.ad_srtarevolution.belajar_api.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ModelData {

    @SerializedName("id_mhs")
    @Expose
    public  String idMahasiswa;

    @SerializedName("nama_mhs")
    @Expose
    private String namaMahasiswa;


    @SerializedName("kelas_mhs")
    @Expose
    private String kelasMahasiswa;

    public static final String id_mahasiswa="ID_MAHASISWA";
    public static final String nama_mahasiswa="ID_MAHASISWA";
    public static final String jenis_mahasiswa="ID_MAHASISWA";

    public ModelData(String id,String nama, String kelas_mhs){
        this.idMahasiswa=id;
        this.namaMahasiswa=nama;
        this.kelasMahasiswa=kelas_mhs;

    }

    public String getIdMahasiswa() {
        return idMahasiswa;
    }

    public void setIdMahasiswa(String idMahasiswa) {
        this.idMahasiswa = idMahasiswa;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getKelasMahasiswa() {
        return kelasMahasiswa;
    }

    public void setKelasMahasiswa(String kelasMahasiswa) {
        this.kelasMahasiswa = kelasMahasiswa;
    }
}
