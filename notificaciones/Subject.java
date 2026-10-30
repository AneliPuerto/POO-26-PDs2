package notificaciones;

public interface Subject {
    void registrarObservador(Observer o);
    void eliminarObservador(Observer o);
    void notificarObservadores();
}

