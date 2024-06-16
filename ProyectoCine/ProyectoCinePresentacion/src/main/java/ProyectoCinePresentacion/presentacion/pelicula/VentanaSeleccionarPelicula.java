package ProyectoCinePresentacion.presentacion.pelicula;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.util.stream.Collectors;

import ProyectoCinePersistencia.dao.pelicula.PeliculaDAOImpl;
import ProyectoCinePersistencia.dao.categoria.CategoriaDAOImpl;
import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePersistencia.entities.Categoria;
import ProyectoCinePersistencia.utils.MyBatisUtil;

public class VentanaSeleccionarPelicula extends JFrame {

    private JComboBox<String> peliculasComboBox;
    private List<Pelicula> peliculas;

    public VentanaSeleccionarPelicula() {
        PeliculaDAOImpl peliculaDAO = new PeliculaDAOImpl(MyBatisUtil.getSqlSessionFactory());
        this.peliculas = peliculaDAO.Listar();
        initComponents();
    }

    private void initComponents() {
        setTitle("Seleccionar Película");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Seleccionar Película:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        String[] titulosPeliculas = peliculas.stream().map(Pelicula::getTitulo).toArray(String[]::new);
        peliculasComboBox = new JComboBox<>(titulosPeliculas);
        panel.add(peliculasComboBox, gbc);

        JButton seleccionarButton = new JButton("Seleccionar");
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = peliculasComboBox.getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < peliculas.size()) {
                    Pelicula peliculaSeleccionada = peliculas.get(selectedIndex);
                    CategoriaDAOImpl categoriaDAO = new CategoriaDAOImpl(MyBatisUtil.getSqlSessionFactory());
                    List<String> nombresCategorias = categoriaDAO.Listar().stream()
                            .map(Categoria::getNombre)
                            .collect(Collectors.toList());
                    VentanaEditarPelicula ventanaEditarPelicula = new VentanaEditarPelicula(peliculaSeleccionada.getIdPelicula(), nombresCategorias);
                    ventanaEditarPelicula.mostrar();
                    dispose();
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(seleccionarButton, gbc);

        add(panel);
        pack();
    }

    public void mostrar() {
        setVisible(true);
    }

    /*
     
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaSeleccionarPelicula ventana = new VentanaSeleccionarPelicula();
            ventana.mostrar();
        });
    }
     */
}
