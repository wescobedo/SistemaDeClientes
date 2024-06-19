package servicio;
import modelo.Cliente;
import java.util.ArrayList;
import java.util.List;



public class ClienteServicio {

    private List<Cliente> listaClientes;

    public ClienteServicio() {
        this.listaClientes = new ArrayList<>();
    }
    public void listarClientes() {
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente.toString());
        }
    }
    public void agregarCliente(Cliente cliente){
              if (cliente==null) {
                System.out.println("Error , no se aceptan datos nulos");
              } else {
                  listaClientes.add(cliente);
              }
    }

    public void editarCliente(String run, int opcion, String nuevoValor){

        Cliente cliente = buscarClientePorRun(run);

        if (cliente != null) {
            if (opcion == 1) {
                cliente.setRunCliente(nuevoValor);
            } else if (opcion == 2) {
                cliente.setNombreCliente(nuevoValor);
            } else if (opcion == 3) {
                cliente.setApellidoCliente(nuevoValor);
            } else if (opcion == 4) {
                cliente.setAniosCliente(nuevoValor);
            } else {
                System.out.println("Opción no válida.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }

    }


    public Cliente buscarClientePorRun(String run) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getRunCliente().equals(run)) {
                return cliente;
            }
        }
        return null;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }


}
