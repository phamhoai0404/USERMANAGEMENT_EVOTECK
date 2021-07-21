package USERMANAGEMENT.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 255)
    @Column(name="fullname",nullable = false)
    private String fullName;

    @Lob
    @Column(name="user_describe")
    private String userDescribe;

   @NotNull
   @Temporal(TemporalType.DATE)
   @Column(name="date_of_birth",nullable = false)
   private Date dateOfBirth;

   @Temporal(TemporalType.DATE)
   @Column(name="date_created")
   private Date dateCreated;

   @NotNull
   @Size(min = 3, max = 255)
   @Column(name = "address", nullable = false )
   private String address;

   @Column(name="maker")
   private String maker;

   @Temporal(TemporalType.DATE)
   @Column(name="update_day")
   private Date updateDay;

   @Column(name="updater")
   private String updater;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
