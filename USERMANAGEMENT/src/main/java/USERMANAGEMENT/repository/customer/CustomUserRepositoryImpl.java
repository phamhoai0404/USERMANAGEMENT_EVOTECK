package USERMANAGEMENT.repository.customer;


import USERMANAGEMENT.model.User;
import USERMANAGEMENT.model.search.SearchUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository{
    @PersistenceContext //thực hiện câu query
    EntityManager entityManager;

    @Override
    public List<User> searchUser(SearchUser searchRequest) {
        StringBuilder hql = new StringBuilder("select u from User u  where 1=1");
        if(searchRequest.getSearchBasic() != null){
            hql.append(" and ( fullName like '%"+searchRequest.getSearchBasic()+"%' "+
                       " or userDescribe like '%"+searchRequest.getSearchBasic()+"%' " +
                       " or address like '%"+searchRequest.getSearchBasic()+"%' ) "
            );
        }

        if(searchRequest.getFromDOB() != null ){
            hql.append(" and dateOfBirth >= '"+ searchRequest.getFromDOB() +"'" );
        }

        if(searchRequest.getToDOB() != null ){
            hql.append(" and dateOfBirth <= '"+ searchRequest.getToDOB() +"'" );
        }

        if(searchRequest.getTeamName() != null){
            hql.append(" and team.teamName = '"+searchRequest.getTeamName()+"' ");
        }

        Query query = entityManager.createQuery(hql.toString());
        List<User> data = query.getResultList();

        return data;
    }

}
