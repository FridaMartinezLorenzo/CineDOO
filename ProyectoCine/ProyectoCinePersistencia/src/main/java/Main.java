
public class Main {

    public static void main(String[] args) {
        /*
        
        try {
            FuncionDAO funcionDAO = new FuncionDAOImpl(MyBatisUtil.getSqlSessionFactory());
            
            // Crear entidades relacionadas
            Pelicula pelicula = new Pelicula();
            pelicula.setIdPelicula(1); // Asumiendo que ya existe una película con ID 1
            Horario horario = new Horario();
            horario.setIdHorario(1); // Asumiendo que ya existe un horario con ID 1
            Sala sala = new Sala();
            sala.setIdSala(1); // Asumiendo que ya existe una sala con ID 1
            
            // Crear una nueva función
            Funcion nuevaFuncion = new Funcion(0, pelicula, horario, sala);
            funcionDAO.Crear(nuevaFuncion);
            System.out.println("Función creada con ID: " + nuevaFuncion.getIdFuncion());
            
            // Buscar la función por ID
            Funcion funcion = funcionDAO.Buscar(nuevaFuncion.getIdFuncion());
            System.out.println("Función encontrada: " + funcion.getIdFuncion());
            
            // Actualizar la función
            funcion.getHorario().setIdHorario(2); // Cambiar el horario
            funcionDAO.Actualizar(funcion);
            System.out.println("Función actualizada");
            
            // Listar todas las funciones
            List<Funcion> funciones = funcionDAO.Listar();
            System.out.println("Listando todas las funciones:");
            for (Funcion f : funciones) {
                System.out.println(f.getIdFuncion());
            }
            
            // Eliminar la función
            funcionDAO.Eliminar(nuevaFuncion.getIdFuncion());
            System.out.println("Función eliminada");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
    }
}
