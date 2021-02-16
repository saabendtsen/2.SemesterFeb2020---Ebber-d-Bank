public class Costumer {

    private String costumer_id;
    private String name;
    private String city;

    public Costumer(String costumer_id, String name, String city) {
        costumer_id = costumer_id;
        this.name = name;
        this.city = city;
    }

    public String getCostumer_id() {
        return costumer_id;
    }

    public void setCostumer_id(String costumer_id) {
        this.costumer_id = costumer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
