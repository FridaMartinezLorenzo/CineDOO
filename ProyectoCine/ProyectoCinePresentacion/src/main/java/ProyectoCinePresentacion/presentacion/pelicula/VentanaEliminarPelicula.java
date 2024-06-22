package ProyectoCinePresentacion.presentacion.pelicula;

import ProyectoCinePresentacion.controllers.PeliculaController;
import ProyectoCinePersistencia.entities.Pelicula;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaEliminarPelicula extends JFrame {

    private PeliculaController peliculaController;
    private JComboBox<String> peliculasComboBox;
    private List<Pelicula> peliculasList;

    public VentanaEliminarPelicula() {
        this.peliculaController = new PeliculaController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Eliminar Película");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel peliculasLabel = new JLabel("Seleccione la película a eliminar:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(peliculasLabel, gbc);

        peliculasList = peliculaController.listarPeliculas();
        String[] peliculasArray = peliculasList.stream().map(Pelicula::getTitulo).toArray(String[]::new);
        peliculasComboBox = new JComboBox<>(peliculasArray);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(peliculasComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5); // Margin before the button
        gbc.anchor = GridBagConstraints.CENTER;
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPelicula();
            }
        });
        panel.add(eliminarButton, gbc);

        add(panel);
        pack();
    }

    private void eliminarPelicula() {
        int selectedIndex = peliculasComboBox.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una película de la lista", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Pelicula selectedPelicula = peliculasList.get(selectedIndex);
        int idPelicula = selectedPelicula.getIdPelicula();
        boolean exito = peliculaController.eliminarPelicula(idPelicula);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Película eliminada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            peliculasList = peliculaController.listarPeliculas(); // Refresh the list
            String[] peliculasArray = peliculasList.stream().map(Pelicula::getTitulo).toArray(String[]::new);
            peliculasComboBox.setModel(new DefaultComboBoxModel<>(peliculasArray)); // Update the combobox model
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar la película. Verifique el ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
