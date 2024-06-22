package ProyectoCinePresentacion.presentacion.horario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ProyectoCinePersistencia.entities.Horario;
import ProyectoCinePresentacion.controllers.HorariosController;

public class VentanaListarHorario extends JFrame {

    private HorariosController horariosController;
    private JTable table;
    private DefaultTableModel tableModel;

    public VentanaListarHorario() {
        horariosController = new HorariosController();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Lista de Horarios");
        setSize(600, 400);
        // Cambiar de JFrame.EXIT_ON_CLOSE a JFrame.DISPOSE_ON_CLOSE
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con márgenes
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));  // Añade márgenes alrededor del panel

        // Table model
        String[] columnNames = {"ID", "Horario"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Centrar texto en la tabla
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        // Load data
        loadData();

        panel.add(scrollPane, BorderLayout.CENTER);

        // Botón Aceptar
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(e -> dispose());  // Cierra la ventana al hacer clic en Aceptar
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(aceptarButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void loadData() {
        List<Horario> horarios = horariosController.ListarHorarios();
        for (Horario horario : horarios) {
            Object[] rowData = {horario.getIdHorario(), horario.getHoraInicio()};
            tableModel.addRow(rowData);
        }
    }

    public void mostrar() {
        setVisible(true);
    }

}
