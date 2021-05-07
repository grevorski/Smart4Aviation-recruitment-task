import java.util.Date;

public class Flight {
    private int flightId;
    private int flightNumber;
    private String departureAirportIATACode;
    private String arrivalAirportIATACode;
    private Date departureDate;


    public Flight() {
    }

    public int getFlightId() {
        return this.flightId;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureAirportIATACode() {
        return departureAirportIATACode;
    }

    public String getArrivalAirportIATACode() {
        return arrivalAirportIATACode;
    }

    public Date getDepartureDate() {
        return (Date) this.departureDate.clone();
    }


}
