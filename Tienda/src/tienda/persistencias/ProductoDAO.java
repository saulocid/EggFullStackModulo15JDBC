package tienda.persistencias;

import java.util.ArrayList;
import tienda.entidades.Producto;

public class ProductoDAO extends DAO {

    public ArrayList<Producto> AgregarProductos() throws Exception {
        try {
            String sql = "SELECT * FROM producto;";
            consultarBase(sql);

            ArrayList<Producto> productos = new ArrayList();
            while (resultado.next()) {
                Producto p = new Producto();
                p.setCodigo(resultado.getInt(1));
                p.setNombre(resultado.getString(2));
                p.setPrecio(resultado.getDouble(3));
                p.setCodFab(resultado.getInt(4));
                productos.add(p);
            }
            return productos;
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    // 1. Listar nombres
    public void mostrarNombre() throws Exception {
        try {
            String sql = "SELECT nombre FROM producto;";
            consultarBase(sql);
            while (resultado.next()) {
                System.out.println(resultado.getString(1));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    // 2. Listar nombres y precio
    public void mostrarNombrePrecio() throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM producto;";
            consultarBase(sql);
            while (resultado.next()) {
                System.out.println("Nombre: " + resultado.getString(1) + ", Precio: " + resultado.getDouble(2));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    // 3. Listar nombres cuando el precio esté entre 120 y 202
    public void mostrarNombrePorPrecio() throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM producto WHERE precio BETWEEN 120 AND 202;";
            consultarBase(sql);
            while (resultado.next()) {
                System.out.println("Nombre: " + resultado.getString(1) + ", Precio: " + resultado.getDouble(2));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    // 4. Listar portátiles
    public void mostrarNombrePortatil() throws Exception {
        try {
            String sql = "SELECT nombre FROM producto WHERE nombre like '%portatil%';";
            consultarBase(sql);
            while (resultado.next()) {
                System.out.println(resultado.getString(1));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    // 5. Listar nombre y precio del producto más barato
    public void mostrarNombrePrecioMin() throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM producto ORDER BY precio ASC LIMIT 1;";
            consultarBase(sql);
            while (resultado.next()) {
                System.out.println("Nombre: " + resultado.getString(1) + ", Precio: " + resultado.getDouble(2));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    // 6. Ingresar Producto
    public void ingresarProducto(String sql) throws Exception {
        try {
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    // 8. Editar producto con datos a elección
    public void editarProducto(String sql) throws Exception {
        try {
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    //Para la 8 vamos a tener que traer codigo y nombre del producto
    //luego devolvemos ese valor para poder acceder en otro método
    //luego un menú para que elija qué editar del menú y aplicar el update
    public void NombreId() throws Exception {
        try {
            consultarBase("SELECT codigo, nombre FROM producto");
            while (resultado.next()) {
                System.out.println("ID: " + resultado.getInt(1) + " | Nombre: "
                        + resultado.getString(2));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public Boolean validarId(Integer opc) throws Exception {
        Boolean band = false;
        try {
            consultarBase("SELECT codigo FROM producto");
            while (resultado.next()) {
                if (resultado.getInt(1) == opc) {
                    band = true;
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
        return band;
    }

    public void mostrarTodo() throws Exception {
        int cont = 0;
        try {
            consultarBase("SELECT * FROM producto p INNER JOIN fabricante f ON p.codigo_fabricante = f.codigo ORDER BY p.codigo ASC;");
            while (resultado.next()) {
                cont++;
                System.out.println("-------------------------------------------------");
                System.out.println("Producto Nº " + cont);
                System.out.println("Código: " + resultado.getInt(1) + "\n"
                        + "Nombre Producto: " + resultado.getNString(2) + "\n"
                        + "Precio: " + resultado.getDouble(3) + "\n"
                        + "Código fabricante: " + resultado.getInt(4) + "\n"
                        + "Fabricante: " + resultado.getString(6));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

}
