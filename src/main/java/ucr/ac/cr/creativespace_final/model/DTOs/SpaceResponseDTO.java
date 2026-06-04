package ucr.ac.cr.creativespace_final.model.DTOs;

public class SpaceResponseDTO {
    private Integer id;
    private String name;
    private String location;
    private String type;
    private Double price;
    private Integer capacity;

    public SpaceResponseDTO(Integer id, String name, String location, String type, Double price, Integer capacity) {
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

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getCapacity() {
        return capacity;
    }
}
