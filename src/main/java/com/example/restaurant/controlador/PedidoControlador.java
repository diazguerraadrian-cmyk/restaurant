package com.example.restaurant.controlador;

import com.example.restaurant.repository.LineaPedidoRepository;
import com.example.restaurant.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class PedidoControlador {
    private final PedidoRepository pedidoRepository;
    private final LineaPedidoRepository lineaPedidoRepository;

    // @GetMapping orders
    // filtrar por restaurante, filtrar por usuario
    @GetMapping("pedidos")
    public String orders(Model model) {
        model.addAttribute("pedidos",  pedidoRepository.findAll());
        return "pedidos/lista-pedidos";
    }

    // @GetMapping orders/{id}
    @GetMapping("pedidos/{id}")
    public String order(Model model, @PathVariable Long id){
        model.addAttribute("pedido", pedidoRepository.findById(id).orElseThrow());
        model.addAttribute("lineaPedido", lineaPedidoRepository.findByOrder_Id(id));
        return "pedidos/detalles-pedido";
    }
}
