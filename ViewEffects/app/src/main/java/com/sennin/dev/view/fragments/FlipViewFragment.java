package com.sennin.dev.view.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sennin.dev.view.custom.FlipView;
import com.sennin.dev.vieweffects.R;

public class FlipViewFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.flip_view_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Read Arguments keys from here
        Bundle args = getArguments();
        FlipView flipView = view.findViewById(R.id.flipView);
        flipView.setViews(BitmapFactory.decodeResource(getResources(),R.drawable.document1),BitmapFactory.decodeResource(getResources(),R.drawable.document2));

    }
}
