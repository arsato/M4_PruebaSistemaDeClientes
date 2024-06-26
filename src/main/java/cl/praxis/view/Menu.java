package cl.praxis.desafios.reciclajeans;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Menu {

    public static void navigationMenu(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Producto> lista = new ArrayList<>();
        String op = Menu.mainMenu();
        String dir = null;
        do {
            if (op.equals("1")) {
                Menu.listMenu(lista);
                Menu.enterToContinue();
            } else if (op.equals("2")) {
                Menu.editMenu(lista,dir);
                Menu.enterToContinue();
            } else if (op.equals("3")) {
                System.out.println("Importar datos");
                System.out.println("Ingresa la ruta donde se encuentra el archivo ProductosImportados.csv: ");
                dir = sc.nextLine();
                lista = ArchivoServicio.cargarDatos(dir);
                Menu.enterToContinue();
            }else{
                System.out.println("Opcion no valida");
                Menu.enterToContinue();
            }
            op = Menu.mainMenu();
        } while (!op.equals("4"));
    }

    public static String mainMenu(){
        Utils.clearScreenFalse();
        System.out.println("--------------");
        System.out.println("Menu Principal");
        System.out.println("--------------");
        System.out.println();
        System.out.println("1. Listar Producto");
        System.out.println("2. Editar Datos");
        System.out.println("3. Importar Datos");
        System.out.println("4. Salir");
        System.out.println();
        System.out.println("Ingresa una opción: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static void listMenu(ArrayList<Producto> lista){
        if (!lista.isEmpty()) {
            ArchivoServicio.showData(lista);
        } else {
            System.out.println("Lista vacia, favor importar datos");
        }
    }

    public static void editMenu(ArrayList<Producto> lista, String filePath){
        if(lista.isEmpty()){
            System.out.println("Lista vacia, favor importar datos");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Editar producto");
        System.out.println("Ingrese el numero 1 para editar los datos del producto: ");
        String op2 = sc.nextLine();
        if (Objects.equals(op2, "1")) {
            System.out.println("Ingrese el codigo del producto a editar:");
            String codigo = sc.nextLine();
            Producto productoEncontrado = lista.stream().filter(a -> Objects.equals(a.getCodigo(), codigo)).findAny().get();
            System.out.println("1. El nombre del articulo es: " + productoEncontrado.getArticulo());
            System.out.println("2. El código del producto es: " + productoEncontrado.getCodigo());
            System.out.println("3. El color del articulo es: " + productoEncontrado.getColor());
            System.out.println("4. La descripción del articulo es: " + productoEncontrado.getDescripcion());
            System.out.println("5. La marca del articulo es: " + productoEncontrado.getMarca());
            System.out.println("6. El precio del articulo es: " + productoEncontrado.getPrecio());
            System.out.println("7. La talla del articulo es: " + productoEncontrado.getTalla());
            System.out.println("Ingrese la opcion a editar de los datos del producto: ");
            String opEditar = sc.nextLine();
            switch (opEditar) {
                case "1":
                    System.out.println("Ingresa el nuevo nombre del articulo: ");
                    productoEncontrado.setArticulo(sc.nextLine());
                    break;
                case "2":
                    System.out.println("Ingresa el nuevo código del articulo: ");
                    productoEncontrado.setCodigo(sc.nextLine());
                    break;
                case "3":
                    System.out.println("Ingresa el nuevo color del articulo: ");
                    productoEncontrado.setColor(sc.nextLine());
                    break;
                case "4":
                    System.out.println("Ingresa la nueva descripción del articulo: ");
                    productoEncontrado.setDescripcion(sc.nextLine());
                    break;
                case "5":
                    System.out.println("Ingresa la nueva marca del articulo: ");
                    productoEncontrado.setMarca(sc.nextLine());
                    break;
                case "6":
                    System.out.println("Ingresa el nuevo precio del articulo: ");
                    productoEncontrado.setPrecio(sc.nextLine());
                    break;
                case "7":
                    System.out.println("Ingresa la nueva talla del articulo: ");
                    productoEncontrado.setTalla(sc.nextLine());
                    break;
                default:
                    break;
            }
            ArchivoServicio.saveData(lista,filePath);
            System.out.println(productoEncontrado);
            System.out.println("El articulo fue editado con exito");
        }
    }

    public static void enterToContinue(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Presione enter para continuar");
        sc.nextLine();
    }
}
