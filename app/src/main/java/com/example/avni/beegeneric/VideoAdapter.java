package com.example.avni.beegeneric;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avni.beegeneric.model.Videos;

import java.util.List;
import java.util.Vector;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

   Vector<Videos> VideoList;
   Context context;

   public VideoAdapter(Context context,Vector<Videos>VideoList){
       this.context=context;
      this.VideoList=VideoList;
    }

    @NonNull
    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.videos_layout, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.VideoViewHolder holder, int position) {
        holder.videoWeb.loadData(VideoList.get(position).getVideoUrl(),"text/html","utf-8");
    }

    @Override
    public int getItemCount() { return VideoList.size();}

    public class VideoViewHolder extends RecyclerView.ViewHolder{
        private WebView videoWeb;

        public VideoViewHolder(View itemView){
            super(itemView);

            videoWeb=itemView.findViewById(R.id.videoWebView);
            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient() {

            });
        }
    }


}
