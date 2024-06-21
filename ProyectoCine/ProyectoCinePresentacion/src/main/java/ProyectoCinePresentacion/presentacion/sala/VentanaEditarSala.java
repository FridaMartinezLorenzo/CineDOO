package ProyectoCinePresentacion.presentacion.sala;

import ProyectoCinePresentacion.controllers.SalaController;
import ProyectoCinePersistencia.entities.Sala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaEditarSala extends JFrame {

    private JTextField numAsientoField;
    private Sala sala;
    private SalaController salaController;

    public VentanaEditarSala(int idSala) {
        this.salaController = new SalaController();
        this.sala = salaController.buscarSala(idSala);
        initComponents();
    }

    private void initComponents() {
        setTitle("Editar Sala");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Número de Asientos:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        numAsientoField = new JTextField(String.valueOf(sala.getNumAsiento()), 20);
        panel.add(numAsientoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarSala();
            }
        });
        panel.add(guardarButton, gbc);

        add(panel);
        pack();
    }

    private void editarSala() {
        sala.setNumAsiento(Integer.parseInt(numAsientoField.getText()));

        Sala salaEditada = new Sala(this.sala.getIdSala(), sala.getNumAsiento());
        salaEditada.setIdSala(sala.getIdSala());

        boolean exito = salaController.actualizarSala(salaEditada);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Sala actualizada correctamente", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar la sala", "Error", JOptionPane.ERROR_MESSAGE);
        }

        dispose();
    }

    public void mostrar() {
        setVisible(true);
    }
}
