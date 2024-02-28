package functions;

import ch.hsr.geohash.GeoHash;
import model.Weather;
import org.apache.spark.api.java.function.MapFunction;

public class GenerateGeohashWeatherFunction implements MapFunction<Weather, Weather> {
    @Override
    public Weather call(Weather weather) throws Exception {
        var geohash = GeoHash.geoHashStringWithCharacterPrecision(weather.getLat(), weather.getLng(), 4);
        weather.setGeo_hash(geohash);
        return weather;
    }
}
