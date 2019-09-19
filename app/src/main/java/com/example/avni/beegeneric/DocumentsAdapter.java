package com.example.avni.beegeneric;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avni.beegeneric.model.Docs;

import java.util.ArrayList;

public class DocumentsAdapter extends RecyclerView.Adapter<DocumentsAdapter.ViewHolder> {
    Context context;
    ArrayList<Docs> urls;
    DocumentsFragment documentsFragment;

    public DocumentsAdapter(Context context, ArrayList<Docs> urls, DocumentsFragment documentsFragment) {
        this.context = context;
        this.urls = urls;
        this.documentsFragment = documentsFragment;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //to create views for recycler view item
        View view = LayoutInflater.from(context).inflate(R.layout.documents_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //initialise the elements of indiv items i.e documents
        Docs url = urls.get(position);
        holder.nameofFile.setText(url.getName());
        holder.urloffile.setText(url.getUrl());

    }

    @Override
    public int getItemCount() {
        //return the no of items
        return urls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nameofFile, urloffile;

        public ViewHolder(@NonNull View itemView) {//represents indiv list items..
            super(itemView);
            nameofFile= itemView.findViewById(R.id.nameofFile);
            urloffile= itemView.findViewById(R.id.urlofFile);

            urloffile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", urls.get(getAdapterPosition()).getUrl());
                    documentsFragment.setArguments(bundle);
                   documentsFragment.fileDownload(); //calling the file download method on button click
                }
            });

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=recyclerView.getChildLayoutPosition(view);
                    Intent intent= new Intent();
                    intent.setType(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urls.get(position)));
                    context.startActivity(intent);
                }
            });*/
        }
    }
}
