public class Customer {

    private int customer_id;
    private String name;
    private String city;

    public Customer(int customer_id, String name, String city) {
        this.customer_id = customer_id;
        this.name = name;
        this.city = city;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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
