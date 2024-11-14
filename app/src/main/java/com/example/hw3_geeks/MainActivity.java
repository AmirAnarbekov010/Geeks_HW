package com.example.hw3_geeks;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewContinents, recyclerViewCountries, recyclerViewCities;
    private ContinentAdapter continentAdapter;
    private CountryAdapter countryAdapter;
    private CityAdapter cityAdapter;

    private List<String> continents = Arrays.asList("Евразия", "Африка", "Америка", "Австралия", "Антарктида");
    private List<String> countries = Arrays.asList("Казахстан", "Россия");

    private List<String> cities = Arrays.asList("Алматы", "Нур-Султан", "Москва", "Санкт-Петербург", "Нью-Йорк", "Лос-Анджелес", "Пекин", "Шанхай", "Дели", "Мумбаи");
    private List<String> cityImages = Arrays.asList(
            "https://knews.kg/wp-content/uploads/2019/03/Almaty.jpg",
            "https://media-1obl-ru.storage.yandexcloud.net/resize_cache/757427/83132dad08c79bfbcc1d891fdcdbb658/iblock/76b/76b803eb64655c10336f8c569a776a82.jpg",
           " https://7d9e88a8-f178-4098-bea5-48d960920605.selcdn.net/baf0d0f3-d77a-4d0b-b615-b9f86c9432bd/-/format/auto/-/quality/smart_retina/-/stretch/off/-/resize/1900x/",
           "https://sokroma.ru/upload/resize_cache/webp/iblock/a32/8kwc1yej314rkkzlslt614nsrwk8iwrp.webp",
            "https://cdn.tripster.ru/thumbs2/3df6eb14-3a42-11ee-bc4b-aa34287e0de7.1220x600.jpeg",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ58i--MjNMvXTDt2L4nMbPbYIwpsTItUHA0A&s",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTORvc9Y5luhqSY-NMMlYwLANsGzW3xzEPuxA&s",
            "https://i.ytimg.com/vi/F5McqwPNnME/hqdefault.jpg",
            "https://www.cathaypacific.com/content/dam/destinations/delhi/cityguide-gallery/delhi-humayun%27stomb-920x500.jpg",
            "https://worldpodium.ru/sites/default/files/styles/original/public/1456336852_4.jpg?itok=5iX0YCwX"
    );
    private List<String> cityInfo = Arrays.asList(
            "Алматы - крупнейший город Казахстана.",
            "Нур-Султан - столица Казахстана.",
            "Москва - столица России."
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewContinents = findViewById(R.id.recyclerViewContinents);
        recyclerViewCountries = findViewById(R.id.recyclerViewCountries);
        recyclerViewCities = findViewById(R.id.recyclerViewCities);

        recyclerViewContinents.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCountries.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCities.setLayoutManager(new LinearLayoutManager(this));

        continentAdapter = new ContinentAdapter(continents, continent -> showCountriesForContinent(continent));
        countryAdapter = new CountryAdapter(countries, country -> showCitiesForCountry(country));

        cityAdapter = new CityAdapter(cities, cityImages, cityInfo, new CityAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(String cityName, String cityImageUrl, String cityInfo) {
                // Переход к фрагменту с информацией о городе
                CityDetailFragment fragment = CityDetailFragment.newInstance(cityName, cityImageUrl, cityInfo);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onCityImageClick(String cityImageUrl) {
                // Переход к фрагменту с полноэкранным изображением
                FullScreenImageFragment fragment = FullScreenImageFragment.newInstance(cityImageUrl);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        recyclerViewContinents.setAdapter(continentAdapter);
        recyclerViewCountries.setAdapter(countryAdapter);
        recyclerViewCities.setAdapter(cityAdapter);

        recyclerViewContinents.setVisibility(View.VISIBLE);
        recyclerViewCountries.setVisibility(View.GONE);
        recyclerViewCities.setVisibility(View.GONE);
    }

    private void showCountriesForContinent(String continent) {
        countries = continent.equals("Евразия") ? Arrays.asList("Казахстан", "Россия") : Arrays.asList("США", "Бразилия");
        countryAdapter.updateData(countries);

        recyclerViewContinents.setVisibility(View.GONE);
        recyclerViewCountries.setVisibility(View.VISIBLE);
        recyclerViewCities.setVisibility(View.GONE);
    }

    private void showCitiesForCountry(String country) {
        cities = country.equals("Казахстан") ? Arrays.asList("Алматы", "Нур-Султан") :
                country.equals("Россия") ? Arrays.asList("Москва", "Санкт-Петербург") :
                        Arrays.asList("Нью-Йорк", "Лос-Анджелес");

        cityAdapter.updateData(cities, cityImages, cityInfo);

        recyclerViewContinents.setVisibility(View.GONE);
        recyclerViewCountries.setVisibility(View.GONE);
        recyclerViewCities.setVisibility(View.VISIBLE);
    }
}
