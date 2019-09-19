package com.example.avni.beegeneric;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.avni.beegeneric.model.Photos;

import java.util.ArrayList;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Photos> photos;
    PhotosFragment photosFragment;

    public PhotosAdapter(Context context, ArrayList<Photos> photos,PhotosFragment photosFragment) {
        this.context = context;
        this.photos = photos;
        this.photosFragment= photosFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photos_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(photos.get(position).getImgurl()).into(holder.img);
        //Photos photos= photos.get(position).into(holder.img);
        holder.nameofFile.setText(photos.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        TextView nameofFile;

        public ViewHolder(View view) {
            super(view);
            nameofFile= view.findViewById(R.id.nameofFile);
            img = view.findViewById(R.id.photosList);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fragmentManager = ((MainActivity)context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Bundle bundle = new Bundle();
                    //bundle.putParcelable("imgUrl", photos.get(getAdapterPosition()).getImgurl());
                    bundle.putString("imgUrl", photos.get(getAdapterPosition()).getImgurl());
                    bundle.putString("imgTitle", photos.get(getAdapterPosition()).getName());
                    ViewImageFragment viewImageFragment = new ViewImageFragment();
                    viewImageFragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.mainContainer, viewImageFragment);
                    fragmentTransaction.addToBackStack("viewImageFragment");
                    fragmentTransaction.commit();
                }
            });
        }
    }
}
