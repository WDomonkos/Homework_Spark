import functions.ParseHotelDataFunction;
import model.Hotel;
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
                        .as("hotels");
        hotels.show();

        session.stop();
    }
}