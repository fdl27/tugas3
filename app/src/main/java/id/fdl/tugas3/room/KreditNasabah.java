package id.fdl.tugas3.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class KreditNasabah {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "norek")
    public int norek;

    @ColumnInfo(name = "tanggal_jatuh_tempo")
    public String tanggalJatuhTempo;

    @ColumnInfo(name = "jumlah_tagihan")
    public double jumlahTagihan;

}
