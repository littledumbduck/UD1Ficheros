import java.io.*;

public class T2_Bytes {
    public static void main(String[] args) throws IOException {
        File dir = new File("datos/ud1/practica");
        File file = new File(dir, "original.bin");
        dir.mkdirs();

        try (FileOutputStream fos = new FileOutputStream(file)) {
            for  (int i = 0; i < 20000; i++) {
                fos.write(1);
            }
        }

        System.out.println("Tamaño del archivo: " + file.length());

        File copia = new File(dir, "copia.bin");

        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(copia);

        int contador = 0;
        while ((contador = fis.read()) != -1) {
            fos.write(contador);
        }

        System.out.println("Tamaño de original: " + file.length());
        System.out.println("Tamaño de copia: " + copia.length());

        try (FileReader fr = new FileReader(file)) {
            for (int i = 0; i <= 4; i++) {
                int valor = fr.read();
                System.out.println("Leído: " + valor + " | Como carácter: '" + (char) valor + "' ");
            }
        }

        /*

        5: En Java el tipo primitivo byte tiene signo y su rango va de -128 a 127. el método read() necesita
        una forma de avisar cuando se llega al final del archivo EOF y para eso utiliza el valor -1. Si read()
        devolviera un byte sería imposible distinguir si el valor -1 significa si es un valor o si el archivo
        realmente ha terminado.

        */

        /*

        Saldría el valor numérico 255. Nunca sería -1 porque read() no interpreta el byte con signo sino que
        internamente le aplica una máscara a nivel de bits.

         */

    }
}
