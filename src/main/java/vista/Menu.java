package vista;
import java.io.File;
import java.util.Scanner;
import modelo.Cliente;
import utilidades.Utilidad;
import servicio.ArchivoServicio;
import servicio.ClienteServicio;
import servicio.ExportadorCsv;
import servicio.ExportadorTxt;
import modelo.CategoriaEnum;


public class Menu {

    private ClienteServicio clienteServicio = new ClienteServicio();
    private ArchivoServicio archivoServicio = new ArchivoServicio();
    private ExportadorCsv exportadorCsv = new ExportadorCsv();
    private ExportadorTxt exportarTxt = new ExportadorTxt();
    private String fileName = "Clientes";
    private String fileName1 = "DBClientes.csv";
    private Scanner scanner = new Scanner(System.in);

    public void iniciarMenu() {
        Utilidad utilidad= new Utilidad();
        String op;
        do {

            System.out.println("1. Listar Clientes");
            System.out.println("2. Agregar Cliente");
            System.out.println("3. Editar Cliente");
            System.out.println("4. Cargar Datos");
            System.out.println("5. Exportar Datos");
            System.out.println("6. Salir");
            System.out.print("Ingrese una opción: ");
            op = scanner.nextLine();

            if (op.equals("1")) {
                listarClientes();
            } else if (op.equals("2")) {
                agregarCliente();
            } else if (op.equals("3")) {
                editarCliente();
            } else if (op.equals("4")) {
                importarDatos();
            } else if (op.equals("5")) {
                exportarDatos();
            } else if (!op.equals("6")) {
                System.out.println("Opción no válida, por favor intente de nuevo.");
            }
            utilidad.limpiarPantalla();
        }
        while (!op.equals("6"));
        terminarPrograma();
    }

    public void listarClientes() {
        clienteServicio.listarClientes();
    }

    public void agregarCliente() {
        System.out.println("-------------Crear Cliente-------------");

        System.out.print("Ingresa RUN del Cliente: ");
        String run = scanner.nextLine();
        while (run == null ||   run.isEmpty())    {
            System.out.println("Ingrese un valor válido.");
            run = scanner.nextLine();
        }
        System.out.print("Ingresa Nombre del Cliente: ");
        String nombre = scanner.nextLine();
        while ( nombre ==null || nombre.isEmpty()){
            System.out.println("Ingrese un valor válido.");
            nombre = scanner.nextLine();
        }

        System.out.print("Ingresa Apellido del Cliente: ");
        String apellido = scanner.nextLine();
        while ( apellido ==null || apellido.isEmpty()){
            System.out.println("Ingrese un valor válido.");
            apellido = scanner.nextLine();
        }

        System.out.print("Ingresa años como Cliente: ");
        String anios = scanner.nextLine();
        while ( anios ==null || anios.isEmpty()){
            System.out.println("Ingrese un valor válido.");
            anios = scanner.nextLine();
        }

        Cliente cliente = new Cliente(run, nombre, apellido, anios, CategoriaEnum.Activo);
        clienteServicio.agregarCliente(cliente);
        System.out.println("---------------------------------------");
    }

    public void editarCliente(){

        System.out.println("-------------Editar Cliente-------------");
        System.out.println("Seleccione qué desea hacer:");
        System.out.println("1.- Cambiar el estado del Cliente");
        System.out.println("2.- Editar los datos ingresados del Cliente");
        System.out.print("Ingrese opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese RUN del Cliente a editar: ");
        String run = scanner.nextLine();

        if (opcion == 1) {
            System.out.println("-----Actualizando estado del Cliente----");
            Cliente cliente = clienteServicio.buscarClientePorRun(run);
            if (cliente != null) {
                System.out.println("El estado actual es: " + cliente.getNombreCategoria());
                System.out.println("1.- Si desea cambiar el estado del Cliente a Inactivo");
                System.out.println("2.- Si desea mantener el estado del cliente Activo");
                System.out.print("Ingrese opción: ");
                int opcionEstado = scanner.nextInt();
                scanner.nextLine();

                if (opcionEstado == 1) {
                    cliente.setNombreCategoria(CategoriaEnum.Inactivo);
                    System.out.println("El estado del cliente ha sido cambiado a Inactivo.");
                } else if (opcionEstado == 2) {
                    cliente.setNombreCategoria(CategoriaEnum.Activo);
                    System.out.println("El estado del cliente se mantiene Activo.");
                } else {
                    System.out.println("Opción no válida.");
                }

            } else {
                System.out.println("Cliente no encontrado.");
            }
        } else if (opcion == 2) {
            Cliente cliente = clienteServicio.buscarClientePorRun(run);
            if (cliente != null) {
                System.out.println("----Actualizando datos del Cliente-----");
                System.out.println("1.- El RUN del Cliente es: " + cliente.getRunCliente());
                System.out.println("2.- El Nombre del Cliente es: " + cliente.getNombreCliente());
                System.out.println("3.- El Apellido del Cliente es: " + cliente.getApellidoCliente());
                System.out.println("4.- Los años como Cliente son: " + cliente.getAniosCliente());
                System.out.print("Ingrese opción a editar de los datos del cliente: ");
                int opcionDatos = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Ingrese el nuevo valor: ");
                String nuevoValor = scanner.nextLine();

                clienteServicio.editarCliente(run, opcionDatos, nuevoValor);
                System.out.println("Datos cambiados con éxito.");
            } else {
                System.out.println("Cliente no encontrado.");
            }
        } else {
            System.out.println("Opción no válida.");
        }
    }

    public void importarDatos(){
        System.out.print("Ingresa la ruta en donde se encuentra el archivo DBClientes.csv: ");
        String ruta = scanner.nextLine();
        File archivo = new File(ruta + fileName1);
        archivoServicio.cargarDatos(archivo , clienteServicio );

    }

    public void exportarDatos(){

        System.out.println("---------Exportar Datos-----------");
        System.out.println("Seleccione el formato a exportar: ");
        System.out.println("1.-Formato csv");
        System.out.println("2.-Formato txt");
        System.out.print("Ingrese una opción para exportar: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingresa la ruta en donde desea exportar el archivo: ");
        String ruta = scanner.nextLine();
        ruta = ruta + fileName;

        if (opcion == 1) {
            exportadorCsv.exportar(ruta, clienteServicio.getListaClientes());
        } else if (opcion == 2) {
            exportarTxt.exportar(ruta, clienteServicio.getListaClientes());
        } else {
            System.out.println("Opción no válida.");
        }
    }

    public void terminarPrograma() {
        System.out.println("Abandonando el Sistema de Clientes");
    }

}

