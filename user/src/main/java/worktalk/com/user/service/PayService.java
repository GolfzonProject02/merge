package worktalk.com.user.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import worktalk.com.user.domain.Pay;

public interface PayService {
	
	public Pay insert(Pay pay);
	public List<Pay> findByName(Pay pay);
	public List<Pay> findHistoryByName(Pay pay);
	public Pay calRefund(Pay pay, Timestamp current_time);
	public IamportResponse<Payment> verifyByUid(String imp_uid) throws IamportResponseException, IOException;
	public IamportResponse<Payment> cancelByUid(String imp_uid) throws IamportResponseException, IOException;
	public Pay cancelByUid_partial(Pay pay) throws IamportResponseException, IOException;
}
