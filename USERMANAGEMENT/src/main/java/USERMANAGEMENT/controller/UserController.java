package USERMANAGEMENT.controller;

import USERMANAGEMENT.model.dto.UserDTO;
import USERMANAGEMENT.model.search.SearchUser;
import USERMANAGEMENT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins="*", maxAge=3600)
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.getAllUser());
    }


    @PostMapping("/find")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> findUsers(@RequestBody @Valid SearchUser requestUser){
        try{
            return ResponseEntity.ok(userService.searchUsers(requestUser));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addUser(@RequestBody UserDTO dto) {
        try {
            return ResponseEntity.ok(userService.addUser(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.deleteUserByID(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
