package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Weather {

    private Double lng;
    private Double lat;
    private Double avg_tmp_f;
    private Double avg_tmpr_c;
    private String wthr_date;
    private String year;
    private String month;
    private String day;
    private String geo_hash;

}
