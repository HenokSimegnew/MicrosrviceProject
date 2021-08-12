package customers.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Customer {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;

    private Address address;

    public Customer(String Id, String firstname, String lastname, String email, String phone) {
        this.id = Id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }
    public Customer() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getId() {
        return id;
    }

    public void setId(String customerNumber) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String toString() {
        String result= "customer: "+ id +" , "+firstname+" , "+lastname+" , "+email+" , "+phone;
        if (address != null) {
            result+="\n"+address;
        }

        return result;
    }
}
