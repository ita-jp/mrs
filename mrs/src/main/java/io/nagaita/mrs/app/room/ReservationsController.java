package io.nagaita.mrs.app.room;

import io.nagaita.mrs.domain.model.ReservableRoomId;
import io.nagaita.mrs.domain.model.RoleName;
import io.nagaita.mrs.domain.model.User;
import io.nagaita.mrs.domain.service.ReservationService;
import io.nagaita.mrs.domain.service.RoomService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
		model.addAttribute("user", dummyUser());

		return "reservation/reserveForm";
	}

	private User dummyUser() {
		return new User() {{
			setUserId("taro-yamada");
			setFirstName("太郎");
			setLastName("山田");
			setRoleName(RoleName.USER);
		}};
	}
}
