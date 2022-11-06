package worktalk.com.host.service;

import worktalk.com.host.domain.Host;

public interface HostProfileService {
	
	public int update(Host host);
	public int leave(Host host);
	public Host findByName(Host host);
	
}
