package Controlador;

import Modelo.Biblioteca;
import Modelo.Juego;
import Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ControladorBiblioteca {

    private List<Biblioteca> bibliotecas = new ArrayList<>();
    private List<Usuario> usuarios;
    private List<Juego> juegos;

    // Constructor recibe listas de usuarios y juegos existentes
    public ControladorBiblioteca(List<Usuario> usuarios, List<Juego> juegos) {
        this.usuarios = usuarios;
        this.juegos = juegos;
    }
// VER BIBLIOTECA PERSONAL
    public void verBiblioteca(int idUsuario) {
        // Buscar usuario
        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getIdUsuario() == idUsuario) {
                usuario = u;
                break;
            }
        }

        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        // Mostrar juegos del usuario
        System.out.println("Biblioteca de " + usuario.getNombUsuario() + ":");
        boolean tieneJuegos = false;

        for (Biblioteca b : bibliotecas) {
            if (b.getUsuario().getIdUsuario() == idUsuario) {
                Juego j = b.getJuego();
                System.out.println("------------------------");
                System.out.println("Título: " + j.getTitulo());
                System.out.println("Horas jugadas: " + b.getHorasJugadas());
                System.out.println("Última sesión: " + b.getUltmFechJueg());
                System.out.println("Estado instalación: " + b.getEstadoInstalacion());
                tieneJuegos = true;
            }
        }

        if (!tieneJuegos) {
            System.out.println("No hay juegos en la biblioteca.");
        }
    }

// AÑADIR JUEGO A BIBLIOTECA
    public Object anhadirJuego(int idUsuario, int idJuego) {
        // buscar usuario
        Usuario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getIdUsuario() == idUsuario) {
                usuario = u;
                break;
            }
        }
        if (usuario == null) return "Error: Usuario no encontrado";

        // buscar juego
        Juego juego = null;
        for (Juego j : juegos) {
            if (j.getIdJuego() == idJuego) {
                juego = j;
                break;
            }
        }
        if (juego == null) return "Error: Juego no encontrado";
        return null;

    }


}