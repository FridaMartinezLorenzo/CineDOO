package ProyectoCinePresentacion.presentacion.usuario;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import ProyectoCinePersistencia.entities.Usuario;
import ProyectoCinePresentacion.controllers.UsuarioController;

public class VentanaListarUsuario extends JFrame {

    private JTable tablaUsuarios;
    private UsuarioController usuarioController;

    public VentanaListarUsuario() {
        this.usuarioController = new UsuarioController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Listado de Usuarios");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = { "ID", "Nombre", "Correo", "Rol" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tablaUsuarios = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        panel.add(scrollPane, BorderLayout.CENTER);

        tablaUsuarios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = tablaUsuarios.getSelectedRow();
                    int idUsuario = (int) tablaUsuarios.getValueAt(selectedRow, 0);
                    VentanaBuscarUsuario ventanaBuscarUsuario = new VentanaBuscarUsuario(idUsuario);
                    ventanaBuscarUsuario.mostrar();
                }
            }
        });

        cargarUsuarios(model);

        add(panel);
    }

    private void cargarUsuarios(DefaultTableModel model) {
        List<Usuario> usuarios = usuarioController.listarUsuarios();
        for (Usuario usuario : usuarios) {
            model.addRow(new Object[] { usuario.getIdUsuario(), usuario.getNombre(), usuario.getCorreo(),
                    usuario.getIdRol() });
        }
    }

    public void mostrar() {
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaListarUsuario ventana = new VentanaListarUsuario();
            ventana.mostrar();
        });
    }
}
