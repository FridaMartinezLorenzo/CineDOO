package ProyectoCinePresentacion.presentacion.pelicula;

import ProyectoCinePresentacion.controllers.PeliculaController;
import ProyectoCinePresentacion.controllers.CategoriaController;
import ProyectoCinePersistencia.entities.Categoria;
import ProyectoCinePersistencia.entities.Pelicula;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaEditarPelicula extends JFrame {

    private JTextField tituloField;
    private JTextArea sinopsisArea;
    private JTextField duracionField;
    private JTextField fechaEstrenoField;
    private JComboBox<String> categoriasComboBox;
    private Pelicula pelicula;
    private PeliculaController peliculaController;

    public VentanaEditarPelicula(int idPelicula, List<String> categorias) {
        this.peliculaController = new PeliculaController();
        this.pelicula = peliculaController.buscarPelicula(idPelicula);
        initComponents(categorias);
    }

    private void initComponents(List<String> categorias) {
        setTitle("Editar Película");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        panel.add(categoriasComboBox, gbc);
        categoriasComboBox.setSelectedItem(obtenerNombreCategoria(pelicula.getIdCategoria()));

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarPelicula();
            }
        });
        panel.add(guardarButton, gbc);

        add(panel);
        pack();
    }

    private void editarPelicula() {
        String titulo = tituloField.getText();
        String sinopsis = sinopsisArea.getText();
        int duracion = Integer.parseInt(duracionField.getText());
        String fechaEstreno = fechaEstrenoField.getText();
        String categoria = (String) categoriasComboBox.getSelectedItem();

        Pelicula peliculaEditada = new Pelicula(this.pelicula.getIdPelicula(), titulo, sinopsis, duracion, fechaEstreno, obtenerIdCategoria(categoria));
        peliculaEditada.setIdPelicula(pelicula.getIdPelicula());

        boolean exito = peliculaController.actualizarPelicula(peliculaEditada);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Película eliminada exitosamente");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar la película. Verifique el ID.");
        }

        dispose();
    }

    private String obtenerNombreCategoria(int idCategoria) {
        CategoriaController categoriaController = new CategoriaController();
        Categoria categoria = categoriaController.buscarCategoria(idCategoria);
        return categoria.getNombre();
    }

    private int obtenerIdCategoria(String nombreCategoria) {
        CategoriaController categoriaController = new CategoriaController();
        List<Categoria> categorias = categoriaController.listarCategorias();
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equals(nombreCategoria)) {
                return categoria.getIdCategoria();
            }
        }
        return -1; // Categoría no encontrada
    }

    public void mostrar() {
        setVisible(true);
    }
}
