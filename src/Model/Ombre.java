package Model;

import Vue.Affichage;


/**
 * Afin de rendre le véhicule plus tridimensionnel, des ombres sont ajoutées.
 */
public class Ombre extends Paysage {
    private Moto moto;
    public int W = 200;//weight de l'ombre
    public int H = 100;//height de l'ombre

    public Ombre(String ombre, Moto moto) {
        super(ombre);
        this.moto = moto;
        this.x = moto.getAbsc()-50;
        this.y = Affichage.HAUT - 70;
    }

    public void setW(int w) {
        W += w;
        this.x -= w;
    }
}