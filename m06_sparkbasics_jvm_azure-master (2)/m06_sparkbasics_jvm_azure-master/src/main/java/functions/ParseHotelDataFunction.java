package functions;

import model.Hotel;
import org.apache.spark.sql.Row;
import org.apache.spark.api.java.function.MapFunction;

public class ParseHotelDataFunction implements MapFunction<Row, Hotel> {
    @Override
    public Hotel call(Row row) throws Exception{
        return Hotel.builder()
                .id(row.getAs("id"))
                .hotelId(row.getAs("hotel_id"))
                .hotelName(row.getAs("hotel_name"))
                .country(row.getAs("country"))
                .city(row.getAs("city"))
                .latitude(row.getAs("lat"))
                .longitude(row.getAs("lng"))
                .build();
    }
}
