package ProyectoCinePresentacion.presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ProyectoCinePersistencia.dao.categoria.CategoriaDAOImpl;
import ProyectoCinePersistencia.dao.rol.RolDAOImpl;
import ProyectoCinePersistencia.entities.Categoria;
import ProyectoCinePersistencia.entities.Rol;
import ProyectoCinePersistencia.utils.MyBatisUtil;
import ProyectoCinePresentacion.controllers.VentaController;
import ProyectoCinePresentacion.presentacion.ganancias.VentanaObtenerGanancia;
import ProyectoCinePresentacion.presentacion.horario.VentanaActualizarHorario;
import ProyectoCinePresentacion.presentacion.horario.VentanaBuscarHorario;
import ProyectoCinePresentacion.presentacion.horario.VentanaCrearHorario;
import ProyectoCinePresentacion.presentacion.horario.VentanaEliminarHorario;
import ProyectoCinePresentacion.presentacion.horario.VentanaListarHorario;
import ProyectoCinePresentacion.presentacion.pelicula.VentanaCrearPelicula;
import ProyectoCinePresentacion.presentacion.pelicula.VentanaEliminarPelicula;
import ProyectoCinePresentacion.presentacion.pelicula.VentanaListarPeliculas;
import ProyectoCinePresentacion.presentacion.pelicula.VentanaSeleccionarPelicula;
import ProyectoCinePresentacion.presentacion.sala.VentanaCrearSala;
import ProyectoCinePresentacion.presentacion.sala.VentanaEliminarSala;
import ProyectoCinePresentacion.presentacion.sala.VentanaListarSalas;
import ProyectoCinePresentacion.presentacion.sala.VentanaSeleccionarSala;
import ProyectoCinePresentacion.presentacion.usuario.VentanaCrearUsuario;
import ProyectoCinePresentacion.presentacion.usuario.VentanaEliminarUsuario;
import ProyectoCinePresentacion.presentacion.usuario.VentanaListarUsuario;
import ProyectoCinePresentacion.presentacion.usuario.VentanaSeleccionarUsuario;
import ProyectoCinePresentacion.presentacion.venta.VentanaCrearVenta;
import ProyectoCinePresentacion.presentacion.venta.VentanaListarVentas;

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

        // Menú de Sala
        JMenu menuSala = new JMenu("Sala");
        menuSala.add(createMenuItem("Crear Sala"));
        menuSala.add(createMenuItem("Listar Salas"));
        menuSala.add(createMenuItem("Buscar Sala"));
        menuSala.add(createMenuItem("Actualizar Sala"));
        menuSala.add(createMenuItem("Eliminar Sala"));

        // Menú de Usuario
        JMenu menuUsuario = new JMenu("Usuario");
        menuUsuario.add(createMenuItem("Crear Usuario"));
        menuUsuario.add(createMenuItem("Listar Usuarios"));
        menuUsuario.add(createMenuItem("Buscar Usuario"));
        menuUsuario.add(createMenuItem("Actualizar Usuario"));
        menuUsuario.add(createMenuItem("Eliminar Usuario"));

        // Menú de Venta
        JMenu menuVenta = new JMenu("Venta");
        menuVenta.add(createMenuItem("Crear Venta"));
        menuVenta.add(createMenuItem("Listar Ventas"));

        // Agregar los menús a la barra de menú
        menuBar.add(menuPelicula);
        menuBar.add(menuGanancia);
        menuBar.add(menuHorario);
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

                RolDAOImpl rolDAO = new RolDAOImpl(MyBatisUtil.getSqlSessionFactory());
                List<Rol> roles = rolDAO.Listar();
                List<String> nombresRoles = roles.stream()
                        .map(Rol::getNombre)
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
                        VentanaEliminarPelicula ventanaEliminarPelicula = new VentanaEliminarPelicula();
                        ventanaEliminarPelicula.mostrar();
                        break;
                    case "Ver Ganancias":
                        VentanaObtenerGanancia ventanaObtenerGanancia = new VentanaObtenerGanancia();
                        ventanaObtenerGanancia.mostrar();
                        // Ver Ganancias
                        break;
                    case "Crear Horario":
                        VentanaCrearHorario ventanaCrearHorario = new VentanaCrearHorario();
                        ventanaCrearHorario.mostrar();
                        // Crear Horario
                        break;
                    case "Listar Horarios":
                        VentanaListarHorario ventanaListarHorario = new VentanaListarHorario();
                        ventanaListarHorario.mostrar();
                        // Listar Horarios
                        break;
                    case "Actualizar Horario":
                        VentanaActualizarHorario ventanaActualizarHorario = new VentanaActualizarHorario();
                        ventanaActualizarHorario.mostrar();
                        // Actualizar Horario
                        break;
                    case "Buscar Horario":
                        VentanaBuscarHorario ventanaBuscarHorario = new VentanaBuscarHorario();
                        ventanaBuscarHorario.mostrar();
                        break;
                    case "Eliminar Horario":
                        VentanaEliminarHorario ventanaEliminarHorario = new VentanaEliminarHorario();
                        ventanaEliminarHorario.mostrar();
                        // Eliminar Horario
                        break;
                    case "Crear Sala":
                        VentanaCrearSala ventanaCrearSala = new VentanaCrearSala();
                        ventanaCrearSala.mostrar();
                        // Crear Sala
                        break;
                    case "Listar Salas":
                        VentanaListarSalas ventanaListarSalas = new VentanaListarSalas();
                        ventanaListarSalas.mostrar();
                        // Listar Salas
                        break;
                    case "Buscar Sala":
                        VentanaSeleccionarSala ventanaSeleccionarSalaBuscar = new VentanaSeleccionarSala("buscar");
                        ventanaSeleccionarSalaBuscar.mostrar();
                        // Buscar Sala
                        break;
                    case "Actualizar Sala":
                        VentanaSeleccionarSala ventanaSeleccionarSalaActualizar = new VentanaSeleccionarSala("editar");
                        ventanaSeleccionarSalaActualizar.mostrar();
                        // Actualizar Sala
                        break;
                    case "Eliminar Sala":
                        VentanaEliminarSala ventanaEliminarSala = new VentanaEliminarSala();
                        ventanaEliminarSala.mostrar();
                        // Eliminar Sala
                        break;
                    case "Crear Usuario":
                        VentanaCrearUsuario ventanaCrearUsuario = new VentanaCrearUsuario(nombresRoles);
                        ventanaCrearUsuario.mostrar();
                        // Crear Usuario
                        break;
                    case "Listar Usuarios":
                        VentanaListarUsuario ventanaListarUsuario = new VentanaListarUsuario();
                        ventanaListarUsuario.mostrar();
                        // Listar Usuarios
                        break;
                    case "Buscar Usuario":
                        VentanaSeleccionarUsuario ventanaSeleccionarUsuarioBuscar = new VentanaSeleccionarUsuario("buscar");
                        ventanaSeleccionarUsuarioBuscar.mostrar();
                        // Buscar Usuario
                        break;
                    case "Actualizar Usuario":
                        VentanaSeleccionarUsuario ventanaSeleccionarUsuarioActualizar = new VentanaSeleccionarUsuario(
                                "editar");
                        ventanaSeleccionarUsuarioActualizar.mostrar();
                        // Actualizar Usuario
                        break;
                    case "Eliminar Usuario":
                        VentanaEliminarUsuario ventanaEliminarUsuario = new VentanaEliminarUsuario();
                        ventanaEliminarUsuario.mostrar();
                        // Eliminar Usuario
                        break;
                    case "Crear Venta":
                        VentanaCrearVenta ventanaCrearVenta = new VentanaCrearVenta();
                        ventanaCrearVenta.mostrar();
                        break;
                    case "Listar Ventas":
                        VentaController ventaController = new VentaController();
                        List<Object[]> ventas = ventaController.listarVentas();
                        VentanaListarVentas ventanaListarVenta = new VentanaListarVentas(ventas);
                        ventanaListarVenta.mostrar();
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
