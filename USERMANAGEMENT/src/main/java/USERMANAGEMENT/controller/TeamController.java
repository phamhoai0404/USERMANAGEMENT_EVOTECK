package USERMANAGEMENT.controller;

import USERMANAGEMENT.model.dto.TeamDTO;
import USERMANAGEMENT.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/team")
@CrossOrigin(origins="*", maxAge=3600)
public class TeamController {
    @Autowired
    TeamService teamService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(teamService.getAllTeam());
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addTeam(@RequestBody @Valid TeamDTO dto) {
        try {
            return ResponseEntity.ok(teamService.addTeam(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateTeam(@PathVariable Long id, @Valid @RequestBody TeamDTO teamDTO) {
        try {
            return ResponseEntity.ok(teamService.updateTeam(id, teamDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(teamService.deleteTeamByID(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
