package worktalk.com.user.service;

import java.util.List;

import worktalk.com.user.domain.Reservation;

public interface ReservationServiceDAO {
	
	public Reservation reserve(Reservation reservation);
	public int cancel(Reservation reservation);
	public List<Reservation> isBooked(Reservation reservation);
	public Reservation findReservationByNum(Reservation reservation);
	public List<Reservation> findReservationByName(Reservation reservation);
	public List<Reservation> findReservationByStatus(Reservation reservation);

}
