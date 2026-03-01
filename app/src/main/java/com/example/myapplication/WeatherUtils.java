package com.example.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class WeatherUtils {
    public static void setWeatherConditionIcon(Context context, TextView condition, String weatherCondition) {
        Drawable weatherConditionIcon;

        if(weatherCondition.contains("sun") || weatherCondition.contains("bright"))
            weatherConditionIcon = ContextCompat.getDrawable(context, R.drawable.sunny);
        else if(weatherCondition.contains("wind") || weatherCondition.contains("gust") || weatherCondition.contains("air")
                || weatherCondition.contains("breeze"))
            weatherConditionIcon = ContextCompat.getDrawable(context, R.drawable.windy);
        else if(weatherCondition.contains("cloud") || weatherCondition.contains("overcast"))
            weatherConditionIcon = ContextCompat.getDrawable(context, R.drawable.cloudy);
        else if(weatherCondition.contains("rain") || weatherCondition.contains("shower") || weatherCondition.contains("drizzle"))
            weatherConditionIcon = ContextCompat.getDrawable(context, R.drawable.rainy);
        else if(weatherCondition.contains("storm") || weatherCondition.contains("thunder") || weatherCondition.contains("lightning"))
            weatherConditionIcon = ContextCompat.getDrawable(context, R.drawable.storm);
        else if(weatherCondition.contains("fog") || weatherCondition.contains("smog") || weatherCondition.contains("mist")
                || weatherCondition.contains("haze"))
            weatherConditionIcon = ContextCompat.getDrawable(context, R.drawable.fog);
        else if(weatherCondition.contains("snow") || weatherCondition.contains("hail") || weatherCondition.contains("sleet")
                || weatherCondition.contains("flakes"))
            weatherConditionIcon = ContextCompat.getDrawable(context, R.drawable.snowy);
        else
            weatherConditionIcon = ContextCompat.getDrawable(context, R.drawable.normal);

        assert weatherConditionIcon != null;
        weatherConditionIcon.setBounds(0, 0, 600, 600);
        condition.setCompoundDrawables(null, weatherConditionIcon, null, null);
        condition.setCompoundDrawablePadding(8);
    }

    public static void setWindIcon(Context context, TextView wind) {
        Drawable windIcon = ContextCompat.getDrawable(context, R.drawable.wind);
        assert windIcon != null;
        windIcon.setBounds(0, 0, 120, 120);
        wind.setCompoundDrawables(null, windIcon, null, null);
        wind.setCompoundDrawablePadding(8);
    }

    public static void setUVIcon(Context context, TextView uv) {
        Drawable uvIcon = ContextCompat.getDrawable(context, R.drawable.uv);
        assert uvIcon != null;
        uvIcon.setBounds(0, 0, 120, 120);
        uv.setCompoundDrawables(null, uvIcon, null, null);
        uv.setCompoundDrawablePadding(8);
    }

    public static void setHumidityIcon(Context context, TextView humidity) {
        Drawable humidityIcon = ContextCompat.getDrawable(context, R.drawable.humidity);
        assert humidityIcon != null;
        humidityIcon.setBounds(0, 0, 120, 120);
        humidity.setCompoundDrawables(null, humidityIcon, null, null);
        humidity.setCompoundDrawablePadding(8);
    }

    public static void setSunriseIcon(Context context, TextView sunrise) {
        Drawable sunriseIcon = ContextCompat.getDrawable(context, R.drawable.sunrise);
        assert sunriseIcon != null;
        sunriseIcon.setBounds(0, 0, 240, 240);
        sunrise.setCompoundDrawables(null, null, null, sunriseIcon);
        sunrise.setCompoundDrawablePadding(8);
    }

    public static void setSunsetIcon(Context context, TextView sunset) {
        Drawable sunsetIcon = ContextCompat.getDrawable(context, R.drawable.sunset);
        assert sunsetIcon != null;
        sunsetIcon.setBounds(0, 0, 240, 240);
        sunset.setCompoundDrawables(null, null, null, sunsetIcon);
        sunset.setCompoundDrawablePadding(8);
    }
}
