package notificaciones;
import logicanegocio.GestorStand;
import datos.Stand;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit; //realiza operaciones de medición entre fechas y horas.

public class AlertaVencimientoReserva implements Observer, ElementoVisual {

    private GestorStand gestorStands;

    public AlertaVencimientoReserva(GestorStand gestorStands) {
        this.gestorStands = gestorStands;
        this.gestorStands.registrarObservador(this);
    }
    public void actualizar() {
        mostrar();
    }
    public void mostrar() {

        System.out.println("\n vencimiento de reserva ");

        LocalDate hoy = LocalDate.now();

        for (Stand s : gestorStands.obtenerStands()) {

            if ("Reservado".equals(s.getEstado())) {

                if (s.getFechaReserva() == null) {
                    continue;
                }

                // Calcular la fecha de vencimiento: 15 días después
                LocalDate fechaVencimiento =
                        s.getFechaReserva().plusDays(15);

                // Calcular los días restantes
                long diasRestantes = ChronoUnit.DAYS.between(
                        hoy, fechaVencimiento
                );

                System.out.println("\nStand ID: " + s.getIDStand()+ " Editorial: " + s.getEditorial());

                System.out.println( "Fecha de reserva: " + s.getFechaReserva());
                System.out.println("Fecha límite de reserva: " + fechaVencimiento);

                if (diasRestantes < 0) {
                System.out.println( "La reserva ha vencido.\n");

                } else if (diasRestantes <= 5) {
                    System.out.println("¡URGENTE! La reserva está próxima a vencer.");
                    System.out.println("Diás restantes:" + diasRestantes + "\n");
                } else {
                    System.out.println("La reserva todavía está dentro del plazo.\n");
                }
            }
        }
    }
}
