package com.gemalto.ics.jamaica.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.gemalto.ics.jamaica.vieweffects.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CollectionEffectsFragment extends Fragment {
    private ViewPager2 viewPager;
    private CollectionEffectsAdapter collectionEffectsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        collectionEffectsAdapter = new CollectionEffectsAdapter(this);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(collectionEffectsAdapter);
        //in view pager 2 this property help to handle the touch event
        viewPager.setUserInputEnabled(false);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    String titleTab = "";
                    switch (position) {
                        case 0:
                            titleTab = "Page Curl";
                            break;
                        case 1:
                            titleTab = "Page flip";
                            break;
                        case 2:
                            break;
                    }
                    tab.setText(titleTab);

                }
        ).attach();
    }
}
