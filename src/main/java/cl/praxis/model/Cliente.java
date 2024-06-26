package cl.praxis.model;

public class Cliente {

    private String runCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private int anios;
    private CategoriaEnum nombreCategoria;

    public Cliente(String runCliente, String nombreCliente, String apellidoCliente, int anios, CategoriaEnum nombreCategoria) {
        this.runCliente = runCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.anios = anios;
        this.nombreCategoria = nombreCategoria;
    }

    public Cliente() {

    }

    public String getRunCliente() {
        return runCliente;
    }

    public void setRunCliente(String runCliente) {
        this.runCliente = runCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public int getAnios() {
        return anios;
    }

    public void setAnios(int anios) {
        this.anios = anios;
    }

    public CategoriaEnum getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(CategoriaEnum nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @Override
    public String toString() {
        return "----------Datos del Cliente---------- \n\n" + "RUN del Cliente: " + runCliente + "\n" + "Nombre del Cliente: " + nombreCliente + "\n" + "Apellido del Cliente: " + apellidoCliente + "\n" + "Años como Cliente: " + anios + "\n" + "Categoria del Cliente: " + nombreCategoria + "\n\n" + "-------------------------------------";
    }
}