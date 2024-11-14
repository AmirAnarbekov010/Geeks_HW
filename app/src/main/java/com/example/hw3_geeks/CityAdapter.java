package com.example.hw3_geeks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private List<String> cityList;
    private List<String> cityImages;
    private List<String> cityInfo;
    private OnCityClickListener listener;

    // Конструктор адаптера
    public CityAdapter(List<String> cityList, List<String> cityImages, List<String> cityInfo, OnCityClickListener listener) {
        this.cityList = cityList;
        this.cityImages = cityImages;
        this.cityInfo = cityInfo;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.bind(cityList.get(position), cityImages.get(position), cityInfo.get(position));
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    // Метод для обновления данных
    public void updateData(List<String> newCityList, List<String> newCityImages, List<String> newCityInfo) {
        this.cityList = newCityList;
        this.cityImages = newCityImages;
        this.cityInfo = newCityInfo;
        notifyDataSetChanged();  // Уведомляем адаптер о том, что данные изменены
    }

    // ViewHolder для отображения города
    public class CityViewHolder extends RecyclerView.ViewHolder {

        private TextView cityName;
        private ImageView cityImage;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.cityName);
            cityImage = itemView.findViewById(R.id.cityImage);

            // Обработка клика по изображению
            cityImage.setOnClickListener(v -> listener.onCityImageClick(cityImages.get(getAdapterPosition())));

            // Обработка клика по элементу списка
            itemView.setOnClickListener(v -> listener.onCityClick(cityList.get(getAdapterPosition()), cityImages.get(getAdapterPosition()), cityInfo.get(getAdapterPosition())));
        }

        // Метод для привязки данных к элементам
        public void bind(String city, String imageUrl, String info) {
            cityName.setText(city);

            // Загружаем изображение с обработкой ошибок
            Glide.with(cityImage.getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)  // Изображение-заполнитель
                    .error(R.drawable.ic_launcher_foreground)              // Изображение при ошибке
                    .into(cityImage);
        }
    }

    // Интерфейс для обработки кликов по городам
    public interface OnCityClickListener {
        void onCityClick(String cityName, String cityImageUrl, String cityInfo);
        void onCityImageClick(String cityImageUrl);
    }
}
