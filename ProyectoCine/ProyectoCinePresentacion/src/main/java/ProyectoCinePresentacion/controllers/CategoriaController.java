package ProyectoCinePresentacion.controllers;

import java.util.List;

import ProyectoCinePersistencia.dao.categoria.CategoriaDAO;
import ProyectoCinePersistencia.dao.categoria.CategoriaDAOImpl;
import ProyectoCinePersistencia.entities.Categoria;
import ProyectoCinePresentacion.utils.ViewUtil;

public class CategoriaController {

    private CategoriaDAO categoriaDAO = new CategoriaDAOImpl(ViewUtil.getSqlSessionFactory());

    public Categoria buscarCategoria(int id) {
        return categoriaDAO.Buscar(id);
    }

    public List<Categoria> listarCategorias() {
        return categoriaDAO.Listar();
    }
}
