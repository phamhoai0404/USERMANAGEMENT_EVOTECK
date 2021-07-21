package USERMANAGEMENT.model.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SearchUser {
    private String searchBasic;
    private String fromDOB;
    private String toDOB;
    private String teamName;
}
