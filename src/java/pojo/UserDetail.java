package pojo;

import java.util.Date;

public class UserDetail {
    
    private Integer id;
    private Integer userId; // Assuming this refers to the user associated with these details
    private String address;
    private String phoneNumber;
    private String subdistrict; // Corrected from "substrict"
    private String ward;
    private Date updatedAt; // This will hold the timestamp for when the record was updated

    // Default constructor
    public UserDetail() {
    }

    // Parameterized constructor
    public UserDetail(Integer userId, String address, String phoneNumber, String subdistrict, String ward, Date updatedAt) {
        this.userId = userId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.subdistrict = subdistrict;
        this.ward = ward;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSubdistrict() {
        return subdistrict;
    }

    public void setSubdistrict(String subdistrict) {
        this.subdistrict = subdistrict;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", userId=" + userId +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", subdistrict='" + subdistrict + '\'' +
                ", ward='" + ward + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
    