package com.example.restaurant.controlador;

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

    @GetMapping("admin/Usuarios/{id}")
    public String usuario(Model model, @PathVariable Long id){
        model.addAttribute("usuario", usuarioServicio.findById(id));
        return "usuarios/detalles-usuario";
    }
}
