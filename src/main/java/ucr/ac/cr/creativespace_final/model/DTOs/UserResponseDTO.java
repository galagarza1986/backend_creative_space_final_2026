package ucr.ac.cr.creativespace_final.model.DTOs;

public class UserResponseDTO {
    private Integer id;
    private String email;
    private String name;
    private String role;

    public UserResponseDTO(Integer id, String email, String name, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
