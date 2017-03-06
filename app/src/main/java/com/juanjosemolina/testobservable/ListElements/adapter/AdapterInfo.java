package com.juanjosemolina.testobservable.ListElements.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juanjosemolina.testobservable.ListElements.model.dto.atributtesJson;
import com.juanjosemolina.testobservable.R;

import java.util.List;

/**
 * Created by Juan José on 04/03/2017.
 */

public class AdapterInfo extends RecyclerView.Adapter<AdapterInfo.holderInfo> {

    private List<atributtesJson> atributtesJsons;
    private int resource;
    private Activity activity;

    public AdapterInfo(List<atributtesJson> atributtesJsons, int resource, Activity activity) {
        this.atributtesJsons = atributtesJsons;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public holderInfo onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new holderInfo(view);
    }

    @Override
    public void onBindViewHolder(holderInfo holder, int position) {
        atributtesJson atributtesJson = atributtesJsons.get(position);
        holder.title_data.setText(atributtesJson.getName());
    }

    @Override
    public int getItemCount() {
        return atributtesJsons.size();
    }

    public class holderInfo extends RecyclerView.ViewHolder{

        private TextView title_data;

        public holderInfo(View itemView) {
            super(itemView);
            title_data = (TextView) itemView.findViewById(R.id.title_data);
        }
    }
}
