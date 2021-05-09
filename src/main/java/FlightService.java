import data.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightService {
    private final static double LB_TO_KG = 0.45359237;
    private final Deserialization deserialization = new Deserialization();

    public FlightService() throws IOException {
    }


    public String getCargo(int flightNumber, Date date) {

        Flight ourFlight = null;
        for (Flight f : deserialization.getFlight()) {
            int value = f.getFlightNumber();
            if (value == flightNumber && f.getDepartureDate().equals(date)  ) {
                ourFlight = f;
                break;
            }
        }
        if(ourFlight == null) return "flight not found";

        int index = ourFlight.getFlightId();
        double baggageWeight = 0;
        List<Baggage> baggage = deserialization.getCargoEntities()[index].getBaggages();

        for (Baggage b : baggage) {
            if (WeightUnit.lb.equals(b.getWeightUnit())) {
                baggageWeight += b.getWeight() * LB_TO_KG;
            } else baggageWeight += b.getWeight();
        }
        double cargoWeight = 0;

        List<Cargo> cargos = deserialization.getCargoEntities()[index].getCargos();

        for (Cargo c : cargos) {
            if (WeightUnit.lb.equals(c.getWeightUnit())) {
                cargoWeight += c.getWeight() * LB_TO_KG;
            } else cargoWeight += c.getWeight();
        }
        double TotalWeight = cargoWeight + baggageWeight;

        return "Baggage Weight: " + baggageWeight + "kg  \nCargo Weight: " + cargoWeight + "kg  \nTotal Weight: " + TotalWeight + "kg";
    }


    public String getFlights(String code , Date date) {

        List<Flight> departureFlights = new ArrayList<>();
        List<Flight> arrivingFlights = new ArrayList<>();

        int numberOfDeparting = 0;
        int numberOfArriving = 0;

        for (Flight f : deserialization.getFlight()) {
            String departingCode = f.getDepartureAirportIATACode();
            String arrivingCode = f.getArrivalAirportIATACode();
            Date fDate = f.getDepartureDate();
            if (departingCode.equals(code) && fDate.equals(date)) {
                departureFlights.add(f);
                numberOfDeparting++;
            }
            if(arrivingCode.equals(code) && fDate.equals(date)) {
                arrivingFlights.add(f);
                numberOfArriving++;
            }
        }
        if(numberOfArriving == 0 && numberOfDeparting == 0 ) return "No flights arriving or departing to the airport with code: " + code + " at " + date;

        int arrivalBaggage = 0;
        int departureBaggage = 0;

        for (Flight f : departureFlights) {
            List<Baggage> baggage = deserialization.getCargoEntities()[f.getFlightId()].getBaggages();
            for (Baggage b : baggage) {
                departureBaggage += b.getPieces();
            }
        }
        for (Flight f : arrivingFlights) {

            List<Baggage> baggage = deserialization.getCargoEntities()[f.getFlightId()].getBaggages();
            for (Baggage b : baggage) {
                arrivalBaggage += b.getPieces();
            }
        }

        return "Number Of Departing flights: " + numberOfDeparting + " \nNumber Of Arriving flights: " + numberOfArriving +
                " \nNumber Of Departing data.Baggage: " + departureBaggage + " \nNumber Of Arriving baggage: " + arrivalBaggage + " to " + code;
    }


    }
