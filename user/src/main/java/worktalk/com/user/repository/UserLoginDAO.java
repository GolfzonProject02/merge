package worktalk.com.user.repository;

import worktalk.com.user.domain.User;

/**
 * 
 * @author Juhee Fred Lee (이주희)
 * Repository interface for login data crud
 * 
 */

public interface UserLoginDAO {
	
	public User logIn(User user);

}
