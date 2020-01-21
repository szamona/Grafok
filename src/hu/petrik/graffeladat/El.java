package hu.petrik.graffeladat;

/**
 * A gráf egy éle, és a hozzá tartozó adatok.
 */
class El {
    /**
     * Az egyik csúcs indexe
     */
    private int csucs1;
    /**
     * A másik csúcs indexe
     */
    private int csucs2;

    /**
     * Létrehoz egy úgy élt.
     * 
     * @param csucs1 Az egyik csúcs indexe
     * @param csucs2 A másik csúcs indexe
     */
    public El(int csucs1, int csucs2) {
        this.csucs1 = csucs1;
        this.csucs2 = csucs2;
    }

    /**
     * @return Az egyik csúcs indexe
     */
    public int getCsucs1() {
        return csucs1;
    }

    /**
     * @return A másik csúcs indexe
     */
    public int getCsucs2() {
        return csucs2;
    }

    @Override
    public String toString() {
        return String.format("(%d - %d)", csucs1, csucs2);
    }
}
