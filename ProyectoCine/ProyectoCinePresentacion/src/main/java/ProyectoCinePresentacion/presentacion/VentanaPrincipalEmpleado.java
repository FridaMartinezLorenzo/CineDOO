package ProyectoCinePresentacion.presentacion;

import javax.swing.*;

import ProyectoCinePersistencia.dao.categoria.CategoriaDAOImpl;
import ProyectoCinePersistencia.entities.Categoria;
import ProyectoCinePersistencia.utils.MyBatisUtil;
import ProyectoCinePresentacion.presentacion.pelicula.VentanaCrearPelicula;
import ProyectoCinePresentacion.presentacion.pelicula.VentanaListarPeliculas;
import ProyectoCinePresentacion.presentacion.pelicula.VentanaSeleccionarPelicula;
import ProyectoCinePresentacion.presentacion.horario.VentanaListarHorario;
import ProyectoCinePresentacion.presentacion.horario.VentanaBuscarHorario;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class VentanaPrincipalEmpleado extends JFrame {

    public VentanaPrincipalEmpleado() {
        // Configuración del JFrame
        setTitle("Empleado - Cine");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Menú de Película
        JMenu menuPelicula = new JMenu("Película");
        menuPelicula.add(createMenuItem("Buscar"));
        menuPelicula.add(createMenuItem("Listar"));

        // Menú de Horario
        JMenu menuHorario = new JMenu("Horario");
        menuHorario.add(createMenuItem("Buscar Horario"));
        menuHorario.add(createMenuItem("Listar  Horarios"));

        // Menú de Promoción
        JMenu menuPromocion = new JMenu("Promoción");
        menuPromocion.add(createMenuItem("Buscar"));
        menuPromocion.add(createMenuItem("Listar"));

        // Menú de Sala
        JMenu menuSala = new JMenu("Sala");
        menuSala.add(createMenuItem("Buscar"));
        menuSala.add(createMenuItem("Listar"));

        // Menú de Venta
        JMenu menuVenta = new JMenu("Venta");
        menuVenta.add(createMenuItem("Crear"));

        // Agregar los menús a la barra de menú
        menuBar.add(menuPelicula);
        menuBar.add(menuHorario);
        menuBar.add(menuPromocion);
        menuBar.add(menuSala);
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
                 // Hacemos el precargado de los datos que podrían ser necesarios
                CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl(MyBatisUtil.getSqlSessionFactory());
                List<Categoria> categorias = categoriaDAO.Listar();
                List<String> nombresCategorias = categorias.stream()
                        .map(Categoria::getNombre)
                        .collect(Collectors.toList());

                switch (name) {
                    case "Buscar Horario":
                        VentanaBuscarHorario ventanaBuscarHorario = new VentanaBuscarHorario();
                        ventanaBuscarHorario.mostrar();
                        break;
                    case "Listar  Horarios":
                        VentanaListarHorario ventanaListarHorario = new VentanaListarHorario();
                        ventanaListarHorario.mostrar();
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
