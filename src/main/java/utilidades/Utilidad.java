package utilidades;

public class Utilidad {

    public static void limpiarPantalla(){
        try {
                System.out.print("\033[H\033[2J");
                System.out.flush();
        } catch (Exception e) {
                e.printStackTrace();
        }

    }
}
