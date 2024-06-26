package cl.praxis.service;

import cl.praxis.model.Cliente;

import java.util.List;

public abstract class Exportador {

    public abstract void exportar(String fileName, List<Cliente> listaClientes);

}
