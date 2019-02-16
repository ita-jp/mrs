package io.nagaita.mrs.app.admin.user;

import io.nagaita.mrs.domain.model.RoleName;
import io.nagaita.mrs.domain.model.User;
import io.nagaita.mrs.domain.service.user.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @ModelAttribute
    public AdminUserForm setupForm() {
        return new AdminUserForm();
    }

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("userList", userService.listAll());
        return "admin/user/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("roleList", RoleName.all());
        return "admin/user/new";
    }

    @PostMapping("")
    public String create(AdminUserForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return list(model);
        }

        val user = new User();
        user.setUserId(form.getUserId());
        user.setPassword(form.getPassword());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setRoleName(RoleName.lookup(form.getRoleName()));
        userService.save(user);

        return list(model);
    }
}
