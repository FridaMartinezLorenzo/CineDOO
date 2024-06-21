package ProyectoCinePresentacion.presentacion.usuario;

import ProyectoCinePresentacion.controllers.UsuarioController;
import ProyectoCinePersistencia.entities.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaEditarUsuario extends JFrame {

    private JTextField nombreField;
    private JTextField correoField;
    private JTextField contrasenaField;
    private JComboBox<String> rolesComboBox;
    private Usuario usuario;
    private UsuarioController usuarioController;

    public VentanaEditarUsuario(int idUsuario, List<String> roles) {
        this.usuarioController = new UsuarioController();
        this.usuario = usuarioController.buscarUsuario(idUsuario);
        initComponents(roles);
    }

    private void initComponents(List<String> roles) {
        setTitle("Editar Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        nombreField = new JTextField(usuario.getNombre(), 20);
        panel.add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Correo:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        correoField = new JTextField(usuario.getCorreo(), 20);
        panel.add(correoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        contrasenaField = new JTextField(usuario.getContrasena(), 20);
        panel.add(contrasenaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Rol:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        rolesComboBox = new JComboBox<>(roles.toArray(new String[0]));
        rolesComboBox.setSelectedItem(usuario.getIdRol());
        panel.add(rolesComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarUsuario();
            }
        });
        panel.add(guardarButton, gbc);

        add(panel);
        pack();
    }

    private void editarUsuario() {
        String nombre = nombreField.getText();
        String correo = correoField.getText();
        String contrasena = contrasenaField.getText();
        String idRol = (String) rolesComboBox.getSelectedItem();

        Usuario usuarioEditado = new Usuario(this.usuario.getIdUsuario(), nombre, correo, contrasena,
                2);
        usuarioEditado.setIdUsuario(usuario.getIdUsuario());

        boolean exito = usuarioController.actualizarUsuario(usuarioEditado);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el usuario");
        }

        dispose();
    }

    private int obtenerIdRol(String rol) {
        switch (rol) {
            case "Administrador":
                return 1;
            case "Empleado":
                return 2;
            case "Cliente":
                return 3;
            default:
                return -1;
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
