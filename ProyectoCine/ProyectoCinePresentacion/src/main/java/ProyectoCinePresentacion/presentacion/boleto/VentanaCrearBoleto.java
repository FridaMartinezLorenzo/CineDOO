package ProyectoCinePresentacion.presentacion.boleto;

import ProyectoCinePersistencia.entities.Boleto;
import ProyectoCinePresentacion.controllers.BoletoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCrearBoleto extends JFrame {

    private JTextField nombreField;
    private JTextField precioField;
    private BoletoController boletoController;

    public VentanaCrearBoleto() {
        boletoController = new BoletoController();
        setTitle("Crear Boleto");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        nombreField = new JTextField(20);
        panel.add(nombreField, gbc);

        // Precio
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Precio:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        precioField = new JTextField(20);
        panel.add(precioField, gbc);

        // Botón Aceptar
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearBoleto();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(aceptarButton, gbc);

        add(panel);
    }

    private void crearBoleto() {
        String nombre = nombreField.getText();
        String precioText = precioField.getText();

        if (!nombre.isEmpty() && !precioText.isEmpty()) {
            try {
                double precio = Double.parseDouble(precioText);
                Boleto boleto = new Boleto();
                boleto.setNombre(nombre);
                boleto.setPrecio(precio);

                boletoController.agregarBoleto(boleto);
                JOptionPane.showMessageDialog(this, "Boleto creado exitosamente.");
                dispose();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un precio válido.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
        }
    }

    public void mostrar() {
        setVisible(true);
    }

}
