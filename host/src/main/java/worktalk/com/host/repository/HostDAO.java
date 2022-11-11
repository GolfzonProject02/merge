package worktalk.com.host.repository;

import worktalk.com.host.domain.Host;

/**
 * 
 * @author Juhee Fred Lee (이주희)
 * Repository interface for host data crud
 * 
 */

public interface HostDAO {
	
	public int join(Host host);
	public int checkDuplicatedName(Host host);
	public int checkDuplicateMail(Host host);
	public int update(Host host);
	public int leave(Host host);
	public Host findByName(Host host);

}
