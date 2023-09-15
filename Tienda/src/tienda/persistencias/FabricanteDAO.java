package tienda.persistencias;

import java.util.ArrayList;
import tienda.entidades.Fabricante;

public final class FabricanteDAO extends DAO {

    public ArrayList<Fabricante> AgregarFabricante() throws Exception {
        try {
            consultarBase("SELECT * FROM fabricante;");
            ArrayList<Fabricante> fabricantes = new ArrayList();
            while (resultado.next()) {
                Fabricante f = new Fabricante();
                f.setCodigo(resultado.getInt(1));
                f.setNombre(resultado.getString(2));
                fabricantes.add(f);
            }
            return fabricantes;
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    // 7. Ingresar un fabricante
    public void ingresarFabricante(String sql) throws Exception {
        try {
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public Integer codFab() throws Exception {
        Integer total = 0;
        try {
            consultarBase("SELECT count(*) FROM fabricante;");
            while (resultado.next()) {
                total = resultado.getInt(1);
            }
            return total;
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void codigoFabricante() throws Exception {
        try {
            consultarBase("Select * FROM fabricantes;");
            while (resultado.next()) {
                System.out.println("Código: " + resultado.getInt(1) + " | Nombre: " + resultado.getString(2));
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
            consultarBase("SEKECT codigo FROM fabricante");
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

}
