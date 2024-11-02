package pojo;
// Generated Sep 20, 2024 9:19:41 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ConcertTrailer generated by hbm2java
 */
@Entity
@Table(name = "concert_trailer")
public class ConcertTrailer  implements java.io.Serializable {


     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
     
     private String trailer;
     private Date createdAt;
     private Date updatedAt;
     
     @OneToMany(mappedBy = "concert")
     private List<Concert> concert;

    public ConcertTrailer() {
    }

	
//    public ConcertTrailer(String trailer, Date createdAt) {
//        this.trailer = trailer;
//        this.createdAt = createdAt;
//    }
    public ConcertTrailer(String trailer, Date createdAt, Date updatedAt) {
       this.trailer = trailer;
       this.createdAt = createdAt;
       this.updatedAt = updatedAt;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTrailer() {
        return this.trailer;
    }
    
    public void setTrailer(String trailer) {
        this.trailer = trailer;
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
    public List<Concert> getConcert() {
        return concert;
    }

    public void setConcert(List<Concert> concert) {
        this.concert = concert;
    }
}


