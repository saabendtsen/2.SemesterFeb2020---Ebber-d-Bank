public class Customer {

    private String customer_id;
    private String name;
    private String city;

    public Customer(String customer_id, String name, String city) {
        customer_id = customer_id;
        this.name = name;
        this.city = city;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
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
