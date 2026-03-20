package Modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Usuario {

    // RELACIONES
    // BIBLIOTECA <--> USUARIO
    private Biblioteca biblioteca;
    // USUARIO <--> COMPRA
    private List<Compra> compras;
    // USUARIO <--> RESEÑA
    private List<Resenha> resenhas;

    // ATRIBUTOS
    private int idUsuario;
    private String nombUsuario;
    private String email;
    private String contrasenha;
    private String nombre;
    private Pais pais;
    private LocalDate fechaNacimiento;
    private LocalDate fechaRegistro;
    private String avatar;
    private double saldoCartera;
    private EstadoCuenta estadoCuenta;
    private int descuentoActual;



    // CONSTRUCTOR

    public Usuario(int idUsuario, String nombUsuario, String email, String contrasenha, String nombre, Pais pais, LocalDate fechaNacimiento, LocalDate fechaRegistro, String avatar, double saldoCartera, EstadoCuenta estadoCuenta) {
        this.idUsuario = idUsuario;
        this.nombUsuario = nombUsuario;
        this.email = email;
        this.contrasenha = contrasenha;
        this.nombre = nombre;
        this.pais = pais;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaRegistro = LocalDate.now();
        this.avatar = avatar;
        this.saldoCartera = 0.0;
        this.estadoCuenta = EstadoCuenta.ACTIVA;
        this.descuentoActual = 0;
    }
    // CONSTRUCTOR CONTROLADOR
    public Usuario(String nombUsuario, String email, String contrasenha,
                   String nombre, Pais pais, LocalDate fechaNacimiento) {

        setNombUsuario(nombUsuario);
        setEmail(email);
        setContrasenha(contrasenha);
        setNombre(nombre);
        setPais(pais);
        setFechaNacimiento(fechaNacimiento);

        this.fechaRegistro = LocalDate.now();
        this.saldoCartera = 0.0;
        this.estadoCuenta = EstadoCuenta.ACTIVA;
        this.descuentoActual = 0;
    }



    // GETTERS Y SETTERS

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {

        this.idUsuario = idUsuario;
    }

    public String getNombUsuario() {
        return nombUsuario;
    }

    public void setNombUsuario(String nombUsuario) {

        // OBLIGATORIO
        if (nombUsuario == null || nombUsuario.isBlank()){
            throw new IllegalArgumentException("Nombre obligatorio");
        }
        // Longitud entre 3 y 20.
        if (nombUsuario.length() < 3 || nombUsuario.length() > 20){
            throw new IllegalArgumentException("El nombre de usuario debe tener entre 3 y 20 caracteres");
        }

        // Solo alfanuméricos, guiones y guiones bajos
        if (!nombUsuario.matches("[a-zA-Z0-9_-]+")) {
            throw new IllegalArgumentException("Solo se permiten letras, números, guiones y guiones bajos");
        }

        // No puede empezar por número
        if (Character.isDigit(nombUsuario.charAt(0))) {
            throw new IllegalArgumentException("El nombre de usuario no puede empezar por un número");
        }
        this.nombUsuario = nombUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {

        //OBLIGATORIO
        if (email == null || email.isBlank()){
            throw new IllegalArgumentException("Email necesario");
        }

        // FORMATO VÁLIDO
        if (!email.matches("^[^@]+@[^@]+\\.[^@]+$")) {
            throw new IllegalArgumentException("Formato de email inválido");
        }

        this.email = email;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {

        // OBLIGATORIO
        if (contrasenha == null || contrasenha.isBlank()){
            throw new IllegalArgumentException("Campo obligatorio");
        }

        // MINIMO 8 CARACTERES
        if (contrasenha.length() < 8){
            throw new  IllegalArgumentException("Debe tener como mínimo 8 caracteres.");
        }

        // MAYÚSCULA, MMINÚSCULA, NÚMERO
        if (!contrasenha.matches(".*[A-Z].*"))
            throw new IllegalArgumentException("Debe contener al menos una mayúscula");

        if (!contrasenha.matches(".*[a-z].*"))
            throw new IllegalArgumentException("Debe contener al menos una minúscula");

        if (!contrasenha.matches(".*[0-9].*"))
            throw new IllegalArgumentException("Debe contener al menos un número");

        this.contrasenha = contrasenha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        // OBLIGATORIO
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("Campo obligatorio");

        // LONGITUD 2-50
        if (nombre.length() < 2 || nombre.length() > 50){
            throw new IllegalArgumentException("Debe tener nínimo 2 caracteres y máximo 50.");
        }
        this.nombre = nombre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {

        // OBLIGATORIO
        if (pais == null){
            throw new IllegalArgumentException("Campo obligatorio");

        // SOLO PAISES ENUM
       // if(pais != pais)
         //   throw new IllegalArgumentException("El país no es válido");
        }
        this.pais = pais;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {

        // Obligatorio
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("Campo obligatorio");
        }

        // FECHA FUTURA
        if (fechaNacimiento.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("No puede ser una fecha futura");
            }
        // MAYOR DE 13
        if (Period.between(fechaNacimiento, LocalDate.now()).getYears() < 13) {
                throw new IllegalArgumentException("Debe tener al menos 13 años");
            }

        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {

        // OPCIONAL -> SOLO VALIDA SI ES NULL.
        if (avatar != null && avatar.length() > 100){
            throw new IllegalArgumentException("El avatar no puede tener mas de 100 caracteres");
        }

        // NO PUEDE ESTAR VACÍO
        if (avatar != null && avatar.isBlank()){
            throw new IllegalArgumentException("No puede estar vacío");
        }
        this.avatar = avatar;
    }

    public double getSaldoCartera() {
        return saldoCartera;
    }

    public void setSaldoCartera(double saldoCartera) {

        // POSITIVO O CERO
        if(saldoCartera < 0){
            throw new IllegalArgumentException("El salfo no puede ser negativo");
        }

        // MÁXIMO 2 DECIMALES
        if (BigDecimal.valueOf(saldoCartera).scale() > 2) {
            throw new IllegalArgumentException("Máximo 2 decimales");
        }


        this.saldoCartera = saldoCartera;
    }

    public EstadoCuenta getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(EstadoCuenta estadoCuenta) {

        // NO PUEDE SER NULO
        if (estadoCuenta == null){
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }

        this.estadoCuenta = estadoCuenta;
    }
    public void setDescuentoActual(int descuentoActual) {
        if (descuentoActual < 0 || descuentoActual > 100) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 100");
        }
        this.descuentoActual = descuentoActual;
    }

}
