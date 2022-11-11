package worktalk.com.user.service;

import worktalk.com.user.domain.User;

/**
 * 
 * @author Juhee Fred Lee (이주희)
 * Service interface for join service
 * 
 */

public interface JoinService {
	
	public int join(User user);
	public int checkDuplicatedName(User user);

}
