package hu.petrik.graffeladat;

import java.util.*;

/**
 * Irányítatlan, egyszeres gráf.
 */
public class Graf {
    private final int csucsokSzama;
    /**
     * A gráf élei.
     * Ha a lista tartalmaz egy (A,B) élt, akkor tartalmaznia kell
     * a (B,A) vissza irányú élt is.
     */
    private final ArrayList<El> elek = new ArrayList<>();
    /**
     * A gráf csúcsai.
     * A gráf létrehozása után új csúcsot nem lehet felvenni.
     */
    private final ArrayList<Csucs> csucsok = new ArrayList<>();

    /**
     * Létehoz egy úgy, N pontú gráfot, élek nélkül.
     *
     * @param csucsok A gráf csúcsainak száma
     */
    public Graf(int csucsok) {
        this.csucsokSzama = csucsok;

        // Minden csúcsnak hozzunk létre egy új objektumot
        for (int i = 0; i < csucsok; i++) {
            this.csucsok.add(new Csucs(i));
        }
    }

    /**
     * Hozzáad egy új élt a gráfhoz.
     * Mindkét csúcsnak érvényesnek kell lennie:
     * 0 &lt;= cs &lt; csúcsok száma.
     *
     * @param cs1 Az él egyik pontja
     * @param cs2 Az él másik pontja
     */
    public void hozzaad(int cs1, int cs2) {
        if (cs1 < 0 || cs1 >= csucsokSzama ||
                cs2 < 0 || cs2 >= csucsokSzama) {
            throw new IndexOutOfBoundsException("Hibas csucs index");
        }

        // Ha már szerepel az él, akkor nem kell felvenni
        for (El el : elek) {
            if (el.getCsucs1() == cs1 && el.getCsucs2() == cs2) {
                return;
            }
        }

        elek.add(new El(cs1, cs2));
        elek.add(new El(cs2, cs1));
    }

    public void szelessegiBejar(int kezdopont) {
        List<Integer> bejart = new ArrayList<>();
        Queue<Integer> kovetkezok = new LinkedList<>();

        kovetkezok.add(kezdopont);
        bejart.add(kezdopont);

        while (kovetkezok.size() > 0) {
            var k = kovetkezok.poll();
//            kovetkezok.remove(kovetkezok.get(0));
            System.out.println(this.csucsok.get(k));

            for (var el : this.elek) {
                if (el.getCsucs1() == k && !bejart.contains(el.getCsucs2())) {
                    kovetkezok.add(el.getCsucs2());
                    bejart.add(el.getCsucs2());
                }
            }
        }
    }

    public void melysegiBejar(int kezdopont) {
        List<Integer> bejart = new ArrayList<>();
        Stack<Integer> kovetkezok = new Stack<>();

        kovetkezok.add(kezdopont);
        bejart.add(kezdopont);

        while (kovetkezok.size() > 0) {
            var k = kovetkezok.pop();

            System.out.println(this.csucsok.get(k));

            for (var el : this.elek) {
                if (el.getCsucs1() == k && !bejart.contains(el.getCsucs2())) {
                    kovetkezok.add(el.getCsucs2());
                    bejart.add(el.getCsucs2());
                }
            }

        }
    }

    public boolean osszefuggo() {
        List<Integer> bejart = new ArrayList<>();
        Queue<Integer> kovetkezok = new LinkedList<>();

        kovetkezok.add(0);
        bejart.add(0);

        while (kovetkezok.size() > 0) {
            var k = kovetkezok.poll();
            for (var el : this.elek) {
                if (el.getCsucs1() == k && !bejart.contains(el.getCsucs2())) {
                    kovetkezok.add(el.getCsucs2());
                    bejart.add(el.getCsucs2());
                }
            }
        }

        return bejart.size() == this.csucsokSzama;
    }

    public Graf feszitofa() {
        Graf fa = new Graf(this.csucsokSzama);
        List<Integer> bejart = new ArrayList<>();
        Queue<Integer> kovetkezok = new LinkedList<>();

        kovetkezok.add(0);
        bejart.add(0);

        while (kovetkezok.size() > 0) {
            var k = kovetkezok.poll();

            for (var el : this.elek) {
                if (el.getCsucs1() == k && !bejart.contains(el.getCsucs2())) {
                    kovetkezok.add(el.getCsucs1());
                    bejart.add(el.getCsucs2());
                    fa.hozzaad(el.getCsucs1(), el.getCsucs2());
                }
            }

        }
        return fa;

    }

    public HashMap mohoSzinezes() {
        var szinezes = new HashMap<Integer, Integer>();
        var maxSzin = this.csucsokSzama;

        for (int i = 0; i < this.csucsokSzama; i++) {
            List<Integer> valaszthatoSzinek = new ArrayList<>();
            for (int j = 0; j < maxSzin; j++) {
                valaszthatoSzinek.add(j);
            }

            for (var el : this.elek) {
//                if (el.getCsucs1() == this.csucsok.get(i) && szinezes.containsKey(el.getCsucs2())) {
                if (el.getCsucs1() == i && szinezes.containsKey(el.getCsucs2())) {
                    var szin = szinezes.get(el.getCsucs2());
                    valaszthatoSzinek.remove(szin);
                }
            }
            var valasztottSzin = Collections.min(valaszthatoSzinek);
            szinezes.put(i, valasztottSzin);
        }
        return szinezes;
    }

    public HashMap dijkstra(int egesz) {
        var csucsAdatok = new HashMap<Integer, CsucsAdat>();
        for (int i = 0; i < this.csucsokSzama - 1; i++) {
            csucsAdatok.put(i, new CsucsAdat());
            csucsAdatok.get(0).setKoltseg(0);

            var vizsgaltDarab = 0;
            while (vizsgaltDarab < this.csucsokSzama) {
                vizsgaltDarab++;

                var vizsgaltCsucs = this.KovetkezoCsucs(csucsAdatok);
                csucsAdatok.get(vizsgaltCsucs).setVizsgaltuk(true);

                for (var el : this.elek) {
                    if (el.getCsucs1() == i) {
                        var ujKoltseg = csucsAdatok.get(vizsgaltCsucs).getKoltseg() + el.getSuly();
                        if (ujKoltseg < csucsAdatok.get(el.getCsucs2()).getKoltseg()) {
                            csucsAdatok.get(el.getCsucs2()).setKoltseg(ujKoltseg);
                            csucsAdatok.get(el.getCsucs2()).setForrasCsucs(i);
                        }
                    }
                }
            }

        }
        return csucsAdatok;
    }

    public int KovetkezoCsucs(HashMap<Integer, CsucsAdat> csucsAdatok) {
        var minIndex = 0;
        for (var Index : csucsAdatok.keySet()) {
            if (csucsAdatok.get(Index).isVizsgaltuk() == false && csucsAdatok.get(Index).getKoltseg() < csucsAdatok.get(minIndex).getKoltseg()) {
                minIndex = Index;
            }
        }
        return minIndex;
    }

    @Override
    public String toString() {
        String str = "Csucsok:\n";
        for (Csucs cs : csucsok) {
            str += cs + "\n";
        }
        str += "Elek:\n";
        for (El el : elek) {
            str += el + "\n";
        }
        return str;
    }
}
