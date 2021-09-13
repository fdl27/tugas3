package id.fdl.tugas3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;

import id.fdl.tugas3.room.KreditNasabah;

public class KreditNasabahAdapter extends ListAdapter<KreditNasabah, KreditNasabahAdapter.ViewHolder> {

    private final KreditNasabahClickableCallback kreditNasabahClickableCallback;

    protected KreditNasabahAdapter(@NonNull KreditNasabahClickableCallback kreditNasabahClickableCallback) {
        super(new AsyncDifferConfig.Builder<>(new DiffUtil.ItemCallback<KreditNasabah>() {
            @Override
            public boolean areItemsTheSame(@NonNull KreditNasabah oldItem, @NonNull KreditNasabah newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull KreditNasabah oldItem, @NonNull KreditNasabah newItem) {
                return oldItem.id == newItem.id
                        && oldItem.name.equals(newItem.name)
                        && oldItem.norek == newItem.norek
                        && oldItem.jumlahTagihan == newItem.jumlahTagihan
                        && oldItem.tanggalJatuhTempo.equals(newItem.tanggalJatuhTempo)
                        ;
            }
        }).build());
        this.kreditNasabahClickableCallback = kreditNasabahClickableCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.kredit_nasabah_recycler_view_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nama.setText(getItem(position).name);
        holder.tanggalJatuhTempo.setText(getItem(position).tanggalJatuhTempo);
        holder.jumlahTagihan.setText(Double.toString(getItem(position).jumlahTagihan));
        holder.norek.setText(Integer.toString(getItem(position).norek));


    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nama;
        TextView norek;
        TextView tanggalJatuhTempo;
        TextView jumlahTagihan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tv_name);
            norek = itemView.findViewById(R.id.tv_norek);
            tanggalJatuhTempo = itemView.findViewById(R.id.tv_tgl_jatuh_tempo);
            jumlahTagihan = itemView.findViewById(R.id.tv_tagihan);
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
}