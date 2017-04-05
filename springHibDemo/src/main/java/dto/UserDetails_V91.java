package dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DETAILS")
public class UserDetails_V91 {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "USER_NAME")
    private String userName;

    /* cascade the vehicle object while saving. No explicit vehicle object saving is needed now. */
    @OneToMany(cascade = CascadeType.PERSIST)
    private Collection<Vehicle_V91> vehicles = new ArrayList<Vehicle_V91>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Collection<Vehicle_V91> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Collection<Vehicle_V91> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(Vehicle_V91 vehicle) {
        this.getVehicles().add(vehicle);
    }

}
