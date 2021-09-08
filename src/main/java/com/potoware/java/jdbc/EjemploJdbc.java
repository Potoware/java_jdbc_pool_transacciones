package com.potoware.java.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.potoware.java.jdbc.models.Categoria;
import com.potoware.java.jdbc.models.Producto;
import com.potoware.java.jdbc.repositorio.ProductoRepositorio;
import com.potoware.java.jdbc.repositorio.Repositorio;
import com.potoware.java.jdbc.util.ConexionBaseDatos;

public class EjemploJdbc {
public static void main(String[] args) {
		
	
		
		try {
			
			Repositorio<Producto> repo = new ProductoRepositorio();
			System.out.println("==============Listar==================");
			repo.listar().forEach(System.out::println);
			System.out.println("==============Obtener Por ID==================");
			System.out.println(repo.porId(2L));
			System.out.println("==============Insertar Producto==================");
			Producto producto = new Producto();
			producto.setNombre("Parlantes Logitech Z1000");
			producto.setPrecio(1000000);
			producto.setFechaRegistro(new Date());
			Categoria categoria = new Categoria();
			categoria.setId(2L);
			producto.setCategoria(categoria);
			repo.guardar(producto);
			System.out.println("Se guardo el producto");
			repo.listar().forEach(System.out::println);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	
}
}
