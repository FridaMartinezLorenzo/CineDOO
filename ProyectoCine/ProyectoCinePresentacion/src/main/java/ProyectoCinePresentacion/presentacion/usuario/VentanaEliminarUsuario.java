package ProyectoCinePresentacion.presentacion.usuario;

import ProyectoCinePresentacion.controllers.UsuarioController;
import ProyectoCinePersistencia.entities.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaEliminarUsuario extends JFrame {

    private UsuarioController usuarioController;
    private JComboBox<String> usuariosComboBox;
    private List<Usuario> usuariosList;

    public VentanaEliminarUsuario() {
        this.usuarioController = new UsuarioController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Eliminar Usuario");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usuariosLabel = new JLabel("Seleccione el usuario a eliminar:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(usuariosLabel, gbc);

        usuariosList = usuarioController.listarUsuarios();
        String[] usuariosArray = usuariosList.stream().map(Usuario::getNombre).toArray(String[]::new);
        usuariosComboBox = new JComboBox<>(usuariosArray);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(usuariosComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5); // Margin before the button
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
        int selectedIndex = usuariosComboBox.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario de la lista", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario selectedUsuario = usuariosList.get(selectedIndex);
        int idUsuario = selectedUsuario.getIdUsuario();
        boolean exito = usuarioController.eliminarUsuario(idUsuario);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            usuariosList = usuarioController.listarUsuarios(); // Refresh the list
            String[] usuariosArray = usuariosList.stream().map(Usuario::getNombre).toArray(String[]::new);
            usuariosComboBox.setModel(new DefaultComboBoxModel<>(usuariosArray)); // Update the combobox model
        } else {
            JOptionPane.showMessageDialog(this, "No se ha podido eliminar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
