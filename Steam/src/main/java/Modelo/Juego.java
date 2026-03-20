package Modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Juego {
    // RELACIONES
    // JUEGO <--> BIBLIOTECA
    private List<Biblioteca> biblioteca;
    // JUEGO <--> COMPRA
    private List<Compra> compras;
    //JUEGO <--> RESENHA
    private List<Resenha> resenhas;

    // ATRIBUTOS
    private int idJuego;
    private String titulo;
    private String descripcion;
    private String desarrollador;
    private LocalDate fechaLanzamiento;
    private double precioBase;
    private int descAct;
    private String categoria;
    private ClasificacionEdad clasificacionEdad;
    private List<String> idiomas;
    private EstadoJuego estado;


    // CONSTRUCTOR

    public Juego(String titulo, String descripcion, String desarrollador, LocalDate fechaLanzamiento, double precioBase, String categoria, ClasificacionEdad clasificacionEdad, EstadoJuego estado, List<String> idiomas) {
        this.idJuego = idJuego;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.desarrollador = desarrollador;
        this.fechaLanzamiento = fechaLanzamiento;
        this.precioBase = precioBase;
        this.descAct = 0;
        this.categoria = categoria;
        this.clasificacionEdad = clasificacionEdad;
        this.estado = EstadoJuego.DISPONIBLE;
        this.idiomas = idiomas;
    }

    // GETTERS Y SETTERS

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {

        // OBLIGATORIO
        if (titulo == null || titulo.isBlank()){
            throw new IllegalArgumentException("Titulo obligatorio.");
        }

        // LONGITUD ENTER 1 Y 100
        if (titulo.length() < 1 || titulo.length() > 100){
            throw new IllegalArgumentException("Debe tener entre 1 y 100 caracteres.");
        }
        this.titulo = titulo;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {

        // LONGITUS 2000 CARACTERES
        if (descripcion != null && descripcion.length() > 2000){
            throw new IllegalArgumentException("La descripcion no puede tener mas de 2000 caracteres");
        }
        this.descripcion = descripcion;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {

        // OBLIGATORIO
        if (desarrollador == null || desarrollador.isBlank()){
            throw new IllegalArgumentException("Obligatorio");
        }
        // LONGITUD ENTE 2 Y 100
        if (desarrollador.length() < 2 || desarrollador.length() > 100){
            throw new IllegalArgumentException("Debe tener entre 2 y 100 caracteres");
        }
        this.desarrollador = desarrollador;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {

        // OBLIGATORIO
        if (fechaLanzamiento == null){
            throw new IllegalArgumentException("Fecha obligatoria");
        }
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {

        // RANGO ENTE 0.00 Y 999.99
        if (precioBase < 0.00 || precioBase > 999.99){
            throw new IllegalArgumentException("Precio entre 0 y 999.99");
        }

        // MÁXIMO 2 DECIMALES
        if (BigDecimal.valueOf(precioBase).scale() > 2){
            throw new IllegalArgumentException("Máximo 2 decimales");
        }
        this.precioBase = precioBase;
    }

    public int getDescAct() {
        return descAct;
    }

    public void setDescAct(int descAct) {
        if (descAct < 0 || descAct > 100){
            throw new IllegalArgumentException("El descuento debe ser entre 0 y 100");
        }
        this.descAct = descAct;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ClasificacionEdad getClasificacionEdad() {
        return clasificacionEdad;
    }

    public void setClasificacionEdad(ClasificacionEdad clasificacionEdad) {

        // OBLIGATORIA
        if (clasificacionEdad == null){
            throw new IllegalArgumentException("Clasificacion edad obligatoria.");
        }
        this.clasificacionEdad = clasificacionEdad;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        // AL MENOS UN IDIOMA
        if (idiomas != null && idiomas.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un idioma");
        }

        // LONGITUD MÁXIMA 200 CARACT.
        if (idiomas.size() > 200){

        }

        this.idiomas = idiomas;
    }

    public EstadoJuego getEstado() {

        return estado;
    }

    public void setEstado(EstadoJuego estado) {
        this.estado = estado;
    }

}
