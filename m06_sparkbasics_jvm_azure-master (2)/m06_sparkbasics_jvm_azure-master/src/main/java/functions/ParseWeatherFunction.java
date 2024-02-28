package functions;

import model.Weather;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Row;

public class ParseWeatherFunction implements MapFunction<Row, Weather> {
    @Override
    public Weather call(Row row) throws Exception{
        return Weather.builder()
                .lng(row.getAs("lng"))
                .lat(row.getAs("lat"))
                .avg_tmpr_f(row.getAs("avg_tmp_f"))
                .avg_tmpr_c(row.getAs("avg_tmp_c"))
                .wthr_date(row.getAs("wthr_date"))
                .year(row.getAs("year"))
                .month(row.getAs("month"))
                .day(row.getAs("day"))
                .build();
    }
}
