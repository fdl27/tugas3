package id.fdl.tugas3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import id.fdl.tugas3.room.KreditNasabah;
import id.fdl.tugas3.room.KreditNasabahViewModel;

public class RoomFragment extends Fragment {

    private KreditNasabahViewModel kreditNasabahViewModel;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private KreditNasabahAdapter kreditNasabahAdapter;

    private final KreditNasabahClickableCallback kreditNasabahClickableCallback = new KreditNasabahClickableCallback() {
        @Override
        public void onClick(View view, KreditNasabah kreditNasabah) {
            DialogFragment newFragment = DeleteKreditNasabahDialogFragment.newInstance(kreditNasabah);
            newFragment.show(getChildFragmentManager(), "DeleteUserDialogFragment");
        }
    };

    public static RoomFragment newInstance(){
        return new RoomFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kreditNasabahViewModel = new ViewModelProvider(requireActivity()).get(KreditNasabahViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kredit_nasabah_recycler_view, container, false);
        recyclerView = view.findViewById(R.id.roomRecyclerView);
        kreditNasabahAdapter = new KreditNasabahAdapter(kreditNasabahClickableCallback);
        recyclerView.setAdapter(kreditNasabahAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        fab = view.findViewById(R.id.room_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInput();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        kreditNasabahViewModel.getAllKreditNasabah().observe(getViewLifecycleOwner(), users -> {
            if (users != null) {
                kreditNasabahAdapter.submitList(users);
            }
        });
    }

    private void showInput(){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_every_day, KreditNasabahInputFragment.newInstance())
                .commitNow();
    }

}
