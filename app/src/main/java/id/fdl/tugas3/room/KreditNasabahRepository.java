package id.fdl.tugas3.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class KreditNasabahRepository {

    private KreditNasabahDao kreditNasabahDao;
    private LiveData<List<KreditNasabah>> listAllKreditNasabah;

    public KreditNasabahRepository(Application application){
        AppDatabase appDatabase = AppDatabase.getDatabase(application);
        kreditNasabahDao = appDatabase.kreditNasabahDao();
        listAllKreditNasabah = kreditNasabahDao.findAll();
    }

    LiveData<List<KreditNasabah>> getListAllKreditNasabah(){
        return listAllKreditNasabah;
    }

    void insert(KreditNasabah kreditNasabah){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                kreditNasabahDao.insert(kreditNasabah);
            }
        });
    }

    void update(KreditNasabah kreditNasabah){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                kreditNasabahDao.update(kreditNasabah);
            }
        });
    }

    void delete(KreditNasabah kreditNasabah){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                kreditNasabahDao.delete(kreditNasabah);
            }
        });
    }
}
