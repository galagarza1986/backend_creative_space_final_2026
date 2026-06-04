package ucr.ac.cr.creativespace_final.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservations_tb")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id", nullable = false, foreignKey = @ForeignKey(name = "fk_reservation_space"))
    private SpaceEntity space;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email", referencedColumnName = "email",  nullable = false, foreignKey = @ForeignKey(name = "fk_reservation_user"))
    private UserEntity user;

    @Column(name = "date_reserved", nullable = false)
    private LocalDate dateReserved;
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    public ReservationEntity() {
    }

    public ReservationEntity(Integer id, SpaceEntity space, UserEntity user, LocalDate dateReserved, String status) {
        this.id = id;
        this.space = space;
        this.user = user;
        this.dateReserved = dateReserved;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SpaceEntity getSpace() {
        return space;
    }

    public void setSpace(SpaceEntity space) {
        this.space = space;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

