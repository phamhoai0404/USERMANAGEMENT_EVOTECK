package USERMANAGEMENT.service;

import USERMANAGEMENT.model.Team;
import USERMANAGEMENT.model.User;
import USERMANAGEMENT.model.dto.UserDTO;
import USERMANAGEMENT.model.search.SearchUser;

import java.util.List;

public interface IUserService {
    public boolean existsById(Long id);
    public List<UserDTO> getAllUser();
    public UserDTO addUser(UserDTO userDTO)throws Exception;
    public UserDTO deleteUserByID(Long id)throws Exception;
    public UserDTO updateUser(Long id, UserDTO userDTO) throws Exception;
    public void setNullTeam(Long teamID);
    public void setAllTeam(Long oldTeamID, Team newTeam);
    public List<UserDTO> searchUsers(SearchUser userRequest);
}
