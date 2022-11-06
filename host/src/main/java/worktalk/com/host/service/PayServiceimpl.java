package worktalk.com.host.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;

import worktalk.com.host.domain.IamportProperties;
import worktalk.com.host.domain.Pay;
import worktalk.com.host.domain.Payment_status;
import worktalk.com.host.repository.PayDAO;

@Service
public class PayServiceimpl implements PayService {

	private static final Logger logger = LoggerFactory.getLogger(PayServiceimpl.class);
	private IamportClient api;

	public PayServiceimpl() {
		this.api = new IamportClient(IamportProperties.API_KEY, IamportProperties.API_SECRET);
		logger.info("PayServiceimpl()....");
	}

	@Autowired
	PayDAO dao;

	@Override
	public Pay insert(Pay pay) {
		logger.info("insert()....");
		pay.setP_status(Payment_status.Prepaid.toString());
		
		logger.info("{}", pay);
		int flag = dao.insert(pay);
		logger.info("{}", flag);
		if (flag != 0) {
			return dao.findByName(pay).get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Pay> findByName(Pay pay) {
		logger.info("findByName()....");
		logger.info("{}", pay);

		return dao.findByName(pay);
	}

	@Override
	public List<Pay> findHistoryByName(Pay pay) {
		logger.info("findHistoryByName()....");
		logger.info("{}", pay);
		return dao.findHistoryByName(pay);
	}

	@Override
	public void cancelByUid(Pay pay) throws IamportResponseException, IOException {
		logger.info("cancelByUid()....");
		logger.info("{}", pay);
		
		Pay findUid = dao.findByNum(pay).get(0);
		logger.info("UID: {}", findUid);
		logger.info("UID: {}", findUid.getImp_uid());
		
		
		CancelData cd = new CancelData(findUid.getImp_uid(), true);
		
		api.cancelPaymentByImpUid(cd);
		findUid.setP_status(Payment_status.Refund.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Timestamp current = new Timestamp(System.currentTimeMillis());
		String current_time = sdf.format(current);
		findUid.setP_date(current_time);
			
		logger.info("pay before insert : {}", findUid);
			
		dao.insert(findUid);
		
	}

	@Override
	public List<Pay> findHistoryByStatus(Pay pay) {
		logger.info("findHistoryByStatus()....");
		logger.info("{}", pay);
		return dao.findHistoryByStatus(pay);
	}

	@Override
	public int calPaid(Pay pay) {
		logger.info("calPaid()....");
		logger.info("{}", pay);
		return dao.calPaid(pay);
	}

	@Override
	public int calRefund(Pay pay) {
		logger.info("calRefund()....");
		logger.info("{}", pay);
		return dao.calRefund(pay);
	}
}
