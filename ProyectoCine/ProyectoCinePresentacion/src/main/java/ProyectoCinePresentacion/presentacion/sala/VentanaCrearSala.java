package ProyectoCinePresentacion.presentacion.sala;

import ProyectoCinePresentacion.controllers.SalaController;
import ProyectoCinePersistencia.entities.Sala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaCrearSala extends JFrame {

    private JTextField numeroAsientosField;
    private SalaController salaController;

    public VentanaCrearSala() {
        this.salaController = new SalaController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Crear Sala");
        setSize(400, 300);
        // Cambiar el comportamiento del cierre de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Número de Asientos:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        numeroAsientosField = new JTextField(20);
        panel.add(numeroAsientosField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearSala();
            }
        });
        panel.add(guardarButton, gbc);

        add(panel);
        pack();
    }

    private void crearSala() {
        String numeroAsientosText = numeroAsientosField.getText();

        // Validar campo vacío
        if (numeroAsientosText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo 'Número de Asientos' no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int numeroAsientos = Integer.parseInt(numeroAsientosText);

            Sala nuevaSala = new Sala(obtenerIdsala(), numeroAsientos);
            Sala salaCreada = salaController.crearSala(nuevaSala);

            if (salaCreada.getIdSala() != -1) {
                JOptionPane.showMessageDialog(this, "Sala creada con éxito", "Sala Creada", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear la sala", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido para 'Número de Asientos'", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int obtenerIdsala() {
        List<Sala> salas = salaController.listarSalas();
        return salas.size() + 1;
    }

    public void mostrar() {
        setVisible(true);
    }
}
