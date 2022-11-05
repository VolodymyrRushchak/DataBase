package com.rushchak.springbootlab.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "home_address", schema = "rushchak", catalog = "")
public class HomeAddress {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "state")
    private String state;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "street_name")
    private String streetName;
    @Basic
    @Column(name = "house_number")
    private String houseNumber;
    @OneToMany(mappedBy = "homeAddress")
    private List<Client> clients;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeAddress that = (HomeAddress) o;
        return Objects.equals(id, that.id) && Objects.equals(country, that.country) && Objects.equals(state, that.state) && Objects.equals(city, that.city) && Objects.equals(streetName, that.streetName) && Objects.equals(houseNumber, that.houseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, state, city, streetName, houseNumber);
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
