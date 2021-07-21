package USERMANAGEMENT.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "team_name",length = 100,nullable = false)
    private String teamName;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(name="team_id",length = 50, nullable = false)
    private String teamId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="team_group")
    private Team teamGroup;

    @Lob
    @Column(name="team_describe")
    private String teamDescribe;

    @Temporal(TemporalType.DATE)
    @Column(name="team_date_created")
    private Date teamDateCreated;

    @Column(name="team_maker")
    private String teamMaker;

    @Temporal(TemporalType.DATE)
    @Column(name="team_update_day")
    private Date teamUpdateDay;

    @Column(name="team_updater")
    private String teamUpdater;

}

