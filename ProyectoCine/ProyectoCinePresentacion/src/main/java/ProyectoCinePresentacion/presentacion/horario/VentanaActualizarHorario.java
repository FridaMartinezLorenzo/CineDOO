package ProyectoCinePresentacion.presentacion.horario;

import ProyectoCinePresentacion.controllers.HorariosController;
import ProyectoCinePersistencia.entities.Horario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaActualizarHorario extends JFrame {

    private HorariosController horariosController;
    private JComboBox<String> horariosComboBox;
    private JComboBox<String> horaComboBox;
    private JComboBox<String> minutoComboBox;
    private JComboBox<String> amPmComboBox;
    private List<Horario> horariosList;

    public VentanaActualizarHorario() {
        horariosController = new HorariosController();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Actualizar Horario");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel horariosLabel = new JLabel("Seleccione el horario a actualizar:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(horariosLabel, gbc);

        horariosList = horariosController.ListarHorarios();
        String[] horariosArray = horariosList.stream().map(Horario::getHoraInicio).toArray(String[]::new);
        horariosComboBox = new JComboBox<>(horariosArray);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(horariosComboBox, gbc);

        gbc.gridwidth = 1;

        JLabel horaLabel = new JLabel("Nueva hora:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(horaLabel, gbc);

        horaComboBox = new JComboBox<>(generateHourOptions());
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(horaComboBox, gbc);

        JLabel minutoLabel = new JLabel("Nuevo minuto:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(minutoLabel, gbc);

        minutoComboBox = new JComboBox<>(generateMinuteOptions());
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(minutoComboBox, gbc);

        JLabel amPmLabel = new JLabel("AM/PM:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(amPmLabel, gbc);

        amPmComboBox = new JComboBox<>(new String[]{"AM", "PM"});
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(amPmComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5); // Margin before the button
        gbc.anchor = GridBagConstraints.CENTER;
        JButton updateButton = new JButton("Actualizar");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarHorario();
            }
        });
        panel.add(updateButton, gbc);

        add(panel);
        pack();
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
        int selectedIndex = horariosComboBox.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un horario de la lista", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Horario selectedHorario = horariosList.get(selectedIndex);
        int id = selectedHorario.getIdHorario();

        String nuevaHora = (String) horaComboBox.getSelectedItem();
        String nuevoMinuto = (String) minutoComboBox.getSelectedItem();
        String amPm = (String) amPmComboBox.getSelectedItem();

        String horaCompleta = nuevaHora + ":" + nuevoMinuto + " " + amPm;

        Horario horario = new Horario();
        horario.setIdHorario(id);
        horario.setHoraInicio(horaCompleta);
        boolean success = horariosController.ActualizarHorario(horario);
        if (success) {
            JOptionPane.showMessageDialog(this, "Horario actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            horariosComboBox.setSelectedIndex(0);
            horaComboBox.setSelectedIndex(0);
            minutoComboBox.setSelectedIndex(0);
            amPmComboBox.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el horario con el ID proporcionado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
