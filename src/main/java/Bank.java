public class Bank {

    private String name;
    private String Bank_id;
    private String City;

    public Bank(String name, String bank_id, String city) {
        this.name = name;
        Bank_id = bank_id;
        City = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBank_id() {
        return Bank_id;
    }

    public void setBank_id(String bank_id) {
        Bank_id = bank_id;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}
