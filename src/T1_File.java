import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class T1_File {
    public static void main(String[] args) throws IOException {
        // #1
        File base = new File("datos/ud1/practica");

        System.out.println("mkdir: " + base.mkdir());
        // mkdir devuelve "false" ya que solamente es capaz de crear el último base, por lo que devuelve false ya
        // que no es capaz de completar la orden.
        System.out.println("mkdirs: " + base.mkdirs());
        // En cambio mkdirs sí puede crear toda la ruta nueva de directorios, por lo que devuelve "true"

        // #2
        File carta = new File(base, "carta.txt");
        System.out.println("Primer createNewFile -> " + carta.createNewFile());
        System.out.println("Segundo createNewFile -> " + carta.createNewFile());

        // #3
        try (FileWriter writer = new FileWriter(carta)){
            writer.write("Primera linea\n");
            writer.write("Segunda linea\n");
            System.out.println("Se han escrito dos línea en carta.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File cartaOk = new File(base, "carta_ok.txt");
        System.out.println("carta.txt renombrado a carta_ok.txt -> " + carta.renameTo(cartaOk));

        File toDelete = new File(base, "toDelete.txt");
        System.out.println("Creacion archivo toDelete.txt -> " + toDelete.createNewFile());
        System.out.println("Eliminación archivo toDelete.txt -> " + toDelete.delete());

        // #4
        System.out.println("Datos del archivo carta_ok.txt:");
        System.out.println("Nombre: " + cartaOk.getName());
        System.out.println("Ruta absoluta: " + cartaOk.getAbsolutePath());
        System.out.println("Tamaño: " + cartaOk.length());

        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        System.out.println("Última modificación: " + date.format(cartaOk.lastModified()));

        // #5
        File[] lista = base.listFiles();
        if(lista != null) {
            for (File e : lista) {
                System.out.println("----------------------------------------");
                System.out.println(e.getName());

                if (e.isFile()) {
                    System.out.println("Archivo");
                } else {
                    System.out.println("Carpeta");
                }
                long bytes = e.length();
                System.out.println(bytes / 1024.0 + "bytes");
                System.out.println("----------------------------------------");
            }
            System.out.println("----------------------------------------");
            System.out.println("Total de entradas encontradas: " + lista.length);
        }

    }
}