package ProyectoCinePresentacion.presentacion.usuario;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ProyectoCinePersistencia.entities.Rol;
import ProyectoCinePersistencia.entities.Usuario;
import ProyectoCinePresentacion.controllers.RolController;
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
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));  // Añade márgenes

        String[] columnNames = {"ID", "Nombre", "Correo", "Rol"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        tablaUsuarios = new JTable(model);

        // Centrar texto en la tabla
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablaUsuarios.getColumnCount(); i++) {
            tablaUsuarios.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

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

        // Botón Aceptar
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(e -> dispose());  // Cierra la ventana al hacer clic en Aceptar
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(aceptarButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void cargarUsuarios(DefaultTableModel model) {
        List<Usuario> usuarios = usuarioController.listarUsuarios();
        for (Usuario usuario : usuarios) {
            Rol rol = RolController.buscarRol(usuario.getIdRol());
            model.addRow(new Object[]{usuario.getIdUsuario(), usuario.getNombre(), usuario.getCorreo(), rol != null ? rol.getNombre() : "Desconocido"});
        }
    }

    public void mostrar() {
        setVisible(true);
    }

}
