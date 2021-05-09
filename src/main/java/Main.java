import java.io.IOException;
import java.text.ParseException;
import java.util.Date;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        FlightService flightService = new FlightService();
        Deserialization deserialization = new Deserialization();

        Date date = deserialization.getFlight()[1].getDepartureDate();
        System.out.println(flightService.getCargo(4147,date));
        System.out.println(flightService.getFlights("YYZ",date));

    }
}
