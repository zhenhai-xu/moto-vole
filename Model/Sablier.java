package Model;

import Vue.Affichage;

/**
 * pour compter qu'il reste combien de temp
 */
public class Sablier{
    private int min=1;
    private int sec=59;
    Piste piste;
    Moto moto;
    public Sablier(Piste piste,Moto moto){
        this.min=min;
        this.sec=sec;
        this.piste=piste;
        this.moto=moto;
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

    public void getBonus() {
        int bonus = 30;
        if (piste.AvoirPremierPoint2&&
                Math.abs(piste.getTerminal().get(piste.getTerminal().size()-1).y - 800) <= 100 ) {
                if(bonus > 5) bonus--;
                //System.out.println("bonus"+piste.getTerminal().get(piste.getTerminal().size()-1).y);
                if (sec <= 29) {
                    sec += bonus;
                } else {
                    sec = sec + bonus - 59;
                    min += 1;
                }

        }
    }
    public int getMin(){
        return min;
    }
    public int getSec(){
        return sec;
    }
}
