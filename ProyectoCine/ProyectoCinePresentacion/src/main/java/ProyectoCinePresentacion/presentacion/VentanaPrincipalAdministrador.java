package ProyectoCinePresentacion.presentacion;

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
        menuPelicula.add(createMenuItem("Crear"));
        menuPelicula.add(createMenuItem("Leer"));
        menuPelicula.add(createMenuItem("Actualizar"));
        menuPelicula.add(createMenuItem("Eliminar"));

        // Menú de Ganancia
        JMenu menuGanancia = new JMenu("Ganancia");
        menuGanancia.add(createMenuItem("Crear"));
        menuGanancia.add(createMenuItem("Leer"));
        menuGanancia.add(createMenuItem("Actualizar"));
        menuGanancia.add(createMenuItem("Eliminar"));

        // Menú de Horario
        JMenu menuHorario = new JMenu("Horario");
        menuHorario.add(createMenuItem("Crear"));
        menuHorario.add(createMenuItem("Leer"));
        menuHorario.add(createMenuItem("Actualizar"));
        menuHorario.add(createMenuItem("Eliminar"));

        // Menú de Promoción
        JMenu menuPromocion = new JMenu("Promoción");
        menuPromocion.add(createMenuItem("Crear"));
        menuPromocion.add(createMenuItem("Leer"));
        menuPromocion.add(createMenuItem("Actualizar"));
        menuPromocion.add(createMenuItem("Eliminar"));

        // Menú de Sala
        JMenu menuSala = new JMenu("Sala");
        menuSala.add(createMenuItem("Crear"));
        menuSala.add(createMenuItem("Leer"));
        menuSala.add(createMenuItem("Actualizar"));
        menuSala.add(createMenuItem("Eliminar"));

        // Menú de Usuario
        JMenu menuUsuario = new JMenu("Usuario");
        menuUsuario.add(createMenuItem("Crear"));
        menuUsuario.add(createMenuItem("Leer"));
        menuUsuario.add(createMenuItem("Actualizar"));
        menuUsuario.add(createMenuItem("Eliminar"));

        // Menú de Venta
        JMenu menuVenta = new JMenu("Venta");
        menuVenta.add(createMenuItem("Crear"));
        menuVenta.add(createMenuItem("Leer"));
        menuVenta.add(createMenuItem("Actualizar"));
        menuVenta.add(createMenuItem("Eliminar"));

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
            }
        });
        return menuItem;
    }

    public void mostrar() {
        setVisible(true);
    }
}
