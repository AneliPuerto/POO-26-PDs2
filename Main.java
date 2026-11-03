import datos.Stand;
import logicanegocio.GestorStand;
import notificaciones.AlertaVencimientoReserva;
import notificaciones.AlertaProntoPago;
import notificaciones.AlertaPagoTotal;
import java.time.LocalDate;

public class Main {
    // Mostrar los stands disponibles
    public static void mostrarStandsDisponibles(GestorStand gestor) {

        System.out.println("\n  Stands Disponibles \n");

        boolean hayDisponibles = false;

        for (Stand s : gestor.obtenerStands()) {

            if ("Disponible".equals(s.getEstado())) {

                System.out.println(
                    "Stand ID: " + s.getIDStand()
                    + " | Editorial: " + s.getEditorial()
                    + " | Estado: " + s.getEstado()
                );

                hayDisponibles = true;
            }
        }

        if (!hayDisponibles) {
            System.out.println("No hay stands disponibles.");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println(" SISTEMA DE STANDS FILEY \n");
        
        GestorStand gestor = new GestorStand();

        AlertaVencimientoReserva alertaReserva = new AlertaVencimientoReserva(gestor);
        AlertaProntoPago alertaPronto = new AlertaProntoPago(gestor);
        AlertaPagoTotal alertaPagoTotal = new AlertaPagoTotal(gestor);

        
        gestor.registrarEditorial("Editorial Dante");
        gestor.registrarEditorial("Editorial Kóokay Ediciones");
        gestor.registrarEditorial("Editorial Universidad");
        gestor.registrarEditorial("Editorial Kamite");

       

        // Reserva próxima a vencer
        Stand stand1 = new Stand(
                "A01",
                12.0,
                5000.0,
                LocalDate.now().minusDays(14),
                LocalDate.now().minusDays(9),
                LocalDate.now().plusDays(20),
                "Editorial Dante"
        );

        stand1.setEstado("Reservado");

        // Descuento por pronto pago próximo a vencer
        Stand stand2 = new Stand(
                "2",
                18.0,
                7500.0,
                LocalDate.now().minusDays(4),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(30),
                "Editorial Kóokay Ediciones"
        );

        stand2.setEstado("Ocupado");
        stand2.setMontoPagado(20250.0);

        // Fecha límite de pago 
        Stand stand3 = new Stand(
                "3",
                20.0,
                1000.0,
                LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(2),
                "Editorial Universidad"
        );

        stand3.setEstado("Ocupado");
        stand3.setMontoPagado(30000.0);

        // Stand ocupado
        Stand stand4 = new Stand(
                "D04",
                15.0,
                6000.0,
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(5),
                LocalDate.now().plusDays(30),
                "Editorial Kamite"
        );

        stand4.setEstado("Ocupado");
        stand4.setMontoPagado(15000.0);

        // Bloqueado para transferirlo a una editorial
        Stand stand5 = new Stand(
                "E05",
                16.0,
                6500.0,
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                LocalDate.now().plusDays(30),
                "");

        stand5.setEstado("Bloqueado");

        // Stand 6: Disponible
        Stand stand6 = new Stand(
                "F06",
                20.0,
                7000.0,
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                LocalDate.now().plusDays(30),
                "");

        stand6.setEstado("Disponible");

        gestor.agregarStand(stand1);
        gestor.agregarStand(stand2);
        gestor.agregarStand(stand3);
        gestor.agregarStand(stand4);
        gestor.agregarStand(stand5);
        gestor.agregarStand(stand6);

        mostrarStandsDisponibles(gestor);

        //notificaciones
        System.out.println("\nNotificacion de prueba\n");

        gestor.notificarObservadores();

        // Transferir el stand 5 a una editorial
        System.out.println("\n Transferir Stand \n");

        if (gestor.transferirStand("E05", "Editorial Kamite")) {

            System.out.println(
                "El stand E05 fue transferido correctamente "
                + "a Editorial Kamite.");
        } else {
            System.out.println( "No se pudo realizar la transferencia del stand E05.");
        }

        //stands disponibles
        mostrarStandsDisponibles(gestor);

        // Estado final de los Stands
        System.out.println("\n Lista Stands \n");
        for (Stand s : gestor.obtenerStands()) {
            System.out.println("Stand: " + s.getIDStand() + "Editorial: - " + s.getEditorial() + "Estado: " + s.getEstado() );
        }
    }
}

      