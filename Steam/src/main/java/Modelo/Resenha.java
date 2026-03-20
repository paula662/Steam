package Modelo;

import java.time.LocalDate;

public class Resenha {

    // RELACIONES
    // USUARIO <--> RESEÑA
    private Usuario usuario;
    // JUEGO <--> RESEÑA
    private Juego juego;

    // ATRIBUTOS
    private int idUnico;
    private boolean recomendado;
    private String textoResenha;
    private double horasJugadas;
    private LocalDate fechaPublicacion;
    private LocalDate fechaEdicion;
    private EstadoResenha estadoResenha;




    // CONSTRUCTOR
    public Resenha(int idUnico, Usuario usuario, Juego juego, boolean recomendado, int horasResenha, String textoResenha, EstadoResenha estadoResenha) {
        this.idUnico = idUnico;
        this.usuario = usuario;
        this.juego = juego;
        this.recomendado = recomendado;
        this.textoResenha = textoResenha;
        this.horasJugadas = 0.0;
        this.fechaPublicacion = LocalDate.now();
        this.fechaEdicion = null;
        this.estadoResenha = EstadoResenha.PUBLICADA ;
    }


    // GETTERS Y SETTERS



    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        // OBLIGATORIO
        if (usuario == null){
            throw new IllegalArgumentException("usuario obligatorio");
        }
        this.usuario = usuario;
    }

    public int getIdUnico() {
        return idUnico;
    }

    public void setIdUnico(int idUnico) {
        this.idUnico = idUnico;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        // OBLIGATORIO
        if (juego == null){
            throw new IllegalArgumentException("Obligatorio");
        }
        this.juego = juego;
    }

    public boolean isRecomendado() {
        return recomendado;
    }

    public void setRecomendado(boolean recomendado) {
        this.recomendado = recomendado;
    }

    public String getTextoResenha(){
        return textoResenha;
    }
    public String setTextoresenha(){
        // OBLIGATORIO
        if (textoResenha == null || textoResenha.isBlank())
            throw new IllegalArgumentException("Texto obligatortio");

        // LONGITUD ENTRE 50 Y 8000 CARACTERES
        if(textoResenha.length() < 50 || textoResenha.length() > 800){
            throw new IllegalArgumentException("El texto debe tener entre 50 y 8000 caracteres");
        }
        return textoResenha;
    }
    public double getHorasJugadas() {
        return horasJugadas;
    }

    public void setHorasJugadas(double horasJugadas) {
        // POSITIVO O CERO
        if (horasJugadas < 0){
            throw new IllegalArgumentException("Las horas no pueden ser negativas");
        }

        // MAXIMO 1 DECIMAL

        this.horasJugadas = horasJugadas;
    }


    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public LocalDate getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(LocalDate fechaEdicion) {
        if (fechaEdicion != null && fechaEdicion.isBefore(fechaPublicacion)){
            throw new IllegalArgumentException("La fecha de edición no debe ser posterior a la publicación");
        }
        this.fechaEdicion = fechaEdicion;
    }

    public EstadoResenha getEstadoResenha() {
        return estadoResenha;
    }

    public void setEstadoResenha(EstadoResenha estadoResenha) {
        this.estadoResenha = estadoResenha;
    }

}
