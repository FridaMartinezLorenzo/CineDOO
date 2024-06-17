package ProyectoCinePresentacion.presentacion.pelicula;

import ProyectoCinePersistencia.dao.pelicula.PeliculaDAOImpl;
import ProyectoCinePersistencia.dao.categoria.CategoriaDAOImpl;
import ProyectoCinePersistencia.entities.Categoria;
import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePersistencia.utils.MyBatisUtil;

import javax.swing.*;
import java.awt.*;

public class VentanaBuscarPelicula extends JFrame {

    private Pelicula pelicula;

    public VentanaBuscarPelicula(int idPelicula) {
        PeliculaDAOImpl peliculaDAO = new PeliculaDAOImpl(MyBatisUtil.getSqlSessionFactory());
        this.pelicula = peliculaDAO.Buscar(idPelicula);
        initComponents();
    }

    private void initComponents() {
        setTitle("Detalles de la Película");
        setSize(800, 800); // Tamaño adecuado
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana en pantalla

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
        int idCategoria = pelicula.getIdCategoria();
        CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl(MyBatisUtil.getSqlSessionFactory());
        Categoria categoria = categoriaDAO.Buscar(idCategoria);
        panel.add(new JLabel(categoria.getNombre()), gbc);

        // Agregar panel al JFrame
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
