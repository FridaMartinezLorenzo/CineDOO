package ProyectoCinePresentacion.presentacion.pelicula;

import ProyectoCinePresentacion.controllers.PeliculaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEliminarPelicula extends JFrame {

    private JTextField idPeliculaField;
    private PeliculaController peliculaController;

    public VentanaEliminarPelicula() {
        this.peliculaController = new PeliculaController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Eliminar Película");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("ID de la Película:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        idPeliculaField = new JTextField(10);
        panel.add(idPeliculaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
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
        int idPelicula = Integer.parseInt(idPeliculaField.getText());
        boolean exito = peliculaController.eliminarPelicula(idPelicula);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Película eliminada exitosamente");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar la película. Verifique el ID.");
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
