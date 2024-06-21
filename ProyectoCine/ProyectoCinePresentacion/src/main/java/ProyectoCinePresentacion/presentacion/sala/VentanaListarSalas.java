package ProyectoCinePresentacion.presentacion.sala;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
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

        String[] columnNames = { "ID", "Capacidad" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tablaSalas = new JTable(model);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaListarSalas ventana = new VentanaListarSalas();
            ventana.mostrar();
        });
    }
}
