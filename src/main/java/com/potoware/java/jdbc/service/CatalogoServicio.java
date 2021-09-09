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
		}
		return null;
	}

	@Override
	public Producto porID(Long id) throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			productoRepositorio.setConn(conn);
		}
		return null;
	}

	@Override
	public Producto guardar(Producto producto) throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			productoRepositorio.setConn(conn);
		}
		return null;
	}

	@Override
	public void eliminar(Long id) throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			productoRepositorio.setConn(conn);
		}
		
	}

	@Override
	public List<Categoria> listarCategoria() throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			categoriaRepositorio.setConn(conn);
		}
		return null;
	}

	@Override
	public Categoria porIdCategoria(Long id) throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			categoriaRepositorio.setConn(conn);
		}
		return null;
	}

	@Override
	public Categoria guardarCategoria(Categoria categoria) throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			categoriaRepositorio.setConn(conn);
		}
		return null;
	}

	@Override
	public void eliminarCategoria(Long id) throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			categoriaRepositorio.setConn(conn);
		}
		
	}

	@Override
	public void guardarProductoConCategoria(Producto producto, Categoria categoria) throws SQLException {
		try(Connection conn = ConexionBaseDatos.getConnection()){
			categoriaRepositorio.setConn(conn);
			productoRepositorio.setConn(conn);
		}
		
	}

}
