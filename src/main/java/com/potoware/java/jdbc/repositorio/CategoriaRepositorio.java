package com.potoware.java.jdbc.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.DelegatingPreparedStatement;

import com.potoware.java.jdbc.models.Categoria;

public class CategoriaRepositorio implements Repositorio{
	

	private Connection conn;

	public CategoriaRepositorio(Connection conn) {
		this.conn = conn;
	}


	@Override
	public List<Categoria> listar() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		try(Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT * FROM categorias")){
			while(resultSet.next()) {
				categorias.add(crearCategoria(resultSet));
			}
		}
		return categorias;
	}


	

	@Override
	public Categoria porId(Long id) throws SQLException {
		Categoria categoria = null;
		try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categorias as c WHERE c.id=?")){
			stmt.setLong(1, id);
			try(ResultSet resultSet = stmt.executeQuery()){
				if(resultSet.next()) {
					categoria = crearCategoria(resultSet);
				}
			}
		}
		return categoria;
	}

	@Override
	public Object guardar(Object t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(Long id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	private Categoria crearCategoria(ResultSet resultSet) throws SQLException {
		Categoria categoria =new Categoria();
		categoria.setId(resultSet.getLong("id"));
		categoria.setNombre(resultSet.getString("nombre"));
		return categoria;
	}

}
