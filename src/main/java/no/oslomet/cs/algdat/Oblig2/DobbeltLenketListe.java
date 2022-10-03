package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


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
        throw new UnsupportedOperationException();
    }

    public DobbeltLenketListe(T[] a) {
        throw new UnsupportedOperationException();
        //Sjekker om a er null

        //Sjekker om a har 0 i lengde

        // Setter inn siste elementet først, fordi det er lettere å sette elementene på starten av lista
        // Sjekker siste element i lista som != null
        // Bruk for-loop til å gå igjennom lista og lag noder som connecter til hverandre
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
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }

    public String omvendtString() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
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
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


