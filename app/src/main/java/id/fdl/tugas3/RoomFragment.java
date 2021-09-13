package id.fdl.tugas3;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import id.fdl.tugas3.room.KreditNasabahViewModel;

public class RoomFragment extends Fragment {

    private KreditNasabahViewModel kreditNasabahViewModel;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private KreditNasabahAdapter kreditNasabahAdapter;


    public static RoomFragment newInstance(){

    }

}
