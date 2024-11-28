package com.example.bitirmeprojesiversion4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.EarthquakeViewHolder> {

    private List<Earthquake> earthquakeList;

    public EarthquakeAdapter(List<Earthquake> earthquakeList) {
        this.earthquakeList = earthquakeList;
    }

    @NonNull
    @Override
    public EarthquakeAdapter.EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_earthquake, parent, false);
        return new EarthquakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeAdapter.EarthquakeViewHolder holder, int position) {
        Earthquake earthquake = earthquakeList.get(position);
        holder.date.setText("Tarih: "+ earthquake.getDate());
        holder.magnitude.setText("Büyüklük: "+ earthquake.getMagnitude());
        holder.location.setText("Yer: "+ earthquake.getLocation());

    }

    @Override
    public int getItemCount() {
        return earthquakeList.size();
    }

    public static class EarthquakeViewHolder extends RecyclerView.ViewHolder{
        public TextView date, magnitude, location;

        public EarthquakeViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            magnitude = itemView.findViewById(R.id.magnitude);
            location = itemView.findViewById(R.id.location);
        }
    }
}
