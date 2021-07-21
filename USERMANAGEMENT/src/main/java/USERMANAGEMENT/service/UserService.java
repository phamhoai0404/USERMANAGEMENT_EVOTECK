package USERMANAGEMENT.service;

import USERMANAGEMENT.model.Team;
import USERMANAGEMENT.model.User;

import USERMANAGEMENT.model.dto.UserDTO;
import USERMANAGEMENT.model.search.SearchUser;
import USERMANAGEMENT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;
import static java.lang.Integer.parseInt;

@Service//Đảm nhiệm sử lý logic
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public List<UserDTO> getAllUser(){
        return userRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }



    @Override
    public List<UserDTO> searchUsers(SearchUser userRequest){

            return userRepository.searchUser(userRequest).stream().map(this::toDTO).collect(Collectors.toList());


    }

    public UserDTO addUser(UserDTO userDTO)throws Exception{
        if(userDTO.getId() !=null){
            throw new Exception();
        }
        return this.toDTO(userRepository.save(this.toEntity(userDTO)));
    }

    public UserDTO deleteUserByID(Long id)throws Exception {
        try {
            User user = userRepository.findById(id).orElseThrow(null);
            userRepository.deleteById(id);

            return this.toDTO(user);

        } catch (NoSuchElementException e) {
            throw new Exception();
        }
    }


    public UserDTO updateUser(Long id, UserDTO userDTO) throws Exception{
        User user = this.toEntity(userDTO);

        if(!this.existsById(id)){
            throw new Exception();
        }
        user.setId(id);

        return this.toDTO(userRepository.save(user));
    }

    public void setNullTeam(Long teamID){
        List<User> list = userRepository.findAll();

        //Kiểm tra User trong list nếu có teamID mà bằng teamID truyền vào thì sẽ set Team của User ấy bằng Null
        //Đồng thời lưu lại cái User ấy vào Database.
        for(User user: list){
            if(user.getTeam() != null) {
                if (user.getTeam().getId() == teamID) {
                    user.setTeam(null);
                    userRepository.save(user);
                }
            }
        }
    }
    public void setAllTeam(Long oldTeamID, Team newTeam){
        List<User> list = userRepository.findAll();
        for(User user: list){
            if(user.getTeam() != null){
                if(user.getTeam().getId() == oldTeamID){
                    user.setTeam(newTeam);
                    userRepository.save(user);
                }
            }

        }
    }

    private UserDTO toDTO(User user){
        return new UserDTO(user.getId(), user.getFullName(),user.getUserDescribe(),
                user.getDateOfBirth(),user.getDateCreated(),user.getAddress(),user.getMaker(),
                user.getUpdateDay(),user.getUpdater(),user.getTeam());
    }

    private User toEntity(UserDTO userDTO){
        return  new User(userDTO.getId(), userDTO.getFullName(),userDTO.getUserDescribe(),
                userDTO.getDateOfBirth(),userDTO.getDateCreated(),userDTO.getAddress(),userDTO.getMaker(),
                userDTO.getUpdateDay(),userDTO.getUpdater(),userDTO.getTeam());
    }

}

