package com.example.myapplication;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONException;
public class WeatherAPIClient {

    public static HashMap<String, String> getWeatherData(String cityName) {

        String WEATHER_URL = "https://api.weatherapi.com/v1/forecast.json?key=81d03b35d6634681bb7162126242210&q=" + cityName;
        try {
            URI uri = new URI(WEATHER_URL);
            URL url = uri.toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

//            int responseCode = conn.getResponseCode();
//            System.out.println("Response Code : " + responseCode);

            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return filterData(new JSONObject(response.toString()));
            } finally {
                conn.disconnect();
            }
        }
        catch (FileNotFoundException e) {
            return createError("City not found");
        }
        catch (UnknownHostException e) {
            return createError("No internet connection");
        }
        catch (Exception e) {
            return createError("Something went wrong");
        }
    }

    private static HashMap<String, String> filterData(JSONObject res) throws JSONException {
        HashMap<String, String> map = new HashMap<>();

        String city = res.getJSONObject("location").getString("name");
        JSONObject current = res.getJSONObject("current");
        JSONObject forecast = res.getJSONObject("forecast").getJSONArray("forecastday").
                getJSONObject(0).getJSONObject("day");
        JSONObject astro = res.getJSONObject("forecast").getJSONArray("forecastday").
                getJSONObject(0).getJSONObject("astro");

        map.put("City", city);
        map.put("City condition", current.getJSONObject("condition").getString("text"));
        map.put("Current temperature", current.getDouble("temp_c") + "°C");
        map.put("Feels like", "Feels like: "+current.getDouble("feelslike_c")+"°C");
        map.put("High", "High: "+forecast.getDouble("maxtemp_c")+"°C" );
        map.put("Low", "Low: "+forecast.getDouble("mintemp_c")+"°C");
        map.put("Wind", "Wind\n"+current.getDouble("wind_kph")+" km/h");
        map.put("UV", "UV Index\n"+current.getDouble("uv"));
        map.put("Humidity", "Humidity\n"+current.getDouble("humidity"));
        map.put("Sunrise", "Sunrise: "+astro.getString("sunrise")+"\n");
        map.put("Sunset", "Sunset: "+astro.getString("sunset")+"\n");

        return map;
    }

    private static HashMap<String, String> createError(String errorMessage) {
        HashMap<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", errorMessage);
        return errorMap;
    }
}
