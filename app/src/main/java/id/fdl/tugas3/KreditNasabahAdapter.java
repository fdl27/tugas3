package id.fdl.tugas3;



import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import id.fdl.tugas3.room.KreditNasabah;

public class KreditNasabahAdapter extends ListAdapter<KreditNasabah, KreditNasabahAdapter.ViewHolder> {

    private final KreditNasabahClickableCallback userClickableCallback = new KreditNasabahClickableCallback() {
        @Override
        public void onClick(View view, KreditNasabah kreditNasabah) {
            DialogFragment newFragment = DeleteKreditNasabahDialogFragment.newInstance(kreditNasabah);
            newFragment.show(getChildFragmentManager(), "DeleteUserDialogFragment");
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nama;
        TextView norek;
        TextView tanggalJatuhTempo;
        TextView jumlahTagihan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.room_tv_firstname);
            norek = itemView.findViewById(R.id.room_tv_lastname);
            tanggalJatuhTempo = itemView.findViewById(R.id.room_tv_lastname);
            jumlahTagihan = itemView.findViewById(R.id.room_tv_lastname);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAbsoluteAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                KreditNasabah kreditNasabah = getItem(position);
                kreditNasabahClickableCallback.onClick(v, kreditNasabah);
            }
        }
}
