package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("Client avviato");

        Socket s = new Socket("localhost", 3000);
        System.out.println("Client connesso");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("Inserisci la stringa (o 'exit' per uscire): ");
            String stringDigitata = scan.nextLine();

            if (stringDigitata.equals("exit")) {
                System.out.println("Uscita dal server");
                out.writeBytes("exit\n");
                break;
            }

            System.out.println("Scegli l'operazione da eseguire:");
            System.out.println("1 - Trasformare in maiuscolo");
            System.out.println("2 - Trasformare in minuscolo");
            System.out.println("3 - Ribaltare i caratteri");
            System.out.println("4 - Contare il numero di caratteri");

            String operazione = scan.nextLine();

            // Invio della stringa e dell'operazione al server
            out.writeBytes(operazione + ";" + stringDigitata + "\n");
            String stringRicevuta = in.readLine();
            System.out.println("Il server ha risposto: " + stringRicevuta);

        } while (true);
    }
}
