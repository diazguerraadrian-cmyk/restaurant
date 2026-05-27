package com.example.restaurant.controlador;

import com.example.restaurant.model.Role;
import com.example.restaurant.model.Usuario;
import com.example.restaurant.service.UsuarioServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UsuarioController {
    private UsuarioServicio usuarioServicio;

    @GetMapping("admin/usuarios")
    public String listaUsuarios(Model model){
        model.addAttribute("usuarios", usuarioServicio.findAll());
        return "usuarios/lista-usuarios";
    }

    @GetMapping("admin/usuarios/{id}")
    public String usuario(Model model, @PathVariable Long id){
        model.addAttribute("usuario", usuarioServicio.findById(id));
        model.addAttribute("usuario", usuarioServicio.findStatsById(id));
        return "usuarios/detalles-usuario";
    }
    @GetMapping("admin/usuarios/new")
    public String nuevoUsuario(Model model){
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", Role.values());
        model.addAttribute("edit", false);
        return "users/user-form";
    }
}
