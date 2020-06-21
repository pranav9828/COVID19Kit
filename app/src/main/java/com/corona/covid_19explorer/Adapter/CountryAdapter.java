package com.corona.covid_19explorer.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.corona.covid_19explorer.Activity.CountryDetail;
import com.corona.covid_19explorer.Classes.Country;
import com.corona.covid_19explorer.Classes.Headlines;
import com.corona.covid_19explorer.R;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{

    private Context context;
    private List<Country> countries;

    public CountryAdapter(Context context, List<Country> countries){
        this.context = context;
        this.countries = countries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CountryAdapter.ViewHolder holder1 = holder;
        final Country country = countries.get(position);
        holder.countryName.setText(countries.get(position).getName());
        holder.countryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CountryDetail.class);
                intent.putExtra("name", country.getName());
                intent.putExtra("total", country.getTotal());
                intent.putExtra("death", country.getDeath());
                intent.putExtra("cured", country.getCured());
                intent.putExtra("active", country.getActive());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView countryName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.country_name);
        }
    }
}
