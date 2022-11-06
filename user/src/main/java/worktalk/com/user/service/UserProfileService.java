package worktalk.com.user.service;

import worktalk.com.user.domain.User;

public interface UserProfileService {
	
//	public int update(String updateKey, User user);
	public int update(User user);
	public int leave(User user);
	public User findByName(User user);
	
}
