package Modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Biblioteca {
    // RELACIONES
    // USUARIO <--> BIBLIOTECA
    private Usuario usuarioBib;
    // JUEGO <--> BIBLIOTECA
    private Juego juegos;
    // USUARIO <---> JUEGO ( MUCHOS A MUCHOS)
    private List<Juego> juego = new ArrayList<>();

    // ATRIBUTOS
    private String idBiblio;
    private LocalDate fechaAd;
    private double horasJugadas;
    private LocalDate ultmFechJueg;
    private EstadoInstalacion estadoInstalacion;


    // CONSTRUCTOR

    public Biblioteca(Juego juego, Usuario usuario) {
        this.idBiblio = idBiblio;
        this.juego = Collections.singletonList(juego);//camb array
        this.usuarioBib = usuario;
        this.fechaAd = fechaAd;
        this.horasJugadas = 0.0;
        this.ultmFechJueg = ultmFechJueg;
        this.estadoInstalacion = EstadoInstalacion.NO_INSTALADO;
    }



    // GETTERS Y SETTERS

    public Juego getJuego() {
        return juegos;
    }

    public void setJuego(Juego juego) {
        // OBLIGATORIO
        if (juego == null){
            throw new IllegalArgumentException("Juego obligatorio");
        }
        this.juego = (List<Juego>) juego; //??
    }

    public String getIdBiblio() {
        return idBiblio;
    }

    public void setIdBiblio(String idBiblio) {
        this.idBiblio = idBiblio;
    }

    public Usuario getUsuario() {
        return usuarioBib;
    }

    public void setUsuario(Usuario usuario) {
        // OBLIGATORIO
        if (usuario == null){
            throw new IllegalArgumentException("Ususario obligatorio");
        }
        this.usuarioBib = usuario;
    }

    public LocalDate getFechaAd() {
        return fechaAd;
    }

    public void setFechaAd(LocalDate fechaAd) {

        // OBLIGATORIO
        if (fechaAd == null){
            throw new IllegalArgumentException("Campo obligatorio");
        }

        // NO PUEDE SER FUTURA
        if (fechaAd.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha no puede ser futura");
        }

        // ANTERIOR A FECHA REGISTRO
        if (fechaAd.isBefore(usuarioBib.getFechaRegistro())){
            throw new IllegalArgumentException("La fecha no puede ser anterior a la de registro");
        }
        this.fechaAd = fechaAd;
    }

    public double getHorasJugadas() {
        return horasJugadas;
    }

    public void setHorasJugadas(double horasJugadas) {
        // POSITIVO O CERO
        if (horasJugadas < 0){
            throw new IllegalArgumentException("No puede ser negativo");
        }

        // MÁXIMO UN DECIMAL
        if (BigDecimal.valueOf(horasJugadas).scale() > 1){
            throw new IllegalArgumentException("Máximo un decimal");
        }
        this.horasJugadas = horasJugadas;
    }

    public LocalDate getUltmFechJueg() {
        return ultmFechJueg;
    }

    public void setUltmFechJueg(LocalDate ultmFechJueg) {
        // NO PUEDE SER FECHA FUTURA
        if (ultmFechJueg != null){
            if (ultmFechJueg.isAfter(LocalDate.now())){
                throw new IllegalArgumentException("La fecha no puede ser futura");
            }

            // NO PUEDE SER ANTERIOR A LA FECHA DE ADQUISICION
            if (fechaAd != null && ultmFechJueg.isBefore(fechaAd)){
                throw new IllegalArgumentException("No puede ser anterior a la fecha de adquisición");
            }
        }
        this.ultmFechJueg = ultmFechJueg;
    }

    public EstadoInstalacion getEstadoInstalacion() {
        return estadoInstalacion;
    }

    public void setEstadoInstalacion(EstadoInstalacion estadoInstalacion) {
        // ESTADO OBLIGATORIO
        if (estadoInstalacion == null) {
            throw new IllegalArgumentException("Estado obligatorio");
        }
        this.estadoInstalacion = estadoInstalacion;
    }

}
