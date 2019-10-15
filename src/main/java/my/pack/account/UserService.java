package my.pack.account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.interview.questions.beans.ResponseBean;
import com.interview.questions.beans.UserBean;
import com.interview.questions.db.dao.UserModelDao;
import com.interview.questions.db.entities.UserModel;
import com.interview.questions.enums.NotificationType;
import com.interview.questions.misc.NotificationMessages;
import com.interview.questions.populator.Populator;
import com.task.tracker.services.UserServiceCustom;

@Service
public class UserService implements UserDetailsService, UserServiceCustom {

	
	@Autowired
	UserModelDao userModelDao;
	
	@Autowired
	Populator<UserBean, UserModel> populator;


	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		UserModel userr = userModelDao.findByEmail(username);

		User u = createSpringSecurityUser(userr);
		return u;
	}

	private User createSpringSecurityUser(UserModel account) {
		return new User(account.getEmail(), account.getPassword(),
				Collections.singleton(new SimpleGrantedAuthority(account
						.getRole().toString())));
	}

	@Override
	public List<String> getAllUsersNames() {
		List<UserModel> list = userModelDao.findAll();
		List<String> usersList = new ArrayList <String> (); 
		
		for(UserModel model : list)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(model.getFirstName() + " " + model.getLastName() + "<" + model.getEmail() + ">");
			usersList.add(builder.toString());
		}

		return usersList;
	}

	@Override
	public String getNameByEmail(String mail) {
		UserModel user = userModelDao.findByEmail(mail);
		return user.getFirstName() + " " + user.getLastName();
	}

	@Override
	public UserModel getUserByMail(String mail) {
		
		return userModelDao.findByEmail(mail);
	}

	@Override
	public ResponseBean createUser(UserBean userBean) {
		
		if(getUserByMail(userBean.getMail().toLowerCase()) != null)
		{
			return new ResponseBean(NotificationMessages.USER_ALREADY_EXSISTS,NotificationType.ERROR,false);
		}
		UserModel usermodel = populator.populate(userBean);
		userModelDao.save(usermodel);
		
		return new ResponseBean(NotificationMessages.USER_CREATED_SUCCESSFULLY,NotificationType.INFO,true);
	}
	
	
}
