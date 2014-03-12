**Aihe:**

Toteutetaan jatkokehityskelpoinen 2D-peli k�ytt�en javan omia kirjastoja. Peli on suoraan ylh��lt�p�in kuvattu ja
kamera on kiinnitetty pelaajaan (pelaaja aina ruudun keskell�). Pelaajaa k��nnet��n hiirt� liikuttamalla (pelaaja osoittaa aina 
hiiren suuntaan). Pelaajan liikuttelu tapahtuu n�pp�imill� WASD ja liikkuminen on lineaarista. 

Pelaajan v�litt�m��n ymp�rist��nsyntyy jatkuvasti uusia vihollisia joita pelaaja voi ampua hiiren vasemmalla painikkeella(ammukset 
l�htev�t hiiren osoittimen suuntaan). Viholliset pyrkiv�t kohti pelaajaa ja osuessaan poistavat pelaajalta yhden "el�m�n". Pelaajalla on 
tietty m��r� el�mi� ja kun el�m�t loppuvat loppuu my�s peli ja pelaaja saa n�hd� kuinka monta vihollista on tuhonnut.

Alustavasti taustana toimii kuva jossa ei ole "seini�" mutta jos ehdin niin karttaan tulee my�s sein�t ja t�rm�yksentunnistus.



**Yleiset ominaisuudet:**

*Valikko*
- peliss� on valikko josta voi sulkea pelin tai lukea ohjeet(puu-rakenne jos laajenee isoksi)

*Grafiikka*
- itsetehty pixelikrafiikka gif-tiedostoina jotka javassa bufferedimageina



**Pelaajan toiminnot:**

*Ampuminen*
- pelaaja ampuu hiiren osoittimen suuntaaan pit�m�ll� hiiren vasenta nappia pohjassa
- luodit l�htev�t tietyin aikav�lein ja tuhoutuvat kun ovat tietyn et�isyyden p��ss� pelaajasta
- luodin osuessa viholliseen sek� luoti ett� vihollinen tuhoutuu


*K��ntyminen*
- pelaaja on aina k��ntyneen� hiiren osoittamaan suuntaan

*Liikkuminen*
- pelaaja liikkuu napeista WASD(nelj��n suuntaan, pit�m�ll� pohjassa)
- liikkumisella on nopeus esim. 5 pixeli�/pelip�ivitys



**Teko�lyn toiminnot:**

- tulee pelaajaa kohti ja osuessaan r�j�ht��

	