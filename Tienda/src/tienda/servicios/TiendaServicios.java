package tienda.servicios;

import java.util.ArrayList;
import java.util.Scanner;
import tienda.entidades.Fabricante;
import tienda.entidades.Producto;
import tienda.persistencias.FabricanteDAO;
import tienda.persistencias.ProductoDAO;

public class TiendaServicios {

    public final ProductoDAO pDAO = new ProductoDAO();
    public final FabricanteDAO fDAO = new FabricanteDAO();
    public final Scanner leer = new Scanner(System.in).useDelimiter("\n");

    // Generales
    public void mostrarProductos(ArrayList<Producto> p) {
        for (Producto producto : p) {
            System.out.println("Producto: " + producto.getNombre());
        }
        System.out.println("-----------------------------------------");
    }

    public void mostrarFabricantes(ArrayList<Fabricante> f) {
        for (Fabricante fabricante : f) {
            System.out.println("Fabricante: " + fabricante.getNombre());
        }
        System.out.println("-----------------------------------------");
    }

    // Menú
    public void programa() throws Exception {
        Integer opc = 0;
        do {
            System.out.println("-------------------------------------------------");
            System.out.println("MENÚ DE BASE DE DATOS DE TIENDA:");
            System.out.println("1. Mostrar nombre de productos");
            System.out.println("2. Mostrar nombre y precio de productos");
            System.out.println("3. Mostrar productos entre $120 y $202");
            System.out.println("4. Mostrar portátiles");
            System.out.println("5. Nombre y precio del producto más barato");
            System.out.println("6. Ingresa un producto");
            System.out.println("7. Ingresa un fabricante");
            System.out.println("8. Modifica un valor de un producto determinado");
            System.out.println("9. MOSTRAR TODO");
            System.out.println("10. Salir");
            System.out.println("-------------------------------------------------");
            do {
                System.out.print("Elije opciones del 1 al 10: ");
                try {
                    opc = leer.nextInt();
                } catch (Exception e) {
                    System.out.println("Valor erróneo");
                    leer.next();
                }
            } while (opc < 1 || opc > 10);

            String sql = "";
            switch (opc) {
                case 1 ->
                    pDAO.mostrarNombre();
                case 2 ->
                    pDAO.mostrarNombrePrecio();
                case 3 ->
                    pDAO.mostrarNombrePorPrecio();
                case 4 ->
                    pDAO.mostrarNombrePortatil();
                case 5 ->
                    pDAO.mostrarNombrePrecioMin();
                case 6 -> {
                    sql = agregarProducto();
                    pDAO.ingresarProducto(sql);
                }
                case 7 -> {
                    sql = agregarFabricante();
                    fDAO.ingresarFabricante(sql);
                }
                case 8 -> {
                    sql = modificarProducto();
                    pDAO.editarProducto(sql);
                }
                case 9 ->
                    pDAO.mostrarTodo();
                case 10 ->
                    System.exit(0);
            }

        } while (opc != 10);
    }

    public String agregarProducto() throws Exception {
        Boolean band;

        String nombre = "";
        do {
            band = false;
            try {
                System.out.print("Ingrese nombre del producto");
                nombre = leer.next();
            } catch (Exception e) {
                System.out.println("Vuelva a ingresar");
                band = true;
                leer.next();
            }
        } while (band == true);

        Double precio = 0d;
        do {
            band = false;
            try {
                System.out.print("Ingrese precio del producto");
                precio = leer.nextDouble();
            } catch (Exception e) {
                System.out.println("Vuelva a ingresar");
                band = true;
                leer.next();
            }
            if (precio < 1) {
                System.out.println("Ingrese valores positivos");
                band = true;
            }
        } while (band == true);

        Integer cod = 0;
        do {
            band = false;
            try {
                System.out.print("Ingrese código del fabricante para:");
                System.out.println("Ingrese valores entre 1 y " + fDAO.codFab());
                cod = leer.nextInt();
                if (cod < 1 || cod > fDAO.codFab()) {
                    System.out.println("Ingrese valores entre 1 y " + fDAO.codFab());
                    band = true;
                }
            } catch (Exception e) {
                System.out.println("Error de sintaxis, vuelva a ingresar");
                band = true;
                leer.next();
            }

        } while (band == true);
        return "INSERT INTO producto (nombre, precio, codigo_fabricante) VALUES('" + nombre + "', " + precio + ", " + cod + ");";
    }

    public String agregarFabricante() {

        Boolean band;
        String nombre = "";
        do {
            band = false;
            try {
                System.out.println("Ingrese el nombre del fabricante");
                nombre = leer.next();
            } catch (Exception e) {
                System.out.println("Error, vuelva a ingresar");
                band = true;
                leer.next();
            }
        } while (band == true);
        return "INSERT INTO fabricante (nombre) VALUES('" + nombre + "');";
    }

    public String modificarProducto() throws Exception {
        Boolean band;
        System.out.println("Ingrese el número de código para elegir un producto para:");
        pDAO.NombreId();
        Integer opc = 0;
        do {
            band = false;
            try {
                opc = leer.nextInt();
            } catch (Exception e) {
                System.out.println("Ingrese valor entero positivo");
                band = true;
                leer.next();
            }
            if (pDAO.validarId(opc) == false) {
                System.out.println("No se encuentra tal código de producto");
                band = true;
            }
        } while (band == true);

        Integer menu = 0;
        System.out.println("Qué valor desea modificar?");
        System.out.println("1. Código");
        System.out.println("2. Nombre");
        System.out.println("3. Precio");
        System.out.println("4. Código de Fabricante");
        do {
            band = false;
            try {
                menu = leer.nextInt();
            } catch (Exception e) {
                System.out.println("Ingrese un valor entero positivo");
                band = true;
                leer.next();
            }
            if (menu < 1 || menu > 4) {
                System.out.println("Ingrese un valor del menú (1/4)");
                band = true;
            }
        } while (band == true);

        Integer auxNum = 0;
        String auxStr = "";
        Double auxDou = 0d;
        switch (menu) {
            case 1:
                System.out.println("Ingrese el nuevo código.\n"
                        + "Recuerde que NO debe estar repetido con EXISTENTES");
                do {
                    band = false;
                    try {
                        auxNum = leer.nextInt();
                        if (pDAO.validarId(auxNum) == true) {
                            System.out.println("El código ya existe, elija otro");
                            band = true;
                        }
                    } catch (Exception e) {
                        System.out.println("Debe ser un valor entero positivo");
                        band = true;
                        leer.next();
                    }

                } while (band == true);
                return "UPDATE producto SET codigo = " + auxNum + " WHERE codigo = " + opc + ";";
            case 2:
                System.out.println("Ingrese el nuevo nombre del producto");
                do {
                    band = false;
                    try {
                        auxStr = leer.next();
                    } catch (Exception e) {
                        System.out.println("Error del sistema, vuelva a ingresar");
                        band = true;
                        leer.next();
                    }
                } while (band == true);
                return "UPDATE producto SET nombre = '" + auxStr + "' WHERE codigo = " + opc + ";";
            case 3:
                System.out.println("Ingrese el nuevo precio");
                do {
                    band = false;
                    try {
                        auxDou = leer.nextDouble();
                        if (auxDou < 1) {
                            System.out.println("El mínimo precio es 1");
                            band = true;
                        }
                    } catch (Exception e) {
                        System.out.println("Ingrese un valor real");
                        band = true;
                        leer.next();
                    }
                } while (band == true);
                return "UPDATE producto SET precio = " + auxDou + " WHERE codigo = " + opc + ";";
            case 4:
                System.out.println("Ingrese el código de fabricante sabiendo que:");
                fDAO.codigoFabricante();
                do {
                    band = false;
                    try {
                        auxNum = leer.nextInt();
                        if (fDAO.validarId(auxNum) == true) {
                            System.out.println("El código ya existe, elija otro");
                            band = true;
                        }
                    } catch (Exception e) {
                        System.out.println("Debe ser un valor entero positivo");
                        band = true;
                        leer.next();
                    }
                } while (band == true);
                return "UPDATE producto SET codigo_fabricante = " + auxNum + " WHERE codigo = " + opc + ";";
        }
        return null;
    }

}
