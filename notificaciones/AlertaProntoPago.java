
package notificaciones;
import logicanegocio.GestorStand;
import datos.Stand;
import java.time.LocalDate; //para fechas

public class AlertaProntoPago implements Observer, ElementoVisual {

    private GestorStand gestorStands;

    public AlertaProntoPago(GestorStand gestorStands) {
        this.gestorStands = gestorStands;
        this.gestorStands.registrarObservador(this);
    }
    public void actualizar() {
        mostrar();
    }
    public void mostrar() {
        System.out.println("\n PRONTO PAGO ");
        LocalDate hoy = LocalDate.now();
        for (Stand s : gestorStands.obtenerStands()) {
            if ("Ocupado".equals(s.getEstado())) {

                LocalDate fechaLimite = s.getFechaProntoPago();
                if (fechaLimite == null) {
                    continue;
                }
                double costoOriginal = s.getMetrosc() * s.getPrecioBase();
                double descuento = costoOriginal * 0.10;
                System.out.println("\nStand ID: " + s.getIDStand()+ " Editorial: " + s.getEditorial() );
                System.out.println("El descuento es: "+ descuento);
                if (fechaLimite.isBefore(hoy)) { //isbefore compara fechas.
                    System.out.println("El plazo de descuento por pronto pago ha expirado.");
                } else {
                    System.out.println( "El descuento del 10% todavía está vigente.\n");
                    System.out.println("Fecha límite de pronto pago: " + fechaLimite);

                }
            }
        }
    }
}
