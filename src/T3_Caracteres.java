import java.io.*;

public class T3_Caracteres {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        int totalLineas = 0;
        int totalPalabras = 0;
        int totalVocales = 0;
        File dir = new File("datos/ud1/practica");
        File file = new File(dir, "frases.txt");
        dir.mkdirs();

        // Escribimos los bytes en el archivo guardado en la variable file, dentro de frases.txt

        try (FileOutputStream fos = new FileOutputStream(file)) {
            String texto = "Programación\nmañana\ncamión";
            byte[] bytes = texto.getBytes("UTF-8");
            fos.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Usamos BufferedReader, inputStreamReader y FileInputStream para poder leer e imprimir finalmente en consola

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                totalLineas++;
                linea.split("\\s+");
                String lineaLimpia = linea.trim();

                if(!lineaLimpia.isEmpty()) {
                    String[] palabras = lineaLimpia.split("\\s+");
                    totalPalabras += palabras.length;
                }

                String lineaMinuscula = linea.toLowerCase();

                for (int i = 0; i < lineaMinuscula.length(); i++) {
                    char c = lineaMinuscula.charAt(i);
                    if("aeiouáéíóú".indexOf(c) != -1) {
                        totalVocales++;
                    }
                }



            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Haremos lo mismo que antes, pero esta vez en formato Cp1252. Con este formato no lee bien los acentos y
        // carácteres especiales

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Cp1252"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Total de líneas: " + totalLineas);
        System.out.println("Total de palabras: " + totalPalabras);
        System.out.println("Total de vocaLes: " + totalVocales);


    }
}
