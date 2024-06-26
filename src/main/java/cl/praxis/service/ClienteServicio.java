package cl.praxis.service;

import cl.praxis.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteServicio {

    private static ClienteServicio instance;

    public static ClienteServicio getInstance() {
        if (instance == null) {
            instance = new ClienteServicio();
        }
        return instance;
    }

    private final List<Cliente> listaClientes;

    public ClienteServicio(){
        this.listaClientes = new ArrayList<>();
    }

    public ClienteServicio(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public void listarClientes() {
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente.toString());
        }
    }

    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public void editarCliente(Cliente cliente) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getRunCliente().equals(cliente.getRunCliente())) {
                listaClientes.set(i, cliente);
            }
        }
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public Cliente getClienteByRun(String run) {
        for (Cliente cliente : listaClientes) {
            if (run.equals(cliente.getRunCliente())) {
                return cliente;
            }
        }
        return null;
    }

}
