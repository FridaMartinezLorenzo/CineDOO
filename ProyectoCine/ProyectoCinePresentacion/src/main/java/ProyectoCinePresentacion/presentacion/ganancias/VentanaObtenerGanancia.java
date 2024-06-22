package ProyectoCinePresentacion.presentacion.ganancias;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePresentacion.controllers.GananciaController;
import ProyectoCinePresentacion.controllers.PeliculaController;

public class VentanaObtenerGanancia extends JFrame {

    private GananciaController gananciaController;
    private PeliculaController peliculaController;
    private JComboBox<String> peliculaComboBox;
    private List<Pelicula> peliculas;

    public VentanaObtenerGanancia() {
        gananciaController = new GananciaController();
        peliculaController = new PeliculaController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Ganancia de la Película");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con margen
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(mainPanel);

        // Panel para los campos y el botón
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Campo de selección de la Película
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("Película:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        peliculas = peliculaController.listarPeliculas();
        peliculaComboBox = new JComboBox<>();
        for (Pelicula pelicula : peliculas) {
            peliculaComboBox.addItem(pelicula.getTitulo());
        }
        inputPanel.add(peliculaComboBox, gbc);

        // Botón para obtener la ganancia
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton obtenerGananciaButton = new JButton("Obtener Ganancia");
        inputPanel.add(obtenerGananciaButton, gbc);

        // Área de resultados
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel resultLabel = new JLabel(" ");
        inputPanel.add(resultLabel, gbc);

        // Acción del botón
        obtenerGananciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedIndex = peliculaComboBox.getSelectedIndex();
                    if (selectedIndex != -1) {
                        int idPelicula = peliculas.get(selectedIndex).getIdPelicula();
                        double ganancia = gananciaController.obtenerGanancias(idPelicula);
                        resultLabel.setText("La ganancia de la película es: " + ganancia);
                    } else {
                        JOptionPane.showMessageDialog(VentanaObtenerGanancia.this, "Seleccione una película.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(VentanaObtenerGanancia.this, "No hay ganancias aún de esta pelicula.");
                }
            }
        });
    }

    public void mostrar() {
        setVisible(true);
    }
}
