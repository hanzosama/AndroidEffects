package com.gemalto.ics.jamaica.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CollectionEffectsAdapter extends FragmentStateAdapter {


    public CollectionEffectsAdapter(@NonNull Fragment fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            Fragment pageCurlFragment = new PageCurlFragment();
            Bundle args = new Bundle();
            // just to test argument
            args.putInt(PageCurlFragment.ARG_OBJECT, position + 1);

            return pageCurlFragment;
        } else {
            Fragment flipViewFragment = new FlipViewFragment();
            Bundle args = new Bundle();
            // just to test argument
            args.putInt(PageCurlFragment.ARG_OBJECT, position + 1);

            return flipViewFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
