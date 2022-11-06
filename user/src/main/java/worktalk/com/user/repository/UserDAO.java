package worktalk.com.user.repository;

import worktalk.com.user.domain.User;

public interface UserDAO {

	public int join(User user);
	public int checkDuplicatedName(User user);
	public int checkDuplicateMail(User user);
	public int update(User user);
	public int leave(User user);
	public User findByName(User user);
	
}
