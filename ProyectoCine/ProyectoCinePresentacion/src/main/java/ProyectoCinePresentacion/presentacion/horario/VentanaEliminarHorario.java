package ProyectoCinePresentacion.presentacion.horario;

import ProyectoCinePresentacion.controllers.HorariosController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEliminarHorario extends JFrame {

    private HorariosController horariosController;
    private JTextField idField;

    public VentanaEliminarHorario() {
        horariosController = new HorariosController();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Eliminar Horario");
        setSize(300, 150);
        // Cambiar de JFrame.EXIT_ON_CLOSE a JFrame.DISPOSE_ON_CLOSE
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel de entrada
        JPanel panel = new JPanel(new GridLayout(2, 2));

        JLabel idLabel = new JLabel("ID del horario:");
        idField = new JTextField();

        JButton deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteHorario();
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(new JLabel()); // Empty cell
        panel.add(deleteButton);

        add(panel);
    }

    private void deleteHorario() {
        String idText = idField.getText();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El ID no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            boolean success = horariosController.EliminarHorario(id);
            if (success) {
                JOptionPane.showMessageDialog(this, "Horario eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                idField.setText(""); // Clear the field after deleting
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

}
