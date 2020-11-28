package com.company;

import javax.crypto.SecretKey;
import java.io.*;
import java.nio.file.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
//Exercici 1.5
        System.out.println("1.5. Xifrar i desxifrar un text en clar amb una clau generada amb el codi 1.1.1");
        System.out.println("");

        String text = "Hola mundo! Soc un text sense xifrar";
        SecretKey secretKey1 = E1Claus.keygenKeyGeneration(256);
        byte[] xifrat = E1Claus.encryptData(secretKey1, text.getBytes());
        byte[] desxifrat = E1Claus.decryptData(secretKey1, xifrat);
        String textDesxifrat = new String(desxifrat);
        System.out.println(textDesxifrat);


//Exercici 1.6
        System.out.println("");
        System.out.println("1.6. Xifrar i desxifrar un text en clar amb una clau (codi 1.1.2) generada a partir de la paraula de pas.");
        System.out.println("");

        String textA = "Hello World";
        String password = "cocacola"; //password
        SecretKey secretKey2 = E1Claus.passwordKeyGeneration(password, 256);
        byte[] xifrat2 = E1Claus.encryptData(secretKey2, textA.getBytes());
        byte[] desxifrat2 = E1Claus.decryptData(secretKey2, xifrat2);
        String textDesxifrat2 = new String(desxifrat2);
        System.out.println(textDesxifrat2);

//Exercici 1.7
        System.out.println("");
        System.out.println("1.7. Prova alguns dels mètodes que proporciona la classe SecretKey");
        System.out.println("");
        System.out.println(Arrays.toString(secretKey1.getEncoded()));
        System.out.println(secretKey1.getFormat());

//Exercici 1.8
        System.out.println("");
        System.out.println("1.8. Desxifra el text del punt 6 i comprova que donant una paraula de pas incorrecte salta l'excepció BadPaddingException");
        System.out.println("");

        String password2 = "pepsi"; //contrasenya
        SecretKey secretKey3 = E1Claus.passwordKeyGeneration(password2, 256);
        try {
            byte[] desxifrat3 = E1Claus.decryptData(secretKey3, xifrat2);
            String textDesxifrat3 = new String(desxifrat3);
            System.out.println(textDesxifrat3);
        } catch (Exception e) {
            System.out.println(e);
        }

//Exercici 2
        System.out.println("");
        System.out.println("2. Donat un text xifrat (textamagat) amb algoritme estàndard AES i clau simètrica generada amb el mètode SHA-256 a partir d’una contrasenya, i donat un fitxer (clausA4.txt) on hi ha possibles contrasenyes correctes, fes un programa per trobar la bona i desxifrar el missatge.");
        System.out.println("");

        //text amagat
        Path ruta = Paths.get("/home/dam2a/textamagat");
        byte[] fitxer = Files.readAllBytes(ruta);

        //text de claus
        File claus = new File ("/home/dam2a/clausA4.txt");
        FileReader fileReader = new FileReader(claus);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();
        boolean txt =false;
        while (!txt){
            try {
                SecretKey key = E2ClausXifrar.passwordKeyGeneration(line,256);
                byte [] decryptedText = E2ClausXifrar.decryptData(key, fitxer);
                String textamagat = new String(decryptedText);
                System.out.println(line + ": Es la contrasenya es correcta. ");
                System.out.println(" ");
                System.out.println("Aquest es el resultat del seu textamagat desxifrat: ");
                System.out.println(textamagat);
                txt = true;
                break;
            } catch (Exception ex){
                System.out.println(line + ": No es la contrasenya no es correcta."+ "\n" + ex );
                line = bufferedReader.readLine();
            }


        }
    }
}
