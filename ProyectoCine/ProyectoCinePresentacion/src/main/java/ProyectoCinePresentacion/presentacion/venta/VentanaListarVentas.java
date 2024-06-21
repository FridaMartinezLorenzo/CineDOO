package ProyectoCinePresentacion.presentacion.venta;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ProyectoCinePresentacion.controllers.VentaController;

public class VentanaListarVentas extends JFrame {

    private JTable ventasTable;
    private DefaultTableModel tableModel;

    public VentanaListarVentas(List<Object[]> ventas) {
        setTitle("Listar Ventas");
        setSize(600, 400);
        // Cambiamos setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) por setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE)
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents(ventas);
    }

    private void initComponents(List<Object[]> ventas) {
        String[] columnNames = {"Película", "Total"};
        tableModel = new DefaultTableModel(columnNames, 0);
        ventasTable = new JTable(tableModel);

        // Centrando la tabla
        ventasTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
        ventasTable.setFillsViewportHeight(true);

        // Agregar datos a la tabla
        for (Object[] venta : ventas) {
            tableModel.addRow(venta);
        }

        JScrollPane scrollPane = new JScrollPane(ventasTable);

        // Centrar el contenido de la tabla
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(scrollPane, gbc);

        // Botón Aceptar
        JButton aceptarButton = new JButton("Aceptar");
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(aceptarButton, gbc);

        // Acción al presionar el botón Aceptar
        aceptarButton.addActionListener(e -> {
            dispose(); // Cierra la ventana actual
        });

        add(panel);
    }

    public void mostrar() {
        setVisible(true);
    }

    public static void main(String[] args) {
        VentaController ventaController = new VentaController();
        List<Object[]> ventas = ventaController.listarVentas();

        VentanaListarVentas ventanaListarVentas = new VentanaListarVentas(ventas);
        ventanaListarVentas.mostrar();
    }
}
