package ProyectoCinePresentacion.presentacion.pelicula;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import ProyectoCinePersistencia.dao.pelicula.PeliculaDAOImpl;
import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class VentanaListarPeliculas extends JFrame {

    private JTable peliculasTable;
    private DefaultTableModel tableModel;

    public VentanaListarPeliculas() {
        initComponents();
        cargarDatosPeliculas();
    }

    private void initComponents() {
        setTitle("Listar Películas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana en pantalla

        // Crear el modelo de la tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("No.");
        tableModel.addColumn("Título");

        // Crear la tabla y establecer el modelo
        peliculasTable = new JTable(tableModel);

        // Centrar el texto en las celdas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        peliculasTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        peliculasTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        // Ajustar el ancho de las columnas
        TableColumnModel columnModel = peliculasTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50); // Ancho de la columna "No."
        columnModel.getColumn(1).setPreferredWidth(200); // Ancho de la columna "Título"

        // Configurar el JScrollPane con la tabla centrada
        JScrollPane scrollPane = new JScrollPane(peliculasTable);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        // Layout y agregar componentes
        setLayout(new BorderLayout());

        JPanel tablePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        tablePanel.add(scrollPane, gbc);
        add(tablePanel, BorderLayout.CENTER);

        // Botón de cerrar (opcional)
        JPanel buttonPanel = new JPanel();
        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.addActionListener(e -> dispose());
        buttonPanel.add(cerrarButton);

        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 0, 0); // Añadir margen superior de 20px
        tablePanel.add(buttonPanel, gbc);
    }

    private void cargarDatosPeliculas() {
        PeliculaDAOImpl peliculaDAO = new PeliculaDAOImpl(MyBatisUtil.getSqlSessionFactory());
        List<Pelicula> peliculas = peliculaDAO.Listar();

        for (int i = 0; i < peliculas.size(); i++) {
            Pelicula pelicula = peliculas.get(i);
            tableModel.addRow(new Object[]{i + 1, pelicula.getTitulo()});
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
