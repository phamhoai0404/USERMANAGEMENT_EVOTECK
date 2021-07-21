package USERMANAGEMENT.model.dto;

import USERMANAGEMENT.model.Team;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@AllArgsConstructor
@Setter
public class UserDTO{
    private Long id;
    private String fullName;
    private String userDescribe;
    private Date dateOfBirth;
    private Date dateCreated;
    private String address;
    private String maker;
    private Date updateDay;
    private String updater;
    private Team team;

}
