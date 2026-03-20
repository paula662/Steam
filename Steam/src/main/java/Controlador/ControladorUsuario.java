package Controlador;

import Modelo.EstadoCuenta;
import Modelo.Usuario;
import Modelo.Pais;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorUsuario {

    private List<Usuario> usuarios = new ArrayList<>();

    // 1.REGISDTRAR NUEVO USUARIO
    public Usuario registrarUsuario(String nombUsuario, String email, String contrasenha,
                                    String nombre, Pais pais, LocalDate fechaNacimiento) {

        // validacion nombre unico
        if (existeUsuarioConNombre(nombUsuario)) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        // validacion email
        if (existeUsuarioConEmail(email)) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        // crear nuevo usuario
        Usuario nuevo = new Usuario(nombUsuario, email, contrasenha, nombre, pais, fechaNacimiento);

        usuarios.add(nuevo);
        return nuevo;
    }
    // buscar usuario por nombre
    private Usuario buscarUsuarioPorNombre(String nombUsuario) {
        for (Usuario u : usuarios) {
            if (u.getNombUsuario().equalsIgnoreCase(nombUsuario)) {
                return u;
            }
        }
        return null;
    }

    // usuario creado
    public String registrarUsuarioConMensaje(String nombUsuario, String email, String contrasenha,
                                             String nombre, Pais pais, LocalDate fechaNacimiento) {
        try {
            Usuario nuevo = registrarUsuario(nombUsuario, email, contrasenha, nombre, pais, fechaNacimiento);
            return "Usuario creado exitosamente: " + nuevo.getNombUsuario();
        } catch (IllegalArgumentException e) {
            return "Error al crear usuario: " + e.getMessage();
        }
    }

    // usuario con mismo nombre
    private boolean existeUsuarioConNombre(String nombUsuario) {
        for (Usuario u : usuarios) {
            if (u.getNombUsuario().equalsIgnoreCase(nombUsuario)) {
                return true;
            }
        }
        return false;
    }

    // usuario con mismo email
    private boolean existeUsuarioConEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    // 2.CONSULTAR PERFIL
    public String consultarPerfil(String nombUsuario){
        // buscar usuario en lista
        Usuario usuario = buscarUsuarioPorNombre(nombUsuario);
        // si no existe
        if (usuario == null){
            return "Usuario no encontrado";
        }
        // informacion de usuario
        String perfil = "Nombre de usuario: " + usuario.getNombre() + "\n"
                + "Nombre: " + usuario.getIdUsuario() + "\n"
                + "Email: " + usuario.getEmail() + "\n"
                + "País: " + usuario.getPais() +  "\n"
                + "Fecha de registro: " + usuario.getFechaRegistro() + "\n";


        return perfil;
    }
    //  3.AÑADIR SALDO A CARTERA
    public String aNHadirSaldoCartera(String nombUsuario, double cantidad) {
        // buscar usuario
        Usuario usuario = null;
        if (usuario == null) {
            return "Usuario no encontrado";
        }

        // validar cuenta activa
        if (usuario.getEstadoCuenta() != EstadoCuenta.ACTIVA) {
            return "La cuenta no está activa";
        }

        // validar cantidad
        if (cantidad < 5.0 || cantidad > 500.0) {
            return "Cantidad fuera de rango (5.00 - 500.00)";
        }

        // añadir saldo
        double nuevoSaldo = usuario.getSaldoCartera() + cantidad;
        usuario.setSaldoCartera(nuevoSaldo);

        // devolver mensaje
        return "Nuevo saldo: " + nuevoSaldo + " €";
    }
    // 4. CONSULTAR SALDO
    public String consultarSaldo(String nombUsuario){
        // buscar usuario
        Usuario usuario = null;
        for(Usuario u : usuarios){
            if(u.getNombUsuario().equalsIgnoreCase(nombUsuario)){
                usuario = u;
                break;
            }
        }
        // si no existe
        if(usuario == null){
            return "Usuario no encontrado";
        }
        return "Saldo disponible: " + usuario.getSaldoCartera();
    }
}