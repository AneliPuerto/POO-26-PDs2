package logicanegocio;
import datos.Stand;
import notificaciones.Observer;
import notificaciones.Subject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorStand implements Subject {

    private List<Observer> listObserver;
    private List<Stand> listStand;
    private List<String> listEditoriales;

    public GestorStand() {
        this.listObserver = new ArrayList<>();
        this.listStand = new ArrayList<>();
        this.listEditoriales = new ArrayList<>();
    }

    //OBSERVER

    public void registrarObservador(Observer o) {
        listObserver.add(o);
    }

    public void eliminarObservador(Observer o) {
        listObserver.remove(o);
    }

    public void notificarObservadores() {
        for (Observer o : listObserver) {
            o.actualizar();
        }
    }

    // registro de editoriales

    public void registrarEditorial(String editorial) {
        if (editorial != null && !editorial.trim().isEmpty()
                && !listEditoriales.contains(editorial)) {

            listEditoriales.add(editorial);
        }
    }
    //agregar stands
    public void agregarStand(Stand stand) {
        listStand.add(stand);
    }
    public List<Stand> obtenerStands() {
        return listStand;
    }
    // buscar 
    public Stand buscarStand(String id) {

        for (Stand s : listStand) {

            if (s.getIDStand().equals(id)) {
                return s;
            }
        }

        return null;
    }

    // reserva
    public boolean reservarStand(String id, String editorial) {
        Stand s = buscarStand(id);
        if (s == null || !"Disponible".equals(s.getEstado())
                || !listEditoriales.contains(editorial)) {
            return false;
        }
        s.setEditorial(editorial);
        s.setEstado("Reservado");

        // fecha de reserva
        LocalDate hoy = LocalDate.now();
        s.setFechaReserva(hoy);
        // Cinco días para obtener descuento
        s.setFechaProntoPago(hoy.plusDays(5));
        // Quince días para completar la reserva
        s.setFechaLimiteTotal(hoy.plusDays(15));
        //costo original
        double costoOriginal = s.getMetrosc() * s.getPrecioBase();
        s.setCostoFinal(costoOriginal * 0.90); // Aplicar descuento del 10 %
        s.setMontoPagado(0.0);
        return true;
    }
    public boolean confirmarPagoMinimo(String id, double pago) {
        Stand s = buscarStand(id);
        if (s == null || !"Reservado".equals(s.getEstado())) {
            return false;
        }

        double costoOriginal = s.getMetrosc() * s.getPrecioBase();
        double pagoMinimo = costoOriginal * 0.15;
        // Comprobar si todavía tiene descuento
        if (LocalDate.now().isAfter(s.getFechaProntoPago())) {
            s.setCostoFinal(costoOriginal);
        } else {
            s.setCostoFinal(costoOriginal * 0.90);
        }
        if (pago < pagoMinimo || pago > s.getCostoFinal()) {
            return false;
        }
        s.setMontoPagado(pago);
        s.setEstado("Ocupado");
        return true;
    }

    // Registrar pago
    public boolean registrarPago(String id, double pago) {
        Stand s = buscarStand(id);
        if (s == null || !"Ocupado".equals(s.getEstado())|| pago <= 0) {
            return false;
        }
        double nuevoMonto = s.getMontoPagado() + pago;
        if (nuevoMonto > s.getCostoFinal()) {
            return false;
        }
        s.setMontoPagado(nuevoMonto);
        return true;
    }

    // dejar disponible el stand

    public boolean liberarStand(String id) {
        Stand s = buscarStand(id);
        if (s == null || "Disponible".equals(s.getEstado())) {
            return false;
        }
        s.setEstado("Disponible");
        s.setEditorial("");
        s.setMontoPagado(0.0);
        return true;
    }

    // bloqueo manual 
    public boolean bloquearStand(String id) {
        Stand s = buscarStand(id);
        if (s == null || !"Disponible".equals(s.getEstado())) {
            return false;
        }
        s.setEstado("Bloqueado");
        return true;
    }

    // cambiar de estado de bloqueo 
    public boolean transferirStand(String id, String editorial) {
        Stand s = buscarStand(id);
        if (s == null || !"Bloqueado".equals(s.getEstado())
                || !listEditoriales.contains(editorial)) {
            return false;
        }

        s.setEditorial(editorial);
        s.setEstado("Reservado");
        LocalDate hoy = LocalDate.now();

        s.setFechaReserva(hoy);
        s.setFechaProntoPago(hoy.plusDays(5));
        s.setFechaLimiteTotal(hoy.plusDays(15));

        double costoOriginal = s.getMetrosc() * s.getPrecioBase();

        s.setCostoFinal(costoOriginal * 0.90);
        s.setMontoPagado(0.0);

        return true;
    }
}
