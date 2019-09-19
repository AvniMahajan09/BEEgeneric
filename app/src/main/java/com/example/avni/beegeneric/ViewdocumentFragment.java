package com.example.avni.beegeneric;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewdocumentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewdocumentFragment#newInstance} factory method to
 * create an instance of this fragment.

public class ViewdocumentFragment extends Fragment {
    private String docUrl;
    private TextView txtViewdocument;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            docUrl= getArguments().toString("docUrl");
        }
    }
}
*/