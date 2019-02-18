package io.nagaita.mrs.app.room;

import io.nagaita.mrs.domain.AlreadyReservedException;
import io.nagaita.mrs.domain.UnavailableReservationException;
import io.nagaita.mrs.domain.model.ReservableRoom;
import io.nagaita.mrs.domain.model.ReservableRoomId;
import io.nagaita.mrs.domain.model.Reservation;
import io.nagaita.mrs.domain.service.ReservationService;
import io.nagaita.mrs.domain.service.RoomService;
import io.nagaita.mrs.domain.service.user.ReservationDetails;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("reservations/{date}/{roomId}")
public class ReservationsController {

	@Autowired
	private RoomService roomService;
	@Autowired
	private ReservationService reservationService;

	@ModelAttribute
	public ReservationForm setUpForm() {
		return new ReservationForm() {{
			setStartTime(LocalTime.of(9, 0));
			setEndTime(LocalTime.of(10, 0));
		}};
	}

	@GetMapping
	public String reserveForm(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
			@PathVariable("roomId") Integer roomId, Model model) {
		val reservableRoomId = new ReservableRoomId(roomId, date);
		val reservations = reservationService.findReservations(reservableRoomId);
		val timeList =
				Stream.iterate(LocalTime.of(0, 0), t -> t.plusMinutes(30)).limit(24 * 2).collect(Collectors.toList());

		model.addAttribute("room", roomService.findMeetingRoom(roomId).get());
		model.addAttribute("reservations", reservations);
		model.addAttribute("timeList", timeList);

		return "reservation/reserveForm";
	}

	@PostMapping
	public String reserve(@Validated ReservationForm form, BindingResult bindingResult,
			@AuthenticationPrincipal ReservationDetails userDetails,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
			@PathVariable("roomId") Integer roomId, Model model) {
		if (bindingResult.hasErrors()) {
			return reserveForm(date, roomId, model);
		}

		val reservation = new Reservation();
		reservation.setStartTime(form.getStartTime());
		reservation.setEndTime(form.getEndTime());
		reservation.setReservableRoom(new ReservableRoom(new ReservableRoomId(roomId, date)));
		reservation.setUser(userDetails.getUser());

		try {
			reservationService.reserve(reservation);
		} catch (AlreadyReservedException | UnavailableReservationException e) {
			model.addAttribute("error", e.getMessage());
			return reserveForm(date, roomId, model);
		}

		return "redirect:/reservations/{date}/{roomId}";
	}

	@PostMapping(params = "cancel")
	public String cancel(@RequestParam("reservationId") Integer reservationId,
			@AuthenticationPrincipal ReservationDetails userDetails,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
			@PathVariable("roomId") Integer roomId, Model model) {
		try {
			reservationService.findOne(reservationId).ifPresent(reservationService::cancel);
		} catch (AccessDeniedException e) {
			model.addAttribute("error", e.getMessage());
			return reserveForm(date, roomId, model);
		}

		return "redirect:/reservations/{date}/{roomId}";
	}
}
