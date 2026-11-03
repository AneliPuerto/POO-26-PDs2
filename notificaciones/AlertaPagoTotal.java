
package notificaciones;
import logicanegocio.GestorStand;
import datos.Stand;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;  //unidade de tiempo (días, meses, años, horas, segundos,)

public class AlertaPagoTotal implements Observer, ElementoVisual {

    private GestorStand gestorStands;

    public AlertaPagoTotal(GestorStand gestorStands) {
        this.gestorStands = gestorStands;
        this.gestorStands.registrarObservador(this);
    }
    public void actualizar() {
        mostrar();
    }
    public void mostrar() {

        System.out.println("\n Fecha limite de pago total");

        LocalDate hoy = LocalDate.now();

        for (Stand s : gestorStands.obtenerStands()) {

            if ("Ocupado".equals(s.getEstado())) {

                LocalDate fechaPagoTotal = s.getFechaLimiteTotal();

                if (fechaPagoTotal == null) {
                    continue;
                }

                double saldoPendiente = s.getCostoFinal() - s.getMontoPagado();

                System.out.println("Stand ID: " + s.getIDStand()+ " Editorial: " + s.getEditorial());

                System.out.println( "Fecha límite de liquidación: " + fechaPagoTotal);

                System.out.println( "Saldo pendiente: $" + saldoPendiente );


                if (saldoPendiente <= 0) {  System.out.println("El stand ya está liquidado.\n");
                    continue;
                }

                long diasRestantes = ChronoUnit.DAYS.between(hoy, fechaPagoTotal); //calcula la cantidad de días que transcurren  entre  la fecha actual y la fecha límite de pago total // 
        
                if (diasRestantes < 0) {
                    System.out.println("El plazo para pagar el costo total ha vencido.\n");
                } else if (diasRestantes <= 5) {
                    System.out.println("La fecha límite de pago está próxima a vencer.");
                       System.out.println("Días restantes: " + diasRestantes + "\n");

                } else {
                    System.out.println("Plazo de pago total sigue en orden.\n");
                }
            }
        }
    }
}
