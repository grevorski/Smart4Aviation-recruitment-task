public class Baggage {

    private Long id;
    private int weight;
    private WeightUnit weightUnit;
    private int pieces;
    private CargoEntity cargoEntity;


    public Baggage() {
    }


    public Long getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public WeightUnit getWeightUnit() {
        return weightUnit;
    }

    public int getPieces() {
        return pieces;
    }

    public CargoEntity getCargoEntity() {
        return cargoEntity;
    }

}
