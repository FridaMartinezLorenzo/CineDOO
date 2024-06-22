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
import ProyectoCinePersistencia.entities.Categoria;
import ProyectoCinePersistencia.utils.MyBatisUtil;
import ProyectoCinePresentacion.presentacion.funcion.VentanaFuncionesPorPelicula;
import ProyectoCinePresentacion.presentacion.pelicula.VentanaListarPeliculas;
import ProyectoCinePresentacion.presentacion.pelicula.VentanaSeleccionarPelicula;
import ProyectoCinePresentacion.presentacion.venta.VentanaCrearVenta;

public class VentanaPrincipalCliente extends JFrame {

    public VentanaPrincipalCliente() {
        // Configuración del JFrame
        setTitle("Cliente - Cine");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Menú de Película
        JMenu menuPelicula = new JMenu("Película");
        menuPelicula.add(createMenuItem("Buscar Película"));
        menuPelicula.add(createMenuItem("Listar Películas"));

        //Menú de Función
        JMenu menuFuncion = new JMenu("Función");
        menuFuncion.add(createMenuItem("Listar Funciones por Película"));

        // Menú de Venta
        JMenu menuVenta = new JMenu("Venta");
        menuVenta.add(createMenuItem("Comprar Boletos"));

        // Agregar los menús a la barra de menú
        menuBar.add(menuPelicula);
        menuBar.add(menuFuncion);
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
                    case "Listar Películas":
                        VentanaListarPeliculas ventanaListarPeliculas = new VentanaListarPeliculas();
                        ventanaListarPeliculas.mostrar();
                        break;
                    case "Buscar Película":
                        VentanaSeleccionarPelicula ventanaSeleccionarPeliculaBuscar = new VentanaSeleccionarPelicula("buscar");
                        ventanaSeleccionarPeliculaBuscar.mostrar();
                        break;
                    case "Listar Funciones por Película":
                        VentanaFuncionesPorPelicula ventanaFuncionesPorPelicula = new VentanaFuncionesPorPelicula();
                        ventanaFuncionesPorPelicula.mostrar();
                        break;
                    case "Crear Venta":
                        VentanaCrearVenta ventanaCrearVenta = new VentanaCrearVenta();
                        ventanaCrearVenta.mostrar();
                        break;

                    default:
                        break;
                }
            }

        });
        return menuItem;
    }

    /*
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipalEmpleado().setVisible(true);
            }
        });
    }
     */
    public void mostrar() {
        setVisible(true);
    }
}
