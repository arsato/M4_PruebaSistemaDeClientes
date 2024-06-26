package cl.praxis;

import cl.praxis.model.CategoriaEnum;
import cl.praxis.model.Cliente;
import cl.praxis.service.ClienteServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Tests clase ClienteServicio")
public class ClienteServicioTest {

    private ClienteServicio clienteServicio;

    @BeforeEach
    void setUp() {
        clienteServicio = new ClienteServicio();
    }

    @Test
    @DisplayName("Test agregar Cliente")
    void agregarClienteTest() {
        Cliente clienteTest = new Cliente("12345678", "Juan", "Perez", 5, CategoriaEnum.Activo);
        clienteServicio.agregarCliente(clienteTest);
        Cliente expectedClient = clienteServicio.getClienteByRun("12345678");
        assertEquals(clienteTest, expectedClient);
    }

    @Test
    @DisplayName("Test agregar Cliente Null")
    void agregarClienteNullTest() {
        clienteServicio.agregarCliente(null);
        List<Cliente> lista = clienteServicio.getListaClientes();
        assertTrue(lista.contains(null));
    }

}
