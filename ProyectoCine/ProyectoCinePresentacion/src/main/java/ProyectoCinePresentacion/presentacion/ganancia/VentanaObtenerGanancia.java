package ProyectoCinePresentacion.presentacion.ganancia;

import ProyectoCinePresentacion.controllers.GananciaController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaObtenerGanancia extends JFrame {

    private GananciaController gananciaController;

    public VentanaObtenerGanancia() {
        gananciaController = new GananciaController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Ganancia de la Película");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con margen
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(mainPanel);

        // Panel para los campos y el botón
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        // Campo de ID de la Película
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        inputPanel.add(new JLabel("ID Película:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField idPeliculaField = new JTextField(10);
        inputPanel.add(idPeliculaField, gbc);

        // Botón para obtener la ganancia
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton obtenerGananciaButton = new JButton("Obtener Ganancia");
        inputPanel.add(obtenerGananciaButton, gbc);

        // Área de resultados
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel resultLabel = new JLabel(" ");
        inputPanel.add(resultLabel, gbc);

        // Acción del botón
        obtenerGananciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idPelicula = Integer.parseInt(idPeliculaField.getText());
                    double ganancia = gananciaController.obtenerGanancias(idPelicula);
                    resultLabel.setText("La ganancia de la película es: " + ganancia);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(VentanaObtenerGanancia.this, "Por favor ingrese un ID de película válido.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(VentanaObtenerGanancia.this, "Error al obtener la ganancia.");
                }
            }
        });
    }

    public void mostrar() {
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaObtenerGanancia ventana = new VentanaObtenerGanancia();
                ventana.mostrar();
            }
        });
    }
}
