package ProyectoCinePresentacion.presentacion.venta;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ProyectoCinePersistencia.entities.Funcion;
import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePresentacion.controllers.FuncionController;
import ProyectoCinePresentacion.controllers.PeliculaController;

public class VentanaCrearVenta extends JFrame {

    private JComboBox<String> peliculasComboBox;
    private JComboBox<String> horariosComboBox;
    private JTextField cantidadBoletosField;
    private JLabel boletosDisponiblesLabel;
    private List<Pelicula> peliculas;
    private List<Funcion> funciones;
    private PeliculaController peliculaController;
    private FuncionController funcionController;

    public VentanaCrearVenta() {
        this.peliculaController = new PeliculaController();
        this.funcionController = new FuncionController();
        this.peliculas = peliculaController.listarPeliculas();
        initComponents();
    }

    private void initComponents() {
        setTitle("Crear Venta");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Aquí puedes agregar cualquier acción adicional que quieras al cerrar la ventana
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Película
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Seleccionar Película:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        String[] titulosPeliculas = peliculas.stream().map(Pelicula::getTitulo).toArray(String[]::new);
        peliculasComboBox = new JComboBox<>(titulosPeliculas);
        panel.add(peliculasComboBox, gbc);

        // Horario
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Seleccionar Horario:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        horariosComboBox = new JComboBox<>();
        panel.add(horariosComboBox, gbc);

        // Cantidad de Boletos
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Cantidad de Boletos:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        cantidadBoletosField = new JTextField(5);
        panel.add(cantidadBoletosField, gbc);

        // Boletos Disponibles
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Boletos Disponibles:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        boletosDisponiblesLabel = new JLabel();
        panel.add(boletosDisponiblesLabel, gbc);

        // Botón Aceptar
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aceptarVenta();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(aceptarButton, gbc);

        add(panel);
        pack();

        // Agregar listener al comboBox de películas
        peliculasComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarHorarios();
            }
        });

        // Agregar listener al comboBox de horarios
        horariosComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarBoletosDisponibles();
            }
        });

        // Actualizar horarios al inicializar
        actualizarHorarios();
    }

    private void actualizarHorarios() {
        int selectedIndex = peliculasComboBox.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < peliculas.size()) {
            Pelicula peliculaSeleccionada = peliculas.get(selectedIndex);
            this.funciones = funcionController.listarFuncionesPorPelicula(peliculaSeleccionada.getIdPelicula());
            String[] horarios = funciones.stream()
                    .map(funcion -> funcion.getHorario().getHoraInicio()) // Asumiendo que Horario tiene un método getDescripcion
                    .toArray(String[]::new);
            horariosComboBox.setModel(new DefaultComboBoxModel<>(horarios));
            actualizarBoletosDisponibles(); // Actualizar boletos disponibles al cambiar de película
        }
    }

    private void actualizarBoletosDisponibles() {
        int selectedIndexHorario = horariosComboBox.getSelectedIndex();
        if (selectedIndexHorario >= 0 && selectedIndexHorario < funciones.size()) {
            Funcion funcionSeleccionada = funciones.get(selectedIndexHorario);
            int boletosDisponibles = funcionController.boletosDisponibles(funcionSeleccionada.getIdFuncion());
            boletosDisponiblesLabel.setText(String.valueOf(boletosDisponibles));
        } else {
            boletosDisponiblesLabel.setText("");
        }
    }

    private void aceptarVenta() {
        int selectedIndexPelicula = peliculasComboBox.getSelectedIndex();
        int selectedIndexHorario = horariosComboBox.getSelectedIndex();
        String cantidadBoletosText = cantidadBoletosField.getText();

        if (selectedIndexPelicula >= 0 && selectedIndexPelicula < peliculas.size()
                && selectedIndexHorario >= 0 && selectedIndexHorario < funciones.size()
                && !cantidadBoletosText.isEmpty()) {

            try {
                int cantidadBoletos = Integer.parseInt(cantidadBoletosText);
                if (cantidadBoletos > 0) {
                    Pelicula peliculaSeleccionada = peliculas.get(selectedIndexPelicula);
                    Funcion funcionSeleccionada = funciones.get(selectedIndexHorario);

                    // Verificación de disponibilidad de boletos
                    int asientosDisponibles = funcionController.boletosDisponibles(funcionSeleccionada.getIdFuncion());
                    if (cantidadBoletos > asientosDisponibles) {
                        JOptionPane.showMessageDialog(this, "No hay suficientes asientos disponibles.\n"
                                + "Entradas disponibles: " + asientosDisponibles + "\n"
                                + "Por favor, intente con otra función o ingrese una cantidad menor de entradas.");
                    } else {
                        // Aquí puedes manejar la creación de la venta con la información obtenida
                        JOptionPane.showMessageDialog(this, "Venta creada exitosamente:\n"
                                + "Película: " + peliculaSeleccionada.getTitulo() + "\n"
                                + "Horario: " + funcionSeleccionada.getHorario().getHoraInicio() + "\n"
                                + "Cantidad de boletos: " + cantidadBoletos + "\n"
                                + "Entradas disponibles: " + asientosDisponibles);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "La cantidad de boletos debe ser mayor a 0.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido para la cantidad de boletos.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
