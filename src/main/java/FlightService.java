import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightService {
    private final static double LB_TO_KG = 0.45359237;

    final ObjectMapper objectMapper = new ObjectMapper();
    final Flight[] flight = objectMapper.readValue(new File("src/test/java/resources/flight.json"), Flight[].class);
    final CargoEntity[] cargoEntities = objectMapper.readValue(new File("src/test/java/resources/cargo.json"),CargoEntity[].class);

    public FlightService() throws IOException {
    }

    public String getCargo(int flightNumber) {

        Flight ourFlight = null;
        for (Flight f : flight) {
            int value = f.getFlightNumber();
            if (value == flightNumber ) {
                ourFlight = f;
                break;
            }
        }
        if(ourFlight == null) return "flight not found";

        int index = ourFlight.getFlightId();
        double baggageWeight = 0;
        List<Baggage> baggage = cargoEntities[index].getBaggages();

        for (Baggage b : baggage) {
            if (WeightUnit.lb.equals(b.getWeightUnit())) {
                baggageWeight += b.getWeight() * LB_TO_KG;
            } else baggageWeight += b.getWeight();
        }
        double cargoWeight = 0;

        List<Cargo> cargos = cargoEntities[index].getCargos();

        for (Cargo c : cargos) {
            if (WeightUnit.lb.equals(c.getWeightUnit())) {
                cargoWeight += c.getWeight() * LB_TO_KG;
            } else cargoWeight += c.getWeight();
        }
        double TotalWeight = cargoWeight + baggageWeight;

        return "baggageWeight: " + baggageWeight + " cargoWeight: " + cargoWeight + " TotalWeight: " + TotalWeight;
    }


    public String getFlights(String code) {

        List<Flight> departureFlights = new ArrayList<>();
        List<Flight> arrivingFlights = new ArrayList<>();

        int numberOfDeparting = 0;
        int numberOfArriving = 0;

        for (Flight f : flight) {
            String departingCode = f.getDepartureAirportIATACode();
            String arrivingCode = f.getArrivalAirportIATACode();
            if (departingCode.equals(code)) {
                departureFlights.add(f);
                numberOfDeparting++;
            }
            if(arrivingCode.equals(code)) {
                arrivingFlights.add(f);
                numberOfArriving++;
            }
        }

        int arrivalBaggage = 0;
        int departureBaggage = 0;

        for (Flight f : departureFlights) {
            List<Baggage> baggage = cargoEntities[f.getFlightId()].getBaggages();
            for (Baggage b : baggage) {
                departureBaggage += b.getPieces();
            }
        }
        for (Flight f : arrivingFlights) {

            List<Baggage> baggage = cargoEntities[f.getFlightId()].getBaggages();
            for (Baggage b : baggage) {
                arrivalBaggage += b.getPieces();
            }
        }

        return "number Of Departing flights: " + numberOfDeparting + " ,number Of Arriving flights: " + numberOfArriving +
                " ,number Of Departing Baggage: " + departureBaggage + " ,number Of Arriving baggage: " + arrivalBaggage + " to " + code;
    }

    }
