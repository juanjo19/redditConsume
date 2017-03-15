package com.juanjosemolina.testobservable.ListElements.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.juanjosemolina.testobservable.DataElements.DataElementsActivity;
import com.juanjosemolina.testobservable.model.dtos.atributtesJson;
import com.juanjosemolina.testobservable.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
        if(atributtesJson.getImage().equals("")){
            Picasso.with(activity).load("http://i.imgur.com/DvpvklR.png").into(holder.circleImageView);
        }else{
            Picasso.with(activity).load(atributtesJson.getImage()).into(holder.circleImageView);
        }
        holder.cardinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DataElementsActivity.class);
                /**se envia como atributo todo el objeto a la otra vista
                 * para evitar consultarlo de nuevo
                 */
                intent.putExtra("data", atributtesJson);
                activity.startActivity(intent);

                Toast.makeText(activity, "hiciste click"+atributtesJson, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return atributtesJsons.size();
    }

    public class holderInfo extends RecyclerView.ViewHolder{

        private TextView title_data;
        private CircleImageView circleImageView;
        private CardView cardinfo;

        public holderInfo(View itemView) {
            super(itemView);
            title_data = (TextView) itemView.findViewById(R.id.title_data);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.profile_image);
            cardinfo = (CardView) itemView.findViewById(R.id.cardview_data);
        }
    }
}
