package hu.petrik.graffeladat;

/**
 * A gráf egy csúcsa, és a hozzá tartozó adatok.
 */
class Csucs {
    /**
     * A csúcs azonosítója a gráfban
     */
    private int id;

    /**
     * Létrehoz egy új csúcsot a gráfban
     * 
     * @param id A csúcs azonosítója a gráfban
     */
    public Csucs(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return Integer.toString(this.id);
    }
}
