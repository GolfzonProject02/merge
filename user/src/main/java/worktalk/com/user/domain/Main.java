package worktalk.com.user.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String[] args) {
//		long ts = 1667545740000;
//		System.out.println(ts);
		// 1667545740
		// 2147483647
		// 1667546256
		Timestamp p_date = new Timestamp(1667546612);
		System.out.println("p_date : " +p_date);
		System.out.println();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(p_date);
		System.out.println("date: "+date);

	}

}
