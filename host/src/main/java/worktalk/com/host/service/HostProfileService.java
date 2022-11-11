package worktalk.com.host.service;

import worktalk.com.host.domain.Host;

/**
 * 
 * @author Juhee Fred Lee
 * Service interface for host profile service
 *
 */

public interface HostProfileService {
	
	public int update(Host host);
	public int leave(Host host);
	public Host findByName(Host host);
	
}
