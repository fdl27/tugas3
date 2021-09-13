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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.fdl.tugas3.room.KreditNasabah;
import id.fdl.tugas3.room.KreditNasabahViewModel;

public class KreditNasabahUpdateFragment extends Fragment {

    private KreditNasabahViewModel kreditNasabahViewModel;

    private TextInputLayout textInputNameLayout;
    private String name;

    private TextInputLayout textInputNorekLayout;
    private int norek;

    private TextInputLayout textInputJumlahTagihanLayout;
    private double jumlahTagihan;

    private TextInputLayout textInputTanggalJatuhTempo;
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
        View view = inflater.inflate(R.layout.room_user_input, container, false);

        textInputNameLayout = view.findViewById(R.id.textInputNameLayout);
        textInputNameLayout.getEditText().setText(nameFromDialog);

        textInputNorekLayout = view.findViewById(R.id.textInputNorekLayout);
        textInputNorekLayout.getEditText().setText(norekFromDialog);

        textInputJumlahTagihanLayout = view.findViewById(R.id.textInputJumlahTagihanLayout);
        textInputJumlahTagihanLayout.getEditText().setText((int) jumlahTagihanFromDialog);

        textInputTanggalJatuhTempo = view.findViewById(R.id.textInputTanggalJatuhTempo);
        textInputTanggalJatuhTempo.getEditText().setText(dateToString(tanggalJatuhTempoFromDialog));

        roomSaveButton = view.findViewById(R.id.roomSaveButton);
        roomSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = textInputNameLayout.getEditText().getText().toString();
                norek = Integer.valueOf(textInputNorekLayout.getEditText().getText().toString());
                jumlahTagihan = Double.valueOf(textInputJumlahTagihanLayout.getEditText().getText().toString());
                try {
                    tanggalJatuhTempo = toDate(textInputTanggalJatuhTempo.getEditText().getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                KreditNasabah kreditNasabah = new KreditNasabah();
                kreditNasabah.id = id;
                kreditNasabah.name = name;
                kreditNasabah.norek = norek;
                kreditNasabah.jumlahTagihan = jumlahTagihan;
                kreditNasabah.tanggalJatuhTempo = tanggalJatuhTempo;

                kreditNasabahViewModel.update(kreditNasabah);
                backToRoomFragment();
            }
        });
        return view;
    }

    private void backToRoomFragment(){
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_every_day, RoomFragment.newInstance())
                .commitNow();
    }

    private Date toDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(date);
    }

    private String dateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

}
