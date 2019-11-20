package com.bmt.project.model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Thibault
 */
public class OrderEntity {
    
    private int num;
    private ClientEntity client;
    private Date dateEnter;
    private Date dateSent;
    private float port;
    private String receiver;
    private String address;
    private String city;
    private String region;
    private String zipcode;
    private String country;
    private float discount;

    public OrderEntity() {
    }

    public OrderEntity(int num, ClientEntity client, Date dateEnter, Date dateSent, float port, String receiver, String address, String city, String region, String zipcode, String country, float discount) {
        this.num = num;
        this.client = client;
        this.dateEnter = dateEnter;
        this.dateSent = dateSent;
        this.port = port;
        this.receiver = receiver;
        this.address = address;
        this.city = city;
        this.region = region;
        this.zipcode = zipcode;
        this.country = country;
        this.discount = discount;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public Date getDateEnter() {
        return dateEnter;
    }

    public void setDateEnter(Date dateEnter) {
        this.dateEnter = dateEnter;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public float getPort() {
        return port;
    }

    public void setPort(float port) {
        this.port = port;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.num;
        hash = 97 * hash + Objects.hashCode(this.client);
        hash = 97 * hash + Objects.hashCode(this.dateEnter);
        hash = 97 * hash + Objects.hashCode(this.dateSent);
        hash = 97 * hash + Float.floatToIntBits(this.port);
        hash = 97 * hash + Objects.hashCode(this.receiver);
        hash = 97 * hash + Objects.hashCode(this.address);
        hash = 97 * hash + Objects.hashCode(this.city);
        hash = 97 * hash + Objects.hashCode(this.region);
        hash = 97 * hash + Objects.hashCode(this.zipcode);
        hash = 97 * hash + Objects.hashCode(this.country);
        hash = 97 * hash + Float.floatToIntBits(this.discount);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderEntity other = (OrderEntity) obj;
        if (this.num != other.num) {
            return false;
        }
        if (Float.floatToIntBits(this.port) != Float.floatToIntBits(other.port)) {
            return false;
        }
        if (Float.floatToIntBits(this.discount) != Float.floatToIntBits(other.discount)) {
            return false;
        }
        if (!Objects.equals(this.receiver, other.receiver)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.region, other.region)) {
            return false;
        }
        if (!Objects.equals(this.zipcode, other.zipcode)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.dateEnter, other.dateEnter)) {
            return false;
        }
        if (!Objects.equals(this.dateSent, other.dateSent)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderEntity{" + "num=" + num + ", client=" + client + ", dateEnter=" + dateEnter + ", dateSent=" + dateSent + ", port=" + port + ", receiver=" + receiver + ", address=" + address + ", city=" + city + ", region=" + region + ", zipcode=" + zipcode + ", country=" + country + ", discount=" + discount + '}';
    }
    
    
    
    
}
