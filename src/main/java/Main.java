import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FlightService flightService = new FlightService()   ;

       System.out.println(flightService.getCargo(6043));
       System.out.println(flightService.getFlights("YYZ"));


    }
}
