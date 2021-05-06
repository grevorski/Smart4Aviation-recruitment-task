public class Cargo {
    private  int id;
    private  String weightUnit;
    private  int pieces;


    public Cargo(int id, String weightUnit, int pieces) {
        this.id = id;
        this.weightUnit = weightUnit;
        this.pieces = pieces;
    }

    public int getId() {
        return id;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public int getPieces() {
        return pieces;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

}
