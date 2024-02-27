import org.apache.spark.sql.SparkSession;

public class Main {

    public static void main(String[] args) {
        var session = SparkSession.builder()
                .appName("Spark Homework Demo")
                .master("local[*]")
                .getOrCreate();

        session.stop();
    }
}