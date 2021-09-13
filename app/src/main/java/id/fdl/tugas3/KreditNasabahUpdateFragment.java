package id.fdl.tugas3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import id.fdl.tugas3.room.KreditNasabahViewModel;

public class KreditNasabahUpdateFragment extends Fragment {

    private KreditNasabahViewModel kreditNasabahViewModel;

    private TextInputLayout textInputNameLayout;
    private String name;

    private TextInputLayout textInputNorekLayout;
    private int norek;

    private TextInputLayout textInputJumlahTagihanLayout;
    private double jumlahTagihan;

    private MaterialDatePicker.Builder datePicker;
    private Date tanggalJatuhTempo;

    private Button roomSaveButton;


    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String NOREK = "NOREK";
    private static final String JUMLAH_TAGIHAN = "JUMLAH_TAGIHAN";
    private static final String TANGGAL_JATUH_TEMPO = "TANGGAL_JATUH_TEMPO";

    private int id;
    private String nameFromDialog;
    private int norekFromDialog;
    private double jumlahTagihanFromDialog;
    private Date tanggalJatuhTempoFromDialog;

    public static KreditNasabahUpdateFragment newInstance(int id, String name, int norek, double jumlahTagihan, Date tanggalJatuhTempo){
        Bundle args = new Bundle();
        args.putInt(ID, id);
        args.putString(NAME, name);
        args.putInt(NOREK, norek);
        args.putDouble(JUMLAH_TAGIHAN, jumlahTagihan);
        args.putLong(TANGGAL_JATUH_TEMPO, tanggalJatuhTempo.getTime());
        KreditNasabahUpdateFragment kreditNasabahUpdateFragment = new KreditNasabahUpdateFragment();
        kreditNasabahUpdateFragment.setArguments(args);
        return kreditNasabahUpdateFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kreditNasabahViewModel = new ViewModelProvider(requireActivity()).get(KreditNasabahViewModel.class);

        if (getArguments().containsKey(ID)){
            id = getArguments().getInt(ID);
            nameFromDialog = getArguments().getString(NAME);
            norekFromDialog = getArguments().getInt(NOREK);
            jumlahTagihanFromDialog = getArguments().getDouble(JUMLAH_TAGIHAN);
            tanggalJatuhTempoFromDialog = new Date(getArguments().getLong(TANGGAL_JATUH_TEMPO));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day5_room_user_input, container, false);
        roomTextInputLayoutFirstName = view.findViewById(R.id.roomTextInputLayoutFirstName);
        roomTextInputLayoutFirstName.getEditText().setText(firstNameFromDialog);

        roomTextInputLayoutLastName = view.findViewById(R.id.roomTextInputLayoutLastName);
        roomTextInputLayoutLastName.getEditText().setText(lastNameFromDialgo);

        roomSaveUser = view.findViewById(R.id.roomSaveUser);
        roomSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = roomTextInputLayoutFirstName.getEditText().getText().toString();
                lastName = roomTextInputLayoutLastName.getEditText().getText().toString();
                User user = new User();
                user.uid = uid;
                user.firstName = firstName;
                user.lastName = lastName;
                kreditNasabahViewModel.update(user);
                backToRoomFragment();
            }
        });
        return view;
    }

    private void backToRoomFragment(){
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_every_day, Day5RoomFragment.newInstance())
                .commitNow();
    }


}
