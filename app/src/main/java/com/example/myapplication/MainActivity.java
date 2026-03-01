package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText inputCity;
    private TextView cityName, cityCondition, currentTemp, minTemp, maxTemp, feelsLikeTemp, wind, uvIndex, humidity, sunrise, sunset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inputCity = findViewById(R.id.inputCity);
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String city = inputCity.getText().toString().trim().replace(" ", "+");
                if(city.isEmpty()) {
                    Toast.makeText(MainActivity.this, "No city name provided", Toast.LENGTH_LONG).show();
                    return;
                }

                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                FrameLayout frameLayout = findViewById(R.id.frameLayout);
                View weatherLayout = inflater.inflate(R.layout.weather_layout, frameLayout, false);

                new Thread(() -> {
                    HashMap<String, String> weatherData = WeatherAPIClient.getWeatherData(city);

                    if(weatherData.containsKey("Error")) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, weatherData.get("Error"), Toast.LENGTH_LONG).show());
                        return;
                    }

                    runOnUiThread(() -> {
                        cityName = weatherLayout.findViewById(R.id.cityName);
                        cityCondition = weatherLayout.findViewById(R.id.cityCondition);
                        currentTemp = weatherLayout.findViewById(R.id.currentTemp);
                        maxTemp = weatherLayout.findViewById(R.id.maxTemp);
                        minTemp = weatherLayout.findViewById(R.id.minTemp);
                        feelsLikeTemp = weatherLayout.findViewById(R.id.feelsLikeTemp);
                        wind = weatherLayout.findViewById(R.id.wind);
                        uvIndex = weatherLayout.findViewById(R.id.uvIndex);
                        humidity = weatherLayout.findViewById(R.id.humidity);
                        sunrise = weatherLayout.findViewById(R.id.sunrise);
                        sunset = weatherLayout.findViewById(R.id.sunset);


                        cityName.setText(weatherData.get("City"));
                        cityCondition.setText(weatherData.get("City condition"));
                        currentTemp.setText(weatherData.get("Current temperature"));
                        maxTemp.setText(weatherData.get("High"));
                        minTemp.setText(weatherData.get("Low"));
                        feelsLikeTemp.setText(weatherData.get("Feels like"));
                        wind.setText(weatherData.get("Wind"));
                        uvIndex.setText(weatherData.get("UV"));
                        humidity.setText(weatherData.get("Humidity"));
                        sunrise.setText(weatherData.get("Sunrise"));
                        sunset.setText(weatherData.get("Sunset"));

                        WeatherUtils.setWeatherConditionIcon(MainActivity.this, cityCondition,
                                cityCondition.getText().toString().toLowerCase());
                        WeatherUtils.setWindIcon(MainActivity.this, wind);
                        WeatherUtils.setUVIcon(MainActivity.this, uvIndex);
                        WeatherUtils.setHumidityIcon(MainActivity.this, humidity);
                        WeatherUtils.setSunriseIcon(MainActivity.this, sunrise);
                        WeatherUtils.setSunsetIcon(MainActivity.this, sunset);

                        frameLayout.addView(weatherLayout);
                    });
                }).start();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}