package com.potoware.java.jdbc.repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.potoware.java.jdbc.models.Categoria;
import com.potoware.java.jdbc.models.Producto;

public class ProductoRepositorio implements Repositorio<Producto>{
	
	private Connection conn;

	

	public ProductoRepositorio() {
		
	}


	public ProductoRepositorio(Connection conn) {
		this.conn = conn;
	}

	

	public void setConn(Connection conn) {
		this.conn = conn;
	}


	@Override
	public List<Producto> listar() throws SQLException  {
		List<Producto> productos= new ArrayList<>();
		
		try (Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT p.*,c.nombre as categoria FROM PRODUCTOS as p inner join categorias as c ON (p.categoria_id = c.id);");){
			
			while(resultSet.next()) {
			
				Producto p = crearProducto(resultSet);
				productos.add(p);
			}
			
		} 
		
		return productos;
	}
	
	
	@Override
	public Producto porId(Long id) throws SQLException {
		Producto producto = null;
		
		try(PreparedStatement stmt = conn.prepareStatement("SELECT p.*,c.nombre as categoria FROM PRODUCTOS as p inner join categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?");)
		{
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				producto = crearProducto(rs);
			}
			rs.close();
		} 
		
		return producto;
	}
	@Override
	public Producto guardar(Producto t) throws SQLException {
		
		String sql;
		if (t.getId() != null && t.getId()>0) {
			sql = "UPDATE productos SET nombre=?,precio=?,categoria_id=?,sku=? where id=?";
		}
		else {
			sql = "INSERT INTO productos (nombre,precio,categoria_id,sku, fecha_registro) VALUES(?,?,?,?,?)";
		}
		try(PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			
			stmt.setString(1,t.getNombre());
			stmt.setLong(2,t.getPrecio());
			stmt.setLong(3, t.getCategoria().getId());
			stmt.setString(4,t.getSku());
			
			if (t.getId() != null && t.getId()>0) {
				stmt.setLong(5, t.getId());
			}
		else {
			stmt.setDate(5, new Date(t.getFechaRegistro().getTime()));
		}
			stmt.executeUpdate();
			
			//
			if (t.getId() != null) {
				try(ResultSet resultSet = stmt.getGeneratedKeys()){
					if(resultSet.next()) {
						t.setId(resultSet.getLong(1));
					}
				}
			}
			else {}
			//
			
		} 
		
		return t;
	}
	@Override
	public void eliminar(Long id) throws SQLException {
		
		try(PreparedStatement stmt = conn.prepareStatement("DELETE FROM productos WHERE id=?")){
			stmt.setLong(1, id);
			stmt.executeUpdate();
		} 
		
	}
	
	private Producto crearProducto(ResultSet resultSet) throws SQLException {
		Producto p = new Producto();
		Categoria c = new Categoria();
		p.setId(resultSet.getLong("id"));
		p.setNombre(resultSet.getString("nombre"));
		p.setSku(resultSet.getString("sku"));
		
		p.setPrecio(resultSet.getInt("precio"));
		p.setFechaRegistro(resultSet.getDate("fecha_registro"));
		c.setId(resultSet.getLong("categoria_id"));
		c.setNombre(resultSet.getString("categoria"));
		p.setCategoria(c);
		return p;
	}




}
