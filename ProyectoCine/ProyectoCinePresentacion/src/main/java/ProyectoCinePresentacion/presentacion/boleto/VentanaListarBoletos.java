package ProyectoCinePresentacion.presentacion.boleto;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ProyectoCinePersistencia.entities.Boleto;
import ProyectoCinePresentacion.controllers.BoletoController;

public class VentanaListarBoletos extends JFrame {

    private JTable boletosTable;
    private DefaultTableModel tableModel;
    private BoletoController boletoController;

    public VentanaListarBoletos() {
        boletoController = new BoletoController();
        setTitle("Listar Boletos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        String[] columnNames = {"ID", "Nombre", "Precio"};
        tableModel = new DefaultTableModel(columnNames, 0);
        boletosTable = new JTable(tableModel);

        // Centrando la tabla
        boletosTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
        boletosTable.setFillsViewportHeight(true);

        // Agregar datos a la tabla
        List<Boleto> boletos = boletoController.listarBoletos();
        for (Boleto boleto : boletos) {
            tableModel.addRow(new Object[]{boleto.getIdTipoBoleto(), boleto.getNombre(), boleto.getPrecio()});
        }

        JScrollPane scrollPane = new JScrollPane(boletosTable);

        // Centrar el contenido de la tabla
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(scrollPane, gbc);

        add(panel);
    }

    public void mostrar() {
        setVisible(true);
    }

}
