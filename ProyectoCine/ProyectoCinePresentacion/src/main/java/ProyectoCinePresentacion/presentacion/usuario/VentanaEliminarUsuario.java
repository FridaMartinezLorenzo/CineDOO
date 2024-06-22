package ProyectoCinePresentacion.presentacion.usuario;

import ProyectoCinePresentacion.controllers.UsuarioController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEliminarUsuario extends JFrame {

    private JTextField idUsuarioField;
    private UsuarioController usuarioController;

    public VentanaEliminarUsuario() {
        this.usuarioController = new UsuarioController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Eliminar Usuario");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("ID del Usuario:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        idUsuarioField = new JTextField(10);
        panel.add(idUsuarioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });
        panel.add(eliminarButton, gbc);

        add(panel);
        pack();
    }

    private void eliminarUsuario() {
        int idUsuario = Integer.parseInt(idUsuarioField.getText());
        boolean exito = usuarioController.eliminarUsuario(idUsuario);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se ha podido eliminar el usuario", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
