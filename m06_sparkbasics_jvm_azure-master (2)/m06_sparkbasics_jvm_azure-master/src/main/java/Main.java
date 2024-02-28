import functions.CheckCoordinatesFunction;
import functions.GenerateGeohashFunction;
import functions.ParseHotelDataFunction;
import functions.ParseWeatherFunction;
import model.Hotel;
import model.Weather;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.api.java.function.FilterFunction;

public class Main {

    public static void main(String[] args) {
        var session = SparkSession.builder()
                .appName("Spark Homework Demo")
                .master("local[*]")
                .getOrCreate();

        var hotels = session.read()
                        .option("header", true)
                        .csv("input/hotels/")
                        .map(new ParseHotelDataFunction(), Encoders.bean(Hotel.class))
                        .map(new CheckCoordinatesFunction(), Encoders.bean(Hotel.class))
                        .map(new GenerateGeohashFunction(), Encoders.bean(Hotel.class))
                        .as("hotels");
        hotels.show();

        var weather = session.read()
                        .schema(SchemaUtils.getWeatherSchema())
                        .parquet("input/weather/")
                        .map(new ParseWeatherFunction(), Encoders.bean(Weather.class))
                        .as("weather");
        weather.show();

        session.stop();
    }
}