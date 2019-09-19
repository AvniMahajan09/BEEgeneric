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

import com.example.avni.beegeneric.model.Docs;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;


/**
 *
 */
public class DocumentsFragment extends Fragment {
    Uri pdfUri; //Urls that are meant for local storage
    ArrayList<Docs> urls = new ArrayList<>();;
    RecyclerView recyclerView;
    private DocumentsAdapter adapter;
    private final String TAG = getClass().getSimpleName();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_documents, container, false);
        recyclerView = view.findViewById(R.id.rcDocuments);
        initData(); //get the data from firebase
        return view;


    }

    //custom adapter
    //populate the recycler view with adapter so that items can be displayed.
    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new DocumentsAdapter(getActivity(), urls, DocumentsFragment.this);
        recyclerView.setAdapter(adapter);
    }

    private void initData(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("docs");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "Success+++"+ dataSnapshot.toString());
                urls.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Docs docs = ds.getValue(Docs.class);
                    urls.add(new Docs(docs.getId(), docs.getName(), docs.getUrl()));
                }

                initView(); //Initalize the view
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "Error+++"+ databaseError);
            }
        });

    }

    public void fileDownload(){
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
