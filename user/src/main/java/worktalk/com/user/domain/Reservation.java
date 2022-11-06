package worktalk.com.user.domain;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode @ToString
public class Reservation implements Serializable{

	private long r_num; // sequence number for reservation
	private String name; // name of the person who had reservation
	private long room_num; // sequence number for room
	private String room_name; // name for room
	private long space_num; // sequence number for space
	private String space_name; // name for space
	private String r_start; // room check in hour (yyyy-hh-mm hh:mm:ss)
	private String r_end; // room check out hour (yyyy-hh-mm hh:mm:ss)
	private int amount; // amount of cost for using the facilities
	private String status; // whether room reservation is confirmed by host => Enum Reservation_status
	private int paid; // whether the reservation is paid or not => 0 for not paid, 1 for paid
	private String r_date; // when the user applied for room reservation
	private String cancel_reason; // reason why host or user canceled the reservation
	
}