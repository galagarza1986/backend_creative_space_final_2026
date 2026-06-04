package ucr.ac.cr.creativespace_final.model.DTOs;

import java.time.LocalDate;

public class ReservationRequestDTO {
    private Integer spaceId;
    private String userEmail;
    private LocalDate dateReserved;
    private String status;

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDate getDateReserved() {
        return dateReserved;
    }

    public void setDateReserved(LocalDate dateReserved) {
        this.dateReserved = dateReserved;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
