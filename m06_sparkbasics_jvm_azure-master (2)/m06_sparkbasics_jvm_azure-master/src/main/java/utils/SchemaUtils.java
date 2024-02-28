package utils;

import lombok.experimental.UtilityClass;
import org.apache.spark.sql.types.StructType;

@UtilityClass
public class SchemaUtils {

    private final String STRING_TYPE = "string";

    private final String DOUBLE_TYPE = "double";


    public static StructType getWeatherSchema() {
        return new StructType()
                .add("lng", DOUBLE_TYPE)
                .add("lat", DOUBLE_TYPE)
                .add("avg_temp_f", DOUBLE_TYPE)
                .add("avg_temp_c", DOUBLE_TYPE)
                .add("wthr_date", STRING_TYPE)
                .add("year", STRING_TYPE)
                .add("month", STRING_TYPE)
                .add("day", STRING_TYPE);


    }
}
