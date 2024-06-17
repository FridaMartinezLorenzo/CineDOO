package ProyectoCinePresentacion.presentacion.pelicula;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ProyectoCinePersistencia.entities.Categoria;
import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePresentacion.controllers.CategoriaController;
import ProyectoCinePresentacion.controllers.PeliculaController;

public class VentanaBuscarPelicula extends JFrame {

    private Pelicula pelicula;

    public VentanaBuscarPelicula(int idPelicula) {
        PeliculaController peliculaController = new PeliculaController();
        this.pelicula = peliculaController.buscarPelicula(idPelicula);
        initComponents();
    }

    private void initComponents() {
        setTitle("Detalles de la Película");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Título:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(new JLabel(pelicula.getTitulo()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Sinopsis:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JTextArea sinopsisArea = new JTextArea(pelicula.getSinopsis(), 5, 20);
        sinopsisArea.setWrapStyleWord(true);
        sinopsisArea.setLineWrap(true);
        sinopsisArea.setOpaque(false);
        sinopsisArea.setEditable(false);
        sinopsisArea.setFocusable(false);
        sinopsisArea.setBackground(UIManager.getColor("Label.background"));
        sinopsisArea.setFont(UIManager.getFont("Label.font"));
        JScrollPane scrollPane = new JScrollPane(sinopsisArea);
        scrollPane.setBorder(null);
        panel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Duración (min):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(new JLabel(String.valueOf(pelicula.getDuracion())), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Fecha de Estreno:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(new JLabel(pelicula.getFechaEstreno()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Categoría:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        CategoriaController categoriaController = new CategoriaController();
        Categoria categoria = categoriaController.buscarCategoria(pelicula.getIdCategoria());
        panel.add(new JLabel(categoria.getNombre()), gbc);

        add(panel);
        pack();
    }

    public void mostrar() {
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaBuscarPelicula ventana = new VentanaBuscarPelicula(1); // Ejemplo con ID 1
            ventana.mostrar();
        });
    }
}
