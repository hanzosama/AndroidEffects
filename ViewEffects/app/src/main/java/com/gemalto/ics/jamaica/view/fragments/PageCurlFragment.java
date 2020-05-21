package com.gemalto.ics.jamaica.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gemalto.ics.jamaica.vieweffects.R;
import com.gemalto.ics.jamaica.view.custom.PageCurlView;

import java.util.ArrayList;
import java.util.List;

public class PageCurlFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.curl_page_effect_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Read Arguments keys from here
        Bundle args = getArguments();
        PageCurlView pageCurlView = view.findViewById(R.id.PageCurlView);
        List<Integer> pages = new ArrayList<>();
        pages.add(R.drawable.document1);
        pages.add(R.drawable.document2);
        pages.add(R.drawable.document3);
        pages.add(R.drawable.document4);
        pages.add(R.drawable.document5);
        pages.add(R.drawable.document6);
        pageCurlView.setDrawableViews(pages);

    }
}
