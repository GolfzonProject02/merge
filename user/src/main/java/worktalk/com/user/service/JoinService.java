package worktalk.com.user.service;

import worktalk.com.user.domain.User;

public interface JoinService {
	
	public int join(User user);
	public int checkDuplicatedName(User user);

}
