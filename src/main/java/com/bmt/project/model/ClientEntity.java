package com.bmt.project.model;

import java.util.Objects;

/**
 *
 * @author Thibault
 */
public class ClientEntity {
    
    private String code;
    private String company;
    private String contact;
    private String role;
    private String address;
    private String city;
    private String region;
    private String zipCode;
    private String country;
    private String phone;
    private String fax;

    public ClientEntity(String code, String company, String contact, String role, String address, String city, String region, String zipCode, String country, String phone, String fax) {
        this.code = code;
        this.company = company;
        this.contact = contact;
        this.role = role;
        this.address = address;
        this.city = city;
        this.region = region;
        this.zipCode = zipCode;
        this.country = country;
        this.phone = phone;
        this.fax = fax;
    }

    @Override
    public String toString() {
        return "ClientEntity{" + "code=" + code + ", company=" + company + ", contact=" + contact + ", role=" + role + ", address=" + address + ", city=" + city + ", region=" + region + ", zipCode=" + zipCode + ", country=" + country + ", phone=" + phone + ", fax=" + fax + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.code);
        hash = 47 * hash + Objects.hashCode(this.company);
        hash = 47 * hash + Objects.hashCode(this.contact);
        hash = 47 * hash + Objects.hashCode(this.role);
        hash = 47 * hash + Objects.hashCode(this.address);
        hash = 47 * hash + Objects.hashCode(this.city);
        hash = 47 * hash + Objects.hashCode(this.region);
        hash = 47 * hash + Objects.hashCode(this.zipCode);
        hash = 47 * hash + Objects.hashCode(this.country);
        hash = 47 * hash + Objects.hashCode(this.phone);
        hash = 47 * hash + Objects.hashCode(this.fax);
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
        final ClientEntity other = (ClientEntity) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.company, other.company)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
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
        if (!Objects.equals(this.zipCode, other.zipCode)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.fax, other.fax)) {
            return false;
        }
        return true;
    }
    
}
