package com.vg.repository.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="city_weather_forecast",
    indexes = {
        @Index(name = "i_city_forecast", columnList = "city")
    }
)
public class WeatherForecastStore {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "city")
    private String city;

    @Column(name = "description")
    private String description;

    @Column(name = "dt")
    private long dt;

    @Column(name = "temp")
    private double temp;

    @Column(name = "min_temp")
    private double min_temp;

    @Column(name = "max_temp")
    private double max_temp;

    @Column(name = "modified_date")
    private Date modified_date;

}
