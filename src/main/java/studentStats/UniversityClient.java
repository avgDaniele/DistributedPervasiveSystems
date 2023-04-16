package studentStats;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class UniversityClient {
    public static void main(String[] args) throws IOException {
        //leggere da stdInput dati dello studente e poi inviarli a Server

        Socket s = new Socket("localhost", 6789);
        BufferedReader inFromUtente = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream outToServer = new DataOutputStream(s.getOutputStream());

        Student studente = new Student();

        System.out.println("inserimento studente");
        System.out.println("nome:");
        studente.setName(inFromUtente.readLine());
        System.out.println("Cognome:");
        studente.setSurname(inFromUtente.readLine());
        System.out.println("Esame:");
        String exName = inFromUtente.readLine();
        System.out.println("voto:");
        int exMark = Integer.parseInt(inFromUtente.readLine());
        System.out.println("data:");
        String exDate = inFromUtente.readLine();
        Exam esame = new Exam(exName, exMark, exDate);
        studente.addExam(esame);

        //marshalling
        Gson gson = new Gson();
        String studenteStr = gson.toJson(studente);
        System.out.println(String.format("studente: %s", studente));
        System.out.println(String.format("request: %s", studenteStr));
        outToServer.writeBytes(studenteStr+"\n");
    }
}
