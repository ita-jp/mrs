package io.nagaita.mrs.domain.service;

import io.nagaita.mrs.domain.AlreadyReservedException;
import io.nagaita.mrs.domain.UnavailableReservationException;
import io.nagaita.mrs.domain.model.ReservableRoomId;
import io.nagaita.mrs.domain.model.Reservation;
import io.nagaita.mrs.domain.model.RoleName;
import io.nagaita.mrs.domain.model.User;
import io.nagaita.mrs.domain.repository.ReservableRoomRepository;
import io.nagaita.mrs.domain.repository.ReservationRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

		val isOverlap =
				reservationRepository.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId)
						.stream().anyMatch(x -> x.isOverlap(reservation));

		if (isOverlap) {
			throw new AlreadyReservedException("入力の時間帯はすでに予約済みです。");
		}

		reservationRepository.save(reservation);

		return reservation;
	}

	public void cancel(Integer reservationId, User requestUser) {
		val reservation = reservationRepository.findById(reservationId);
		if (RoleName.ADMIN != requestUser.getRoleName() && reservation.map(r -> r.getUser().getUserId()).map(id -> !Objects.equals(id, requestUser.getUserId())).orElse(false)) {
			throw new IllegalStateException("要求されたキャンセルは許可できません。");
		}
		reservationRepository.delete(reservation.get());
	}
}
