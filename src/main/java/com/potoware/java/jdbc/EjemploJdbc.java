package com.potoware.java.jdbc;

import java.sql.SQLException;
import java.util.Date;

import com.potoware.java.jdbc.models.Categoria;
import com.potoware.java.jdbc.models.Producto;
import com.potoware.java.jdbc.service.CatalogoServicio;
import com.potoware.java.jdbc.service.Service;

public class EjemploJdbc {
public static void main(String[] args) throws SQLException {
		
	
		
		Service servicio = new CatalogoServicio();
		
			System.out.println("==================Insertar nueva categoria==================");
			Categoria categoria = new Categoria();
			categoria.setNombre("Mascotas");
			
			
			System.out.println("==============Listar==================");
			servicio.listar().forEach(System.out::println);
			
			
			System.out.println("==============Insertar Producto==================");
			Producto producto = new Producto();
			producto.setNombre("Perro Chihuahua");
			producto.setPrecio(120000);
			producto.setFechaRegistro(new Date());
			producto.setSku("7894777rtr");
			
			servicio.guardarProductoConCategoria(producto,categoria);
			
			System.out.println("Se guardo el producto: "+ producto.getId());
			servicio.listar().forEach(System.out::println);
			
		
	
}
}
