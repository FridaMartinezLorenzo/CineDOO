package ProyectoCinePresentacion.presentacion.horario;

import ProyectoCinePresentacion.controllers.HorariosController;
import ProyectoCinePersistencia.entities.Horario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaBuscarHorario extends JFrame {
    private HorariosController horariosController;
    private JTextField idField;
    private JLabel resultadoLabel;

    public VentanaBuscarHorario() {
        horariosController = new HorariosController();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Buscar Horario");
        setSize(500, 200);
        // Cambiar de JFrame.EXIT_ON_CLOSE a JFrame.DISPOSE_ON_CLOSE
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel de entrada
        JPanel panel = new JPanel(new GridLayout(3, 2));
        
        JLabel idLabel = new JLabel("ID del horario:");
        idField = new JTextField();

        JButton searchButton = new JButton("Buscar");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarHorario();
            }
        });

        resultadoLabel = new JLabel("Resultado: ");

        panel.add(idLabel);
        panel.add(idField);
        panel.add(new JLabel()); // Empty cell
        panel.add(searchButton);
        panel.add(new JLabel()); // Empty cell
        panel.add(resultadoLabel);

        add(panel);
    }

    private void buscarHorario() {
        String idText = idField.getText();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El ID no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            Horario horario = horariosController.BuscarHorario(id);
            if (horario != null) {
                resultadoLabel.setText("Id: " + horario.getIdHorario() + " Horario: " + horario.getHoraInicio());
            } else {
                resultadoLabel.setText("Resultado: No se encontró el horario.");
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
            new VentanaBuscarHorario().setVisible(true);
        });
    }
}
