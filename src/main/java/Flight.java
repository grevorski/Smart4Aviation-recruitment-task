import java.util.Date;

public class Flight {
    final int flightId;
    final int flightNumber;
    final String departureAirportIATACode;
    final String arrivalAirportIATACode;
    final Date departureDate;

    public Flight(int flightId, int flightNumber, String departureAirportIATACode, String arrivalAirportIATACode, Date departureDate) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departureAirportIATACode = departureAirportIATACode;
        this.arrivalAirportIATACode = arrivalAirportIATACode;
        this.departureDate = departureDate;
    }


}
