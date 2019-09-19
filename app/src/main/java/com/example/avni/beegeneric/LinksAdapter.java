package com.example.avni.beegeneric;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avni.beegeneric.model.Docs;
import com.example.avni.beegeneric.model.Links;

import java.util.ArrayList;

public class LinksAdapter extends RecyclerView.Adapter<LinksAdapter.ViewHolder>{
    RecyclerView recyclerView;
    private Context context;
    private ArrayList<Links> links = new ArrayList<>();
    LinksFragment linksFragment;

    public LinksAdapter(@NonNull Context context,ArrayList<Links>links,LinksFragment linksFragment) {
        this.context = context;
        this.links = links;
        this.linksFragment = linksFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.links_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Links links1 = links.get(position);
    holder.nameofFile.setText(links1.getTitle());
    holder.descriptionofFile.setText(links1.getDescription());
    holder.urlofFile.setText(links1.getLinkurl());
    }

    @Override
    public int getItemCount() {
        return links.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameofFile, urlofFile, descriptionofFile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameofFile = itemView.findViewById(R.id.nameofFile);
            descriptionofFile = itemView.findViewById(R.id.descriptionofFile);
            urlofFile = itemView.findViewById(R.id.urlofFile);

            urlofFile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle= new Bundle();
                    bundle.putString("url",links.get(getAdapterPosition()).getLinkurl());
                    linksFragment.setArguments(bundle);
                    linksFragment.shareTextUrl();
                }
            });
        }
    }

}
