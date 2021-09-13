package id.fdl.tugas3;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Date;

import id.fdl.tugas3.room.KreditNasabah;
import id.fdl.tugas3.room.KreditNasabahViewModel;

public class DeleteKreditNasabahDialogFragment extends DialogFragment {

    private static final String TAG_ID = "TAG_ID";
    private static final String TAG_NAME = "TAG_NAME";
    private static final String TAG_NOREK = "TAG_NOREK";
    private static final String TAG_JUMLAH_TAGIHAN = "TAG_JUMLAH_TAGIHAN";
    private static final String TAG_TANGGAL_JATUH_TEMPO = "TAG_TANGGAL_JATUH_TEMPO";

    private int uid = -1;
    private String name;
    private int norek;
    private double jumlahTagihan;
    private String tanggalJatuhTempo;

    private KreditNasabahViewModel kreditNasabahViewModel;

    public static DeleteKreditNasabahDialogFragment newInstance(KreditNasabah kreditNasabah) {
        Bundle args = new Bundle();
        args.putInt(TAG_ID, kreditNasabah.id);
        args.putString(TAG_NAME, kreditNasabah.name);
        args.putInt(TAG_NOREK, kreditNasabah.norek);
        args.putDouble(TAG_JUMLAH_TAGIHAN, kreditNasabah.jumlahTagihan);
        args.putString(TAG_TANGGAL_JATUH_TEMPO, kreditNasabah.tanggalJatuhTempo);
        DeleteKreditNasabahDialogFragment dudf = new DeleteKreditNasabahDialogFragment();
        dudf.setArguments(args);
        return dudf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(TAG_ID)
                && getArguments().containsKey(TAG_NOREK)
                && getArguments().containsKey(TAG_NAME)
                && getArguments().containsKey(TAG_TANGGAL_JATUH_TEMPO)
                && getArguments().containsKey(TAG_JUMLAH_TAGIHAN)) {
            uid = getArguments().getInt(TAG_ID, -1);
            name = getArguments().getString(TAG_NAME, null);
            norek = getArguments().getInt(TAG_NOREK, 0);
            jumlahTagihan = getArguments().getDouble(TAG_JUMLAH_TAGIHAN, 0.0);
            tanggalJatuhTempo = getArguments().getString(TAG_TANGGAL_JATUH_TEMPO, null);
        }

        kreditNasabahViewModel = new ViewModelProvider(requireActivity()).get(KreditNasabahViewModel.class);
    }

    private void deleteUser() {
        if (uid != -1) {
            KreditNasabah kreditNasabah = new KreditNasabah();
            kreditNasabah.id = uid;
            kreditNasabah.name = name;
            kreditNasabah.jumlahTagihan = jumlahTagihan;
            kreditNasabah.norek = norek;
            kreditNasabah.tanggalJatuhTempo = tanggalJatuhTempo;
            kreditNasabahViewModel.delete(kreditNasabah);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Do you want to delete " + name + "?")
                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteUser();
                    }
                })
                .setNegativeButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_every_day, KreditNasabahUpdateFragment.newInstance(uid,name, norek, jumlahTagihan, tanggalJatuhTempo))
                                .commitNow();
                    }
                });
        return builder.create();
    }

}
