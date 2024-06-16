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
import javax.swing.SwingUtilities;

public class VentanaCrearPelicula extends JFrame {

    private JTextField tituloField;
    private JTextArea sinopsisArea;
    private JTextField duracionField;
    private JTextField fechaEstrenoField;
    private JComboBox<String> categoriasComboBox;

    public VentanaCrearPelicula(List<String> categorias) {
        initComponents(categorias);
    }

    private void initComponents(List<String> categorias) {
        setTitle("Crear Película");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana en pantalla

        // Layout y componentes
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Márgenes

        // Componentes: Labels y Inputs
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

        // Botón de Guardar (opcional)
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton guardarButton = new JButton("Guardar");
        panel.add(guardarButton, gbc);

        // Agregar panel al JFrame
        add(panel);
        pack();
    }

    public void mostrar() {
        setVisible(true);
    }

    public static void main(String[] args) {
        // Ejemplo de uso para probar la ventana
        SwingUtilities.invokeLater(() -> {
            List<String> categorias = List.of("Acción", "Comedia", "Drama", "Fantasía", "Ciencia Ficción");
            VentanaCrearPelicula ventana = new VentanaCrearPelicula(categorias);
            ventana.mostrar();
        });
    }
}
