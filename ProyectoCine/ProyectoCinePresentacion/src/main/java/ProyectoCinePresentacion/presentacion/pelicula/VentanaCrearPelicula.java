package ProyectoCinePresentacion.presentacion.pelicula;

import ProyectoCinePresentacion.controllers.PeliculaController;
import ProyectoCinePresentacion.controllers.CategoriaController;
import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePersistencia.entities.Categoria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaCrearPelicula extends JFrame {

    private JTextField tituloField;
    private JTextArea sinopsisArea;
    private JTextField duracionField;
    private JTextField fechaEstrenoField;
    private JComboBox<String> categoriasComboBox;
    private PeliculaController peliculaController;

    public VentanaCrearPelicula(List<String> categorias) {
        this.peliculaController = new PeliculaController();
        initComponents(categorias);
    }

    private void initComponents(List<String> categorias) {
        setTitle("Crear Película");
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
        tituloField = new JTextField(20);
        panel.add(tituloField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Sinopsis:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        sinopsisArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(sinopsisArea);
        panel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Duración (min):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        duracionField = new JTextField(10);
        panel.add(duracionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Fecha de Estreno:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        fechaEstrenoField = new JTextField(10);
        panel.add(fechaEstrenoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Categoría:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        categoriasComboBox = new JComboBox<>(categorias.toArray(new String[0]));
        panel.add(categoriasComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearPelicula();
            }
        });
        panel.add(guardarButton, gbc);

        add(panel);
        pack();
    }

    private void crearPelicula() {
        String titulo = tituloField.getText().trim();
        String sinopsis = sinopsisArea.getText().trim();
        String duracionText = duracionField.getText().trim();
        String fechaEstreno = fechaEstrenoField.getText().trim();
        String categoria = (String) categoriasComboBox.getSelectedItem();

        if (titulo.isEmpty() || sinopsis.isEmpty() || duracionText.isEmpty() || fechaEstreno.isEmpty() || categoria == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int duracion;
        try {
            duracion = Integer.parseInt(duracionText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La duración debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (duracion <= 0) {
            JOptionPane.showMessageDialog(this, "La duración debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Pelicula nuevaPelicula = new Pelicula(0, titulo, sinopsis, duracion, fechaEstreno, obtenerIdCategoria(categoria));
        Pelicula peliculaCreada = peliculaController.crearPelicula(nuevaPelicula);

        if (peliculaCreada.getIdPelicula() != -1) {
            JOptionPane.showMessageDialog(this, "Película creada exitosamente");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear la película. Verifique los datos.");
        }
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
