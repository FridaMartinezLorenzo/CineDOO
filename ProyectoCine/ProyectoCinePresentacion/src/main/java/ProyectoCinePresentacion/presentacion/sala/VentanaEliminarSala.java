package ProyectoCinePresentacion.presentacion.sala;

import ProyectoCinePresentacion.controllers.SalaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEliminarSala extends JFrame {
    
    private JTextField idSalaField;
    private SalaController salaController;

    public VentanaEliminarSala() {
        this.salaController = new SalaController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Eliminar Sala");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("ID de la Sala:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        idSalaField = new JTextField(10);
        panel.add(idSalaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarSala();
            }
        });
        panel.add(eliminarButton, gbc);

        add(panel);
        pack();
    }

    private void eliminarSala() {
        int idSala = Integer.parseInt(idSalaField.getText());
        boolean exito = salaController.eliminarSala(idSala);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Sala eliminada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();//cierra la ventana
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar la sala", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
