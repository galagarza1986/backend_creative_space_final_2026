package ucr.ac.cr.creativespace_final.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucr.ac.cr.creativespace_final.model.DTOs.SpaceRequestDTO;
import ucr.ac.cr.creativespace_final.model.DTOs.SpaceResponseDTO;
import ucr.ac.cr.creativespace_final.service.SpaceService;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/spaces")
public class SpaceController {

    private final SpaceService spaceService;

    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @GetMapping
    public ResponseEntity<?> getAllSpaces() {
        try {
            List<SpaceResponseDTO> spaces = spaceService.getAllSpaces();
            return ResponseEntity.ok(spaces);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSpaceById(@PathVariable Integer id) {
        try {
            SpaceResponseDTO space = spaceService.getSpaceById(id);
            return ResponseEntity.ok(space);

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createSpace(@RequestBody SpaceRequestDTO dto) {
        try {
            SpaceResponseDTO space = spaceService.createSpace(dto);
            return ResponseEntity.status(201).body(space);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSpace(
            @PathVariable Integer id,
            @RequestBody SpaceRequestDTO dto) {

        try {
            SpaceResponseDTO space = spaceService.updateSpace(id, dto);
            return ResponseEntity.ok(space);

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSpace(@PathVariable Integer id) {
        try {
            spaceService.deleteSpace(id);
            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> getSpacesByType(@PathVariable String type) {
        try {
            List<SpaceResponseDTO> spaces = spaceService.getSpacesByType(type);
            return ResponseEntity.ok(spaces);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<?> getSpacesByLocation(@PathVariable String location) {
        try {
            List<SpaceResponseDTO> spaces = spaceService.getSpacesByLocation(location);
            return ResponseEntity.ok(spaces);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
