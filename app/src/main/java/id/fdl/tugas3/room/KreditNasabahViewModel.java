package id.fdl.tugas3.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class KreditNasabahViewModel extends AndroidViewModel {

    private KreditNasabahRepository kreditNasabahRepository;
    private final LiveData<List<KreditNasabah>> allKreditNasabah;

    public KreditNasabahViewModel(@NonNull Application application) {
        super(application);
        this.kreditNasabahRepository = new KreditNasabahRepository(application);
        this.allKreditNasabah = kreditNasabahRepository.getListAllKreditNasabah();
    }

    public LiveData<List<KreditNasabah>> getAllKreditNasabah(){
        return allKreditNasabah;
    }

    public void insert(KreditNasabah kreditNasabah){
        kreditNasabahRepository.insert(kreditNasabah);
    }

    public void update(KreditNasabah kreditNasabah){
        kreditNasabahRepository.update(kreditNasabah);
    }

    public void delete(KreditNasabah kreditNasabah){
        kreditNasabahRepository.delete(kreditNasabah);
    }
}
