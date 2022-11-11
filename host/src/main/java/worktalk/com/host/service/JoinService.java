package worktalk.com.host.service;

import worktalk.com.host.domain.Host;

/**
 * 
 * @author Juhee Fred Lee
 * Service interface for host join service
 *
 */

public interface JoinService {
	
	public int join(Host host);
	public int checkDuplicatedName(Host host);

}
