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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ProyectoCinePersistencia.entities.Boleto;
import ProyectoCinePersistencia.entities.Funcion;
import ProyectoCinePersistencia.entities.Pelicula;
import ProyectoCinePersistencia.entities.Venta;
import ProyectoCinePresentacion.controllers.BoletoController;
import ProyectoCinePresentacion.controllers.FuncionController;
import ProyectoCinePresentacion.controllers.PeliculaController;
import ProyectoCinePresentacion.controllers.UsuarioController;
import ProyectoCinePresentacion.controllers.VentaController;

public class VentanaCrearVenta extends JFrame {

    private JComboBox<String> peliculasComboBox;
    private JComboBox<String> horariosComboBox;
    private JComboBox<String> tipoBoletoComboBox;
    private JTextField cantidadBoletosField;
    private JTextField idUsuarioField;
    private JLabel boletosDisponiblesLabel;
    private JLabel totalLabel;
    private List<Pelicula> peliculas;
    private List<Funcion> funciones;
    private List<Boleto> boletos;
    private PeliculaController peliculaController;
    private FuncionController funcionController;
    private BoletoController boletoController;
    private VentaController ventaController;
    private UsuarioController usuarioController;

    public VentanaCrearVenta() {
        this.peliculaController = new PeliculaController();
        this.funcionController = new FuncionController();
        this.boletoController = new BoletoController();
        this.ventaController = new VentaController();
        this.usuarioController = new UsuarioController();
        this.peliculas = peliculaController.listarPeliculas();
        this.boletos = boletoController.listarBoletos();
        initComponents();
    }

    private void initComponents() {
        setTitle("Crear Venta");
        setSize(400, 350);
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

        // ID Usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("ID Usuario:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        idUsuarioField = new JTextField(10);
        panel.add(idUsuarioField, gbc);

        // Película
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Seleccionar Película:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        String[] titulosPeliculas = peliculas.stream().map(Pelicula::getTitulo).toArray(String[]::new);
        peliculasComboBox = new JComboBox<>(titulosPeliculas);
        panel.add(peliculasComboBox, gbc);

        // Horario
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Seleccionar Horario:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        horariosComboBox = new JComboBox<>();
        panel.add(horariosComboBox, gbc);

        // Tipo de Boleto
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Tipo de Boleto:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        String[] tiposBoletos = boletos.stream().map(Boleto::getNombre).toArray(String[]::new);
        tipoBoletoComboBox = new JComboBox<>(tiposBoletos);
        panel.add(tipoBoletoComboBox, gbc);

        // Cantidad de Boletos
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Cantidad de Boletos:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        cantidadBoletosField = new JTextField(5);
        panel.add(cantidadBoletosField, gbc);

        // Boletos Disponibles
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Boletos Disponibles:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        boletosDisponiblesLabel = new JLabel();
        panel.add(boletosDisponiblesLabel, gbc);

        // Total
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Total:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        totalLabel = new JLabel();
        panel.add(totalLabel, gbc);

        // Botón Aceptar
        JButton aceptarButton = new JButton("Aceptar");
        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aceptarVenta();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 7;
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

        // Agregar listener al comboBox de tipo de boleto
        tipoBoletoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTotal();
            }
        });

        // Agregar listener al campo de cantidad de boletos
        cantidadBoletosField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarTotal();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarTotal();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarTotal();
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

    private void actualizarTotal() {
        int selectedIndexTipoBoleto = tipoBoletoComboBox.getSelectedIndex();
        String cantidadBoletosText = cantidadBoletosField.getText();

        if (selectedIndexTipoBoleto >= 0 && selectedIndexTipoBoleto < boletos.size() && !cantidadBoletosText.isEmpty()) {
            try {
                int cantidadBoletos = Integer.parseInt(cantidadBoletosText);
                if (cantidadBoletos > 0) {
                    Boleto boletoSeleccionado = boletos.get(selectedIndexTipoBoleto);
                    double total = boletoController.calcularTotal(boletoSeleccionado.getIdTipoBoleto(), cantidadBoletos);
                    totalLabel.setText(String.format("%.2f", total));
                } else {
                    totalLabel.setText("0.00");
                }
            } catch (NumberFormatException e) {
                totalLabel.setText("0.00");
            }
        } else {
            totalLabel.setText("0.00");
        }
    }

    private void aceptarVenta() {
        int selectedIndexPelicula = peliculasComboBox.getSelectedIndex();
        int selectedIndexHorario = horariosComboBox.getSelectedIndex();
        int selectedIndexTipoBoleto = tipoBoletoComboBox.getSelectedIndex();
        String cantidadBoletosText = cantidadBoletosField.getText();
        String idUsuarioText = idUsuarioField.getText();

        if (selectedIndexPelicula >= 0 && selectedIndexPelicula < peliculas.size()
                && selectedIndexHorario >= 0 && selectedIndexHorario < funciones.size()
                && selectedIndexTipoBoleto >= 0 && selectedIndexTipoBoleto < boletos.size()
                && !cantidadBoletosText.isEmpty()
                && !idUsuarioText.isEmpty()) {

            try {
                int cantidadBoletos = Integer.parseInt(cantidadBoletosText);
                int idUsuario = Integer.parseInt(idUsuarioText);

                if (cantidadBoletos > 0) {
                    // Verificar usuario
                    if (usuarioController.buscarUsuario(idUsuario) == null) {
                        JOptionPane.showMessageDialog(this, "El ID de usuario no es válido.");
                        return;
                    }

                    Pelicula peliculaSeleccionada = peliculas.get(selectedIndexPelicula);
                    Funcion funcionSeleccionada = funciones.get(selectedIndexHorario);
                    Boleto boletoSeleccionado = boletos.get(selectedIndexTipoBoleto);

                    // Verificación de disponibilidad de boletos
                    int asientosDisponibles = funcionController.boletosDisponibles(funcionSeleccionada.getIdFuncion());
                    if (cantidadBoletos > asientosDisponibles) {
                        JOptionPane.showMessageDialog(this, "No hay suficientes asientos disponibles.\n"
                                + "Entradas disponibles: " + asientosDisponibles + "\n"
                                + "Por favor, intente con otra función o ingrese una cantidad menor de entradas.");
                    } else {
                        // Calcular total
                        double total = boletoController.calcularTotal(boletoSeleccionado.getIdTipoBoleto(), cantidadBoletos);
                        totalLabel.setText(String.format("%.2f", total));

                        // Crear nueva venta
                        Venta nuevaVenta = new Venta(0, idUsuario, "Usuario", funcionSeleccionada.getIdFuncion(), peliculaSeleccionada.getTitulo(), funcionSeleccionada.getHorario().getHoraInicio(), (float) total, cantidadBoletos);
                        ventaController.crearVenta(nuevaVenta);
                        JOptionPane.showMessageDialog(this, "Venta creada exitosamente:\n"
                                + "Película: " + peliculaSeleccionada.getTitulo() + "\n"
                                + "Horario: " + funcionSeleccionada.getHorario().getHoraInicio() + "\n"
                                + "Tipo de Boleto: " + boletoSeleccionado.getNombre() + "\n"
                                + "Cantidad de boletos: " + cantidadBoletos + "\n\n\n"
                                + "Total: " + total);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "La cantidad de boletos debe ser mayor a 0.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido para la cantidad de boletos y el ID del usuario.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
        }
    }

    public void mostrar() {
        setVisible(true);
    }
}
