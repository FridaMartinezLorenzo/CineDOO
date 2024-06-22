package ProyectoCinePresentacion.presentacion;

import javax.swing.*;

import ProyectoCinePresentacion.presentacion.sala.VentanaListarSalas;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

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
        menuHorario.add(createMenuItem("Buscar"));
        menuHorario.add(createMenuItem("Listar"));

        // Menú de Promoción
        JMenu menuPromocion = new JMenu("Promoción");
        menuPromocion.add(createMenuItem("Buscar"));
        menuPromocion.add(createMenuItem("Listar"));

        // Menú de Sala
        JMenu menuSala = new JMenu("Sala");
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
                // JOptionPane.showMessageDialog(VentanaPrincipalEmpleado.this, "Seleccionaste:
                // " + name);

                switch (name) {
                    case "Listar":
                        VentanaListarSalas ventanaListarSalas = new VentanaListarSalas();
                        ventanaListarSalas.mostrar();
                        // Listar Salas
                        break;
                }
            }
        });
        return menuItem;
    }

    /*
     * 
     * public static void main(String[] args) {
     * SwingUtilities.invokeLater(new Runnable() {
     * 
     * @Override
     * public void run() {
     * new VentanaPrincipalEmpleado().setVisible(true);
     * }
     * });
     * }
     */
    public void mostrar() {
        setVisible(true);
    }
}
