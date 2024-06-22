package ProyectoCinePresentacion.presentacion.sala;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ProyectoCinePersistencia.entities.Sala;
import ProyectoCinePresentacion.controllers.SalaController;

public class VentanaSeleccionarSala extends JFrame {

    private JComboBox<String> salasComboBox;
    private List<Sala> salas;
    private String caso;

    public VentanaSeleccionarSala(String caso) {
        this.caso = caso;
        SalaController salaController = new SalaController();
        this.salas = salaController.listarSalas();
        initComponents();
    }

    private void initComponents() {
        setTitle("Seleccionar Sala");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Seleccionar Sala:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        String[] titulosSalas = salas.stream().map(Sala::getIdSala).map(String::valueOf).toArray(String[]::new);
        salasComboBox = new JComboBox<>(titulosSalas);
        panel.add(salasComboBox, gbc);

        JButton seleccionarButton = new JButton("Seleccionar");
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = salasComboBox.getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < salas.size()) {
                    Sala salaSeleccionada = salas.get(selectedIndex);
                    if (caso.equals("Actualizar")) {

                        VentanaActualizarSala ventanaActualizarSala = new VentanaActualizarSala(salaSeleccionada.getIdSala());
                        ventanaActualizarSala.mostrar();
                    } else if (caso.equals("buscar")) {
                        VentanaBuscarSala ventanaBuscarSala = new VentanaBuscarSala(salaSeleccionada.getIdSala());
                        ventanaBuscarSala.mostrar();
                    }
                    dispose();
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(seleccionarButton, gbc);

        add(panel);
        pack();

    }

    public void mostrar() {
        setVisible(true);
    }
}
