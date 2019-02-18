package io.nagaita.mrs.app.room;

import io.nagaita.mrs.domain.service.RoomService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/rooms/")
public class RoomController {

	@Autowired
	private RoomService roomService;

	@GetMapping
	public String listRooms(Model model) {
		val today = LocalDate.now();
		model.addAttribute("date", today);
		return listRooms(today, model);
	}

	@GetMapping("{date}")
	public String listRooms(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
			Model model) {
		val rooms = roomService.findReservableRooms(date);
		model.addAttribute("rooms", rooms);
		return "rooms/listRooms";
	}
}
