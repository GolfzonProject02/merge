package worktalk.com.master.service;

import worktalk.com.master.domain.Master;

public interface JoinService {
	
	public int join(Master master);
	public int checkDuplicatedName(Master master);

}
