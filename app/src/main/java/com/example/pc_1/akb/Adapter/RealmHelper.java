package com.example.pc_1.akb.Adapter;
import android.util.Log;

import com.example.pc_1.akb.Model.FriendModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public  RealmHelper(Realm realm){
        this.realm = realm;
    }

    // untuk menyimpan data
    public void save(final FriendModel friendModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNum = realm.where(FriendModel.class).max("id");
                    int nextId;
                    if (currentIdNum == null){
                        nextId = 1;
                    }else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    friendModel.setId(nextId);
                    FriendModel model = realm.copyToRealm(friendModel);
                }else{
                    Log.e("ppppp", "execute: Database not Exist");
                }
            }
        });
    }

    // untuk memanggil semua data
    public List<FriendModel> getAllMahasiswa(){
        RealmResults<FriendModel> results = realm.where(FriendModel.class).findAll();
        return results;
    }

    // untuk meng-update data
    public void update(final Integer id,final Integer nim, final String nama,final String kelas,
                       final String telp, final String email, final String socmed){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                FriendModel model = realm.where(FriendModel.class)
                        .equalTo("id", id)
                        .findFirst();
                model.setNim(nim);
                model.setNama(nama);
                model.setKelas(kelas);
                model.setTelp(telp);
                model.setEmail(email);
                model.setSocmed(socmed);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("pppp", "onSuccess: Update Successfully");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    // untuk menghapus data
    public void delete(Integer nim){
        final RealmResults<FriendModel> model = realm.where(FriendModel.class).equalTo("nim", nim).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                model.deleteFromRealm(0);
            }
        });
    }

}
