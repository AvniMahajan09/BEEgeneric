package com.example.avni.beegeneric;

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
import android.widget.LinearLayout;

import com.example.avni.beegeneric.model.Photos;
import com.example.avni.beegeneric.model.Videos;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Vector;

public class VideosFragment extends Fragment{
    RecyclerView recyclerView;
    Vector<Videos> videosVector= new Vector<>();
    private VideoAdapter videoAdapter;
    private final String TAG = getClass().getSimpleName();


    /*@Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Videos,ViewVideoFragment> adapter= new FirebaseRecyclerAdapter<Videos, ViewVideoFragment>() {
            @Override
            protected void onBindViewHolder(@NonNull ViewVideoFragment viewVideoFragment, int i, @NonNull Videos videos) {

            }

            @NonNull
            @Override
            public ViewVideoFragment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }
        }
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos, container, false);
        recyclerView = view.findViewById(R.id.rcVideos);
        initData();
        return view;
}
    private void initData() {
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("videos");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG,"Success+++"+dataSnapshot.toString());
                videosVector.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Videos videos= ds.getValue(Videos.class);
                   // videosVector.add(new Videos(videos.getId(),videos.getVideoUrl()));
                    videosVector.add(new Videos(videos.getId(), videos.getVideoUrl()));
                }
                initView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG,"Error+++"+databaseError);
            }
        });
    }
    private void initView() {
        //initArrayList();
        //initData();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        videoAdapter = new VideoAdapter(getActivity(), videosVector);
        recyclerView.setAdapter(videoAdapter);

    }

    /* private void initArrayList() {
        videosVector.add(new Videos("<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/m61YLnPLBaM\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>"));
        videosVector.add(new Videos("<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/ye4RpHAqwDk\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>") );
    } */

}





