package functions;

import client.GeocoderClient;
import model.Hotel;
import org.apache.spark.api.java.function.MapFunction;

public class CheckCoordinatesFunction implements MapFunction<Hotel, Hotel> {

    @Override
    public Hotel call(Hotel hotel) throws Exception{
        try{
            Double.parseDouble(hotel.getLatitude());
            Double.parseDouble(hotel.getLongitude());
        } catch (Exception e){
            var coords = GeocoderClient.INSTANCE.requestCoordinates(
                    hotel.getHotelName(),
                    hotel.getCity(),
                    hotel.getCountry()
            );

            hotel.setLatitude(String.valueOf(coords.getLatitude()));
            hotel.setLongitude(String.valueOf(coords.getLongitude()));
        }

        return hotel;
    }
}
