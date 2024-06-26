package cl.praxis.view;

import cl.praxis.model.CategoriaEnum;
import cl.praxis.model.Cliente;
import cl.praxis.service.ArchivoServicio;
import cl.praxis.service.ClienteServicio;
import cl.praxis.service.ExportadorCsv;
import cl.praxis.service.ExportadorTxt;
import cl.praxis.utils.Utils;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Menu {

    private ClienteServicio clienteServicio;
    private final ArchivoServicio archivoServicio = new ArchivoServicio();
    private ExportadorCsv exportarCsv;
    private ExportadorTxt exportarTxt;
    private final String EXPORTAR_FILENAME = "Clientes";
    private final String IMPORTAR_FILENAME = "DBClientes.csv";

    public void iniciarMenu() {
        clienteServicio = ClienteServicio.getInstance();
        String op = mainMenu();
        do {
            if (op.equals("1")) {
                listarCliente();
                enterToContinue();
            } else if (op.equals("2")) {
                agregarCliente();
                enterToContinue();
            } else if (op.equals("3")) {
                editarCliente();
                enterToContinue();
            } else if (op.equals("4")) {
                importarDatos();
                enterToContinue();
            } else if (op.equals("5")) {
                exportarDatos();
                enterToContinue();
            } else {
                System.out.println("Opción no valida");
                enterToContinue();
            }
            op = mainMenu();
        } while (!op.equals("6"));
        terminarPrograma();
    }

    public String mainMenu() {
        Utils.clearScreenFalse();
        System.out.println("--------------");
        System.out.println("Menu Principal");
        System.out.println("--------------");
        System.out.println();
        System.out.println("1. Listar Clientes");
        System.out.println("2. Agregar Cliente");
        System.out.println("3. Editar Cliente");
        System.out.println("4. Cargar Datos");
        System.out.println("5. Exportar Datos");
        System.out.println("6. Salir");
        System.out.println();
        System.out.println("Ingresa una opción: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void listarCliente() {
        ArrayList<Cliente> lista = (ArrayList<Cliente>) clienteServicio.getListaClientes();
        if (!lista.isEmpty()) {
            clienteServicio.listarClientes();
        } else {
            System.out.println("Lista vacía, favor importar o registrar nuevos datos");
        }
    }

    private void agregarCliente() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------Crear Cliente------------");
        System.out.println();
        System.out.println("Ingresar RUN del Cliente: ");
        String run = sc.nextLine();
        System.out.println("Ingresar Nombre del Cliente: ");
        String nombre = sc.nextLine();
        System.out.println("Ingresar Apellido del Cliente: ");
        String apellido = sc.nextLine();
        System.out.println("Ingresar años como Cliente: ");
        String anios = String.valueOf(sc.nextInt());
        System.out.println();
        System.out.println("-------------------------------------");

        Cliente nuevoCliente = new Cliente(run, nombre, apellido, Integer.parseInt(anios), CategoriaEnum.Activo);

        clienteServicio.agregarCliente(nuevoCliente);

    }

    public void editarCliente() {
        ArrayList<Cliente> lista = (ArrayList<Cliente>) clienteServicio.getListaClientes();
        boolean status = false;
        if (lista.isEmpty()) {
            System.out.println("Lista vacía, favor importar o registrar nuevos datos");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------Editar Cliente--------------");
        System.out.println("Seleccione qué desea hacer:");
        System.out.println("1. Cambiar el estado de un Cliente");
        System.out.println("2. Editar los datos ingresados de un Cliente");
        System.out.println();
        System.out.println("Ingrese la opción: ");
        String op = sc.nextLine();
        System.out.println("------------------------------------------");
        System.out.println("Ingrese el RUN del Cliente a editar:");
        String run = sc.nextLine();
        Cliente clienteEncontrado = lista.stream().filter(a -> Objects.equals(a.getRunCliente(), run)).findAny().get();
        if (Objects.equals(op, "1")) {
            System.out.println("-----Actualizando estado del Cliente------");
            System.out.println("El estado actual es: " + clienteEncontrado.getNombreCategoria());
            if (Objects.equals(clienteEncontrado.getNombreCategoria().toString(), "Activo")) {
                status = true;
            }
            System.out.println("1. Si desea cambiar el estado del Cliente a " + (status ? "Inactivo" : "Activo"));
            System.out.println("2. Si desea mantener el estado del Cliente " + (status ? "Activo" : "Inactivo"));
            System.out.println();
            System.out.println("Ingrese la opción: ");
            String opStatus = sc.nextLine();
            System.out.println("--------------------------------------");
            switch (opStatus) {
                case "1":
                    clienteEncontrado.setNombreCategoria(status ? CategoriaEnum.Inactivo : CategoriaEnum.Activo);
                    System.out.println("El estado fue editado con éxito");
                    break;
                case "2":
                    System.out.println("El estado sigue siendo " + (status ? "Activo" : "Inactivo"));
                    break;
                default:
                    System.out.println("Alternativa invalida, favor ingrese 1 o 2");
                    break;
            }

        } else if (Objects.equals(op, "2")) {
            System.out.println("----Actualizando datos del Cliente----");
            System.out.println("1. El RUN del Cliente es: " + clienteEncontrado.getRunCliente());
            System.out.println("2. El Nombre del Cliente es: " + clienteEncontrado.getNombreCliente());
            System.out.println("3. El Apellido del Cliente es: " + clienteEncontrado.getApellidoCliente());
            System.out.println("4. Los años como Cliente son: " + clienteEncontrado.getAnios());
            System.out.println("Ingrese la opción a editar de los datos del cliente: ");
            String opEditar = sc.nextLine();
            System.out.println("--------------------------------------");
            switch (opEditar) {
                case "1":
                    System.out.println("Ingresa el nuevo RUN del Cliente: ");
                    clienteEncontrado.setRunCliente(sc.nextLine());
                    break;
                case "2":
                    System.out.println("Ingresa el Nombre del Cliente: ");
                    clienteEncontrado.setNombreCliente(sc.nextLine());
                    break;
                case "3":
                    System.out.println("Ingresa el nuevo Apellido del Cliente: ");
                    clienteEncontrado.setApellidoCliente(sc.nextLine());
                    break;
                case "4":
                    System.out.println("Ingresa los nuevos años como Cliente: ");
                    clienteEncontrado.setAnios(Integer.parseInt(sc.nextLine()));
                    break;
                default:
                    break;
            }
            System.out.println(clienteEncontrado);
            System.out.println("El Cliente fue editado con éxito");
        }
    }

    public void importarDatos() {
        Scanner sc = new Scanner(System.in);
        String os = Utils.getOS();
        System.out.println("--------Cargar Datos en " + (os.equals("Windows") ? "Windows" : "Linux o Mac") + "--------");
        System.out.println("Ingresa la ruta en donde se encuentra el archivo DBClientes.csv:");
        String dir = sc.nextLine();
        archivoServicio.cargarDatos(dir.concat("/").concat(IMPORTAR_FILENAME));
    }

    public void exportarDatos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Exportar Datos-------");
        System.out.println("Seleccione el formato a exportar: ");
        System.out.println("1. Formato CSV");
        System.out.println("2. Formato txt");
        System.out.println("Ingrese una opción para exportar: ");
        String op = sc.nextLine();
        System.out.println("-----------------------------");
        String os = Utils.getOS();
        System.out.println("--------Exportar Datos en " + (os.equals("Windows") ? "Windows" : "Linux o Mac") + "--------");
        System.out.println("Ingresa la ruta en donde desea exportar el archivo Clientes" + (op.equals("1") ? ".csv" : ".txt") + ":");
        String dir = sc.nextLine();
        archivoServicio.exportar(dir.concat("/").concat(EXPORTAR_FILENAME).concat(op.equals("1") ? ".csv" : ".txt"), clienteServicio.getListaClientes());
    }

    public void terminarPrograma() {
        System.out.println("Saliendo del programa...");
        System.exit(0);
    }

    public void enterToContinue() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Presione enter para continuar");
        sc.nextLine();
    }
}
