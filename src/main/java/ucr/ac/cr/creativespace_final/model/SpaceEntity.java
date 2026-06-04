package ucr.ac.cr.creativespace_final.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "spaces_tb")
public class SpaceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "location", nullable = false, length = 150)
    private String location;
    @Column(name = "type", nullable = false, length = 50)
    private String type;
    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;
    @OneToMany(mappedBy = "space")
    @JsonIgnore
    private List<ReservationEntity> reservations;

    public SpaceEntity() {
    }

    public SpaceEntity(Integer id, String name, String location, String type, Double price, Integer capacity) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
        this.price = price;
        this.capacity = capacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
