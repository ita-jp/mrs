package io.nagaita.mrs.domain.service;

import io.nagaita.mrs.domain.AlreadyReservedException;
import io.nagaita.mrs.domain.UnavailableReservationException;
import io.nagaita.mrs.domain.model.ReservableRoomId;
import io.nagaita.mrs.domain.model.Reservation;
import io.nagaita.mrs.domain.repository.ReservableRoomRepository;
import io.nagaita.mrs.domain.repository.ReservationRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private ReservableRoomRepository reservableRoomRepository;

	public List<Reservation> findReservations(ReservableRoomId reservableRoomId) {
		return reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId);
	}

	public Reservation reserve(Reservation reservation) {
		val reservableRoomId = reservation.getReservableRoom().getReservableRoomId();
		val reservableRoom = reservableRoomRepository.findOneForUpdateByReservableRoomId(reservableRoomId);

		if (!reservableRoom.isPresent()) {
			throw new UnavailableReservationException("入力の日付・部屋の組み合わせは予約できません。");
		}

		val isOverlap = reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId)
				.stream().anyMatch(x -> x.isOverlap(reservation));

		if (isOverlap) {
			throw new AlreadyReservedException("入力の時間帯はすでに予約済みです。");
		}

		reservationRepository.save(reservation);

		return reservation;
	}

	@PreAuthorize("hasRole('ADMIN') or #reservation.user.userId == principal.user.userId")
	public void cancel(@P("reservation") Reservation reservation) {
		reservationRepository.delete(reservation);
	}

	public Optional<Reservation> findOne(Integer reservationId) {
		return reservationRepository.findById(reservationId);
	}
}
