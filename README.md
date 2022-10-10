# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Navn Navnesen, S123456, s123456@oslomet.no

Deryja Sadi, s334886, s334886@oslomet.no
Leo Lieu, s364570, s364570@oslomet.no
Daniel Yirgalem Ogbalidet, s364536, s364536@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Leo har hatt hovedansvar for oppgave 1, 2a, 2b. 
* Deryja har hatt hovedansvar for oppgave 3, 7, 9. 
* Daniel har hatt hovedansvar for oppgave 4, 5, 6, 8, 10.

# Oppgavebeskrivelse

Oppgave 1
Lagde metodene antall() og tom(). Antall returnerer int og tom() returnerer boolean.
Ladge først DobbeltLenketListe() konstruktøren.
Deretter lagde vi DobbeltLenketListe(T [] a) konstruktøren. For denne konstruktøren sjekket vi først om a er null. 
Deretter sjekket vi om lengden til a var 0. Etter det lagde vi en antall-variabel, som skulle øke hver gang en ny node ble lagt til i lista. 
For å gå igjennom lista brukte vi en for - loop som skulle gå igjennom lista først for å finne første verdi som ikke var null. 
Dette skulle settes som både hale og hode i lista. Deretter gikk vi inn i en ny for - loop som gikk igjennom resten av lista og glyttet hodet til hver ny verdi som ble satt inn. 
antall - variablen økte hver gang en ny node ble satt inn. Når lista var tom satt vi inn en break for å avslutte for-løkken.

Oppgave 2a)
toString() - metoden ble laget ved hjelp av kompendiet. Men med små endringer i kode.
omvendtString() - metoden var nesten lik toString() - metoden bare at vi gikk fra hale til hode. Motsatt vei.

Oppgave 2b)
leggInn(T verdi) - metoden, sjekket først om verdien som skal legges inn er null
Hvis lista er tom, skal verdien legges på starten og være både hode og hale i lista
Ellers skal verdien legges inn på slutten av lista
Øker antall, endringer og returnerer true når den er ferdig.


Oppgave 3:

a) 
    Fremgangsmåte:
    Hvis indeks er mindre enn antall/2, så skal letingen starte fra hode og gå mot høyre ved hjelp av neste pekere,
    dermed setter man p lik hode, og en forløkke som går gjennom listen. Hvis ikke indeks er mindre enn anall/2, 
    så begynner letingen fra høyre ved å sette p lik hale, og samme forløkke men i negativ versjon. 
    
Etter det initialiserer man metoden hent med indekskontroll og false som paramterer, for så å returnere finnNode med indeks sin verdi
    Til slutt legger man til metoden oppdater som også består av indekskontroll og erstatter verdien på plass indeks
    med nyverdi, for så å returnere det som lå der. 
  
b) Fremgangsmåte:
/*
Først sjekker vi om indeksene fra og til er lovlige med to if setninger, deretter setter vi liste lik dobbeltlenketliste,
og p lik hode for å begynne fra venstre. Deretter legger vi til en forløkke som leser av listen,
med en if setning som setter inn p.verdi dersom indeks er større eller lik fra. Etter det setter vi p=p.neste og returnerer listen.
*/

Oppgave 4:
Her begynner jeg først med å sjekke om verdien er null. Deretter lager jeg en peker og kjører gjennom en loop for å finne verdien.
Når verdien er funnet returnerer jeg indeksen, hvis ikke returnerer jeg en -1 for å indikere at verdien ikke finnes i denne listen.

Oppgave 5:
Jeg starter med å først sjekke verdien og indeksen som blir sendt inn med kallet.
Skulle det ikke bli sendt en exception vil den gå gjennom en rekke else if setninger først som sjekker
om det skal legges til en ny node først eller sist i listen. På den måten kan vi bytte hode og hale til den nye noden som blir lagt inn.
Det sjekkes også om listen er tom som da vil gjør den nye noden til både hode og halen med pekere til null. Ellers vil det bli lagd en peker
som vil finne plassen med en for loop. Her vil den stoppe på indeksen før, og med bruk av en hjelpe node vil den kunne legge til en ny node
mellom disse to nodene.

Oppgave 6:
Her starter den ved å sjekke indeks med en if setning som vil sende et unntak ved ulovlig indeks. Deretter sjekkes det om hode eller
halen skal fjernes da vi må bytte disse skulle dette skje. Hvis ikke lager vi en peker som igjen går gjennom listen med en loop og finner noden som skal fjernes.
Peker stopper på indeksen før og bruker en hjelpe node som peker på noden som skal fjernes. Til slutt returnerer vi verdien til noden som ble fjernet. Ved fjern(indeks, verdi)
må også verdi sjekkes om den er lovlig. Denne metoden vil være ganske lik som den forrige, hvor den største forskjellen vil være at for loopen ikke kan stoppe før indeksen og må sjekke
hver nodes verdi. Skulle den finne en node med verdien vil den bli fjernet på akkurat samme måte som den første metoden og returnerer true som vil avslutte metoden. Ellers vil den
returnere false som vil si at verdien ikke finnes.


Oppgave 7:
Den raskeste metoden for nullstilling er metode 1. Fremgangsmåten gikk ut på å starte fra hode ved å sette p lik hode,
for så å sette q lik p.neste, for hver node nulles nodeverdien og alle nodens pekere ved å sette p.neste lik null,
p.forrige lik null og p.verdi lik null. Det er til resirkuleringen. Til slutt oppdaterer man p ved å sette
p=q, og til slutt teller vi opp endringer med endringer++, setter antall lik 0 og hode og hale til null.

Oppgave 8:
Her sjekkes det først om iteratorendringer er likt som vanlig endringer. Hvis dette er likt sjekkes det om hasNext returnerer true. Her sjekkes det om det finnes en neste. FjernOK vil bli byttet
til true som nå skal gjøre det mulig å fjerne en node. Den henter verdien som returneres og gjør om denne til neste noden.
Det lages også en Iterator metode som returnerer en instans av DobbelLenketListeIterator ved å returnere denne.
Det lages en konstruktør som lager en instans av klassen med starter denne ved en bestemt indeks. Dette gjøres ved å starte den med en for loop og en peker som stopper ved indeks og
gjør den om til denne.
Siste metoden Iterator med indeks sjekker førs indeksen med en indeksKontroll og bruker denne nye konstruktøren får å starte ved en bestemt indeks.


Oppgave 9:
Fremgangsmåte:
//Steg 1: Hode og hale nulles hvis det som skal fjernes er eneste verdi
//Steg 2: hvis den siste skal fjernes, så må hale oppdateres med q = hale, for så å sette hale lik forrige og hale.neste lik null
//Steg 3: Hvis den første skal fjernes, så må hode oppdateres ved å sette hode lik neste og forrige lik null.
//Steg 4: Hvis en node i listen skal fjernes, så må pekerne i hver side oppdateres 

Oppgave 10:
Her brukes det compareTo metoden i comparatoren. Ved hjelp av en sorteringsalgoritme kunne vi sortere listen som ble sendt inn. Da dette allerede er ganske tregt vil man bruke en effektiv algoritme.
Først ble det testet med utvalgssortering, men da jeg testet dette ved forskjellige arrays tok dette veldig lang tid ved større mengder. Da byttet jeg til quicksort i stede for en mer effektiv
sortering av listen. Her brukes det hent metoder og compareTo i quicksorten og tiden som den brukte sank betydelig. 



