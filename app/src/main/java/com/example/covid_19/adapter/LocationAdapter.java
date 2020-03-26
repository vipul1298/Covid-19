package com.example.covid_19.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.Details;
import com.example.covid_19.R;
import com.example.covid_19.models.Regional;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    public List<Regional> regionalList;
    Context context;

    public LocationAdapter(List<Regional> regionalList,Context context) {
        this.regionalList = regionalList;
        this.context=context;
    }

    @NonNull
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new LocationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder holder, int position) {
        Regional data = regionalList.get(position);
        holder.mcity.setText(data.getLocation()+"");
        holder.mindians.setText(data.getConfirmed_indian_case()+"");

    }

    @Override
    public int getItemCount() {
        return regionalList.size();
    }

    public void setData(List<Regional> data){
        this.regionalList.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mcity;
        public TextView mindians;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mcity=itemView.findViewById(R.id.city);
            mindians=itemView.findViewById(R.id.num_people);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Regional regional = regionalList.get(getAdapterPosition());
                    Intent intent = new Intent(context, Details.class);
                    intent.putExtra("city",regional.getLocation());
                    intent.putExtra("c_indian",regional.getConfirmed_indian_case());
                    intent.putExtra("c_foreign",regional.getConfirmed_foreigner_case());
                    intent.putExtra("c_discharged",regional.getDischared());
                    intent.putExtra("c_deaths",regional.getDeaths());
                    context.startActivity(intent);
                }
            });
        }
    }
}
