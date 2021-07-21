package USERMANAGEMENT.model.dto;

import USERMANAGEMENT.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Data
@Setter
@AllArgsConstructor
public class TeamDTO {
    private Long id;
    private String teamName;
    private String teamId;
    private Team teamGroup;
    private String teamDescribe;
    private Date teamDateCreated;
    private String teamMaker;
    private Date teamUpdateDay;
    private String teamUpdater;
}
