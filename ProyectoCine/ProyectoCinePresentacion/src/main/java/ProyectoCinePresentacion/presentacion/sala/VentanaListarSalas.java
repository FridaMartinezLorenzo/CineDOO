package ProyectoCinePresentacion.presentacion.sala;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import ProyectoCinePersistencia.entities.Sala;
import ProyectoCinePresentacion.controllers.SalaController;

public class VentanaListarSalas extends JFrame {

    private JTable tablaSalas;
    private SalaController salaController;

    public VentanaListarSalas() {
        this.salaController = new SalaController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Listado de Salas");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));  // Añade márgenes

        String[] columnNames = {"ID", "Capacidad"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tablaSalas = new JTable(model);

        // Centrar texto en la tabla
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablaSalas.getColumnCount(); i++) {
            tablaSalas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tablaSalas);
        panel.add(scrollPane, BorderLayout.CENTER);

        tablaSalas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = tablaSalas.getSelectedRow();
                    int idSala = (int) tablaSalas.getValueAt(selectedRow, 0);
                    VentanaBuscarSala ventanaBuscarSala = new VentanaBuscarSala(idSala);
                    ventanaBuscarSala.mostrar();
                }
            }
        });

        cargarSalas(model);

        // Botón Aceptar
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(e -> dispose());  // Cierra la ventana al hacer clic en Aceptar
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(aceptarButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void cargarSalas(DefaultTableModel model) {
        List<Sala> salas = salaController.listarSalas();
        for (Sala sala : salas) {
            Object[] rowData = {
                sala.getIdSala(),
                sala.getNumAsiento()
            };
            model.addRow(rowData);
        }
    }

    public void mostrar() {
        setVisible(true);
    }

}
