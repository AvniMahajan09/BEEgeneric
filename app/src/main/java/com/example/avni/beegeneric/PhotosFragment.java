package com.example.avni.beegeneric;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.avni.beegeneric.model.Photos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PhotosFragment extends Fragment {
    ArrayList<Photos> photosList= new ArrayList<>();
    RecyclerView recyclerView;
    private PhotosAdapter adapter;
    private final String TAG = getClass().getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photos, container, false);
        recyclerView = view.findViewById(R.id.rcPhotos);
        initData();//get data from firebase
        //initView();
        return view;
    }

    private void initData() {
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("photos");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG,"Success+++"+dataSnapshot.toString());
                photosList.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Photos photos= ds.getValue(Photos.class);
                    photosList.add(new Photos(photos.getId(),photos.getName(),photos.getImgurl()));
                }
               initView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG,"Error+++"+databaseError);
            }
        });
    }

    private void initView(){
        //initArrayList();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        //adapter = new PhotosAdapter(getActivity(), photosList,PhotosFragment.this);
        adapter= new PhotosAdapter(getActivity(),photosList,PhotosFragment.this);
        recyclerView.setAdapter(adapter);

    }

    /* private void initArrayList(){
        photosList = new ArrayList<>();
        photosList.add(new Photos(1, "Regional Workshop","https://beenet.gov.in/WorkshopImages/photo_newdelhi/IMG-20160102-WA0000.jpg"));
        photosList.add(new Photos(2, "EsCerts","https://beenet.gov.in/WorkshopImages/Trading_launch_event/Pic%201.jpg"));
        photosList.add(new Photos(3, "Launch","https://beenet.gov.in/WorkshopImages/Trading_launch_event/Pic%202%20(00000002).jpg"));
        photosList.add(new Photos(4, "Ribbon","https://beenet.gov.in/WorkshopImages/Trading_launch_event/Pic%203%20(00000002).jpg"));

    }*/


}
