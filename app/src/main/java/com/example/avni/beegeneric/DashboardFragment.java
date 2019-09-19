package com.example.avni.beegeneric;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DashboardFragment extends Fragment {
    CardView cvDocuments,cvPhotos,cvLinks,cvVideos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        cvDocuments=view.findViewById(R.id.cvDocuments);
        cvPhotos=view.findViewById(R.id.cvPhotos);
        cvLinks=view.findViewById(R.id.cvLinks);
        cvVideos=view.findViewById(R.id.cvVideos);
        initView();
        return view;
    }

    private void initView() {
        cvDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DocumentsFragment documentsFragment=new DocumentsFragment();
                fragmentTransaction.replace(R.id.mainContainer, documentsFragment);
                fragmentTransaction.addToBackStack("documentsFragment");
                fragmentTransaction.commit();

            }
        });

        cvPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PhotosFragment photosFragment=new PhotosFragment();
                fragmentTransaction.replace(R.id.mainContainer, photosFragment);
                fragmentTransaction.addToBackStack("photosFragment");
                fragmentTransaction.commit();

            }
        });

        cvLinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                LinksFragment linksFragment=new LinksFragment();
                fragmentTransaction.replace(R.id.mainContainer, linksFragment);
                fragmentTransaction.addToBackStack("linksFragment");
                fragmentTransaction.commit();

            }
        });
        cvVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                VideosFragment videosFragment=new VideosFragment();
                fragmentTransaction.replace(R.id.mainContainer, videosFragment);
                fragmentTransaction.addToBackStack("videosFragment");
                fragmentTransaction.commit();


            }
        });

    }


}
