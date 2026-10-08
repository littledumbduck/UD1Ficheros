import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class T4_Aleatorio {
    public static void main(String[] args) {
        File dir = new File("datos/ud1/practica");
        File file = new File(dir, "butacas.dat");

        dir.mkdirs();

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.setLength(0);
            String[] nombres = {"David", "Alberto", "Laura", "Marta", "Eloy"};

            for (int i = 1; i <= 5; i++) {
                raf.writeInt(i);
                raf.writeChars(formatearNombre(nombres[i-1]));
                raf.writeLong((System.currentTimeMillis()));
            }

            System.out.println("--- Fin de escritura inicial ---");
            System.out.println("length(): " + raf.length() + " (Esperado: 160 = 5 * 32)");
            System.out.println("getFilePointer(): " + raf.getFilePointer());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    public static String formatearNombre(String nombre) {
        if (nombre.length()> 10) {
            return nombre.substring(0, 10);
        }
        // Tiene menos de 10 caracteres por lo que rellenamos con espacios en blanco a la derecha
        return String.format("%-10s", nombre);
    }

}
