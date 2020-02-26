package com.heroes;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myrecycleview.R;

import java.util.ArrayList;
//setelah bikin view dan heroData
//Adapter berperan sebagai pengulang data

public class ListHeroAdapter extends RecyclerView.Adapter<ListHeroAdapter.ListViewHolder> {
    private ArrayList<Hero> listHero;// new ArrayList<>();

    //private Contexy context
    //public ListHeroAdapter(ArrayList<Hero>listHero,Context context)
    public ListHeroAdapter(ArrayList<Hero> list) { //atau bisa pakai constructor
        //
        this.listHero = list;
        //this.context=context;
    }

    @NonNull
    @Override
    //habis bikin extend awal recycle
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_hero, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, int position) {
        final Hero hero = listHero.get(position);
        Glide.with(holder.itemView.getContext())
                .load(hero.getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imgPhoto);
        holder.tvName.setText(hero.getName());
        holder.tvDetail.setText(hero.getDetail());
        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String share = "Ini adalah Pahlawan " + listHero.get(holder.getAdapterPosition()).getName() + "\n" + "Berikut merupakan deskripsi dari Pahlawan " + listHero.get(holder.getAdapterPosition()).getName() + "\n" + listHero.get(holder.getAdapterPosition()).getDetail();
                intent.putExtra(Intent.EXTRA_TEXT, share);
                holder.itemView.getContext().startActivity(Intent.createChooser(intent, "Share With"));
            }
        });
        holder.imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Ini Pahlawan Kita " + listHero.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailHeroes.class);
                intent.putExtra("image", hero.getPhoto());
                intent.putExtra("title", hero.getName());
                intent.putExtra("desc", hero.getDetail());
                Toast.makeText(holder.itemView.getContext(), "Deskripsi Pahlawan  " + listHero.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    //biar diulang sesuai jumlah di list hero
    public int getItemCount() {
        return listHero.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDetail;
        private Button btnShare, btnDetails;

        ListViewHolder(View itemView) {
            super(itemView);
            //agar terkoneksi dengan tampilan view
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDetail = itemView.findViewById(R.id.tv_item_detail);
            btnShare = itemView.findViewById(R.id.btn_share);
            btnDetails = itemView.findViewById(R.id.btn_details);

        }
    }


}