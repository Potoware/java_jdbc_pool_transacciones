package com.potoware.java.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.potoware.java.jdbc.models.Categoria;
import com.potoware.java.jdbc.models.Producto;
import com.potoware.java.jdbc.repositorio.CategoriaRepositorio;
import com.potoware.java.jdbc.repositorio.ProductoRepositorio;
import com.potoware.java.jdbc.repositorio.Repositorio;
import com.potoware.java.jdbc.util.ConexionBaseDatos;


public class CatalogoServicio implements Service{
	
	private Repositorio<Producto> productoRepositorio;
	private Repositorio<Categoria> categoriaRepositorio;
	
	
	public CatalogoServicio() {
		this.productoRepositorio = new ProductoRepositorio();
		this.categoriaRepositorio = new CategoriaRepositorio();
	}

	@Override
	public List<Producto> listar() throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			productoRepositorio.setConn(conn);
			return productoRepositorio.listar();
		}
		
	}

	@Override
	public Producto porID(Long id) throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			productoRepositorio.setConn(conn);
		}
		return productoRepositorio.porId(id);
	}

	@Override
	public Producto guardar(Producto producto) throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			productoRepositorio.setConn(conn);
			
			if(conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			Producto nuevoProducto = null;
			try {
				
				nuevoProducto = productoRepositorio.guardar(producto);
				conn.commit();
			}
			catch(SQLException e){
				conn.rollback();
				e.printStackTrace();
			}
			return nuevoProducto;
		}
		
	}

	@Override
	public void eliminar(Long id) throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			productoRepositorio.setConn(conn);
			
			if(conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			try {
				
				productoRepositorio.eliminar(id);
				conn.commit();
			}
			catch(SQLException e){
				conn.rollback();
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<Categoria> listarCategoria() throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			categoriaRepositorio.setConn(conn);
			
			return categoriaRepositorio.listar();
		}
	}

	@Override
	public Categoria porIdCategoria(Long id) throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			categoriaRepositorio.setConn(conn);
			
			return categoriaRepositorio.porId(id);
		}
		
	}

	@Override
	public Categoria guardarCategoria(Categoria categoria) throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			categoriaRepositorio.setConn(conn);
			
			if(conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			
			Categoria nuevaCategoria = null;
			try {
				
				nuevaCategoria = categoriaRepositorio.guardar(categoria);
				conn.commit();
			}
			catch(SQLException e){
				conn.rollback();
				e.printStackTrace();
			}
			return nuevaCategoria;
		}
		
	}

	@Override
	public void eliminarCategoria(Long id) throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			categoriaRepositorio.setConn(conn);
			
			if(conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			try {
				
				categoriaRepositorio.eliminar(id);
				conn.commit();
			}
			catch(SQLException e){
				conn.rollback();
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void guardarProductoConCategoria(Producto producto, Categoria categoria) throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			categoriaRepositorio.setConn(conn);
			productoRepositorio.setConn(conn);
			
			if(conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			try {
				Categoria nuevaCategoria = categoriaRepositorio.guardar(categoria);
				producto.setCategoria(nuevaCategoria);
				productoRepositorio.guardar(producto);
				
				conn.commit();
			}
			catch(SQLException e){
				conn.rollback();
				e.printStackTrace();
			}
			
		}
		
	}

}
