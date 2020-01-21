package hu.petrik.graffeladat;

public class CsucsAdat {
    private int forrasCsucs;
    private double koltseg;
    private boolean vizsgaltuk;

    public CsucsAdat( ) {
        this.forrasCsucs = -1;
        this.koltseg = Double.POSITIVE_INFINITY;
        this.vizsgaltuk = false;
    }

    public int getForrasCsucs() {
        return forrasCsucs;
    }

    public void setForrasCsucs(int forrasCsucs) {
        this.forrasCsucs = forrasCsucs;
    }

    public double getKoltseg() {
        return koltseg;
    }

    public void setKoltseg(double koltseg) {
        this.koltseg = koltseg;
    }

    public boolean isVizsgaltuk() {
        return vizsgaltuk;
    }

    public void setVizsgaltuk(boolean vizsgaltuk) {
        this.vizsgaltuk = vizsgaltuk;
    }
}
