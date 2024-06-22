package ProyectoCinePresentacion.presentacion.horario;

import ProyectoCinePresentacion.controllers.HorariosController;
import ProyectoCinePersistencia.entities.Horario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaEliminarHorario extends JFrame {

    private HorariosController horariosController;
    private JComboBox<String> horariosComboBox;
    private List<Horario> horariosList;

    public VentanaEliminarHorario() {
        horariosController = new HorariosController();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Eliminar Horario");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel horariosLabel = new JLabel("Seleccione el horario a eliminar:");
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

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        gbc.insets = new Insets(10, 5, 5, 5); // Margin before the button
        gbc.anchor = GridBagConstraints.CENTER;
        JButton deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteHorario();
            }
        });
        panel.add(deleteButton, gbc);

        add(panel);
        pack();
    }

    private void deleteHorario() {
        int selectedIndex = horariosComboBox.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un horario de la lista", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Horario selectedHorario = horariosList.get(selectedIndex);
        int id = selectedHorario.getIdHorario();

        boolean success = horariosController.EliminarHorario(id);
        if (success) {
            JOptionPane.showMessageDialog(this, "Horario eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            horariosList = horariosController.ListarHorarios(); // Refresh the list
            String[] horariosArray = horariosList.stream().map(Horario::getHoraInicio).toArray(String[]::new);
            horariosComboBox.setModel(new DefaultComboBoxModel<>(horariosArray)); // Update the combobox model
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el horario con el ID proporcionado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
