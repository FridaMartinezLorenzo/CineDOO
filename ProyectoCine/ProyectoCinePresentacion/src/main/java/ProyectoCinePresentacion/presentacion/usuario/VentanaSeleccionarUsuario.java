package ProyectoCinePresentacion.presentacion.usuario;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ProyectoCinePresentacion.controllers.RolController;
import ProyectoCinePresentacion.controllers.UsuarioController;
import ProyectoCinePersistencia.entities.Rol;
import ProyectoCinePersistencia.entities.Usuario;

public class VentanaSeleccionarUsuario extends JFrame {

    private JComboBox<String> usuariosComboBox;
    private List<Usuario> usuarios;
    private String caso;

    public VentanaSeleccionarUsuario(String caso) {
        this.caso = caso;
        UsuarioController usuarioController = new UsuarioController();
        this.usuarios = usuarioController.listarUsuarios();
        initComponents();
    }

    private void initComponents() {
        setTitle("Seleccionar Usuario");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Seleccionar Usuario:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        String[] nombresUsuarios = usuarios.stream().map(Usuario::getNombre).toArray(String[]::new);
        usuariosComboBox = new JComboBox<>(nombresUsuarios);
        panel.add(usuariosComboBox, gbc);

        JButton seleccionarButton = new JButton("Seleccionar");
        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = usuariosComboBox.getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < usuarios.size()) {
                    Usuario usuarioSeleccionado = usuarios.get(selectedIndex);
                    if (caso.equals("Actualizar")) {
                        RolController rolController = new RolController();
                        List<String> nombresRoles = rolController.listarRoles().stream()
                                .map(Rol::getNombre)
                                .collect(Collectors.toList());
                        VentanaActualizarUsuario ventanaActualizarUsuario = new VentanaActualizarUsuario(
                                usuarioSeleccionado.getIdUsuario(), nombresRoles);
                        ventanaActualizarUsuario.mostrar();
                    } else if (caso.equals("buscar")) {
                        VentanaBuscarUsuario ventanaBuscarUsuario = new VentanaBuscarUsuario(
                                usuarioSeleccionado.getIdUsuario());
                        ventanaBuscarUsuario.mostrar();
                    }
                    dispose();
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(seleccionarButton, gbc);

        add(panel);
        pack();
    }

    public void mostrar() {
        setVisible(true);
    }
}
