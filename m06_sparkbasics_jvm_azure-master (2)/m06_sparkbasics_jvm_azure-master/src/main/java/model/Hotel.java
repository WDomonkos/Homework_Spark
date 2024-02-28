package model;

import com.clearspring.analytics.stream.cardinality.HyperLogLog;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Hotel {

    private String id;
    private String hotelId;
    private String hotelName;
    private String country;
    private String city;
    private String latitude;
    private String longitude;
    private String geoHash;

}
