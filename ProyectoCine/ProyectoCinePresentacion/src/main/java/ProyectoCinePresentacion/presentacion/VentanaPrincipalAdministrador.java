package ProyectoCinePresentacion.presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ProyectoCinePersistencia.dao.categoria.CategoriaDAOImpl;
import ProyectoCinePersistencia.entities.Categoria;
import ProyectoCinePersistencia.utils.MyBatisUtil;
import ProyectoCinePresentacion.presentacion.pelicula.VentanaCrearPelicula;
import ProyectoCinePresentacion.presentacion.pelicula.VentanaListarPeliculas;
import ProyectoCinePresentacion.presentacion.pelicula.VentanaSeleccionarPelicula;
import ProyectoCinePresentacion.presentacion.venta.VentanaCrearVenta; // Importa VentanaCrearVenta

public class VentanaPrincipalAdministrador extends JFrame {

    public VentanaPrincipalAdministrador() {
        // Configuración del JFrame
        setTitle("Administrador - Cine");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Menú de Película
        JMenu menuPelicula = new JMenu("Película");
        menuPelicula.add(createMenuItem("Crear Película"));
        menuPelicula.add(createMenuItem("Listar Películas"));
        menuPelicula.add(createMenuItem("Buscar Película"));
        menuPelicula.add(createMenuItem("Actualizar Película"));
        menuPelicula.add(createMenuItem("Eliminar Película"));

        // Menú de Ganancia
        JMenu menuGanancia = new JMenu("Ganancia");
        menuGanancia.add(createMenuItem("Ver Ganancias"));

        // Menú de Horario
        JMenu menuHorario = new JMenu("Horario");
        menuHorario.add(createMenuItem("Crear Horario"));
        menuHorario.add(createMenuItem("Listar Horarios"));
        menuHorario.add(createMenuItem("Actualizar Horario"));
        menuHorario.add(createMenuItem("Eliminar Horario"));

        // Menú de Promoción
        JMenu menuPromocion = new JMenu("Promoción");
        menuPromocion.add(createMenuItem("Crear Promoción"));
        menuPromocion.add(createMenuItem("Listar Promociones"));
        menuPromocion.add(createMenuItem("Actualizar Promoción"));
        menuPromocion.add(createMenuItem("Eliminar Promoción"));

        // Menú de Sala
        JMenu menuSala = new JMenu("Sala");
        menuSala.add(createMenuItem("Crear Sala"));
        menuSala.add(createMenuItem("Listar Salas"));
        menuSala.add(createMenuItem("Actualizar Sala"));
        menuSala.add(createMenuItem("Eliminar Sala"));

        // Menú de Usuario
        JMenu menuUsuario = new JMenu("Usuario");
        menuUsuario.add(createMenuItem("Crear Usuario"));
        menuUsuario.add(createMenuItem("Listar Usuarios"));
        menuUsuario.add(createMenuItem("Actualizar Usuario"));
        menuUsuario.add(createMenuItem("Eliminar Usuario"));

        // Menú de Venta
        JMenu menuVenta = new JMenu("Venta");
        menuVenta.add(createMenuItem("Crear Venta"));
        menuVenta.add(createMenuItem("Eliminar Venta"));

        // Agregar los menús a la barra de menú
        menuBar.add(menuPelicula);
        menuBar.add(menuGanancia);
        menuBar.add(menuHorario);
        menuBar.add(menuPromocion);
        menuBar.add(menuSala);
        menuBar.add(menuUsuario);
        menuBar.add(menuVenta);

        // Establecer la barra de menú en el JFrame
        setJMenuBar(menuBar);
    }

    private JMenuItem createMenuItem(String name) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción a realizar cuando se selecciona el ítem del menú
                // JOptionPane.showMessageDialog(VentanaPrincipalAdministrador.this, "Seleccionaste: " + name);

                // Hacemos el precargado de los datos que podrían ser necesarios
                CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl(MyBatisUtil.getSqlSessionFactory());
                List<Categoria> categorias = categoriaDAO.Listar();
                List<String> nombresCategorias = categorias.stream()
                        .map(Categoria::getNombre)
                        .collect(Collectors.toList());

                switch (name) {
                    case "Crear Película":
                        VentanaCrearPelicula ventanaCrearPelicula = new VentanaCrearPelicula(nombresCategorias);
                        ventanaCrearPelicula.mostrar();
                        break;
                    case "Listar Películas":
                        VentanaListarPeliculas ventanaListarPeliculas = new VentanaListarPeliculas();
                        ventanaListarPeliculas.mostrar();
                        break;
                    case "Actualizar Película":
                        VentanaSeleccionarPelicula ventanaSeleccionarPeliculaEditar = new VentanaSeleccionarPelicula("editar");
                        ventanaSeleccionarPeliculaEditar.mostrar();
                        break;
                    case "Buscar Película":
                        VentanaSeleccionarPelicula ventanaSeleccionarPeliculaBuscar = new VentanaSeleccionarPelicula("buscar");
                        ventanaSeleccionarPeliculaBuscar.mostrar();
                        break;
                    case "Eliminar Película":
                        // Eliminar Película
                        break;
                    case "Ver Ganancias":
                        // Ver Ganancias
                        break;
                    case "Crear Horario":
                        // Crear Horario
                        break;
                    case "Listar Horarios":
                        // Listar Horarios
                        break;
                    case "Actualizar Horario":
                        // Actualizar Horario
                        break;
                    case "Eliminar Horario":
                        // Eliminar Horario
                        break;
                    case "Crear Promoción":
                        // Crear Promoción
                        break;
                    case "Listar Promociones":
                        // Listar Promociones
                        break;
                    case "Actualizar Promoción":
                        // Actualizar Promoción
                        break;
                    case "Eliminar Promoción":
                        // Eliminar Promoción
                        break;
                    case "Crear Sala":
                        // Crear Sala
                        break;
                    case "Listar Salas":
                        // Listar Salas
                        break;
                    case "Actualizar Sala":
                        // Actualizar Sala
                        break;
                    case "Eliminar Sala":
                        // Eliminar Sala
                        break;
                    case "Crear Usuario":
                        // Crear Usuario
                        break;
                    case "Listar Usuarios":
                        // Listar Usuarios
                        break;
                    case "Actualizar Usuario":
                        // Actualizar Usuario
                        break;
                    case "Eliminar Usuario":
                        // Eliminar Usuario
                        break;
                    case "Crear Venta":
                        VentanaCrearVenta ventanaCrearVenta = new VentanaCrearVenta();
                        ventanaCrearVenta.mostrar();
                        break;
                    case "Eliminar Venta":
                        // Eliminar Venta
                        break;
                    default:
                        break;
                }
            }
        });
        return menuItem;
    }

    public void mostrar() {
        setVisible(true);
    }

    /*

     public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipalAdministrador().setVisible(true);
            }
        });
    }
     */
}
