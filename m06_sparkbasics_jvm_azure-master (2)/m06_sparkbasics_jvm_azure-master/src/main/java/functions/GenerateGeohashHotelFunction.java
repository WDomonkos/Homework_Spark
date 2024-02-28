package functions;

import ch.hsr.geohash.GeoHash;
import model.Hotel;
import org.apache.spark.api.java.function.MapFunction;

public class GenerateGeohashHotelFunction implements MapFunction<Hotel, Hotel> {

    @Override
    public Hotel call(Hotel hotel) throws Exception{
        var geohash = GeoHash.geoHashStringWithCharacterPrecision(hotel.getParsetLat(), hotel.getParsetLng(), 4);
        hotel.setGeoHash(geohash);
        return hotel;
    }

}
