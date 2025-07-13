package com.weather_project.statistics;

import com.weather_project.consumer.model.domain.DateParser;
import com.weather_project.consumer.model.domain.WeatherData;

import java.util.*;

public class Statistics {

    public static void getWeeklyStatistics(List<WeatherData> weeklyData) {
        if (weeklyData.isEmpty()) {
            System.out.println("No statistics yet.");
            return;
        }
        HashMap<String, List<WeatherData>> cityData = new HashMap<>();

        for (var data : weeklyData) {
            if (!cityData.containsKey(data.getCity())) {
                cityData.put(data.getCity(), new ArrayList<>());
            }
            cityData.get(data.getCity()).add(data);
        }

        WeatherData hottest = weeklyData.get(0);

        long maxDaysWithRain = 0;
        String cityWithMaxDaysWithRain = "";

        double lowestAvgTemperature = Double.MAX_VALUE;
        String cityWithLowestAvgTemperature = "";

        for (var entry : cityData.entrySet()) {
            String city = entry.getKey();
            System.out.println("------------- " + city + " -------------");
            List<WeatherData> cityWeatherData = entry.getValue();

            IntSummaryStatistics stats = cityWeatherData.stream().mapToInt(WeatherData::getTemperature).summaryStatistics();
            double avg = stats.getAverage();
            System.out.println("Avg temperature: " + avg);
            if (avg < lowestAvgTemperature) {
                cityWithLowestAvgTemperature = city;
                lowestAvgTemperature = avg;
            }

            WeatherData hottestDay = cityWeatherData.stream().max(Comparator.comparingInt(WeatherData::getTemperature)).get();
            System.out.println("Max temperature: " + hottestDay.getTemperature());
            System.out.println("Hottest day: " + hottestDay.getFixationTime());
            if (hottestDay.getTemperature() > hottest.getTemperature()) {
                hottest = hottestDay;
            }

            WeatherData coldestDay = cityWeatherData.stream().min(Comparator.comparingInt(WeatherData::getTemperature)).get();
            System.out.println("Min temperature: " + coldestDay.getTemperature());
            System.out.println("Coldest day: " + coldestDay.getFixationTime());

            long daysWithRain = cityWeatherData.stream().filter(data -> data.getWeatherState().equals("RAIN")).count();
            System.out.println("Days with rain:" + daysWithRain);
            if (daysWithRain > maxDaysWithRain) {
                maxDaysWithRain = daysWithRain;
                cityWithMaxDaysWithRain = city;
            }
        }

        System.out.println("------------- Overall -------------");
        System.out.println("Max days with rain: " + maxDaysWithRain + ". Was in city: " + cityWithMaxDaysWithRain);
        System.out.println("Lowest average temperature: " + lowestAvgTemperature + ". Was in city: " + cityWithLowestAvgTemperature);
        System.out.println("Hottest day: " + DateParser.dateToString(hottest.getFixationTime()) + ". Temperature: " + hottest.getTemperature() + ". Was in city: " + hottest.getCity());
    }
}
