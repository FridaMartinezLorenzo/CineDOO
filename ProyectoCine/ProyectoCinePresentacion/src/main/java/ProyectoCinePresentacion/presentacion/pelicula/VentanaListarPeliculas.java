package ProyectoCinePresentacion.presentacion.pelicula;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePresentacion.controllers.PeliculaController;

public class VentanaListarPeliculas extends JFrame {

    private JTable tablaPeliculas;
    private PeliculaController peliculaController;

    public VentanaListarPeliculas() {
        this.peliculaController = new PeliculaController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Listado de Películas");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"ID", "Título", "Duración", "Fecha de Estreno"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tablaPeliculas = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tablaPeliculas);
        panel.add(scrollPane, BorderLayout.CENTER);

        tablaPeliculas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = tablaPeliculas.getSelectedRow();
                    int idPelicula = (int) tablaPeliculas.getValueAt(selectedRow, 0);
                    VentanaBuscarPelicula ventanaBuscarPelicula = new VentanaBuscarPelicula(idPelicula);
                    ventanaBuscarPelicula.mostrar();
                }
            }
        });

        cargarPeliculas(model);

        add(panel);
    }

    private void cargarPeliculas(DefaultTableModel model) {
        List<Pelicula> peliculas = peliculaController.listarPeliculas();
        for (Pelicula pelicula : peliculas) {
            Object[] rowData = {
                pelicula.getIdPelicula(),
                pelicula.getTitulo(),
                pelicula.getDuracion(),
                pelicula.getFechaEstreno()
            };
            model.addRow(rowData);
        }
    }

    public void mostrar() {
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaListarPeliculas ventana = new VentanaListarPeliculas();
            ventana.mostrar();
        });
    }
}
