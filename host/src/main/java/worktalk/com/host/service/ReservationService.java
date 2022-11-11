package worktalk.com.host.service;

import java.util.List;

import worktalk.com.host.domain.Reservation;

/**
 * 
 * @author Juhee Fred Lee
 * Service class for host reservation service
 *
 */

public interface ReservationService {
	
	public Reservation updateStatus(Reservation reservation);
	public Reservation cancel(Reservation reservation);
	public Reservation findReservationByNum(Reservation reservation);
	public List<Reservation> findReservationByName(Reservation reservation);
	public List<Reservation> findReservationByKeywords(Reservation reservation);

}
