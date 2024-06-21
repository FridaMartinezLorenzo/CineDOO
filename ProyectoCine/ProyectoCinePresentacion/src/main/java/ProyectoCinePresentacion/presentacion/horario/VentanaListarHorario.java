package ProyectoCinePresentacion.presentacion.horario;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

        // Table model
        String[] columnNames = {"ID", "Horario"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Load data
        loadData();

        add(scrollPane, BorderLayout.CENTER);
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
