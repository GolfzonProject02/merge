package worktalk.com.user.service;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import worktalk.com.user.domain.IamportProperties;
import worktalk.com.user.domain.Pay;
import worktalk.com.user.domain.Payment_status;
import worktalk.com.user.repository.CalTimestamp;
import worktalk.com.user.repository.PayDAO;

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
		
//		Timestamp p_date = new Timestamp(Long.valueOf(pay.getP_date()));
//		logger.info("p_date: {}", p_date);
//		logger.info("{}", System.currentTimeMillis());
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//		String date = sdf.format(p_date);
//		pay.setP_date(date);
		
		
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
	public IamportResponse<Payment> verifyByUid(String imp_uid) throws IamportResponseException, IOException {
		logger.info("verifyByUid()....");
		logger.info("{}", imp_uid);
		return api.paymentByImpUid(imp_uid);
	}

	@Override
	public IamportResponse<Payment> cancelByUid(String imp_uid) throws IamportResponseException, IOException {
		logger.info("cancelByUid()....");
		logger.info("{}", imp_uid);
		CancelData cd = new CancelData(imp_uid, true);
		return api.cancelPaymentByImpUid(cd);
	}

	@Override
	public Pay calRefund(Pay pay, Timestamp current_time) {
		logger.info("calRefund()....");
		logger.info("{}", pay);
		
		Pay result = dao.findDateUidByRnum(pay).get(0);
		
		
		Timestamp reserve_date = Timestamp.valueOf(result.getReserve_date());
		Timestamp checkin_date = Timestamp.valueOf(result.getCheckin_date());
		CalTimestamp ct = new CalTimestamp();
		
		
		if (result.getP_status() == Payment_status.Deposit.toString()) {
			
			Timestamp standard = ct.calHour(current_time, -1);
			
			logger.info("{}", reserve_date);
			logger.info("{}", standard);
			
			if (standard.equals(reserve_date) || standard.after(reserve_date)) {
				result.setP_amount(result.getP_amount());
				return result;
			} else {
				result.setP_amount(0);
				return result;
			}
		} else {
			if (ct.calDate(checkin_date, -3).after(current_time)){
				result.setP_amount((int)(result.getP_amount() * 0.7));
				logger.info("70% : {}", result);
				return result;
			} else if (ct.calDate(checkin_date, -2).after(current_time)) {
				result.setP_amount((int)(result.getP_amount() * 0.5));
				logger.info("50% : {}", result);
				return result;
			} else if (ct.calDate(checkin_date, -1).after(current_time)) {
				result.setP_amount((int)(result.getP_amount() * 0.25));
				logger.info("25% :{}", result);
				return result;
			} else {
				result.setP_amount(0);
				logger.info("0% :{}", result);
				return result;
			}
		}
	}

	@Override
	public Pay cancelByUid_partial(Pay pay) throws IamportResponseException, IOException {
		logger.info("cancelByUid()....");
		logger.info("{}", pay);
		
		if (pay.getP_amount() != 0) {
			CancelData cd = new CancelData(pay.getImp_uid(), true, new BigDecimal(pay.getP_amount()));
			api.cancelPaymentByImpUid(cd);
			pay.setP_status(Payment_status.Refund.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			Timestamp current = new Timestamp(System.currentTimeMillis());
			String current_time = sdf.format(current);
			pay.setP_date(current_time);
			
			logger.info("pay before insert : {}", pay);
			
			dao.insert(pay);
		}
		
		return pay;
	}
}
