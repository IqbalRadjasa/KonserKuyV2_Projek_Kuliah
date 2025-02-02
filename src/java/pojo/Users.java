package pojo;
// Generated Sep 20, 2024 9:19:41 AM by Hibernate Tools 4.3.1

import DAO.DAOLogin;
import DAO.DAOUser;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
/**
 * Users generated by hbm2java
 */
public class Users  implements java.io.Serializable {


     private Integer id;
     private String username;
     private String email;
     private String password;
     private String address;
     private String phoneNumber;
     private String substrict;
     private String ward;
     private Date createdAt;
     private Date updatedAt;

    public Users() {
    }
    
    public Users(String username, String email, String password, String address, String phoneNumber, String substrict, String ward, Date createdAt, Date updatedAt) {
       this.username = username;
       this.email = email;
       this.password = password;
       this.address = address;
       this.phoneNumber = phoneNumber;
       this.substrict = substrict;
       this.ward = ward;
       this.createdAt = createdAt;
       this.updatedAt = updatedAt;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getSubstrict() {
        return this.substrict;
    }
    
    public void setSubstrict(String substrict) {
        this.substrict = substrict;
    }
    public String getWard() {
        return this.ward;
    }
    
    public void setWard(String ward) {
        this.ward = ward;
    }
    
    public Date getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public Date getUpdatedAt() {
        return this.updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public Users validasiLogin(){
        DAOLogin uDao = new DAOLogin();
        List<Users> us = uDao.getBy(username, password);
        if (us != null) {
            return us.get(0);
        }
        return null;
    }
    
    public Users insert(){
        DAOLogin uDao = new DAOLogin();

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);

        this.createdAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        this.updatedAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        uDao.insertUser(this);

//        username = "";
//        email = "";
//        password = "";
//        createdAt = null;
//        updatedAt = null;
        
        return this;
    }
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", substrict='" + substrict + '\'' +
                ", ward='" + ward + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}


