import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

public class CargoEntity{

    private Long flightId;
    @JsonProperty("baggage")  private List<Baggage> baggages;
    @JsonProperty("cargo") private List<Cargo> cargos;


    public CargoEntity() {
    }


    public Long getFlightId() {
        return flightId;
    }

    public List<Baggage> getBaggages() {
        return baggages;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }
}
