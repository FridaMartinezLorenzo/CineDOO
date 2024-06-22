package ProyectoCinePresentacion.presentacion.horario;

import ProyectoCinePresentacion.controllers.HorariosController;
import ProyectoCinePersistencia.entities.Horario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaBuscarHorario extends JFrame {

    private HorariosController horariosController;
    private JComboBox<String> horariosComboBox;
    private JLabel resultadoLabel;
    private List<Horario> horariosList;

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

        // Panel de entrada con borde
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel horariosLabel = new JLabel("Seleccione el horario:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(horariosLabel, gbc);

        horariosList = horariosController.ListarHorarios();
        String[] horariosArray = horariosList.stream().map(Horario::getHoraInicio).toArray(String[]::new);
        horariosComboBox = new JComboBox<>(horariosArray);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(horariosComboBox, gbc);

        JButton searchButton = new JButton("Buscar");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarHorario();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(searchButton, gbc);

        resultadoLabel = new JLabel("Resultado: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(resultadoLabel, gbc);

        add(panel);
        pack();
    }

    private void buscarHorario() {
        int selectedIndex = horariosComboBox.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un horario de la lista", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Horario selectedHorario = horariosList.get(selectedIndex);
        int id = selectedHorario.getIdHorario();
        Horario horario = horariosController.BuscarHorario(id);
        if (horario != null) {
            resultadoLabel.setText("Id: " + horario.getIdHorario() + " Horario: " + horario.getHoraInicio());
        } else {
            resultadoLabel.setText("Resultado: No se encontró el horario.");
        }
    }

    public void mostrar() {
        setVisible(true);
    }

}
