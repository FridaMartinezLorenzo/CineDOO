package ProyectoCinePresentacion.presentacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ProyectoCinePersistencia.entities.Usuario;
import ProyectoCinePresentacion.controllers.MainController;

public class VentanaLogin extends JFrame {

    private MainController controller = new MainController();

    public VentanaLogin(MainController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        setTitle("Login");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Layout setup
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margen entre los componentes

        JLabel emailLabel = new JLabel("Usuario:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(emailLabel, gbc);

        JTextField emailField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                Usuario usuario = new Usuario();
                usuario = controller.autenticarUsuario(email, password);

                if (usuario != null) {
                    JOptionPane.showMessageDialog(VentanaLogin.this, "Login exitoso");
                    // Lógica adicional para abrir la siguiente vista

                    if (usuario.getIdRol() == 1) {
                        VentanaPrincipalAdministrador ventanaPrincipalAdministrador = new VentanaPrincipalAdministrador();
                        ventanaPrincipalAdministrador.mostrar();
                    } else if (usuario.getIdRol() == 2) {
                        VentanaPrincipalEmpleado ventanaPrincipalEmpleado = new VentanaPrincipalEmpleado();
                        ventanaPrincipalEmpleado.mostrar();
                    }
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(VentanaLogin.this, "Credenciales incorrectas");
                }
            }
        });
    }

    public void mostrar() {
        setVisible(true);
    }
}
