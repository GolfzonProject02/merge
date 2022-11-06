package worktalk.com.master.repository;

import java.util.List;

import worktalk.com.master.domain.Master;

public interface MasterDAO {
	
	public int join(Master master);
	public int update(Master master);
	public int update_penalty(Master master);
	public int leave(Master master);
	public Master findByName(Master master);
	public Master findByMail(Master master);
	public List<Master> findAll();

}
