package ProyectoCinePresentacion.presentacion.horario;

import ProyectoCinePresentacion.controllers.HorariosController;
import ProyectoCinePersistencia.entities.Horario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCrearHorario extends JFrame {

    private HorariosController horariosController;
    private JComboBox<String> horaComboBox;
    private JComboBox<String> minutoComboBox;
    private JComboBox<String> amPmComboBox;

    public VentanaCrearHorario() {
        horariosController = new HorariosController();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Crear Nuevo Horario");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel de entrada con borde
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Añadir borde vacío

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel horaLabel = new JLabel("Hora:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(horaLabel, gbc);

        horaComboBox = new JComboBox<>(generateHourOptions());
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(horaComboBox, gbc);

        JLabel minutoLabel = new JLabel("Minuto:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(minutoLabel, gbc);

        minutoComboBox = new JComboBox<>(generateMinuteOptions());
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(minutoComboBox, gbc);

        JLabel amPmLabel = new JLabel("AM/PM:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(amPmLabel, gbc);

        amPmComboBox = new JComboBox<>(new String[]{"AM", "PM"});
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(amPmComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0; // Add vertical space
        panel.add(Box.createVerticalStrut(10), gbc); // Add empty space

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveHorario();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(saveButton, gbc);

        add(panel);
    }

    private String[] generateHourOptions() {
        String[] hours = new String[12];
        for (int i = 1; i <= 12; i++) {
            hours[i - 1] = String.format("%02d", i);
        }
        return hours;
    }

    private String[] generateMinuteOptions() {
        String[] minutes = new String[60];
        for (int i = 0; i < 60; i++) {
            minutes[i] = String.format("%02d", i);
        }
        return minutes;
    }

    private void saveHorario() {
        String hora = (String) horaComboBox.getSelectedItem();
        String minuto = (String) minutoComboBox.getSelectedItem();
        String amPm = (String) amPmComboBox.getSelectedItem();

        String horaCompleta = hora + ":" + minuto + " " + amPm;

        if (hora.isEmpty() || minuto.isEmpty() || amPm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Aquí deberías implementar la lógica para guardar el horario.
        Horario nuevoHorario = new Horario(0, horaCompleta); // ID se asignará automáticamente
        horariosController.CrearHorario(nuevoHorario);

        JOptionPane.showMessageDialog(this, "Horario creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        horaComboBox.setSelectedIndex(0);
        minutoComboBox.setSelectedIndex(0);
        amPmComboBox.setSelectedIndex(0);
    }

    public void mostrar() {
        setVisible(true);
    }
}
