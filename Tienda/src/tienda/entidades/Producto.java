package tienda.entidades;

public class Producto {
    
    //codigo, nombre, precio, codigo_fabricante
    private Integer codigo;
    private String nombre;
    private Double precio;
    private Integer codFab;

    public Producto() {
    }

    public Producto(Integer codigo, String nombre, Double precio, Integer codFab) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.codFab = codFab;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCodFab() {
        return codFab;
    }

    public void setCodFab(Integer codFab) {
        this.codFab = codFab;
    }

    @Override
    public String toString() {
        return "Producto: " + codigo + ", nombre: " + nombre + ", precio: " + precio + ", codFab: " + codFab;
    }
    
}
