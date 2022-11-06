package worktalk.com.master.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import worktalk.com.master.controller.HomeController;
import worktalk.com.master.domain.Master;
import worktalk.com.master.repository.MasterDAO;

/**
 * 
 * @author Juhee Fred Lee(이주희) Creating random code for email validation. Send
 *         Mail to join member email.
 *
 */

@Service
public class MailSenderServiceimpl implements MailSenderService {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	JavaMailSender javaMailSender;
	@Autowired
	MasterDAO dao;

	public MailSenderServiceimpl() {
		logger.info("MailSenderServiceimpl()....");
	}

	/*
	 * call for dao.checkDuplicatedMail in oreder to find any duplicate mail in
	 * MEMBER table returns 1 if table has duplicated email
	 */
	@Override
	public int checkDuplicatedEmail(Master master) {
		logger.info("checkDuplicatedEmail()....");
		logger.info("{}", master);

		if (dao.findByMail(master) == null) {
			return 0;
		} else {
			return 1;
		}
	}

	/*
	 * creates 6 digits long random number
	 */
	@Override
	public int creatingRandomCode() {
		logger.info("makeAccesCode()....");

		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		logger.info("ckechNum : {}", checkNum);

		return checkNum;
	}

	/*
	 * create an email form containing the validation code(6 digit random num) and
	 * sends it if the email address is duplicated => returns "Duplicated Email"
	 * else returns => validation code (Integer.toString(code))
	 */
	@Override
	public String joinMail(String email) {
		logger.info("joinMail()....");

		Master master = new Master();

		master.setEmail(email);

		int flag = checkDuplicatedEmail(master);

		if (flag == 0) {
			int code = creatingRandomCode();
			String setFrom = "brownenvelope613@gmail.com"; // setting sender's own eamil that were registerd at
															// email-config
			String toMail = master.getEmail();
			String title = "회원 가입 인증 이메일 입니다."; // Email Title
			String content = "홈페이지를 방문해주셔서 감사합니다." + // Should be written in html
					"<br><br>" + "인증 번호는 " + String.valueOf(code) + "입니다." + "<br>" + "해당 인증번호를 인증번호 확인란에 기입하여 주세요."; // Email
																														// content
			sendMail(setFrom, toMail, title, content);
			return Integer.toString(code);
		} else {
			return "Duplicated Email";
		}

	}

	/*
	 * method for sending validation email
	 */
	@Override
	public void sendMail(String setFrom, String toMail, String title, String content) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
