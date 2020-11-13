package com.bisaai.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private ArrayList<Siswa> dataSiswa;
    private OnItemClickListener onItemClickListener;

    public Adapter(Context context, ArrayList<Siswa> dataSiswa) {
        this.context = context;
        this.dataSiswa = dataSiswa;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Siswa siswa = dataSiswa.get(position);
//        holder.bind(siswa);
        holder.tvNisn.setText(siswa.getNisn());
        holder.tvNama.setText(siswa.getNama());
        holder.tvKelas.setText(siswa.getKelas());
        holder.tvAlamat.setText(siswa.getAlamat());
        holder.itemView.setOnClickListener(v -> onItemClickListener.clicked(siswa));
        holder.imageView.setOnClickListener(v -> onItemClickListener.deleteData(siswa, position));
    }

    @Override
    public int getItemCount() {
        return dataSiswa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView tvNisn, tvNama, tvKelas, tvAlamat;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivUserDelete);
            tvNisn = itemView.findViewById(R.id.tvUserNisn);
            tvNama = itemView.findViewById(R.id.tvUserNama);
            tvKelas = itemView.findViewById(R.id.tvUserKelas);
            tvAlamat = itemView.findViewById(R.id.tvUserAlamat);

        }

//        public void bind(Siswa data){
//            tvNisn.setText(data.getNisn());
//            tvNama.setText(data.getNama());
//            tvKelas.setText(data.getKelas());
//            tvAlamat.setText(data.getAlamat());
//            itemView.setOnClickListener(v -> onItemClickListener.clicked(data));
//        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void clicked(Siswa data);
        void deleteData(Siswa data, int position);
    }
}
