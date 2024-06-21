package ProyectoCinePresentacion.presentacion.horario;

import ProyectoCinePresentacion.controllers.HorariosController;
import ProyectoCinePersistencia.entities.Horario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

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

    public void mostrar(){
        setVisible(true);
    }

    public static void main(String[] args) {
        HorariosController controller = new HorariosController();
        SwingUtilities.invokeLater(() -> {
            new VentanaListarHorario().mostrar();
        });
    }
}
