import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class FlightServiceTest {

    private static FlightService flightService;
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    @BeforeEach
    public void setUp() throws IOException {
        flightService = new FlightService();
    }
    private Date createNewDate(String s){
        Date date = null;
        try {
            date = formatter.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Test
    @DisplayName("getCargo method with correct parameters passed")
    public void getCargo_CorrectParameters(){
        Date date = createNewDate("2015-03-18T10:37:13-01:00");

        assertEquals("Baggage Weight: 3900.7243376kg  \n" + "Cargo Weight: 2716.0kg  \n"
                + "Total Weight: 6616.724337600001kg", flightService.getCargo(6043,date));
    }

    @Test
    @DisplayName("getCargo method with not existing flightNumber")
    public void getCargo_NotExistingFlightNumber(){
        Date date = createNewDate("2015-03-18T10:37:13-01:00");

        assertEquals("flight not found", flightService.getCargo(1,date));
    }

    @Test
    @DisplayName("getCargo method with not existing date")
    public void getCargo_NotExistingDate(){
        Date date = createNewDate("2012-03-18T10:37:13-01:00");

        assertEquals("flight not found", flightService.getCargo(6043,date));
    }

    @Test
    @DisplayName("getFlight method with correct parameters")
    public void getFlight_CorrectParameters(){
        Date date = createNewDate("2018-07-21T04:37:00-02:00");

        assertEquals("Number Of Departing flights: 0 \n" + "Number Of Arriving flights: 1 \n" + "Number Of Departing data.Baggage: 0 \n"
                + "Number Of Arriving baggage: 2235 to PPX", flightService.getFlights("PPX",date));
    }

    @Test
    @DisplayName("getFlight method with code as empty string")
    public void getFlight_EmptyString(){
        Date date = createNewDate("2018-07-21T04:37:00-02:00");

        assertEquals("You did not pass airport code", flightService.getFlights("",date));
    }

    @Test
    @DisplayName("getFlight method with not existing code")
    public void getFlight_NotExistingAirPortCode(){
        Date date = createNewDate("2018-07-21T04:37:00-02:00");

        assertEquals("No flights arriving or departing to the airport with code: xdd at Sat Jul 21 08:37:00 CEST 2018", flightService.getFlights("xdd",date));
    }

    @Test
    @DisplayName("getFlight method with not existing date")
    public void getFlight_NotExistingDate(){
        Date date = createNewDate("2012-03-18T10:37:13-01:00");

        assertEquals("No flights arriving or departing to the airport with code: YYT at Sun Mar 18 12:37:13 CET 2012", flightService.getFlights("YYT",date));
    }
}
