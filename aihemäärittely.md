**Aihe:**

Toteutetaan jatkokehityskelpoinen 2D-peli käyttäen javan omia kirjastoja. Peli on suoraan ylhäältäpäin kuvattu ja
kamera on kiinnitetty pelaajaan (pelaaja aina ruudun keskellä). Pelaajaa käännetään hiirtä liikuttamalla (pelaaja osoittaa aina 
hiiren suuntaan). Pelaajan liikuttelu tapahtuu näppäimillä WASD ja liikkuminen on lineaarista. 

Pelaajan välittömään ympäristöönsyntyy jatkuvasti uusia vihollisia joita pelaaja voi ampua hiiren vasemmalla painikkeella(ammukset 
lähtevät hiiren osoittimen suuntaan). Viholliset pyrkivät kohti pelaajaa ja osuessaan poistavat pelaajalta yhden "elämän". Pelaajalla on 
tietty määrä elämiä ja kun elämät loppuvat loppuu myös peli ja pelaaja saa nähdä kuinka monta vihollista on tuhonnut.

Alustavasti taustana toimii kuva jossa ei ole "seiniä" mutta jos ehdin niin karttaan tulee myös seinät ja törmäyksentunnistus.



**Yleiset ominaisuudet:**

*Valikko*
- pelissä on valikko josta voi sulkea pelin tai lukea ohjeet(puu-rakenne jos laajenee isoksi)

*Grafiikka*
- itsetehty pixelikrafiikka gif-tiedostoina jotka javassa bufferedimageina



**Pelaajan toiminnot:**

*Ampuminen*
- pelaaja ampuu hiiren osoittimen suuntaaan pitämällä hiiren vasenta nappia pohjassa
- luodit lähtevät tietyin aikavälein ja tuhoutuvat kun ovat tietyn etäisyyden päässä pelaajasta
- luodin osuessa viholliseen sekä luoti että vihollinen tuhoutuu


*Kääntyminen*
- pelaaja on aina kääntyneenä hiiren osoittamaan suuntaan

*Liikkuminen*
- pelaaja liikkuu napeista WASD(neljään suuntaan, pitämällä pohjassa)
- liikkumisella on nopeus esim. 5 pixeliä/pelipäivitys



**Tekoälyn toiminnot:**

- tulee pelaajaa kohti ja osuessaan räjähtää

	