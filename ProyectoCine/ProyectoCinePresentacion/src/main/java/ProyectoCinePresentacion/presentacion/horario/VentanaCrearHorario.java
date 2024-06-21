package ProyectoCinePresentacion.presentacion.horario;

import ProyectoCinePresentacion.controllers.HorariosController;
import ProyectoCinePersistencia.entities.Horario;

import javax.swing.*;
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
        // Cambiar de JFrame.EXIT_ON_CLOSE a JFrame.DISPOSE_ON_CLOSE
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel de entrada
        JPanel panel = new JPanel(new GridLayout(4, 2));
        
        JLabel horaLabel = new JLabel("Hora:");
        horaComboBox = new JComboBox<>(generateHourOptions());
        
        JLabel minutoLabel = new JLabel("Minuto:");
        minutoComboBox = new JComboBox<>(generateMinuteOptions());
        
        JLabel amPmLabel = new JLabel("AM/PM:");
        amPmComboBox = new JComboBox<>(new String[]{"AM", "PM"});
        
        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveHorario();
            }
        });

        panel.add(horaLabel);
        panel.add(horaComboBox);
        panel.add(minutoLabel);
        panel.add(minutoComboBox);
        panel.add(amPmLabel);
        panel.add(amPmComboBox);
        panel.add(new JLabel()); // Empty cell
        panel.add(saveButton);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaCrearHorario().setVisible(true);
        });
    }
}
