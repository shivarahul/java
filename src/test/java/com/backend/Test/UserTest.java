package com.backend.Test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.backend.DAO.UserDAO;
import com.backend.config.DbConfig;
import com.backend.model.Forum;
import com.backend.model.Job;
import com.backend.model.UserDetail;


@ComponentScan("com.backend")
public class UserTest {

static UserDAO  userDAO;
	
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.register(DbConfig.class);
		context.scan("com.backend");
		context.refresh();

		userDAO=(UserDAO)context.getBean("userDAO");
	}
	
	@Test
	public void addUserTest()
	{
		UserDetail user=new UserDetail();
		user.setUserId(13);
		user.setFirstName("John");
		user.setLastName("mike");
		user.setEmailId("john@gmail.com");
		user.setPassword("7890");
		user.setRole("Admin");
		user.setStatus("available");
		user.setIsOnline("N");
		assertTrue("Problem in Inserting user", userDAO.addUserDetail(user));

	}

	@Test
	public void getAllUserTest(){
		List<UserDetail> userList=(List<UserDetail>)userDAO.getAllUserDetails();
		assertNotNull("Job list not found ",userList.get(0));
		for(UserDetail user:userList)
		{
			System.out.println("EmailID:"+ user.getEmailId() + "Status:"+ user.getStatus());
		}
		
		/*@Transactional
		@Test
		public void getUserDetailTest(){

			UserDetail user=(UserDetail)userDAO.getUserDetails("swetha");
			
			System.out.println("UserFirstname:" + user.getFirstName());
			System.out.println("UserRole:" + user.getRole());
			
			assertNotNull("UserDetail not found", user);
		}*/
	}
	
}
