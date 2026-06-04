package ucr.ac.cr.creativespace_final.service;

import org.springframework.stereotype.Service;
import ucr.ac.cr.creativespace_final.model.DTOs.SpaceRequestDTO;
import ucr.ac.cr.creativespace_final.model.DTOs.SpaceResponseDTO;
import ucr.ac.cr.creativespace_final.model.SpaceEntity;
import ucr.ac.cr.creativespace_final.repository.SpaceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpaceService {
    private final SpaceRepository spaceRepository;

    public SpaceService(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    public List<SpaceResponseDTO> getAllSpaces() {
        List<SpaceEntity> spaces = spaceRepository.findAll();
        List<SpaceResponseDTO> responseList = new ArrayList<>();

        for (SpaceEntity space : spaces) {
            responseList.add(convertToResponseDTO(space));
        }

        return responseList;
    }

    public SpaceResponseDTO getSpaceById(Integer id) {
        Optional<SpaceEntity> optionalSpace = spaceRepository.findById(id);

        if (optionalSpace.isEmpty()) {
            throw new RuntimeException("Space not found");
        }

        return convertToResponseDTO(optionalSpace.get());
    }

    public SpaceResponseDTO createSpace(SpaceRequestDTO dto) {
        SpaceEntity space = new SpaceEntity();

        space.setName(dto.getName());
        space.setLocation(dto.getLocation());
        space.setType(dto.getType());
        space.setPrice(dto.getPrice());
        space.setCapacity(dto.getCapacity());

        SpaceEntity savedSpace = spaceRepository.save(space);

        return convertToResponseDTO(savedSpace);
    }

    public SpaceResponseDTO updateSpace(Integer id, SpaceRequestDTO dto) {
        Optional<SpaceEntity> optionalSpace = spaceRepository.findById(id);

        if (optionalSpace.isEmpty()) {
            throw new RuntimeException("Space not found");
        }

        SpaceEntity space = optionalSpace.get();

        space.setName(dto.getName());
        space.setLocation(dto.getLocation());
        space.setType(dto.getType());
        space.setPrice(dto.getPrice());
        space.setCapacity(dto.getCapacity());

        SpaceEntity updatedSpace = spaceRepository.save(space);

        return convertToResponseDTO(updatedSpace);
    }

    public void deleteSpace(Integer id) {
        if (!spaceRepository.existsById(id)) {
            throw new RuntimeException("Space not found");
        }

        spaceRepository.deleteById(id);
    }

    public List<SpaceResponseDTO> getSpacesByType(String type) {
        List<SpaceEntity> spaces = spaceRepository.findByType(type);
        List<SpaceResponseDTO> responseList = new ArrayList<>();

        for (SpaceEntity space : spaces) {
            responseList.add(convertToResponseDTO(space));
        }

        return responseList;
    }

    public List<SpaceResponseDTO> getSpacesByLocation(String location) {
        List<SpaceEntity> spaces = spaceRepository.findByLocation(location);
        List<SpaceResponseDTO> responseList = new ArrayList<>();

        for (SpaceEntity space : spaces) {
            responseList.add(convertToResponseDTO(space));
        }

        return responseList;
    }

    private SpaceResponseDTO convertToResponseDTO(SpaceEntity space) {
        return new SpaceResponseDTO(
                space.getId(),
                space.getName(),
                space.getLocation(),
                space.getType(),
                space.getPrice(),
                space.getCapacity()
        );
    }

    public SpaceEntity getSpaceEntityById(Integer id) {
        Optional<SpaceEntity> optionalSpace = spaceRepository.findById(id);

        if (optionalSpace.isEmpty()) {
            throw new RuntimeException("Space not found");
        }

        return optionalSpace.get();
    }
}
