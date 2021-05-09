import com.fasterxml.jackson.databind.ObjectMapper;
import data.CargoEntity;
import data.Flight;

import java.io.File;
import java.io.IOException;

public class Deserialization {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Flight[] flight = objectMapper.readValue(new File("src/test/java/resources/flight.json"), Flight[].class);
    private final CargoEntity[] cargoEntities = objectMapper.readValue(new File("src/test/java/resources/cargo.json"), CargoEntity[].class);

    public Deserialization() throws IOException {
    }

    public Flight[] getFlight() {
        return flight;
    }

    public CargoEntity[] getCargoEntities() {
        return cargoEntities;
    }
}
