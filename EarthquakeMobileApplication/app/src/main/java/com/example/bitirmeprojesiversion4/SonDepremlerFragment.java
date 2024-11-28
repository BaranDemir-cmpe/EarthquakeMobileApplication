package com.example.bitirmeprojesiversion4;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class SonDepremlerFragment extends Fragment {

    private RecyclerView recyclerView;
    private EarthquakeAdapter adapter;
    private List<Earthquake> earthquakeList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_son_depremler,container,false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        earthquakeList = new ArrayList<>();
        adapter = new EarthquakeAdapter(earthquakeList);
        recyclerView.setAdapter(adapter);

        // Depremleri yükle
        new FetchEarthquakeData().execute();

        return view;
    }

    private class FetchEarthquakeData extends AsyncTask<Void, Void, List<Earthquake>>{
        @Override
        protected List<Earthquake> doInBackground(Void... voids){

            List<Earthquake> earthquakes = new ArrayList<>();
            try {
                Document document = Jsoup.connect("https://deprem.afad.gov.tr/last-earthquakes.html").get();

                Element table = document.select("table.content-table").first();
                Elements rows = table.select("tbody tr");
                int maxRows = Math.min(100, rows.size());

                for(int i=0;i< maxRows; i++){
                    Element row = rows.get(i);
                    Elements columns = row.select("td");
                    if(columns.size() >=6){
                        String date = columns.get(0).text();
                        String magnitude = columns.get(5).text();
                        String location = columns.get(6).text();

                        Earthquake earthquake = new Earthquake(date, magnitude, location);
                        earthquakes.add(earthquake);

                        // Loglama ile kontrol
                        Log.d("EarthquakeData", "Date: " + date + ", Magnitude: " + magnitude + ", Location: " + location);
                    }
                }

            }catch (Exception e){
                Log.e("ConnectionError", "Veri çekme sırasında hata oluştu: " + e.getMessage());
            }
            return earthquakes;
        }
        @Override
        protected void onPostExecute(List<Earthquake> result){
            super.onPostExecute(result);
            earthquakeList.addAll(result);
            adapter.notifyDataSetChanged();
        }

    }
}

