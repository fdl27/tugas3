package id.fdl.tugas3;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import id.fdl.tugas3.room.KreditNasabah;
import id.fdl.tugas3.room.KreditNasabahViewModel;

public class KreditNasabahInputFragment extends Fragment {


    private KreditNasabahViewModel kreditNasabahViewModel;

    private TextInputLayout textInputName;
    private String name;

    private TextInputLayout textInputNorek;
    private int norek;

    private TextInputLayout textInputJumlahTagihan;
    private double jumlahTagihan;

    private TextInputLayout textnputTanggalJatuhTempo;
    private String tanggalJatuhTempo;

    private Button roomSaveUser;
    
    private DatePickerDialog datePickerDialog;
    private Button btDatePicker;
    private SimpleDateFormat dateFormatter;

    public static KreditNasabahInputFragment newInstance() {
        return new KreditNasabahInputFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kreditNasabahViewModel = new ViewModelProvider(requireActivity()).get(KreditNasabahViewModel.class);
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.room_user_input, container, false);

        textInputNorek = view.findViewById(R.id.textInputNorekLayout);
        textInputName = view.findViewById(R.id.textInputNameLayout);
        textInputJumlahTagihan = view.findViewById(R.id.textInputJumlahTagihanLayout);
        textnputTanggalJatuhTempo = view.findViewById(R.id.textInputTanggalJatuhTempo);

        Calendar newCalendar = Calendar.getInstance();
        btDatePicker = view.findViewById(R.id.bt_datepicker);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(container.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        tanggalJatuhTempo = dateFormatter.format(newDate.getTime());
                        textnputTanggalJatuhTempo.setPlaceholderText(tanggalJatuhTempo);
                    }

                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        roomSaveUser = view.findViewById(R.id.roomSaveButton);

        roomSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = textInputName.getEditText().getText().toString();
                norek = Integer.valueOf(textInputNorek.getEditText().getText().toString());
                jumlahTagihan = Double.valueOf(textInputJumlahTagihan.getEditText().getText().toString());
                KreditNasabah kreditNasabah = new KreditNasabah();
                kreditNasabah.name = name;
                kreditNasabah.norek = norek;
                kreditNasabah.tanggalJatuhTempo = tanggalJatuhTempo;
                kreditNasabah.jumlahTagihan = jumlahTagihan;
                kreditNasabahViewModel.insert(kreditNasabah);
                backToRoomFragment();
            }
        });
        return view;
    }

    private Date toDate(String date){
        Date newDate = new Date();
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            newDate = sdf.parse(date);
        }catch(Exception e){
            e.printStackTrace();
        }
        return newDate;
    }

    private void backToRoomFragment(){
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_every_day, RoomFragment.newInstance())
                .commitNow();
    }

}
