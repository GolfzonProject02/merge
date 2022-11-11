package worktalk.com.host.repository;

import worktalk.com.host.domain.Host;

/**
 * 
 * @author Juhee Fred Lee (이주희)
 * Repository interface for host login data crud
 * 
 */


public interface HostLoginDAO {
	
	public Host logIn(Host host);

}
