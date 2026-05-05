package com.example.restaurant;

import com.example.restaurant.model.*;
import com.example.restaurant.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class RestaurantApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(RestaurantApplication.class, args);

        // obtener los repositorios para hacer operaciones de base de datos
        // Los repositorios nos dan las operaciones CRUD (findAll, findById, save, delete)
        RestauranteRepository restauranteRepository = context.getBean(RestauranteRepository.class);
        EmpleadoRepository empleadoRepository = context.getBean(EmpleadoRepository.class);
        PlatoRepository platoRepository = context.getBean(PlatoRepository.class);
        PedidoRepository pedidoRepository = context.getBean(PedidoRepository.class);
        LineaPedidoRepository lineaPedidoRepository = context.getBean(LineaPedidoRepository.class);
        ReviewRepository reviewRepository = context.getBean(ReviewRepository.class);

        // crear un objeto restaurante: new
        Restaurante pacoBar = new Restaurante();
        pacoBar.setNombre("pacoBar");
        pacoBar.setPrecioMedio(20.0);
        pacoBar.setNumero_empleados(5);

        // guardar el restaurante en base de datos usando el repositorio: .save()
        restauranteRepository.save(pacoBar);

        // Opción 1: crear objeto vacío
        Restaurante rest = new Restaurante();

        // Opción 2: crear un objeto con datos
        Restaurante rest2 = new Restaurante("100 montaditos", 33.0, 7);
        restauranteRepository.save(rest2);

        // Opción 3: crearlo vacío y actualizarlo con métodos set
        Restaurante rest3 = new Restaurante();
        rest3.setNombre("Rest3");
        rest3.setPrecioMedio(50.22);
        restauranteRepository.save(rest3);


        Empleado emp1 = new Empleado();
        emp1.setNombre("Pablo");
        emp1.setApellidos("López");
        emp1.setEdad(45);
        empleadoRepository.save(emp1);

        // Obtener todos los restaurantes de la base de datos
        // SELECT * from restaurantes;
        List<Restaurante> restaurantes = restauranteRepository.findAll();

        // imprimir los restaurantes obtenidos con un bucle for
        System.out.println(restauranteRepository.findAll());
        System.out.println(restaurantes);
        //for (int i = 0; i < restaurantes.size(); i++) {
           // System.out.println(restaurantes.get(i));
       // }
        for (Restaurante restaurante : restaurantes) { // bucle for-each: itera uno a uno los restaurantes sin crear un index
            System.out.println(restaurante);
        }

        List<Empleado> empleados = empleadoRepository.findAll();
        System.out.println(empleadoRepository.findAll());

        for (Empleado empleado : empleados) {
            System.out.println(empleado);
        }
        Restaurante r1 = new Restaurante("R1", 10.0, 3);
        Restaurante r2 = new Restaurante("R2", 15.0, 4);
        // opción clásica para crear lista:
        List<Restaurante> sitiosParaComer = new ArrayList<>(); // crea una lista vacía

        List<Restaurante> sitiosGuaposParaComer = List.of(r1,r2);
        restauranteRepository.saveAll(sitiosGuaposParaComer);

        long numeroRestaurantes = restauranteRepository.count();
        if (numeroRestaurantes > 0){
            System.out.println("Hay para comer, todos tranquis");
        }
        else {
            System.out.println("Estamos en crisis, no hay restaurantes");
        }

        long id = 1;
        boolean existe =  restauranteRepository.existsById(id);
        if (existe = true){
            System.out.println("El restaurante 1 si existe");
        }
        else {
            System.out.println("El restaurante 1 no existe");
        }
        // restauranteRepository.existsById(2L);

        // restauranteRepository.deleteAll();

        Long idABorrar = 1L;
        restauranteRepository.deleteById(1L); // hard coded
        System.out.println("restaurante con id" + idABorrar + " existe : " + restauranteRepository.existsById(idABorrar));
        // restauranteRepository.deleteById(rest.getId());

        restauranteRepository.delete(r2); // le pasamos un objeto restaurante

        Long idABuscar = 2L;
        // Restaurante restauranteEncontrado = restauranteRepository.findById(idABuscar);
        Optional<Restaurante> restauranteFromDatabase = restauranteRepository.findById(idABuscar);
        // var restauranteFromDatabase = restaurantRepository.findById(idABuscar);
        if (restauranteFromDatabase.isPresent()){
            Restaurante restaurante2 = restauranteFromDatabase.get();
            System.out.println(restaurante2);
        }

        // crear un restaurante español
        Restaurante restauranteEspañol = new Restaurante();
        restauranteEspañol.setTipoComida(TipoComida.SPANISH);
        restauranteEspañol.setPrecioMedio(4d);
        restauranteRepository.save(restauranteEspañol);
        System.out.println(restauranteEspañol);
        // crear un restaurante de comida japonesa
        Restaurante restauranteJapones = new Restaurante();
        restauranteJapones.setTipoComida(TipoComida.JAPANESE);
        restauranteJapones.setPrecioMedio(16d);
        restauranteRepository.save(restauranteJapones);
        System.out.println(restauranteJapones);
        // probar fecha de startDate del restaurante

        Restaurante smashBurger = new Restaurante();
        smashBurger.setStarDate(LocalDate.now()); // fecha actual
        smashBurger.setNombre("Smash Burger");
        restauranteRepository.save(smashBurger);
        System.out.println(smashBurger);
        // restauranteRepository.findById(idABuscar);

        // fecha futura
        Restaurante sidreria = new Restaurante();
        sidreria.setNombre("Sidreria");
        sidreria.setStarDate(LocalDate.of(2025, 1, 1));
        restauranteRepository.save(sidreria);
        System.out.println(sidreria);

        // MANY TO ONE - ASOCIAR UN RESTAURANTE A DOS EMPLEADOS
        // Paso 1. crear restaurante y guardarlo

        // Paso 2. crear empleados, setRestaurante y guardar

        // Paso 3. ver en h2-console si los empleados tienen restaurante

        Restaurante restauranteAsociado = new Restaurante();
        restauranteAsociado.setNombre("Restaurante Asociado");
        restauranteAsociado.setTipoComida(TipoComida.SPANISH);
        restauranteRepository.save(restauranteAsociado);
        System.out.println(restauranteAsociado);

        Empleado empReal = new Empleado();
        empReal.setNombre("Empleado Real");
        empReal.setRestaurante(restauranteAsociado);
        empReal.setEdad(18); // No
        empleadoRepository.save(empReal);
        System.out.println(empReal);

        Empleado empReal2 = new Empleado();
        empReal2.setNombre("Empleado Real 2");
        empReal2.setRestaurante(restauranteAsociado);
        empReal2.setEdad(35); // Sí cumple el filtro de findByEdadGreaterThanEqual
        empleadoRepository.save(empReal2);
        System.out.println(empReal2);

        // bucle for para iterar sobre todos los empleados imprimiendo el nombre del empleado y el nombre del restaurante
        // si lo tiene
        for (Empleado empleado : empleadoRepository.findAll()) {
            System.out.println("Empleado: " + empleado.getNombre());
            if (empleado.getRestaurante() != null) {
                System.out.println("Restaurante: " + empleado.getRestaurante().getNombre());
            } else {
                System.out.println("No tiene restaurante asociado");
            }
        }
            // probar a filtrar por nombre de restaurante
            // List<Empleado> empleados20 = empleadoRepository.findByEdad(20);
            List<Empleado> empleadosRestaurante = empleadoRepository.findByRestauranteNombre("Restaurante Asociado");
            System.out.println(empleadosRestaurante);

            System.out.println("FILTRAR EMPLEADOS POR TIPO DE COMIDA DE RESTAURANTE:");
            empleadoRepository.findByRestaurante_TipoComida(TipoComida.SPANISH);
            for (var empleado1 : empleadoRepository.findByRestaurante_TipoComida(TipoComida.SPANISH)) {
                System.out.println(empleado1);
            }

            System.out.println("FILTRAR EMPLEADOS POR EDAD MAYOR O IGUAL QUE");
            for (var empleado1 : empleadoRepository.findByEdadGreaterThanEqual(20)) {
                System.out.println(empleado1);
            }

            System.out.println("TRAER TODOS LOS EMPLEADOS ORDENADOS POR NOMBRE POR NOMBRE ASCENDENTE A-Z");
            for (var empleado1 : empleadoRepository.findByOrderByNombreAsc()) {
                System.out.println(empleado1);
            }

            // filtrar por apellido
            // filtrar por edad

            Plato plato1 = new Plato(null, "Ensalada", "de puñetazos", 5.0, TipoPlato.PRIMERO, restauranteEspañol);
            Plato plato2 = new Plato(null, "Lentejas", "con chorizo", 8.0, TipoPlato.SEGUNDO, restauranteEspañol);
            Plato plato3 = new Plato(null, "Tarta de queso", null, 7.50, TipoPlato.POSTRE, restauranteEspañol);
            platoRepository.saveAll(List.of(plato1, plato2, plato3));

            // platoRepository.findByPrecioLessThanEqual(10.0);
            for (var plato : platoRepository.findByPrecioLessThanEqual(7.99)){
                System.out.println(plato);
            }

            System.out.println("TRAER PLATOS DE UN RESTAURANTE ORDENADOS POR PRECIO ASCENDENTE");
            Long restauranteId = restauranteEspañol.getId();
            for (var plato : platoRepository.findByRestauranteIdOrderByPrecio(restauranteId)){
                System.out.println(plato);
            }

            Pedido pedido1 = new Pedido();
            pedido1.setNumeroTabla(1);
            pedido1.setNumeroPersonas(2);
            pedido1.setRestaurante(restauranteEspañol);
            pedido1.setPropina(2.33);
            pedidoRepository.save(pedido1);

            Pedido pedido2 = new Pedido(4, 2, 4.23, restauranteEspañol);
            pedidoRepository.save(pedido2);

            LineaPedido lineapedido1 = new LineaPedido(pedido1, plato1, 1);
            LineaPedido lineapedido2 = new LineaPedido(pedido1, plato2, 2);
            LineaPedido lineapedido3 = new LineaPedido(pedido1, plato3, 3);

            List<LineaPedido> lineasPedido = lineaPedidoRepository.saveAll(List.of(lineapedido1, lineapedido2, lineapedido3));

            double precioTotal = 0.0;
            for (LineaPedido lineaPedido : lineasPedido) {
                double preciolinea = lineaPedido.getPlato().getPrecio() * lineaPedido.getCantidad();
                precioTotal += preciolinea;
            }


            pedido1.setPrecioTotal(precioTotal);
            pedido1.setTipoPedido(TipoPedido.COMPLETED);
            pedidoRepository.save(pedido1);

           Double precioTotal2 = lineaPedidoRepository.calculatepreciototal(pedido1.getId());

        System.out.println("Precio precioTotal: " + precioTotal);
        System.out.println("Precio precioTotal2: " + precioTotal2);

        Review review1 = Review.builder()
                .Title("Excelente comida y servicio")
                .descripcion("Muy buena atención al cliente")
                .restaurante(restauranteEspañol)
                .calificacion(7)
                .build();

        Review review2 = Review.builder()
                .Title("Mala comida y servicio")
                .descripcion("Muy mala atención al cliente")
                .restaurante(restauranteEspañol)
                .calificacion(2)
                .build();

        reviewRepository.saveAll(List.of(review1, review2));

        Review review3 = Review.builder()
                .Title("No tan asqueroso, pero fatal")
                .descripcion("Muy mala atención al cliente")
                .plato(plato2)
                .calificacion(2)
                .build();

        Review review4 = Review.builder()
                .Title("Asqueroso")
                .descripcion("Muy mala atención al cliente")
                .plato(plato1)
                .calificacion(0)
                .build();

    }

}
