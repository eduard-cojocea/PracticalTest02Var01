package pdsd.systems.cs.pub.ro.practicaltest02var01.model;

import pdsd.systems.cs.pub.ro.practicaltest02var01.general.Constants;

/**
 * Created by iedi on 18-May-15.
 */
public class WeatherForecastInformation {

    private String temperature;
    private String humidity;

    public WeatherForecastInformation() {
        this.temperature = null;
        this.humidity    = null;
    }

    public WeatherForecastInformation(
            String temperature,
            String humidity) {
        this.temperature = temperature;
        this.humidity    = humidity;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getHumidity() {
        return humidity;
    }

    @Override
    public String toString() {
        return Constants.TEMPERATURE + ": " + temperature + ", " +
                Constants.HUMIDITY + ": " + humidity;
    }

}