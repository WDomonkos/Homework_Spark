import functions.*;
import model.Hotel;
import model.Weather;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import utils.SchemaUtils;

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
                        .map(new GenerateGeohashHotelFunction(), Encoders.bean(Hotel.class))
                        .as("hotels");
        hotels.show();

        var weather = session.read()
                        .schema(SchemaUtils.getWeatherSchema())
                        .parquet("input/weather/")
                        .map(new ParseWeatherFunction(), Encoders.bean(Weather.class))
                        .map(new GenerateGeohashWeatherFunction(), Encoders.bean(Weather.class))
                        .as("weather");
        weather.show();

        var joined = hotels.join(weather, weather.col("geo_hash").equalTo(hotels.col("geoHash")), "left");
        joined.show();

        joined.write()
                        .partitionBy("year", "month")
                        .parquet(String.format("output/%s", session.sparkContext().applicationId()));



        session.stop();
    }
}