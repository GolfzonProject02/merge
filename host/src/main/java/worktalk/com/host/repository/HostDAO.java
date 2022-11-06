package worktalk.com.host.repository;

import worktalk.com.host.domain.Host;

public interface HostDAO {
	
	public int join(Host host);
	public int checkDuplicatedName(Host host);
	public int checkDuplicateMail(Host host);
	public int update(Host host);
	public int leave(Host host);
	public Host findByName(Host host);

}
