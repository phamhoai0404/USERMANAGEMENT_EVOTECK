package USERMANAGEMENT.service;

import USERMANAGEMENT.model.Team;
import USERMANAGEMENT.model.User;
import USERMANAGEMENT.model.dto.TeamDTO;
import USERMANAGEMENT.model.dto.UserDTO;
import USERMANAGEMENT.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TeamService  {
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    IUserService userService;

    //Tìm kiếm sự tồn tại của id , nếu tồn tại id trả về true, không trả về false
    public boolean existsById(Long id) {
        return teamRepository.existsById(id);
    }

    //Trả về dữ liệu của cả bảng team
    public List<TeamDTO> getAllTeam(){
        return teamRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    //Thêm dữ liệu vào database
    public TeamDTO addTeam(TeamDTO teamDTO)throws Exception{
        //Kiểm tra đối tượng thêm vào có id hay chưa nếu có id thì đi vào lỗi, nếu id rỗng thì null thì thêm dữ liệu vào
        if(teamDTO.getId()!=null){
            throw new Exception();
        }
        return this.toDTO(teamRepository.save(this.toEntity(teamDTO)));
    }

    public TeamDTO updateTeam(Long id, TeamDTO teamDTO) throws Exception{
        Team team = this.toEntity(teamDTO);
        if(!this.existsById(id)){
            throw new Exception();
        }
        team.setId(id);
        return this.toDTO(teamRepository.save(team));
    }

    public void setNullTeam(Long parentTeamID){
        List<Team> list = teamRepository.findAll();
        for(Team team: list){
            if(team.getTeamGroup() != null){
                if(team.getTeamGroup().getId() == parentTeamID){
                    team.setTeamGroup(null);
                    teamRepository.save(team);
                }
            }

        }
    }
    public void setAllTeam(Long oldID, Team newTeam){
        List<Team> list = teamRepository.findAll();
        for(Team team: list){
            if(team.getTeamGroup() != null){
                if(team.getTeamGroup().getId() == oldID){
                    if(team.getTeamGroup().getId() != newTeam.getId()) {//Nếu nó không phải là chính nó thì set bằng cái newTeam
                        team.setTeamGroup(newTeam);
                        teamRepository.save(team);
                    }else{//Nếu mà nó bằng chính nó thì set = null;
                        team.setTeamGroup(null);
                        teamRepository.save(team);
                    }
                }
            }

        }
    }

    public TeamDTO deleteTeamByID(Long id)throws Exception {
        try {
            Team team = teamRepository.findById(id).orElseThrow(null);

            if(team.getTeamGroup() == null ){//Nếu nó không có cha thì set thì
                this.setNullTeam(id);//Sét null của đơn vị con thành null
                userService.setNullTeam(id);//sét null của user thành null

            }else{
                //Đây là trừ trường hợp team.id = team.teamgroup.id đấy nếu nó giống nhau thì xét cách khác
                Team team1 = team.getTeamGroup();
                this.setAllTeam(id,team1); //Cho đơn vị của nó bằng đơn vị ông
                userService.setAllTeam(id,team1);//cho người dùng của nó bằng đơn vị ông
            }

            teamRepository.deleteById(id);
            return this.toDTO(team);

        } catch (NoSuchElementException e) {
            throw new Exception();
        }
    }

    //Trả về 1 TeamDTO với dữ liệu truyền vào là team
    private TeamDTO toDTO(Team team){
        return new TeamDTO(team.getId(),team.getTeamName(),team.getTeamId(),team.getTeamGroup(),
                team.getTeamDescribe(),team.getTeamDateCreated(),team.getTeamMaker(),team.getTeamUpdateDay(),
                team.getTeamUpdater());
    }

    //Trả về  1 Entity với dữ liệu truyền vào là TeamDTO
    private Team toEntity(TeamDTO teamDTO){
        return new Team(teamDTO.getId(),teamDTO.getTeamName(),teamDTO.getTeamId(),teamDTO.getTeamGroup(),
                teamDTO.getTeamDescribe(),teamDTO.getTeamDateCreated(),teamDTO.getTeamMaker(),teamDTO.getTeamUpdateDay(),
                teamDTO.getTeamUpdater());
    }
}
