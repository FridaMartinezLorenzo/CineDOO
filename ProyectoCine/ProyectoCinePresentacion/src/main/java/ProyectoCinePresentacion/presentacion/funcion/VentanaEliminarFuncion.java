package ProyectoCinePresentacion.presentacion.funcion;

import ProyectoCinePersistencia.entities.Funcion;
import ProyectoCinePresentacion.controllers.FuncionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaEliminarFuncion extends JFrame {

    private FuncionController funcionController;
    private JComboBox<String> funcionesComboBox;

    public VentanaEliminarFuncion() {
        this.funcionController = new FuncionController();
        initComponents();
        cargarFunciones();
    }

    private void initComponents() {
        setTitle("Eliminar Función");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Seleccione la Función:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        funcionesComboBox = new JComboBox<>();
        funcionesComboBox.setPreferredSize(new Dimension(200, 25));
        panel.add(funcionesComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarFuncion();
            }
        });
        panel.add(eliminarButton, gbc);

        add(panel);
        pack();
    }

    private void cargarFunciones() {
        // Obtener la lista de funciones desde el controlador
        List<Funcion> funciones = funcionController.listarFunciones();

        // Llenar el ComboBox con títulos de película y hora de inicio
        for (Funcion funcion : funciones) {
            String tituloPelicula = funcion.getPelicula().getTitulo();
            String horaInicio = funcion.getHorario().getHoraInicio();
            funcionesComboBox.addItem(tituloPelicula + " - " + horaInicio);
        }
    }

    private void eliminarFuncion() {
        int indiceSeleccionado = funcionesComboBox.getSelectedIndex();
        if (indiceSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione una función.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener la función seleccionada
        List<Funcion> funciones = funcionController.listarFunciones();
        Funcion funcionSeleccionada = funciones.get(indiceSeleccionado);

        // Eliminar la función usando el ID de la función seleccionada
        int idFuncion = funcionSeleccionada.getIdFuncion();
        funcionController.eliminarFuncion(idFuncion);
        Funcion funcionEliminada = funcionController.buscarFuncion(idFuncion);
        if (funcionEliminada == null) {
            JOptionPane.showMessageDialog(this, "Función eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Cerrar la ventana después de eliminar
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar la función.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrar() {
        setVisible(true);
    }

}
