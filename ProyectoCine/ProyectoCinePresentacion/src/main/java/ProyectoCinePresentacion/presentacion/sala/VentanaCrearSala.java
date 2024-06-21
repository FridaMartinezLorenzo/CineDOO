package ProyectoCinePresentacion.presentacion.sala;

import ProyectoCinePresentacion.controllers.SalaController;
import ProyectoCinePersistencia.entities.Sala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaCrearSala extends JFrame {

    private JTextField numeroAsientosField;
    private SalaController salaController;

    public VentanaCrearSala() {
        this.salaController = new SalaController();
        initComponents();// Esto es lo que se necesita para que la ventana se muestre
    }

    private void initComponents() {
        setTitle("Crear Sala");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Número de Asientos:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        numeroAsientosField = new JTextField(20);
        panel.add(numeroAsientosField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearSala();
            }
        });
        panel.add(guardarButton, gbc);

        add(panel);
        pack();
    }

    private void crearSala() {
        int numeroAsientos = Integer.parseInt(numeroAsientosField.getText());

        Sala nuevaSala = new Sala(obtenerIdsala(), numeroAsientos);
        Sala salaCreada = salaController.crearSala(nuevaSala);

        if (salaCreada.getIdSala() != -1) {
            JOptionPane.showMessageDialog(this, "Sala creada con éxito", "Sala Creada",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear la sala", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int obtenerIdsala() {
        List<Sala> salas = salaController.listarSalas();
        return salas.size() + 1;
    }

    public void mostrar() {
        setVisible(true);
    }
}
