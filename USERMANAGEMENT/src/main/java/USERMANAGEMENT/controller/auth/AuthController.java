package USERMANAGEMENT.controller.auth;

import USERMANAGEMENT.model.auth.Account;
import USERMANAGEMENT.model.auth.ERole;
import USERMANAGEMENT.model.auth.Role;
import USERMANAGEMENT.payload.request.LoginRequest;
import USERMANAGEMENT.payload.request.SignupRequest;
import USERMANAGEMENT.payload.response.JwtResponse;
import USERMANAGEMENT.payload.response.MessageResponse;
import USERMANAGEMENT.repository.auth.AccountRepository;
import USERMANAGEMENT.repository.auth.RoleRepository;
import USERMANAGEMENT.security.JwtUtils;
import USERMANAGEMENT.service.auth.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AccountRepository accountRepository ;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication;
        try{
             authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        }catch(Exception e){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Lỗi nha "));
        }



        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles =  userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){

        if(signupRequest.getEmail()==null || signupRequest.getPassword() == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("ERROR: email and password not null!"));
        }
        if(accountRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("ERROR: email is already taken!"));
        }


        //create new user's account
        Account account = new Account(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));

        Set<String> strRole = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if(strRole == null){
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }else {
            strRole.forEach(role ->{
                switch(role){
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(()-> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(()-> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        account.setRoles(roles);
        accountRepository.save(account);

        return ResponseEntity.ok(new MessageResponse("User registered successfuly"));
    }
}
