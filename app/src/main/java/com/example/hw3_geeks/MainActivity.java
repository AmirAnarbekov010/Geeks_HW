package com.example.hw3_geeks;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
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
        cityAdapter = new CityAdapter(cities);

        recyclerViewContinents.setAdapter(continentAdapter);
        recyclerViewCountries.setAdapter(countryAdapter);
        recyclerViewCities.setAdapter(cityAdapter);

        // Сначала показываем только континенты
        recyclerViewCountries.setVisibility(View.GONE);
        recyclerViewCities.setVisibility(View.GONE);
    }

    private void showCountriesForContinent(String continent) {
        // Здесь можно подставить логику для фильтрации стран по континенту
        // Например, для "Евразия" показать Казахстан и Россию
        countries = continent.equals("Евразия") ? Arrays.asList("Казахстан", "Россия") : Arrays.asList("США", "Бразилия");
        countryAdapter = new CountryAdapter(countries, country -> showCitiesForCountry(country));
        recyclerViewCountries.setAdapter(countryAdapter);

        recyclerViewContinents.setVisibility(View.GONE);
        recyclerViewCountries.setVisibility(View.VISIBLE);
        recyclerViewCities.setVisibility(View.GONE);
    }

    private void showCitiesForCountry(String country) {
        cities = country.equals("Казахстан") ? Arrays.asList("Алматы", "Нур-Султан") :
                country.equals("Россия") ? Arrays.asList("Москва", "Санкт-Петербург") :
                        Arrays.asList("Нью-Йорк", "Лос-Анджелес");

        cityAdapter = new CityAdapter(cities);
        recyclerViewCities.setAdapter(cityAdapter);

        recyclerViewContinents.setVisibility(View.GONE);
        recyclerViewCountries.setVisibility(View.GONE);
        recyclerViewCities.setVisibility(View.VISIBLE);
    }
}
