package servicio;

import modelo.Cliente;
import modelo.CategoriaEnum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ArchivoServicio extends Exportador {

public void cargarDatos(File archivo, ClienteServicio clienteServicio){

    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        String linea="";
        String separador=",";
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(separador);
            String run = datos[0];
            String nombre = datos[1];
            String apellido = datos[2];
            String anios = datos[3];
            CategoriaEnum categoria = CategoriaEnum.valueOf(datos[4]);
            Cliente cliente = new Cliente(run, nombre, apellido, anios, categoria);
            clienteServicio.agregarCliente(cliente);

        }
        System.out.println("Datos cargados correctamente en la lista");
    } catch (IOException e) {
        System.out.println("Error al cargar datos: " + e.getMessage());
    }
}
    @Override

    public void exportar(String fileName, List<Cliente> listaClientes) {

    }

    public ArchivoServicio() {
    }

}



