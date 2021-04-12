package Vue;

import Model.Piste;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 *La fin de l'interface, montrant le score et
 * e score le plus élevé et le classement de l'histoire
 */

public class EndGame {
    private Frame frame;
    private Piste piste;
    private String x;

    private int[] arr2 = new int[10000];
    private int rank=0;
    private int firstScore=0;

    public EndGame(Piste piste,Frame frame){
        this.piste=piste;
        this.frame=frame;
    }

    public void highScore() throws IOException {
        File file = new File("src\\data.txt"); // Fichier pour stocker les données du tableau
        BufferedReader in = new BufferedReader(new FileReader(file)); // Convertit les octets en caractères, puis peut read
        String line; // données de ligne
        // Lire ligne par ligne et mettre chaque donnée dans le tableau
        String[] temporary = null;
        while ((line = in.readLine()) != null) {
            temporary = line.split("\t");
            for (int j = 0; j < temporary.length; j++) {
                arr2[j] = (int) Double.parseDouble(temporary[j]);
            }
        }
        in.close();
        // Afficher les données lues

        for (int i = 0; i < temporary.length; i++) {
            if (piste.getPosition() >= arr2[i]) {
                for (int j = temporary.length - 1; j >= i; j--) {
                    arr2[j + 1] = arr2[j];
                }
                arr2[i] = piste.getPosition();
                rank = i + 1;
                break;
            }
        }
        firstScore = arr2[0];
        FileWriter out = new FileWriter(file); // Écriture de fichier
        for (int i = 0; i < temporary.length + 1; i++) {
            out.write(arr2[i] + "\t");
        }
        out.close();
        this.x = "<html><body>" + "Game Over" + "<br/>"+"Your Score : "+piste.getPosition()+"<br/>"
                +"Meilleur score : "+firstScore +"<br/>"+"Your Rank: " + rank +"</body></html> ";

    }
    public JLabel newLable(){
        JLabel label = new JLabel(this.x);
        label.setFont(new Font("Verdana", 1, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(frame.getSize());
        return label;
    }

}
