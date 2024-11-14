package com.example.hw3_geeks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class CityDetailFragment extends Fragment {

    private ImageView cityImage;
    private TextView cityInfo;
    private TextView cityName;

    private String imageUrl;

    public static CityDetailFragment newInstance(String cityName, String cityImageUrl, String cityInfo) {
        CityDetailFragment fragment = new CityDetailFragment();
        Bundle args = new Bundle();
        args.putString("CITY_NAME", cityName);
        args.putString("CITY_IMAGE_URL", cityImageUrl);
        args.putString("CITY_INFO", cityInfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_city_detail, container, false);

        cityImage = rootView.findViewById(R.id.cityImage);
        cityInfo = rootView.findViewById(R.id.cityInfo);
        cityName = rootView.findViewById(R.id.cityName);

        if (getArguments() != null) {
            String name = getArguments().getString("CITY_NAME");
            imageUrl = getArguments().getString("CITY_IMAGE_URL");
            String info = getArguments().getString("CITY_INFO");

            cityName.setText(name);
            cityInfo.setText(info);
            Glide.with(getContext()).load(imageUrl).into(cityImage);
        }

        // Клик по изображению для полноэкранного режима
        cityImage.setOnClickListener(v -> showFullImage(imageUrl));

        return rootView;
    }

    private void showFullImage(String imageUrl) {
        FullScreenImageFragment fragment = FullScreenImageFragment.newInstance(imageUrl);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
