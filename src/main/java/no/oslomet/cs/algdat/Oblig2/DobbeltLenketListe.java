package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;

public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        // throw new UnsupportedOperationException();
        hode = hale = null;        // hode og hale til null
        antall = 0;                // ingen verdier - listen er tom
        endringer = 0;       // ingen endringer når vi starter
    }

    public DobbeltLenketListe(T[] a) {
        // throw new UnsupportedOperationException();

        //Sjekker om a er null
        Objects.requireNonNull(a, "Tabellen a er null!");

        //Sjekker om a har 0 i lengde
        if (a.length == 0){
            hode = hale = null;
        }

        // Setter inn siste elementet først, fordi det er lettere å sette elementene på starten av lista
        // Sjekker siste element i lista som != null
        antall = 0;
        for (int i = a.length - 1; i >= 0; i--){
            if (a[i] != null){ //finner det siste elementet i lista som ikke er 0
                hode = hale = new Node<>(a[i], null, null); // NB! Sjekk om den er null!!!!
                antall++; //teller opp antall
                for (int j = i - 1; j >= 0; j--){
                    if (a[j] != null){
                        hode.forrige = new Node<>(a[j], null, hode);
                        hode = hode.forrige;
                        antall++; //teller opp antall;
                    }
                }
                break; // stopper for-loopen
            }
        }
    }

//Oppgave 3a

    //Fra kompendiet programkode 3.3.3 a
    private Node<T> finnNode(int indeks){

        Node<T> p;
        if(indeks<this.antall/2){
            p = this.hode;
            for (int i = 0; i < indeks; i++) p = p.neste;}

        else{
            //Nå samme forløkke fra kompendiet men vi må la den kjøre motsatt retning med negative verdier
            // (for at den skal gå fra halen mot venstre)
            p = this.hale;
            for (int i=this.antall-1;i>indeks;i--){
                p = p.forrige;}
        }
        return p;
    }

    //Fra kompendiet 3.3.3 b
    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }


    /*
     Lag  også  metoden T  oppdater(int  indeks,  T  nyverdi).  Den  skal  erstatte
verdien på plass indeks med nyverdi og returnere det som lå der fra før. Husk at indeks må
sjekkes, at null-verdier ikke skal kunne legges inn og at variabelen endringer skal økes.
     */

    //inspirert av kompendiet programkode 3.3.3 b og kildekode kap 3
    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi, "Ikke tillatt med null-verdier!");

        indeksKontroll(indeks, false);  // Se Liste, false: indeks = antall er ulovlig

        Node<T> p = finnNode(indeks);
        T gammelVerdi = p.verdi;

        p.verdi = nyverdi;
        endringer++;
        return gammelVerdi;
    }





    //Oppgave 3b

    public Liste<T> subliste(int fra, int til) {
        if(fra < 0 || til > antall) throw new IndexOutOfBoundsException();
        if(fra > til) throw new IllegalArgumentException();

        Liste<T> list = new DobbeltLenketListe<>();

        Node<T> p = hode;

        for(int i = 0; i < til; i++) {
            if(i >= fra) {
                list.leggInn(p.verdi);
            }
            p = p.neste;
        }

        return list;
    }

    @Override
    public int antall() {
        // throw new UnsupportedOperationException();
        return antall;
    }

    @Override
    public boolean tom() {
        // throw new UnsupportedOperationException();
        if (antall == 0){
            return true;
        }
        else return false;
    }

    @Override
    public boolean leggInn(T verdi) {
        // throw new UnsupportedOperationException();
        if (verdi == null){
            Objects.requireNonNull(verdi, "Kan ikke legge inn null-verdi!"); //Metode for å sjekke om verdi er null?
            return false;
        }

        if (antall == 0){
            hode = hale = new Node<>(verdi, null, null);
        }

        else {
            hale.neste = new Node<>(verdi, hale, null);
            hale = hale.neste;
        }

        antall++;
        endringer++;
        return true;
    }

    //Inspirert fra programkode 3.2.3 b)
    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi);
        if(indeks < 0 || indeks > antall) throw new IndexOutOfBoundsException(melding(indeks));

        else if(antall == 0) hode = hale = new Node<>(verdi, null, null);
        else if(indeks == 0) hode = hode.forrige = new Node<>(verdi, null, hode);
        else if(indeks == antall) hale = hale.neste = new Node<>(verdi, hale, null);
        else {
            Node<T> p = hode;
            Node<T> q;
            for(int i = 0; i < indeks - 1; i++) {
                p = p.neste;
            }

            q = p.neste;
            Node<T> ny = new Node<>(verdi, p, q);
            p.neste = ny;
            q.forrige = ny;
        }
        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        //Sjekker med indeksTil om verdien finnes eller om vi får -1 tilbake
        return indeksTil(verdi) != -1;
    }



    @Override
    public int indeksTil(T verdi) {
        //Sjekke først om verdien er null
        if(verdi == null) {
            return -1;
        }

        //lager en peker som går gjennom listen og sjekker hver verdi mot parameter verdien
        Node<T> p = hode;
        for(int i = 0; i < antall; i++) {
            if(p.verdi.equals(verdi)) return i;
            p = p.neste;
        }

        //returnerer -1 hvis den ikke finner verdien
        return -1;
    }


    //Inspirasjon hentet fra programkode 3.3.3 c)
    @Override
    public boolean fjern(T verdi) {
        if(verdi == null) return false;

        if(hode.verdi.equals(verdi)) {
            hode = hode.neste;
            if(hode != null) hode.forrige = null;
        }
        else if(hale.verdi.equals(verdi)) {
            hale = hale.forrige;
            if(hale != null) hale.neste = null;
        }
        else {
            Node<T> p = hode;
            Node<T> q;
            for(int i = 0; i < antall - 1; i++) {
                if(p.neste.verdi.equals(verdi)) {
                    q = p.neste;
                    if(q.equals(hale)) {
                        hale = p;
                    }

                    p.neste = q.neste;
                    q.neste.forrige = p;

                    antall--;
                    endringer++;
                    return true;
                }

                p = p.neste;
            }
            return false;
        }
        antall--;
        endringer++;
        return true;
    }

    //Inspirasjon fra programbit 3.3.3 c)
    @Override
    public T fjern(int indeks) {
        if(antall == 0 || indeks < 0 || indeks > antall - 1) throw new IndexOutOfBoundsException(melding(indeks));
        T verdi;

        if(indeks == 0) {
            verdi = hode.verdi;
            hode = hode.neste;
            if(antall > 1) hode.forrige = null;
        }
        else if (indeks == antall - 1) {
            verdi = hale.verdi;
            hale = hale.forrige;
            if(antall > 1) hale.neste = null;
        }
        else {
            Node<T> p = hode;
            for(int i = 0; i < indeks - 1; i++) {
                p = p.neste;
            }

            Node<T> q = p.neste;
            if(q.equals(hale)) hale = p;
            p.neste = q.neste;
            q.neste.forrige = p;
            verdi = q.verdi;
        }

        antall--;
        endringer++;
        return verdi;
    }

    //Oppgave 7

    public void nullstill()
    {


        //Begynn på hodet først:
        Node<T> p = hode;

        //Inspirert fra kompendiet  3.2.3 oppgave 2
        if (p != null){

         /* Vi setter q = p.neste, og deretter nullifiserer vi p.neste og p.verdi
            p er hodet, og ved hjelp av p.neste kan vi flytte den til neste node ved å sette lik q, for så å
            nullifisere forrige node ved å deretter sette p.neste og q.verdi lik "null", også fortsetter den videre slik
            helt til hodet og halen har blitt nullifisert, antall = 0 og endringer økes med ++  */


            Node<T> q = p.neste;

            p.neste = null;     // Dette er til resirkuleringen
            p.forrige = null;   // Dette er til resirkuleringen
            p.verdi = null;     // Dette er til resirkuleringen

            //   Nå oppdaterer vi Node q --> med p = q;
            p = q;
        }
        antall = 0;
        endringer++;
        hode = hale = null;
    }

    // hentet fra EnkeltLenketListe klassen i kompendiet, med noen endringer
    @Override
    public String toString() {
        // throw new UnsupportedOperationException();
        StringBuilder s = new StringBuilder();

        s.append('[');

        //sjekker om lista er tom
        if (!tom()) {
            Node<T> peker = hode;
            s.append(peker.verdi);

            peker = peker.neste;

            while (peker != null) { //går igjennom hele lista til pekern peker på null-verdien til hale.neste
                s.append(',').append(' ').append(peker.verdi);
                peker = peker.neste;
            }
        }

        s.append(']');
        return s.toString();
    }

    public String omvendtString() {
        // throw new UnsupportedOperationException();
        StringBuilder s = new StringBuilder();

        s.append('[');

        //sjekker om lista er tom
        if (!tom()) {
            Node<T> peker = hale;
            s.append(peker.verdi);

            peker = peker.forrige;

            while (peker != null) { //går igjennom hele lista til pekern peker på null-verdien til hale.neste
                s.append(',').append(' ').append(peker.verdi);
                peker = peker.forrige;
            }
        }

        s.append(']');
        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            Node<T> p = hode;
            for(int i = 0; i < indeks; i++) {
                p = p.neste;
            }

            denne = p;
            fjernOK = false;
            iteratorendringer = endringer;
        }

        //Brukt programkode 3.3.4 b)
        @Override
        public boolean hasNext() {
            return denne != null;
        }

        //inspirasjon fra 3.3.4 c)
        @Override
        public T next() {
            if(iteratorendringer != endringer) throw new ConcurrentModificationException();
            if(!hasNext()) throw new NoSuchElementException();

            fjernOK = true;
            T denneVerdi = denne.verdi;;
            denne = denne.neste;

            return denneVerdi;
        }


        //Hode er peker til den første noden, og hale er peker til den siste noden
        //forrige og neste er pekerne i listen


//Inspirert fra kompendiet programkode 3.3.4 d, og kap 3 kildekode

        @Override
        public void remove() {

            if (antall == 0 || !fjernOK) {
                throw new IllegalStateException("Antall i listen kan ikke være 0");
            }
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException();
            }
            fjernOK = false;

            Node<T> q = hode;
            if (antall == 1) { //Oppgave 1: Hode og hale nulles hvis det som skal fjernes er eneste verdi
                hode = hale = null;
            } else if (denne == null) { //Oppgave 2: hvis den siste skal fjernes, så oppdaterer vi halen ved å
                // sette denne =null;
                q = hale;
                hale = hale.forrige;
                hale.neste = null;
            }
            else if (denne.forrige == hode) {
                //Oppgave 3: Hvis den første skal fjernes, så må hode oppdateres

                hode = hode.neste;
                hode.forrige = null;
            } else {
                q = denne.forrige;
                q.forrige.neste = q.neste;
                q.neste.forrige = q.forrige;
            }

            antall--; //Antall skal reduseres og både iteratorer og endringer skal økes
            endringer++;
            iteratorendringer++;
        }
    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        quickSort(liste, 0, liste.antall() - 1, c);
    }

    //Hentet inspirasjon fra kapittel 1.5.7
    private static <T> void quickSort(Liste<T> liste, int lavIndeks, int høyIndeks, Comparator<? super T> c) {
        if (lavIndeks >= høyIndeks) return;

        //lager en random pivot som jeg "sender" til slutten av arraeyet
        int pivotIndeks = new Random().nextInt(høyIndeks - lavIndeks) + lavIndeks;
        T pivot = liste.hent(pivotIndeks);
        bytt(liste, pivotIndeks, høyIndeks);

        //venstrePeker er hvor vi deler arraeyet til mindre array hvor vi gjør algoritmer igjen rekursivt
        int venstrePeker = partisjon(liste, lavIndeks, høyIndeks, pivot, c);


        quickSort(liste, lavIndeks, venstrePeker - 1, c);
        quickSort(liste, venstrePeker + 1, høyIndeks, c);
    }

    private static <T> int partisjon(Liste<T> liste, int lavIndeks, int høyIndeks, T pivot, Comparator<? super T> c) {
        int høyrePeker = høyIndeks;
        int venstrePeker = lavIndeks;

        while (venstrePeker < høyrePeker) {
            //Finner tall som er høyere enn pivot tallet
            while (c.compare(liste.hent(venstrePeker), pivot) <= 0  && venstrePeker < høyrePeker) {
                venstrePeker++;
            }

            //finner tall som er mindre enn pivot
            while (c.compare(liste.hent(høyrePeker), pivot) >= 0 && venstrePeker < høyrePeker) {
                høyrePeker--;
            }

            //bruker partisjonering for å bytte plass på tallene
            bytt(liste, venstrePeker, høyrePeker);
        }

        //bytter tilbake pivot tallet som nå vil ligge på rett plass i forhold til de andre tallene
        bytt(liste, venstrePeker, høyIndeks);
        return venstrePeker;
    }

    public static <T> void bytt(Liste<T> liste, int i, int j) {
        T iVerdi = liste.hent(i);
        T jVerdi = liste.hent(j);

        liste.oppdater(j, iVerdi);
        liste.oppdater(i, jVerdi);
    }

} // class DobbeltLenketListe


