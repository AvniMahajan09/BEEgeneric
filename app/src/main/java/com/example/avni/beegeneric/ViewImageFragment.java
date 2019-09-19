package com.example.avni.beegeneric;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class ViewImageFragment extends Fragment {
    private String imgUrl, imgTitle;
    private ImageView imageView;
    private TextView nameofFile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() !=null){
            imgUrl = getArguments().getString("imgUrl");
            imgTitle = getArguments().getString("imgTitle");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_image, container, false);
        imageView = view.findViewById(R.id.imgViewImage);
        nameofFile = view.findViewById(R.id.nameofFile);
        Glide.with(getActivity()).load(imgUrl).into(imageView);
        nameofFile.setText(imgTitle);
        return view;
    }


}
