package ucr.ac.cr.creativespace_final.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.creativespace_final.model.DTOs.ReservationRequestDTO;
import ucr.ac.cr.creativespace_final.model.DTOs.ReservationResponseDTO;
import ucr.ac.cr.creativespace_final.service.ReservationService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<?> getAllReservations() {
        try {
            List<ReservationResponseDTO> reservations =
                    reservationService.getAllReservations();

            return ResponseEntity.ok(reservations);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Integer id) {
        try {
            ReservationResponseDTO reservation =
                    reservationService.getReservationById(id);

            return ResponseEntity.ok(reservation);

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createReservation(
            @RequestBody ReservationRequestDTO dto) {

        try {
            ReservationResponseDTO reservation =
                    reservationService.createReservation(dto);

            return ResponseEntity.status(201).body(reservation);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(
            @PathVariable Integer id,
            @RequestBody ReservationRequestDTO dto) {

        try {
            ReservationResponseDTO reservation =
                    reservationService.updateReservation(id, dto);

            return ResponseEntity.ok(reservation);

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateReservationStatus(
            @PathVariable Integer id,
            @RequestBody Map<String, String> body) {

        try {
            String status = body.get("status");

            ReservationResponseDTO reservation =
                    reservationService.updateReservationStatus(id, status);

            return ResponseEntity.ok(reservation);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Integer id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<?> getReservationsByUser(@PathVariable String email) {
        try {
            List<ReservationResponseDTO> reservations =
                    reservationService.getReservationsByUser(email);

            return ResponseEntity.ok(reservations);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/space/{spaceId}")
    public ResponseEntity<?> getReservationsBySpace(
            @PathVariable Integer spaceId) {

        try {
            List<ReservationResponseDTO> reservations =
                    reservationService.getReservationsBySpace(spaceId);

            return ResponseEntity.ok(reservations);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<?> getReservationsByDate(@PathVariable String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);

            List<ReservationResponseDTO> reservations =
                    reservationService.getReservationsByDate(localDate);

            return ResponseEntity.ok(reservations);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid date format. Use yyyy-MM-dd");
        }
    }
}
