package ProyectoCinePresentacion.presentacion;

import ProyectoCinePresentacion.presentacion.pelicula.VentanaCrearPelicula;
import ProyectoCinePersistencia.dao.categoria.CategoriaDAOImpl;
import ProyectoCinePersistencia.entities.Categoria;
import ProyectoCinePersistencia.utils.MyBatisUtil;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                JOptionPane.showMessageDialog(VentanaPrincipalAdministrador.this, "Seleccionaste: " + name);
                // Menú de tratamiento de la opción seleccionada

                // Hacemos el precargado de los datos que podrían ser necesarios
                CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl(MyBatisUtil.getSqlSessionFactory());
                List<Categoria> categorias = categoriaDAO.Listar();

                if (name.equals("Crear Película")) {
                    // Convertir la lista de Categoria a una lista de String
                    List<String> nombresCategorias = categorias.stream()
                            .map(Categoria::getNombre)
                            .collect(Collectors.toList());
                    VentanaCrearPelicula ventanaCrearPelicula = new VentanaCrearPelicula(nombresCategorias);
                    ventanaCrearPelicula.mostrar();
                }
            }
        });
        return menuItem;
    }

    public void mostrar() {
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipalAdministrador().setVisible(true);
            }
        });
    }
}
