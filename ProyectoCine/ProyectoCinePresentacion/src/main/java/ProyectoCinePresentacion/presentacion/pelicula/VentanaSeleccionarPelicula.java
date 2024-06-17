package ProyectoCinePresentacion.presentacion.pelicula;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ProyectoCinePresentacion.controllers.PeliculaController;
import ProyectoCinePresentacion.controllers.CategoriaController;
import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePersistencia.entities.Categoria;

public class VentanaSeleccionarPelicula extends JFrame {

    private JComboBox<String> peliculasComboBox;
    private List<Pelicula> peliculas;
    private String caso;

    public VentanaSeleccionarPelicula(String caso) {
        this.caso = caso;
        PeliculaController peliculaController = new PeliculaController();
        this.peliculas = peliculaController.listarPeliculas();
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
                    if (caso.equals("editar")) {
                        CategoriaController categoriaController = new CategoriaController();
                        List<String> nombresCategorias = categoriaController.listarCategorias().stream()
                                .map(Categoria::getNombre)
                                .collect(Collectors.toList());
                        VentanaEditarPelicula ventanaEditarPelicula = new VentanaEditarPelicula(peliculaSeleccionada.getIdPelicula(), nombresCategorias);
                        ventanaEditarPelicula.mostrar();
                    } else if (caso.equals("buscar")) {
                        VentanaBuscarPelicula ventanaBuscarPelicula = new VentanaBuscarPelicula(peliculaSeleccionada.getIdPelicula());
                        ventanaBuscarPelicula.mostrar();
                    }
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
}
