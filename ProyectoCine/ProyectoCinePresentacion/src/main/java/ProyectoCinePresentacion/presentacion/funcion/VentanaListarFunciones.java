package ProyectoCinePresentacion.presentacion.funcion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ProyectoCinePersistencia.entities.Funcion;
import ProyectoCinePresentacion.controllers.FuncionController;

public class VentanaListarFunciones extends JFrame {

    private FuncionController funcionController;
    private JTable funcionesTable;

    public VentanaListarFunciones() {
        this.funcionController = new FuncionController();
        initComponents();
        listarFunciones();
    }

    private void initComponents() {
        setTitle("Listar Funciones");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir margen

        String[] columnNames = {"Película", "Horario"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        funcionesTable = new JTable(model);

        // Centrar el contenido de las celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < funcionesTable.getColumnCount(); i++) {
            funcionesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(funcionesTable);
        scrollPane.setPreferredSize(new Dimension(500, 300)); // Tamaño preferido del JScrollPane

        // Panel para centrar la tabla
        JPanel tablePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        tablePanel.add(scrollPane, gbc);

        panel.add(tablePanel, BorderLayout.CENTER);

        // Botón Aceptar
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(e -> dispose()); // Cierra la ventana al hacer clic en Aceptar

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(aceptarButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void listarFunciones() {
        // Obtener la lista de funciones desde el controlador
        List<Funcion> funciones = funcionController.listarFunciones();

        // Crear modelo de tabla con columnas "Película" y "Horario"
        DefaultTableModel model = (DefaultTableModel) funcionesTable.getModel();

        // Llenar la tabla con datos de funciones
        for (Funcion funcion : funciones) {
            String tituloPelicula = funcion.getPelicula().getTitulo();
            String horarioInicio = funcion.getHorario().getHoraInicio();
            model.addRow(new Object[]{tituloPelicula, horarioInicio});
        }
    }

    public void mostrar() {
        setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            VentanaListarFunciones ventana = new VentanaListarFunciones();
            ventana.mostrar();
        });
    }
}
