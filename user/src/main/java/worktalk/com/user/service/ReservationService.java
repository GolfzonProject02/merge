package worktalk.com.user.service;

import java.util.List;

import worktalk.com.user.domain.Reservation;

public interface ReservationService {
	
	public Reservation reserve(Reservation reservation);
	public Reservation cancel(Reservation reservation);
	public int delete(Reservation reservation);
	public List<Reservation> isBooked(Reservation reservation);
	public long findSpaceNum(Reservation reservation);
	public Reservation findReservationByNum(Reservation reservation);
	public List<Reservation> findReservationByName(Reservation reservation);
	public List<Reservation> findReservationByStatus(Reservation reservation);

}
