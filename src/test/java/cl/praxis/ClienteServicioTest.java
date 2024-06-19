package cl.praxis;

import modelo.CategoriaEnum;
import modelo.Cliente;
import org.junit.jupiter.api.Test;
import servicio.ClienteServicio;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteServicioTest {



    private ClienteServicio clienteServicio = new ClienteServicio();

    @Test
    public void agregarClienteTest() {

        Cliente cliente = new Cliente("1-2", "Pepe", "Lira", "2 años", CategoriaEnum.Activo);
        clienteServicio.agregarCliente(cliente);
        assertEquals(1, clienteServicio.getListaClientes().size());
        assertEquals("Pepe", clienteServicio.getListaClientes().get(0).getNombreCliente());

    }

    @Test
    public void agregarClienteNullTest() {
        Cliente cliente = null;
        clienteServicio.agregarCliente(cliente);
        assertEquals(0, clienteServicio.getListaClientes().size());
    }

}
