package USERMANAGEMENT;

import USERMANAGEMENT.model.Team;
import USERMANAGEMENT.repository.TeamRepository;
import USERMANAGEMENT.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class UsermanagementApplication {

	public static void main(String[] args) {

		//Chạy chương trình
		//SpringApplication.run(UsermanagementApplication.class, args);
		SpringApplication.run(UsermanagementApplication.class, args);

		//Test dữ liệu trực tiếp ở IJ
		/*ApplicationContext applicationContext = SpringApplication.run(UsermanagementApplication.class, args);

		UserService userService = applicationContext.getBean(UserService.class);

		System.out.println(userService.findByDescribe("3").get(0).getMaker());*/
	}

}
