package com.example.restaurant.controlador;

import com.example.restaurant.model.*;
import com.example.restaurant.repository.LineaPedidoRepository;
import com.example.restaurant.repository.PedidoRepository;
import com.example.restaurant.repository.PlatoRepository;
import com.example.restaurant.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class PedidoControlador {
    private final PedidoRepository pedidoRepository;
    private final LineaPedidoRepository lineaPedidoRepository;
    private final RestauranteRepository restauranteRepository;
    private final PlatoRepository platoRepository;

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
        Pedido pedido = pedidoRepository.findById(id).orElseThrow();
        model.addAttribute("pedido", pedidoRepository.findById(id).orElseThrow());
        model.addAttribute("lineaPedido", lineaPedidoRepository.findByPedido_Id(id));
        List<Plato> platos = platoRepository.findByRestauranteIdOrderByPrecio(pedido.getRestaurante().getId());
        model.addAttribute("platos", platos);
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
    @PostMapping("pedidos/{id}/lineas")
    public String addLineaPlato(@PathVariable Long id, @RequestParam Long platoId){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow();
        Plato plato = platoRepository.findById(platoId).orElseThrow();
        Optional<LineaPedido> lineaOptional = lineaPedidoRepository.findByPedido_IdAndPlato_Id(id, platoId);
        LineaPedido lineaPedido;
        if (lineaOptional.isPresent()) {
            lineaPedido = lineaOptional.get();
            lineaPedido.setCantidad(lineaPedido.getCantidad() + 1);
            lineaPedidoRepository.save(lineaPedido);
        } else {
            lineaPedido = new LineaPedido();
            lineaPedido.setPlato(plato);
            lineaPedido.setPedido(pedido);
            lineaPedido.setCantidad(1);
        }
        lineaPedidoRepository.save(lineaPedido);
        // opción alternativa estilo funcional
        // LineaPedido linea = lineaPedidoRepository
        // .findByPedido_IdAndPlato_Id(id, platoId)
        // .orElseGet(() -> new LineaPedido(0, pedido, plato));
//
//        line.setQuantity(line.getQuantity() + 1);
//        orderLineRepository.save(line);


        if (pedido.getTipoPedido() == TipoPedido.PENDING) pedido.setTipoPedido(TipoPedido.IN_PROGRESS);
        Double precioTotal = lineaPedidoRepository.calculatepreciototal(pedido.getId());
        pedido.setPrecioTotal(precioTotal);
        pedidoRepository.save(pedido);
        return "redirect:/pedidos/" + pedido.getId();



    }
    @GetMapping("pedidos/{id}/complete")
    public String acabado(@PathVariable Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow();
        pedido.setTipoPedido(TipoPedido.COMPLETED);
        pedido.setPrecioTotal(lineaPedidoRepository.calculatepreciototal(pedido.getId()));
        pedidoRepository.save(pedido);
        return "redirect:/pedidos/" + id;
    }
}
