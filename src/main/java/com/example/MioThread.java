package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread {

    Socket s;

    public MioThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            do {
                String stringaRicevuta = in.readLine();
                
                // Se il client invia "exit", il server chiude la connessione
                if (stringaRicevuta.equals("exit")) {
                    System.out.println("Il client sta chiudendo");
                    s.close();
                    break;
                }

                // Parsing della stringa ricevuta (operazione;stringa)
                String[] parts = stringaRicevuta.split(";");
                String operazione = parts[0];
                String stringa = parts[1];

                String stringaTrasformata = "";

                switch (operazione) {
                    case "1":
                        stringaTrasformata = stringa.toUpperCase();
                        break;
                    case "2":
                        stringaTrasformata = stringa.toLowerCase();
                        break;
                    case "3":
                        stringaTrasformata = new StringBuilder(stringa).reverse().toString();
                        break;
                    case "4":
                        stringaTrasformata = "Numero di caratteri: " + stringa.length();
                        break;
                    default:
                        stringaTrasformata = "Operazione non valida";
                        break;
                }

                out.writeBytes(stringaTrasformata + "\n");

            } while (true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
