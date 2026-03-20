package Controlador;

import Modelo.ClasificacionEdad;
import Modelo.EstadoJuego;
import Modelo.Juego;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControladorJuego {

    // LISTA DE TODOS LOS JUEGOS
    private List<Juego> juegos = new ArrayList<>();

    // AÑADIR JUEGO
    public String anhadirJuego(String titulo, String descripcion, String desarrollador, LocalDate fechaLanzamiento, double precioBase, String categoria, ClasificacionEdad clasificacionEdad, EstadoJuego estado, List<String> idiomas) {

        try {
            // crear juego con los datos
            Juego j = new Juego(titulo, descripcion, desarrollador, fechaLanzamiento, precioBase, categoria, clasificacionEdad, estado, idiomas);
            // guardar juego en lista
            juegos.add(j);

            return "Juego creado exitosamente: " + j.getIdJuego();

        } catch (IllegalArgumentException e) {
            return "Error al crear juego: " + e.getMessage();
        }
    }
    // 2.BUSCAR JUeGOS
    public List<Juego> buscarJuegos(String texto){
        List<Juego> resultados = new ArrayList<>();
        for (Juego j : juegos){
            // comprobar si el juego ocincide
            if (j.getTitulo().toLowerCase().contains(texto.toLowerCase())){
                resultados.add(j);

            }
        }
        return resultados;
    }
    // datos mostrados
    public String mostrarDatos(String texto){
        List<Juego> encontrados = buscarJuegos(texto);

        if (encontrados.isEmpty()){
            return "No se encontraron juegos que coincidan con: " + texto;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Juegos encontrados : ");

        for (Juego j: encontrados){
            sb.append("ID: ").append(j.getIdJuego())
                    .append("Título: ").append(j.getTitulo())
                    .append("Desarrollador: ").append(j.getDesarrollador())
                    .append("Precio").append(String.format("%.2f", j.getPrecioBase()));
        }
        return sb.toString();
    }
    // 3. CONSULTAR CATALOGO
    public String consultarCatalogo(String orden) {
        List<Juego> copiaJuegos = new ArrayList<>(juegos);

        // menu opciones opcional
        if (orden != null) {
            switch (orden.toLowerCase()) {
                case "alfabetico":
                    copiaJuegos.sort((j1, j2) -> j1.getTitulo().compareToIgnoreCase(j2.getTitulo()));
                    break;
                case "precio":
                    copiaJuegos.sort((j1, j2) -> Double.compare(j1.getPrecioBase(), j2.getPrecioBase()));
                    break;
                case "fecha":
                    copiaJuegos.sort((j1, j2) -> j1.getFechaLanzamiento().compareTo(j2.getFechaLanzamiento()));
                    break;
                default:
                    break;
            }
        }

        // texto salida
        if (copiaJuegos.isEmpty()) {
            return "No hay juegos disponibles en el catálogo.";
        }
        for (Juego j : juegos) {
            System.out.println("----------------------------");
            System.out.println("Título: " + j.getTitulo());
            System.out.println("Desarrollador: " + j.getDesarrollador());
            System.out.println("Precio base: " + j.getPrecioBase() + " €");
            System.out.println("Descuento actual: " + j.getDescAct() + "%");
            System.out.println("Clasificación por edad: " + j.getClasificacionEdad());
        }
        System.out.println("----------------------------");
        System.out.println("Total de juegos en catálogo: " + juegos.size());
        return orden;
    }

    // 5. APLICAR DESCUENTO
    public String aplicarDescuento(int idJuego, int descuento) {

        Juego juego = null;
        for (Juego j : juegos) {
            if (j.getIdJuego() == idJuego) {
                juego = j;
                break;
            }
        }

        //el juego existe
        if (juego == null) {
            return "Error: Juego no encontrado";
        }

        // descuento
        if (descuento < 0 || descuento > 100) {
            return "Error: El descuento debe estar entre 0 y 100";
        }

        // actualizar descuento
        juego.setDescAct(descuento);

        // precio final
        double precioFinal = juego.getPrecioBase() * (1 - descuento / 100.0);
        return "Descuento aplicado correctamente. Precio final: " + String.format("%.2f", precioFinal) + " €";
    }

    // CAMBIAR ESTADO JUEGO
    public String cambiarEstadoJuego(int idJuego, EstadoJuego nuevoEstado) {
        Juego juego = null;
        for (Juego j : juegos) {
            if (j.getIdJuego() == idJuego) {
                juego = j;
                break;
            }
        }
        if (juego == null) {
            return "Error: Juego no encontrado";
        }

        if (nuevoEstado == null) {
            return "Error: Estado inválido";
        }

        juego.setEstado(nuevoEstado);

        return "Estado del juego '" + juego.getTitulo() + "' actualizado a: " + juego.getEstado();
    }


}