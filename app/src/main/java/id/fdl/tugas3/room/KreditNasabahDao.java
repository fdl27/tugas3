package id.fdl.tugas3.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface KreditNasabahDao {

    @Query("select * from kreditnasabah")
    public LiveData<List<KreditNasabah>> findAll();

    @Insert
    public void insert(KreditNasabah kreditNasabah);

    @Delete
    public void delete(KreditNasabah kreditNasabah);

    @Update
    public void update(KreditNasabah kreditNasabah);

}
