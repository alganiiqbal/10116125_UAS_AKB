package com.example.pc_1.akb.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FriendModel extends RealmObject {

    @PrimaryKey
    private Integer id;
    private Integer nim;
    private String nama;
    private String kelas;
    private String telp;
    private String email;
    private String socmed;

    public void setId(Integer id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
    public void setNim(Integer nim) {
        this.nim = nim;
    }

    public int getNim() {
        return nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setKelas(String kelas){
        this.kelas=kelas;
    }

    public String getKelas(){
        return kelas;
    }

    public void setTelp(String telp){
        this.telp=telp;
    }

    public String getTelp() {
        return telp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setSocmed(String socmed) {
        this.socmed = socmed;
    }

    public String getSocmed() {
        return socmed;
    }

}
