package io.nagaita.mrs.app.admin.room;

import io.nagaita.mrs.domain.model.MeetingRoom;
import io.nagaita.mrs.domain.service.room.MeetingRoomService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/rooms/")
public class AdminRoomController {

	@Autowired
	private MeetingRoomService meetingRoomService;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("roomList", meetingRoomService.findAll());
		return "admin/rooms/list";
	}

	@GetMapping("new")
	public String newForm() {
		return "admin/rooms/new";
	}

	@PostMapping
	public String create(AdminMeetingRoomForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return list(model);
		}

		val room = new MeetingRoom();
		room.setRoomName(form.getRoomName());
		meetingRoomService.create(room);

		return "redirect:/admin/rooms/";
	}

	@GetMapping("{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		val roomOpt = meetingRoomService.findOne(id);
		if (!roomOpt.isPresent()) {
			return list(model);
		}

		model.addAttribute("room", roomOpt.get());
		return "admin/rooms/show";
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model) {
		val roomOpt = meetingRoomService.findOne(id);
		if (!roomOpt.isPresent()) {
			return list(model);
		}

		model.addAttribute("room", roomOpt.get());
		return "admin/rooms/edit";
	}

	@PutMapping("{id}")
	public String update(@PathVariable("id") Integer id, AdminMeetingRoomForm form, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return edit(id, model);
		}

		val room = new MeetingRoom();
		room.setRoomId(id);
		room.setRoomName(form.getRoomName());
		meetingRoomService.update(room);
		return show(id, model);
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {
		meetingRoomService.delete(id);
		return list(model);
	}
}
