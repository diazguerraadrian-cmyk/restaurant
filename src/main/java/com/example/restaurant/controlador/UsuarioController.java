package com.example.restaurant.controlador;

import com.example.restaurant.model.Role;
import com.example.restaurant.model.Usuario;
import com.example.restaurant.service.UsuarioServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {
    private UsuarioServicio usuarioServicio;

    @GetMapping("admin/usuarios")
    public String listaUsuarios(Model model){
        model.addAttribute("usuarios", usuarioServicio.findAll());
        return "users/user-list";
    }

    @GetMapping("admin/usuarios/{id}")
    public String usuario(Model model, @PathVariable Long id){
        model.addAttribute("usuario", usuarioServicio.findById(id));
        model.addAttribute("usuario", usuarioServicio.findStatsById(id));
        return "users/user-detail";
    }
    @GetMapping("admin/usuarios/new")
    public String nuevoUsuario(Model model){
        model.addAttribute("user", new Usuario());
        model.addAttribute("roles", Role.values());
        model.addAttribute("edit", false);
        return "users/user-form";
    }
    @GetMapping("admin/usuarios/edit/{id}")
    public String editUser(Model model, @PathVariable Long id){
        Usuario user = usuarioServicio.findById(id);
        user.setPassword(null);
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("edit", true);
        return "users/user-form";
    }
    @PostMapping("admin/usuarios")
    public String save(@ModelAttribute Usuario user, RedirectAttributes ra){
        if (user.getId() == null){
            usuarioServicio.create(user);
            ra.addFlashAttribute("message", "usuario creado");
        } else {
            usuarioServicio.update(user);
            ra.addFlashAttribute("message", "usuario actualizado");
        }
        usuarioServicio.save(user);
        return "redirect:/admin/usuarios";
    }
}
