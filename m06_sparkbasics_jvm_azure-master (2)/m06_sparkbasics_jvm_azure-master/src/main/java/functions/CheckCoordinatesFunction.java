package functions;

import client.GeocoderClient;
import model.Hotel;
import org.apache.spark.api.java.function.MapFunction;

public class CheckCoordinatesFunction implements MapFunction<Hotel, Hotel> {

    @Override
    public Hotel call(Hotel hotel) throws Exception{
        try{
            var parsetLat = Double.parseDouble(hotel.getLatitude());
            var parsetLng = Double.parseDouble(hotel.getLongitude());

            hotel.setParsetLat(parsetLat);
            hotel.setParsetLng(parsetLng);
        } catch (Exception e){
            var coords = GeocoderClient.INSTANCE.requestCoordinates(
                    hotel.getHotelName(),
                    hotel.getCity(),
                    hotel.getCountry()
            );

            hotel.setParsetLat(coords.getLatitude());
            hotel.setParsetLng(coords.getLongitude());
        }

        return hotel;
    }
}
