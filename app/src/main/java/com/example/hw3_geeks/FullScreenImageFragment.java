package com.example.hw3_geeks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class FullScreenImageFragment extends Fragment {

    private ImageView fullScreenImage;

    public static FullScreenImageFragment newInstance(String imageUrl) {
        FullScreenImageFragment fragment = new FullScreenImageFragment();
        Bundle args = new Bundle();
        args.putString("IMAGE_URL", imageUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_full_screen_image, container, false);
        fullScreenImage = rootView.findViewById(R.id.fullScreenImage);

        if (getArguments() != null) {
            String imageUrl = getArguments().getString("IMAGE_URL");
            Glide.with(this).load(imageUrl).into(fullScreenImage);
        }

        return rootView;
    }
}
