package ProyectoCinePresentacion.presentacion.usuario;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ProyectoCinePersistencia.entities.Usuario;
import ProyectoCinePresentacion.controllers.UsuarioController;

public class VentanaBuscarUsuario extends JFrame {

    private Usuario usuario;

    public VentanaBuscarUsuario(int idUsuario) {
        UsuarioController usuarioController = new UsuarioController();
        this.usuario = usuarioController.buscarUsuario(idUsuario);
        initComponents();
    }

    private void initComponents() {
        setTitle("Detalles del Usuario");
        setSize(400, 400);
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
        panel.add(new JLabel(usuario.getNombre()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Correo:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(new JLabel(usuario.getCorreo()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Rol:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(new JLabel(String.valueOf(usuario.getIdRol())), gbc);

        add(panel);
    }

    public void mostrar() {
        setVisible(true);
    }

}
