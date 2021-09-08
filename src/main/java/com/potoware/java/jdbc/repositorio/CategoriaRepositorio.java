package com.potoware.java.jdbc.repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.DelegatingPreparedStatement;

import com.potoware.java.jdbc.models.Categoria;

public class CategoriaRepositorio implements Repositorio<Categoria> {

	private Connection conn;

	public CategoriaRepositorio(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Categoria> listar() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		try (Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT * FROM categorias")) {
			while (resultSet.next()) {
				categorias.add(crearCategoria(resultSet));
			}
		}
		return categorias;
	}

	@Override
	public Categoria porId(Long id) throws SQLException {
		Categoria categoria = null;
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categorias as c WHERE c.id=?")) {
			stmt.setLong(1, id);
			try (ResultSet resultSet = stmt.executeQuery()) {
				if (resultSet.next()) {
					categoria = crearCategoria(resultSet);
				}
			}
		}
		return categoria;
	}

	@Override
	public Categoria guardar(Categoria t) throws SQLException {

		String sql;
		if (t.getId() != null && t.getId() > 0) {
			sql = "UPDATE categorias SET nombre=? where id=?";
		} else {
			sql = "INSERT INTO categorias (nombre) VALUES(?)";
		}
		try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			stmt.setString(1, t.getNombre());

			if (t.getId() != null && t.getId() > 0) {
				stmt.setLong(2, t.getId());
			}

			stmt.executeUpdate();

			//
			if (t.getId() != null) {
				try (ResultSet resultSet = stmt.getGeneratedKeys()) {
					if (resultSet.next()) {
						t.setId(resultSet.getLong(1));
					}
				}
			}

		}

		return t;

	}

	@Override
	public void eliminar(Long id) throws SQLException {
		try(PreparedStatement stmt = conn.prepareStatement("DELETE FROM categorias WHERE id=?")){
			stmt.setLong(1, id);
			stmt.executeUpdate();
		}

	}

	private Categoria crearCategoria(ResultSet resultSet) throws SQLException {
		Categoria categoria = new Categoria();
		categoria.setId(resultSet.getLong("id"));
		categoria.setNombre(resultSet.getString("nombre"));
		return categoria;
	}

}
