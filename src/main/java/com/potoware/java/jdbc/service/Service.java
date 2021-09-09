package com.potoware.java.jdbc.service;

import java.sql.SQLException;
import java.util.List;

import com.potoware.java.jdbc.models.Categoria;
import com.potoware.java.jdbc.models.Producto;

public interface Service {
	
	List<Producto> listar() throws SQLException;
	
	Producto porID (Long id) throws SQLException;
	
	Producto guardar (Producto producto) throws SQLException; 
	
	void eliminar(Long id) throws SQLException;
	
	List<Categoria> listarCategoria() throws SQLException;
	
	Categoria porIdCategoria (Long id) throws SQLException;
	
	Categoria guardarCategoria(Categoria categoria) throws SQLException;
	
	void eliminarCategoria(Long id) throws SQLException;
	
	void guardarProductoConCategoria(Producto producto, Categoria categoria)throws SQLException;
}
