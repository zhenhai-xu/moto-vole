package Model;

/**
 * pour compter que l'il reste combien de temp
 */
public class Sablier{
    private int min=1;
    private int sec=59;
    public Sablier(){
        this.min=min;
        this.sec=sec;
    }
    public void Soustraire_min() {
        if (sec == 0) {
            min--;
            sec=59;
        }
    }

    public void Soustraire_sec() {
     sec--;
    }
    public int getMin(){
        return min;
    }
    public int getSec(){
        return sec;
    }
}
