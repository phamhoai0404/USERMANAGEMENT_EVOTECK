package USERMANAGEMENT.repository.customer;

import USERMANAGEMENT.model.User;
import USERMANAGEMENT.model.search.SearchUser;

import java.util.List;

public interface CustomUserRepository {
    public List<User> searchUser(SearchUser searchRequest) ;
}
