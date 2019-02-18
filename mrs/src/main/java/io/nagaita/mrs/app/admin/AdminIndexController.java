package io.nagaita.mrs.app.admin;

import io.nagaita.mrs.domain.service.user.ReservationDetails;
import io.nagaita.mrs.domain.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminIndexController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String index(@AuthenticationPrincipal ReservationDetails userDetail, Model model) {
		model.addAttribute("currentUser", userDetail.getUser());
		model.addAttribute("userList", userService.listAll());
		return "admin/index";
	}
}
