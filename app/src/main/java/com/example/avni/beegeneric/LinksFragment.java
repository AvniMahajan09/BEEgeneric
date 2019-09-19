package com.example.avni.beegeneric;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.avni.beegeneric.model.Links;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class LinksFragment extends Fragment{
    ArrayList<Links>links=new ArrayList<>();
    RecyclerView recyclerView;
    private LinksAdapter adapter;
    private final String TAG = getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_links, container, false);
        recyclerView = view.findViewById(R.id.rcLinks);
        initData();
        return view;
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new LinksAdapter(getActivity(),links,LinksFragment.this);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("links");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "Success+++"+ dataSnapshot.toString());
                links.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Links links1 = ds.getValue(Links.class);
                    links.add(new Links(links1.getId(),links1.getTitle(),links1.getDescription(),links1.getLinkurl()));

                }

                initView(); //Initalize the view
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "Error+++"+ databaseError);
            }
        });

    }

    public void shareTextUrl() {
        String url="";


        if(getArguments() !=null){
            url = getArguments().getString("url");
            Log.d(TAG, "Write file download code here+++");
        }
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        share.putExtra(Intent.EXTRA_TEXT, url);

        startActivity(Intent.createChooser(share, "Share link!"));
    }
}
