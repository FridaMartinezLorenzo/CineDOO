package ProyectoCinePresentacion.presentacion.usuario;

import ProyectoCinePresentacion.controllers.UsuarioController;
import ProyectoCinePersistencia.entities.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaCrearUsuario extends JFrame {

    private JTextField nombreField;
    private JTextField correoField;
    private JTextField contrasenaField;
    private JComboBox<String> rolesComboBox;
    private UsuarioController usuarioController;

    public VentanaCrearUsuario(List<String> roles) {
        this.usuarioController = new UsuarioController();
        initComponents(roles);
    }

    private void initComponents(List<String> roles) {
        setTitle("Crear Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        nombreField = new JTextField(20);
        panel.add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Correo:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        correoField = new JTextField(20);
        panel.add(correoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        contrasenaField = new JTextField(20);
        panel.add(contrasenaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Rol:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        rolesComboBox = new JComboBox<>(roles.toArray(new String[0]));
        panel.add(rolesComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton crearButton = new JButton("Crear");
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearUsuario();
            }
        });
        panel.add(crearButton, gbc);
        add(panel);
        pack();
    }

    private void crearUsuario() {
        String nombre = nombreField.getText();
        String correo = correoField.getText();
        String contrasena = contrasenaField.getText();
        String rol = (String) rolesComboBox.getSelectedItem();

        // Validar campos vacíos
        if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || rol == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario nuevoUsuario = new Usuario(obtenerIdUsuario(), nombre, correo, contrasena, obtenerIdRol(rol));
        Usuario usuarioCreado = usuarioController.crearUsuario(nuevoUsuario);

        if (usuarioCreado.getIdUsuario() != -1) {
            JOptionPane.showMessageDialog(this, "Usuario creado correctamente");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al crear el usuario");
        }
    }

    private int obtenerIdUsuario() {
        List<Usuario> usuarios = usuarioController.listarUsuarios();
        return usuarios.size() + 1;
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
