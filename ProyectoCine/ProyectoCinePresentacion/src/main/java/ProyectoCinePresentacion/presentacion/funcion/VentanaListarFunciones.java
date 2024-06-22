package ProyectoCinePresentacion.presentacion.funcion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ProyectoCinePersistencia.entities.Funcion;
import ProyectoCinePresentacion.controllers.FuncionController;

public class VentanaListarFunciones extends JFrame {

    private FuncionController funcionController;
    private JTable funcionesTable;

    public VentanaListarFunciones() {
        this.funcionController = new FuncionController();
        initComponents();
        listarFunciones();
    }

    private void initComponents() {
        setTitle("Listar Funciones");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añade un borde vacío al panel

        // Configurar tabla de funciones
        funcionesTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(funcionesTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Elimina el borde del JScrollPane
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    private void listarFunciones() {
        // Obtener la lista de funciones desde el controlador
        List<Funcion> funciones = funcionController.listarFunciones();

        // Crear modelo de tabla con columnas "Película" y "Horario"
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Película");
        model.addColumn("Horario");

        // Llenar la tabla con datos de funciones
        for (Funcion funcion : funciones) {
            String tituloPelicula = funcion.getPelicula().getTitulo();
            String horarioInicio = funcion.getHorario().getHoraInicio();
            model.addRow(new Object[]{tituloPelicula, horarioInicio});
        }

        // Establecer el modelo en la tabla
        funcionesTable.setModel(model);

        // Centrar la tabla en la ventana
        centrarTabla();
    }

    private void centrarTabla() {
        // Obtener el tamaño preferido del JScrollPane
        Dimension scrollPaneSize = funcionesTable.getPreferredSize();

        // Obtener el tamaño de la ventana
        Dimension frameSize = getSize();

        // Calcular las coordenadas para centrar el JScrollPane
        int x = (frameSize.width - scrollPaneSize.width) / 2;
        int y = (frameSize.height - scrollPaneSize.height) / 2;

        // Establecer las nuevas coordenadas del JScrollPane
        JScrollPane scrollPane = (JScrollPane) funcionesTable.getParent().getParent();
        scrollPane.getViewport().setViewPosition(new Point(x, y));
    }

    public void mostrar() {
        setVisible(true);
    }

}
