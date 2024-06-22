package ProyectoCinePresentacion.presentacion.sala;

import ProyectoCinePresentacion.controllers.SalaController;
import ProyectoCinePersistencia.entities.Sala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaEliminarSala extends JFrame {

    private JComboBox<Integer> idSalaComboBox;
    private SalaController salaController;

    public VentanaEliminarSala() {
        this.salaController = new SalaController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Eliminar Sala");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Seleccione ID de la Sala:"), gbc);

        List<Sala> salas = salaController.listarSalas();
        Integer[] idSalaArray = salas.stream().map(Sala::getIdSala).toArray(Integer[]::new);
        idSalaComboBox = new JComboBox<>(idSalaArray);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(idSalaComboBox, gbc);

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
        int idSala = (int) idSalaComboBox.getSelectedItem();
        boolean exito = salaController.eliminarSala(idSala);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Sala eliminada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar la sala", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
