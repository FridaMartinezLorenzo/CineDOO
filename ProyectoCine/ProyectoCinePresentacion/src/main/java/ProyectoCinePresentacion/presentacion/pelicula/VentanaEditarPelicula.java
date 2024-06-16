package ProyectoCinePresentacion.presentacion.pelicula;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ProyectoCinePersistencia.dao.categoria.CategoriaDAOImpl;
import ProyectoCinePersistencia.dao.pelicula.PeliculaDAOImpl;
import ProyectoCinePersistencia.entities.Categoria;
import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class VentanaEditarPelicula extends JFrame {

    private JTextField tituloField;
    private JTextArea sinopsisArea;
    private JTextField duracionField;
    private JTextField fechaEstrenoField;
    private JComboBox<String> categoriasComboBox;
    private Pelicula pelicula;

    public VentanaEditarPelicula(int idPelicula, List<String> categorias) {
        PeliculaDAOImpl peliculaDAO = new PeliculaDAOImpl(MyBatisUtil.getSqlSessionFactory());
        this.pelicula = peliculaDAO.Buscar(idPelicula);
        initComponents(categorias);
    }

    private void initComponents(List<String> categorias) {
        setTitle("Editar Película");
        setSize(400, 300);
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
        tituloField = new JTextField(pelicula.getTitulo(), 20);
        panel.add(tituloField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Sinopsis:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        sinopsisArea = new JTextArea(pelicula.getSinopsis(), 5, 20);
        JScrollPane scrollPane = new JScrollPane(sinopsisArea);
        panel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Duración (min):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        duracionField = new JTextField(String.valueOf(pelicula.getDuracion()), 10);
        panel.add(duracionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Fecha de Estreno:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        fechaEstrenoField = new JTextField(pelicula.getFechaEstreno(), 10);
        panel.add(fechaEstrenoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Categoría:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        categoriasComboBox = new JComboBox<>(categorias.toArray(new String[0]));
        int idCategoria = pelicula.getIdCategoria();
        CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl(MyBatisUtil.getSqlSessionFactory());
        Categoria categoria = categoriaDAO.Buscar(idCategoria);

        categoriasComboBox.setSelectedItem(categoria.getNombre());
        panel.add(categoriasComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton guardarButton = new JButton("Guardar");
        panel.add(guardarButton, gbc);

        add(panel);
        pack();
    }

    public void mostrar() {
        setVisible(true);
    }

    /*
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<String> categorias = List.of("Acción", "Comedia", "Drama", "Fantasía", "Ciencia Ficción");
            VentanaEditarPelicula ventana = new VentanaEditarPelicula(1, categorias);
            ventana.mostrar();
        });
    }
     */
}
