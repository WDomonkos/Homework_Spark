package client;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import model.Coordinates;
import model.Hotel;

import static utils.Constants.API_KEY;

public enum GeocoderClient {

    INSTANCE;

    GeocoderClient(){
        jOpenCageGeocoder = new JOpenCageGeocoder(API_KEY);
    }

    private final JOpenCageGeocoder jOpenCageGeocoder;

    public Coordinates requestCoordinates(String name, String city, String country){
        var queryAddress = String.format("%s, %s, %s", name, city, country);
        var request = new JOpenCageForwardRequest(queryAddress);
        var result = jOpenCageGeocoder.forward(request).getFirstPosition();
        return Coordinates.builder()
                .latitude(result.getLat())
                .longitude(result.getLng())
                .build();
    }



}
