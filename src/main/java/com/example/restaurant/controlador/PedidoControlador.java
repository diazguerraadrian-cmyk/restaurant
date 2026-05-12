package com.example.restaurant.controlador;

import com.example.restaurant.model.Pedido;
import com.example.restaurant.model.Restaurante;
import com.example.restaurant.model.TipoPedido;
import com.example.restaurant.repository.LineaPedidoRepository;
import com.example.restaurant.repository.PedidoRepository;
import com.example.restaurant.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class PedidoControlador {
    private final PedidoRepository pedidoRepository;
    private final LineaPedidoRepository lineaPedidoRepository;
    private final RestauranteRepository restauranteRepository;

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
        model.addAttribute("lineaPedido", lineaPedidoRepository.findByPedido_Id(id));
        return "pedidos/detalles-pedido";
    }
    @GetMapping("pedidos/new")
    public String newOrder(Model model, @RequestParam Long restauranteId) {
        Restaurante restaurante = restauranteRepository.findById(restauranteId).orElseThrow();
        Pedido pedido = new Pedido();
        pedido.setRestaurante(restaurante);
        model.addAttribute("pedido", pedido);
        return "pedidos/form-pedido";
    }
    @PostMapping("pedidos")
    public String guardarPedido(@ModelAttribute Pedido pedido){
        pedido.setTipoPedido(TipoPedido.PENDING);
        pedido.setFechaPedido(LocalDateTime.now());
        pedido.setPrecioTotal(0d);
        pedido.setNumeroPersonas(1);
        pedidoRepository.save(pedido);
        return "redirect:/pedidos" + pedido.getId();
    }
}
