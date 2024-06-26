package cl.praxis.service;

import cl.praxis.model.CategoriaEnum;
import cl.praxis.model.Cliente;

import java.io.*;
import java.util.List;

public class ArchivoServicio extends Exportador {

    @Override
    public void exportar(String filePath, List<Cliente> listaClientes) {
        boolean state = true;
        File file = new File(filePath);

        if (!file.getParentFile().exists()) {
            state = file.getParentFile().mkdir();
            System.out.println(state ? "Directorio creado correctamente" : "Error al crear el directorio");
        } else
            System.out.println("Directorio ya se encuentra creado");

        if (state) {
            try {
                if (file.exists()) {
                    System.out.println("Archivo ya existe, por lo que se sobreescribirán los datos");
                }
                file.createNewFile();
                try {
                    FileWriter fw = new FileWriter(file);
                    PrintWriter pw = new PrintWriter(fw);
                    for (Cliente cliente : listaClientes) {
                        pw.println(cliente.getRunCliente() + "," + cliente.getNombreCliente() + "," + cliente.getApellidoCliente() + "," + cliente.getAnios() + "," + cliente.getNombreCategoria());
                    }
                    pw.flush();
                    pw.close();
                    fw.close();
                    System.out.println("Datos registrados correctamente");
                } catch (IOException e) {
                    System.out.println("Ocurrió un error de I/O al intentar escribir en el archivo");
                }
            } catch (IOException e) {
                System.out.println("Ocurrio un error de I/O al intentar crear el archivo");
            }
        }

    }

    public void cargarDatos(String filePath) {
        ClienteServicio clienteServicio = ClienteServicio.getInstance();
        File file = new File(filePath);
        if (file.exists()) {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                br.lines().forEach(row -> {
                    String[] data = row.split(",");
                    Cliente cliente = new Cliente();
                    cliente.setRunCliente(data[0]);
                    cliente.setNombreCliente(data[1]);
                    cliente.setApellidoCliente(data[2]);
                    cliente.setAnios(Integer.parseInt(data[3]));
                    cliente.setNombreCategoria(CategoriaEnum.valueOf(data[4]));
                    clienteServicio.agregarCliente(cliente);
                    System.out.println(cliente);
                });
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo no existe");
        }
        System.out.println("Datos cargados correctamente en la lista");
    }

}
