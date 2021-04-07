package Model;

/**
 * pour compter qu'il reste combien de temp
 */
public class Sablier{
    private int min=0;//initialisation il reste 0 minute
    private int sec=30;//initialisation il reste 30 seconde
    Piste piste;
    Moto moto;
    private boolean canGetBonus =true;//verifier s'il peut augmenter le temps
    private int index=0;//Utile dans le temps croissant
    public Sablier(Piste piste, Moto moto){
        this.min=min;
        this.sec=sec;
        this.piste=piste;
        this.moto=moto;
    }

    /**
     * soustraire la minute
     */
    public void Soustraire_min() {
        if (sec == 0) {
            min--;
            sec=59;
        }
    }

    /**
     * soustraire la seconde.
     */
    public void Soustraire_sec() {
     sec--;
    }

    /**
     * augmenter le temps total ，lorsque la moto dépasse le point de contrôle
     */
    public void getBonus() {
        int bonus = 30;
        if (piste.AvoirPremierPoint2&&
                Math.abs(piste.getTerminal().get(piste.getTerminal().size()-1).getY()- 800) <= 20 && canGetBonus) {
                if (sec <= 59-bonus) {
                    sec += bonus;
                    if (bonus>=5)
                        bonus--;
                } else {
                    sec = sec + bonus - 59;
                    min += 1;
                    if (bonus>=5)
                    bonus--;
                }
                canGetBonus =false;
            //System.out.print(index+"  ");
        }
        index++;//pour laisse une fois chaque point de contrôle
        if(index>=80){
            canGetBonus =true;
            index=0;
        }
    }

    /**
     * retourner la minute
     * @return min
     */
    public int getMin(){
        return min;
    }

    /**
     * retourner la seconde
     * @return sec
     */
    public int getSec(){
        return sec;
    }
}
