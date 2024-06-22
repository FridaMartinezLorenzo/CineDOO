package ProyectoCinePresentacion.presentacion.funcion;

import ProyectoCinePresentacion.controllers.FuncionController;
import ProyectoCinePresentacion.controllers.HorariosController;
import ProyectoCinePresentacion.controllers.PeliculaController;
import ProyectoCinePresentacion.controllers.SalaController;
import ProyectoCinePersistencia.entities.Funcion;
import ProyectoCinePersistencia.entities.Horario;
import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePersistencia.entities.Sala;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaCrearFuncion extends JFrame {

    private JComboBox<String> peliculaComboBox;
    private JComboBox<String> horarioComboBox;
    private JComboBox<String> salaComboBox;

    private PeliculaController peliculaController = new PeliculaController();
    private HorariosController horarioController = new HorariosController();
    private SalaController salaController = new SalaController();

    public VentanaCrearFuncion() {
        peliculaController = new PeliculaController();
        horarioController = new HorariosController();
        salaController = new SalaController();

        initComponents();
    }

    private void initComponents() {
        setTitle("Crear Función");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Combo box de Películas
        List<Pelicula> peliculas = peliculaController.listarPeliculas();
        String[] peliculaNames = peliculas.stream().map(Pelicula::getTitulo).toArray(String[]::new);
        peliculaComboBox = new JComboBox<>(peliculaNames);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Seleccionar Película:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(peliculaComboBox, gbc);

        // Combo box de Horarios
        List<Horario> horarios = horarioController.ListarHorarios();
        String[] horarioNames = horarios.stream().map(Horario::getHoraInicio).toArray(String[]::new);
        horarioComboBox = new JComboBox<>(horarioNames);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Seleccionar Horario:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(horarioComboBox, gbc);

        // Combo box de Salas
        List<Sala> salas = salaController.listarSalas();
        String[] salaNames = salas.stream().map(s -> String.valueOf(s.getIdSala())).toArray(String[]::new);
        salaComboBox = new JComboBox<>(salaNames);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Seleccionar Sala:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(salaComboBox, gbc);

        // Botón Crear Función
        JButton crearFuncionButton = new JButton("Crear Función");
        crearFuncionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearFuncion();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(crearFuncionButton, gbc);

        add(panel);
        pack();
    }

    private void crearFuncion() {
        // Obtener índices seleccionados
        int indicePelicula = peliculaComboBox.getSelectedIndex();
        int indiceHorario = horarioComboBox.getSelectedIndex();
        int indiceSala = salaComboBox.getSelectedIndex();

        // Validar selección de índices
        if (indicePelicula == -1 || indiceHorario == -1 || indiceSala == -1) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione una película, horario y sala.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener objetos seleccionados
        List<Pelicula> peliculas = peliculaController.listarPeliculas();
        List<Horario> horarios = horarioController.ListarHorarios();
        List<Sala> salas = salaController.listarSalas();

        Pelicula peliculaSeleccionada = peliculas.get(indicePelicula);
        Horario horarioSeleccionado = horarios.get(indiceHorario);
        Sala salaSeleccionada = salas.get(indiceSala);

        // Crear la función con los objetos seleccionados
        Funcion funcion = new Funcion();
        funcion.setPelicula(peliculaSeleccionada);
        funcion.setHorario(horarioSeleccionado);
        funcion.setSala(salaSeleccionada);

        // Aquí deberías tener un método en FuncionController para crear la función con estos parámetros
        FuncionController funcionController = new FuncionController();
        funcionController.crearFuncion(funcion);

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, "Función creada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        // Cerrar la ventana
        dispose();
    }

    public void mostrar() {
        setVisible(true);
    }

}
