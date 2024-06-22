package ProyectoCinePresentacion.presentacion.funcion;

import ProyectoCinePresentacion.controllers.FuncionController;
import ProyectoCinePresentacion.controllers.PeliculaController;
import ProyectoCinePersistencia.entities.Funcion;
import ProyectoCinePersistencia.entities.Horario;
import ProyectoCinePersistencia.entities.Pelicula;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaFuncionesPorPelicula extends JFrame {

    private FuncionController funcionController;
    private JComboBox<String> peliculaComboBox;
    private JComboBox<String> horarioComboBox;

    public VentanaFuncionesPorPelicula() {
        funcionController = new FuncionController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Funciones por Película");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Obtener lista de películas disponibles
        PeliculaController peliculaController = new PeliculaController();
        List<Pelicula> peliculas = peliculaController.listarPeliculas();
        String[] peliculaNames = peliculas.stream().map(Pelicula::getTitulo).toArray(String[]::new);
        peliculaComboBox = new JComboBox<>(peliculaNames);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Seleccionar Película:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(peliculaComboBox, gbc);

        // Lista desplegable para los horarios
        horarioComboBox = new JComboBox<>();
        horarioComboBox.setPreferredSize(new Dimension(200, 25));

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Seleccionar Horario:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(horarioComboBox, gbc);

        // Botón para mostrar funciones
        JButton mostrarButton = new JButton("Mostrar Funciones");
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarFunciones();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(mostrarButton, gbc);

        add(panel);
        pack();
    }

    private void mostrarFunciones() {
        // Obtener el índice seleccionado de la película
        int selectedIndex = peliculaComboBox.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una película", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener el ID de la película seleccionada
        PeliculaController peliculaController = new PeliculaController();
        int idPelicula = peliculaController.listarPeliculas().get(selectedIndex).getIdPelicula();

        // Obtener las funciones por película
        List<Funcion> funciones = funcionController.listarFuncionesPorPelicula(idPelicula);

        // Limpiar combobox de horarios
        horarioComboBox.removeAllItems();

        // Llenar combobox de horarios con los horarios disponibles
        for (Funcion funcion : funciones) {
            Horario horario = funcion.getHorario();
            if (horario != null) {
                horarioComboBox.addItem(horario.getHoraInicio());
            }
        }
    }

    public void mostrar() {
        setVisible(true);
    }

}
