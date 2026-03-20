package Modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Compra {

    // RELACIONES
    private Usuario usuario;
    private Juego juego;

    //ATRIBUTOS
    private int idCompra;
    private LocalDate fechaCompra;
    private MetodoPago metodoPago;
    private double precioSinDescuento;
    private double descuentoAplicado;
    private EstadoCompra estadoCompra;

    // CONSTRUCTOR
    public Compra(int idCompra, Usuario usuario, Juego juego, LocalDate fechaCompra, MetodoPago metodoPago, double precioSinDescuento, double descuentoAplicado, EstadoCompra estadoCompra) {

        this.idCompra = idCompra;
        this.usuario = usuario;
        this.juego = juego;
        this.fechaCompra = LocalDate.now();
        this.metodoPago = MetodoPago.PAYPAL;
        this.precioSinDescuento = precioSinDescuento;
        this.descuentoAplicado = 0;
        this.estadoCompra = EstadoCompra.PENDIENTE;
    }

    // GETTERS Y SETTERS

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        // OBLIGATORIO
        if (usuario == null){
            throw new IllegalArgumentException("Usuario obligatorio");
        }

        // CUENTA ACTIVA
        if (usuario.getEstadoCuenta() != EstadoCuenta.ACTIVA) {
            throw new IllegalArgumentException("La cuenta no está activa");
        }
        this.usuario = usuario;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        // OBLIGATORIO
        if (juego == null){
            throw new IllegalArgumentException("Juego obligatorio");
        }

        // ESTADO JUEGO
        if (juego.getEstado() != EstadoJuego.DISPONIBLE && juego.getEstado() != EstadoJuego.PREVENTA && juego.getEstado() != EstadoJuego.ACCESO_ANTICIPADO) {
            throw new IllegalArgumentException("El juego no se puede comprar en su estado actual");
        }
        this.juego = juego;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        // OBLIGATORIO
        if (metodoPago == null){
            throw new IllegalArgumentException("Método de pago obligatorio");
        }
        this.metodoPago = metodoPago;
    }

    public double getPrecioSinDescuento() {
        return precioSinDescuento;
    }

    public void setPrecioSinDescuento(double precioSinDescuento) {

        // NO PUEDE SER NEGATIVO
        if (precioSinDescuento < 0){
            throw new IllegalArgumentException("No puede ser negativo");
        }
        // MAXIMO 2 DECIMALES
        if (BigDecimal.valueOf(precioSinDescuento).scale() > 2){
            throw new IllegalArgumentException("Máximo 2 decimales");
        }
        this.precioSinDescuento = precioSinDescuento;
    }

    public double getDescuentoAplicado() {
        return descuentoAplicado;
    }

    public void setDescuentoAplicado(double descuentoAplicado) {
        // rango 0 a 100
        if (descuentoAplicado < 0 || descuentoAplicado > 100){
            throw new IllegalArgumentException("Descuento entre 0 y 100");
        }
        this.descuentoAplicado = descuentoAplicado;
    }

    public EstadoCompra getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(EstadoCompra estadoCompra) {

        this.estadoCompra = estadoCompra;
    }
}