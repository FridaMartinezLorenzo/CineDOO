package ProyectoCinePresentacion.presentacion.pelicula;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePresentacion.controllers.PeliculaController;

public class VentanaListarPeliculas extends JFrame {

    private JTable tablaPeliculas;
    private PeliculaController peliculaController = new PeliculaController();

    public VentanaListarPeliculas() {
        this.peliculaController = new PeliculaController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Listado de Películas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir margen

        String[] columnNames = {"ID", "Título", "Duración", "Fecha de Estreno"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tablaPeliculas = new JTable(model);

        // Centrar el contenido de las celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tablaPeliculas.getColumnCount(); i++) {
            tablaPeliculas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tablaPeliculas);
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
}
