package datos;
import java.time.LocalDate;

public class Stand {

    private String IDStand;
    private double metrosc;
    private double precioBase;
    private String Estado;
    private double MontoPagado;
    private double costoFinal;
    private LocalDate FechaReserva;
    private LocalDate FechaProntoPago;
    private LocalDate FechaLimiteTotal;
    private String Editorial;

    public Stand(String idStand, double metrosc, double precioBase,
            LocalDate fechaReserva, LocalDate fechaProntoPago,
            LocalDate fechaLimiteTotal, String editorial) {

        this.IDStand = idStand;
        this.metrosc = metrosc;
        this.precioBase = precioBase;
        this.Estado = "Disponible";
        this.MontoPagado = 0.0;
        this.FechaReserva = fechaReserva;
        this.FechaProntoPago = fechaProntoPago;
        this.FechaLimiteTotal = fechaLimiteTotal;
        this.Editorial = editorial;
        this.costoFinal = metrosc * precioBase;
    }

    public String getIDStand() {
        return IDStand;
    }

    public double getMetrosc() {
        return metrosc;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        this.Estado = estado;
    }

    public LocalDate getFechaReserva() {
        return FechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.FechaReserva = fechaReserva;
    }

    public LocalDate getFechaProntoPago() {
        return FechaProntoPago;
    }

    public void setFechaProntoPago(LocalDate fechaProntoPago) {
        this.FechaProntoPago = fechaProntoPago;
    }

    public LocalDate getFechaLimiteTotal() {
        return FechaLimiteTotal;
    }

    public void setFechaLimiteTotal(LocalDate fechaLimiteTotal) {
        this.FechaLimiteTotal = fechaLimiteTotal;
    }

    public double getMontoPagado() {
        return MontoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.MontoPagado = montoPagado;
    }

    public double getCostoFinal() {
        return costoFinal;
    }

    public void setCostoFinal(double costoFinal) {
        this.costoFinal = costoFinal;
    }

    public String getEditorial() {
        return Editorial;
    }

    public void setEditorial(String editorial) {
        this.Editorial = editorial;
    }
}
