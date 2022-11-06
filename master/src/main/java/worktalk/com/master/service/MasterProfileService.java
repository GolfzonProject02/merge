package worktalk.com.master.service;

import worktalk.com.master.domain.Master;

public interface MasterProfileService {
	
	public int update(Master master);
	public int leave(Master master);
	public Master findByName(Master master);
	
}
