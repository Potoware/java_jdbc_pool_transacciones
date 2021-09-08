package com.potoware.java.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.potoware.java.jdbc.models.Categoria;
import com.potoware.java.jdbc.models.Producto;
import com.potoware.java.jdbc.repositorio.CategoriaRepositorio;
import com.potoware.java.jdbc.repositorio.ProductoRepositorio;
import com.potoware.java.jdbc.repositorio.Repositorio;
import com.potoware.java.jdbc.util.ConexionBaseDatos;

public class EjemploJdbc {
public static void main(String[] args) {
		
	
		
		try (Connection conn = ConexionBaseDatos.getConnection()){
			if(conn.getAutoCommit()) {
				conn.setAutoCommit(false);
			}
			try {
			Repositorio<Producto> repo = new ProductoRepositorio(conn);
			System.out.println("==================Insertar nueva categoria==================");
			Repositorio<Categoria> repoCat = new CategoriaRepositorio(conn);
			
			Categoria categoria = new Categoria();
			categoria.setNombre("Vehiculos");
			Categoria nuevaCategoria = repoCat.guardar(categoria);
			System.out.println("Se guardo la categoria: "+ nuevaCategoria.getId());
			
			System.out.println("==============Listar==================");
			repo.listar().forEach(System.out::println);
			System.out.println("==============Obtener Por ID==================");
			System.out.println(repo.porId(2L));
			System.out.println("==============Insertar Producto==================");
			Producto producto = new Producto();
			producto.setNombre("Mercedes Benz G500");
			producto.setPrecio(700000000);
			producto.setFechaRegistro(new Date());
			producto.setSku("7894q77rtr");
			
			producto.setCategoria(nuevaCategoria);
			repo.guardar(producto);
			System.out.println("Se guardo el producto: "+ producto.getId());
			repo.listar().forEach(System.out::println);
			conn.commit();
			}catch(SQLException e) {
				
				conn.rollback();
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
}
}
