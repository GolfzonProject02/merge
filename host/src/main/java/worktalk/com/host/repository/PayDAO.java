package worktalk.com.host.repository;

import java.util.List;

import worktalk.com.host.domain.Pay;

public interface PayDAO {
	
	public int insert(Pay pay);
	public List<Pay> findByName(Pay pay);
	public List<Pay> findByNum(Pay pay);
	public List<Pay> findHistoryByName(Pay pay);
	public List<Pay> findHistoryByStatus(Pay pay);
	public List<Pay> findDateUidByRnum(Pay pay);
	public int calPaid(Pay pay);
	public int calRefund(Pay pay);

}
