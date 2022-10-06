package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


//Sjekk den her
//Sjekk den her, Leo

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

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
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
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
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

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

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

        @Override
        public boolean hasNext() {
            return denne != null;
        }

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

//Mangler siste else, og metoden public T next fra tidligere oppgaver må gjøres for at metoden remove skal fungere
//Inspirert fra kompendiet programkode 3.3.4 d, og kap 3 kildekode

        @Override
        public void remove() {
            //vet ikke når det ikke er lov å remove, bør sjekke testkode kanskje
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
            } else if (denne == null) { //Oppgave 2: hvis den siste skal fjernes, så må hale oppdateres
                q = hale;
                hale = hale.forrige;
                hale.neste = null;
            }
            else if (denne.forrige == hode) { //Oppgave 3: Hvis den første skal fjernes, så må hode oppdateres
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
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


