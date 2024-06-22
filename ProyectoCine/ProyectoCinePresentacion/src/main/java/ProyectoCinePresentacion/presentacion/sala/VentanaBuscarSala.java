package ProyectoCinePresentacion.presentacion.sala;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ProyectoCinePersistencia.entities.Sala;
import ProyectoCinePresentacion.controllers.SalaController;

public class VentanaBuscarSala extends JFrame {

    private Sala sala;

    public VentanaBuscarSala(int idSala) {
        SalaController salaController = new SalaController();
        this.sala = salaController.buscarSala(idSala);
        initComponents();
    }

    private void initComponents() {
        setTitle("Detalles de la Sala");
        setSize(800, 800);
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
        panel.add(new JLabel(String.valueOf(sala.getNumAsiento())), gbc);

        add(panel);
        pack();
    }

    public void mostrar() {
        setVisible(true);
    }

}
