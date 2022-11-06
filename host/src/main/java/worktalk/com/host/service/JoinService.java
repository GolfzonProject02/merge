package worktalk.com.host.service;

import worktalk.com.host.domain.Host;

public interface JoinService {
	
	public int join(Host host);
	public int checkDuplicatedName(Host host);

}
