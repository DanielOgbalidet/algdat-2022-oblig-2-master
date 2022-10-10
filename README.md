# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Navn Navnesen, S123456, s123456@oslomet.no

Deryja Sadi, s334886, s334886@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Per har hatt hovedansvar for oppgave 1, 3, og 5. 
* Else har hatt hovedansvar for oppgave 2, 4, og 6. 
* Fatima har hatt hovedansvar for oppgave 7 og 8. 
* Vi har i fellesskap løst oppgave 10. 



Deryja gjorde oppgave 3, 7 og 9.


# Oppgavebeskrivelse


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




Oppgave 7:
Den raskeste metoden for nullstilling er metode 1. Fremgangsmåten gikk ut på å starte fra hode ved å sette p lik hode,
for så å sette q lik p.neste, for hver node nulles nodeverdien og alle nodens pekere ved å sette p.neste lik null,
p.forrige lik null og p.verdi lik null. Det er til resirkuleringen. Til slutt oppdaterer man p ved å sette
p=q, og til slutt teller vi opp endringer med endringer++, setter antall lik 0 og hode og hale til null.


Oppgave 9:

Fremgangsmåte:
//Steg 1: Hode og hale nulles hvis det som skal fjernes er eneste verdi
//Steg 2: hvis den siste skal fjernes, så må hale oppdateres med q = hale, for så å sette hale lik forrige og hale.neste lik null
//Steg 3: Hvis den første skal fjernes, så må hode oppdateres ved å sette hode lik neste og forrige lik null.
//Steg 4: Hvis en node i listen skal fjernes, så må pekerne i hver side oppdateres 



