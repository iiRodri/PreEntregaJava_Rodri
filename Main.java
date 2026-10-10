import java.util.Scanner;

import exception.ProductoNoEncontradoException;
import exception.StockInsuficienteException;
import model.Producto;
import service.ProductoService;
import ui.MenuProducto;
import util.Validador;

public class Main {
    public static void main(String[] args) {
        ProductoService service = new ProductoService();
        Scanner sc = new Scanner(System.in);
        MenuProducto menu = new MenuProducto(sc, service);
        cargarDatosDePrueba(service);

        int opcion;

        do {
            menu.mostrarMenu();
            opcion = Validador.leerEntero(sc, "Elija una opción:  ");

            try {
                switch (opcion) {
                    case 1 -> menu.agregarProducto();
                    case 2 -> menu.listarProductos();
                    case 3 -> menu.buscarProducto();
                    case 4 -> menu.actualizarProducto();
                    case 5 -> menu.eliminarProducto();
                    case 6 -> System.out.println("¡Hasta luego!");
                    default -> System.out.println("Opción inválida. Elija un número del 1 al 6.");
                }
            } catch (ProductoNoEncontradoException | StockInsuficienteException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        } while (opcion != 6);

        sc.close();

    }

    private static void cargarDatosDePrueba(ProductoService service) {
        service.guardar(new Producto("Jersey Real Madrid 2020", 40000,10, "Camisetas Europeas"));
        service.guardar(new Producto("Jersey Liverpool 2015", 45000, 10, "Camisetas Europeas"));
        service.guardar(new Producto("Jersey Barcelona 2017", 45000, 10, "Camisetas Europeas"));
        service.guardar(new Producto("Jersey Manchester City 2011", 45000, 10, "Camisetas Europeas"));
        service.guardar(new Producto("Jersey Chelsea 2019", 45000, 10, "Camisetas Europeas"));
        System.out.println("Se cargaron los productos de prueba.\n");
    }
}