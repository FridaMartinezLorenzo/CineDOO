package ProyectoCinePresentacion.presentacion.horario;

import ProyectoCinePresentacion.controllers.HorariosController;
import ProyectoCinePersistencia.entities.Horario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaActualizarHorario extends JFrame {
    private HorariosController horariosController;
    private JTextField idField;
    private JComboBox<String> horaComboBox;
    private JComboBox<String> minutoComboBox;
    private JComboBox<String> amPmComboBox;

    public VentanaActualizarHorario() {
        horariosController = new HorariosController();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Actualizar Horario");
        setSize(400, 200);
        // Cambiar de JFrame.EXIT_ON_CLOSE a JFrame.DISPOSE_ON_CLOSE
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    
        // Panel de entrada
        JPanel panel = new JPanel(new GridLayout(5, 2));
        
        JLabel idLabel = new JLabel("ID del horario:");
        idField = new JTextField();
        
        JLabel horaLabel = new JLabel("Nueva hora:");
        horaComboBox = new JComboBox<>(generateHourOptions());
        
        JLabel minutoLabel = new JLabel("Nuevo minuto:");
        minutoComboBox = new JComboBox<>(generateMinuteOptions());
        
        JLabel amPmLabel = new JLabel("AM/PM:");
        amPmComboBox = new JComboBox<>(new String[]{"AM", "PM"});
        
        JButton updateButton = new JButton("Actualizar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarHorario();
            }
        });
    
        panel.add(idLabel);
        panel.add(idField);
        panel.add(horaLabel);
        panel.add(horaComboBox);
        panel.add(minutoLabel);
        panel.add(minutoComboBox);
        panel.add(amPmLabel);
        panel.add(amPmComboBox);
        panel.add(new JLabel()); // Empty cell
        panel.add(updateButton);
    
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

    private void actualizarHorario() {
        String idText = idField.getText();
        String nuevaHora = (String) horaComboBox.getSelectedItem();
        String nuevoMinuto = (String) minutoComboBox.getSelectedItem();
        String amPm = (String) amPmComboBox.getSelectedItem();
        
        String horaCompleta = nuevaHora + ":" + nuevoMinuto + " " + amPm;

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El ID no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            Horario horario = new Horario();
            horario.setIdHorario(id);
            horario.setHoraInicio(horaCompleta);
            boolean success = horariosController.ActualizarHorario(horario);
            if (success) {
                JOptionPane.showMessageDialog(this, "Horario actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                idField.setText(""); // Clear the ID field
                horaComboBox.setSelectedIndex(0);
                minutoComboBox.setSelectedIndex(0);
                amPmComboBox.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el horario con el ID proporcionado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrar() {
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaActualizarHorario().setVisible(true);
        });
    }
}
