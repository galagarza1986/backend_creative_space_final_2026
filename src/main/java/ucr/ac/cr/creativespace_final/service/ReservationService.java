package ucr.ac.cr.creativespace_final.service;

import org.springframework.stereotype.Service;
import ucr.ac.cr.creativespace_final.model.DTOs.ReservationRequestDTO;
import ucr.ac.cr.creativespace_final.model.DTOs.ReservationResponseDTO;
import ucr.ac.cr.creativespace_final.model.ReservationEntity;
import ucr.ac.cr.creativespace_final.model.SpaceEntity;
import ucr.ac.cr.creativespace_final.model.UserEntity;
import ucr.ac.cr.creativespace_final.repository.ReservationRepository;
import ucr.ac.cr.creativespace_final.repository.SpaceRepository;
import ucr.ac.cr.creativespace_final.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final SpaceService spaceService;

    public ReservationService(
            ReservationRepository reservationRepository,
            UserService userService,
            SpaceService spaceService) {

        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.spaceService = spaceService;
    }

    public List<ReservationResponseDTO> getAllReservations() {
        List<ReservationEntity> reservations = reservationRepository.findAll();
        List<ReservationResponseDTO> responseList = new ArrayList<>();

        for (ReservationEntity reservation : reservations) {
            responseList.add(convertToResponseDTO(reservation));
        }

        return responseList;
    }

    public ReservationResponseDTO getReservationById(Integer id) {
        Optional<ReservationEntity> optionalReservation =
                reservationRepository.findById(id);

        if (optionalReservation.isEmpty()) {
            throw new RuntimeException("Reservation not found");
        }

        return convertToResponseDTO(optionalReservation.get());
    }

    public ReservationResponseDTO createReservation(ReservationRequestDTO dto) {
        validateStatus(dto.getStatus());

        if (reservationRepository.existsBySpaceIdAndDateReserved(
                dto.getSpaceId(),
                dto.getDateReserved())) {

            throw new RuntimeException("The space is already reserved for this date");
        }

        UserEntity user = userService.getUserEntityByEmail(dto.getUserEmail());
        SpaceEntity space = spaceService.getSpaceEntityById(dto.getSpaceId());

        ReservationEntity reservation = new ReservationEntity();

        reservation.setUser(user);
        reservation.setSpace(space);
        reservation.setDateReserved(dto.getDateReserved());
        reservation.setStatus(dto.getStatus());

        ReservationEntity savedReservation =
                reservationRepository.save(reservation);

        return convertToResponseDTO(savedReservation);
    }

    public ReservationResponseDTO updateReservation(
            Integer id,
            ReservationRequestDTO dto) {

        validateStatus(dto.getStatus());

        Optional<ReservationEntity> optionalReservation =
                reservationRepository.findById(id);

        if (optionalReservation.isEmpty()) {
            throw new RuntimeException("Reservation not found");
        }

        UserEntity user = userService.getUserEntityByEmail(dto.getUserEmail());
        SpaceEntity space = spaceService.getSpaceEntityById(dto.getSpaceId());

        ReservationEntity reservation = optionalReservation.get();

        reservation.setUser(user);
        reservation.setSpace(space);
        reservation.setDateReserved(dto.getDateReserved());
        reservation.setStatus(dto.getStatus());

        ReservationEntity updatedReservation =
                reservationRepository.save(reservation);

        return convertToResponseDTO(updatedReservation);
    }

    public ReservationResponseDTO updateReservationStatus(
            Integer id,
            String status) {

        validateStatus(status);

        Optional<ReservationEntity> optionalReservation =
                reservationRepository.findById(id);

        if (optionalReservation.isEmpty()) {
            throw new RuntimeException("Reservation not found");
        }

        ReservationEntity reservation = optionalReservation.get();
        reservation.setStatus(status);

        ReservationEntity updatedReservation =
                reservationRepository.save(reservation);

        return convertToResponseDTO(updatedReservation);
    }

    public void deleteReservation(Integer id) {
        if (!reservationRepository.existsById(id)) {
            throw new RuntimeException("Reservation not found");
        }

        reservationRepository.deleteById(id);
    }

    public List<ReservationResponseDTO> getReservationsByUser(String email) {
        List<ReservationEntity> reservations =
                reservationRepository.findByUserEmail(email);

        List<ReservationResponseDTO> responseList = new ArrayList<>();

        for (ReservationEntity reservation : reservations) {
            responseList.add(convertToResponseDTO(reservation));
        }

        return responseList;
    }

    public List<ReservationResponseDTO> getReservationsBySpace(Integer spaceId) {
        List<ReservationEntity> reservations =
                reservationRepository.findBySpaceId(spaceId);

        List<ReservationResponseDTO> responseList = new ArrayList<>();

        for (ReservationEntity reservation : reservations) {
            responseList.add(convertToResponseDTO(reservation));
        }

        return responseList;
    }

    public List<ReservationResponseDTO> getReservationsByDate(LocalDate dateReserved) {
        List<ReservationEntity> reservations =
                reservationRepository.findByDateReserved(dateReserved);

        List<ReservationResponseDTO> responseList = new ArrayList<>();

        for (ReservationEntity reservation : reservations) {
            responseList.add(convertToResponseDTO(reservation));
        }

        return responseList;
    }

    private void validateStatus(String status) {
        if (status == null) {
            throw new RuntimeException("Reservation status is required");
        }

        if (!status.equals("PENDING")
                && !status.equals("CONFIRMED")
                && !status.equals("CANCELED")) {

            throw new RuntimeException("Invalid reservation status");
        }
    }

    private ReservationResponseDTO convertToResponseDTO(
            ReservationEntity reservation) {

        return new ReservationResponseDTO(
                reservation.getId(),
                reservation.getSpace().getId(),
                reservation.getSpace().getName(),
                reservation.getUser().getEmail(),
                reservation.getUser().getName(),
                reservation.getDateReserved(),
                reservation.getStatus()
        );
    }
}
