package worktalk.com.host.service;

import java.io.IOException;
import java.util.List;

import com.siot.IamportRestClient.exception.IamportResponseException;

import worktalk.com.host.domain.Pay;

/**
 * 
 * @author Juhee Fred Lee (이주희)
 * Service interface for payment service
 * 
 */

public interface PayService {
	
	public Pay insert(Pay pay);
	public List<Pay> findByName(Pay pay);
	public List<Pay> findHistoryByName(Pay pay);
	public List<Pay> findHistoryByStatus(Pay pay);
	public void cancelByUid(Pay	pay) throws IamportResponseException, IOException;
	public int calPaid(Pay pay);
	public int calRefund(Pay pay);
}
