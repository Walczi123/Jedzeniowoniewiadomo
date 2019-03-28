INSERT INTO category_groups (name)
VALUES ('dania'),
       ('kuchnie');
INSERT INTO categories (name, category_group_id)
VALUES ('śniadania', 1),
       ('drugie śniadania', 1),
       ('zupy', 1),
       ('obiady', 1),
       ('desery', 1),
       ('ciasta', 1),
       ('kolacje', 1),
       ('włoska', 2),
       ('francuska', 2),
       ( 'polska', 2);
INSERT INTO photos (url)
VALUES ('photoAddress1'),
       ('photoAddress2'),
       ('photoAddress3');
INSERT INTO products (name)
VALUES ('pomidor'),
       ('ziemniak'),
       ('marchew'),
       ('cebula'),
       ('czosnek'),
       ('filet z kurczaka'),
       ('mięso mielone'),
       ('mięso wołowe'),
       ('mieso wieprzowe'),
       ( 'frytki'),
       ( 'ryż do risotto'),
       ( 'białe wino'),
       ( 'czerwona papryka'),
       ( 'cukinia'),
       ( 'bulion'),
       ( 'parmezan'),
       ( 'gorgonzola'),
       ( 'masło'),
       ( 'oregano'),
       ( 'tymianek'),
       ( 'papryka słodka'),
       ( 'papryka ostra'),
       ( 'mąka pszenna'),
       ( 'cukier'),
       ( 'mleko'),
       ( 'olej'),
       ( 'proszek do pieczenia'),
       ( 'soda oczyszczona'),
       ( 'kakao'),
       ( 'jajka'),
       ( 'banan'),
       ( 'śmietana 30%'),
       ( 'cukier puder'),
       ( 'czekolada mleczna');
INSERT INTO products_photos (product_id, photo_id)
VALUES (1, 2),
       (5, 3);
INSERT INTO prices (value, product_id, date)
VALUES (7.53, 2, NOW()),
       (0.65, 10, NOW());
INSERT INTO users (login)
VALUES ('SuperUser'),
       ('AlaAdmin'),
       ('KubaAdmin'),
       ('PatrykAdmin');
INSERT INTO recipes (name, description, user_id)
VALUES ('przepis 1', '
 <recipe>
    <stages>
        <stage>Udka umyj, <b>posyp</b> <i>przyprawą<i> do kurczaka, podsmaż na smalcu lub oleju na większym ogniu po kilka minut na każdej stronie.</stage>
        <stage>Następnie zmniejsz ogień i posmaż jeszcze 15-20 min pod przykrywką.</stage>
        <stage>Gotowe.</stage>
    </stages>
</recipe>', 2),
       ('przepis2', '<recipe>
    <stages>
        <stage>Makaron ugotuj w solonej wodzie (zgodnie z przepisem na opakowaniu).</stage>
        <stage>Jak kiełbasa będzie prawie podsmażona, wrzuć do niej surowego pokrojonego w kostkę fileta i podsmaż do miękkości (Kilka minut).</stage>
        <stage>Sos wymieszaj z zimną wodą i śmietaną zgodnie z przepisem na opakowaniu: wlej na patelnie do podsmaonych składników i zagotuj 3-4 min. Ugotowany makaron wsym na patelnie, wymieszaj i podgrzej w sosie.</stage>
		<stage>Gotowe.</stage>
    </stages>
</recipe>', 2),
       ('risotto', '<recipe>
    <stages>
        <stage>W garnku na 1 łyżce oliwy zeszklić pokrojoną w kosteczkę cebulę oraz starty na tarce czosnek. Przesunąć je na bok garnka, a w wolne miejsce włożyć 1 łyżkę masła oraz pokrojonego w kosteczkę kurczaka, doprawić go solą oraz pieprzem i obsmażać przez około 3 minuty co chwilę mieszając.</stage>
        <stage>Dodać ryż i dokładnie go obsmażyć. Wlać wino i gotować przez kilkanaście sekund aż odparuje. Następnie dodać pokrojoną w kosteczkę paprykę i cukinię i smażyć razem przez około minutę. W międzyczasie dodać wszystkie przyprawy.</stage>
        <stage>Wlewać po około pół szklanki gorącego bulionu i gotować bez przykrycia od czasu do czasu mieszając przez około 15 minut. Dodać kolejną porcję bulionu gdy poprzednia będzie wchłonięta przez ryż. Na koniec ryż ma być ugotowany al dente.</stage>
        <stage>Odstawić z ognia, dodać 2/3 ilości tartego parmezanu, 2 łyżki masła oraz pokrojoną na kawałki gorgonzolę, wymieszać. Wyłożyć na talerze, posypać resztą sera.</stage>
		<stage>Gotowe.</stage>
    </stages>
</recipe>', 2),
       ('kopiec kreta', '<recipe>
    <stages>
          <stage>Ciasto: Jajka wbij do misy miksera i ubij z cukrem na gładki, puszysty krem. Mąkę wymieszaj z kakao, proszkiem do pieczenia i sodą. Na przemian dodawaj do ubitych jajek suche składniki przesiane przez sito, mleko oraz olej.</stage>
          <stage>Ciasto wylej do tortownicy o średnicy 24 cm wyłożonej papierem do pieczenia, bądź wysmarowanej tłuszczem i poprószonej kakao. Piecz w temperaturze 160 stopni, ok. 50 minut, termoobieg, do tzw. suchego patyczka.</stage>
          <stage>Z ciasta zetnij wierzch na wysokości około 2 cm. Wydrąż delikatnie połowę miąższu tak, aby zostały brzegi ciasta i nietknięty spód.</stage>
          <stage>Krem: Czekoladę pokrój na drobne kawałki. Śmietanę ubij na niskich obrotach z cukrem pudrem. Dodaj czekoladę i wymieszaj. Banany obierz ze skórki, wierzch skrop sokiem z cytryny, aby nie ściemniały. Ułóż w wydrążonym cieście. Nałóż śmietanę, kształtując kopiec. Wierzch posyp rozdrobnionym ciastem i lekko dociśnij.</stage>
          <stage>Ciasto schłodź w lodówce przez 30 minut.</stage>
      <stage>Gotowe.</stage>', 4),
	  ('Spaghetti Bolognese', '<recepie>
         <stage>Usmażyć na patelni mięso</stage>
         <stage>W między czasie zagotować wodę na makaron</stage>
         <stage>Wlać do mięsa passatę pomidorową</stage>
         <stage>Doprawić do smaku</stage>
         <stage>Ugotować makaron według przepisu na opakowaniu</stage>
     </recepie>', 2),
	 ('Chilli Con Carne', '<recepie>
         <stage>Usmażyć na patelni mięso</stage>
         <stage>Dodać pomidory w kostce</stage>
         <stage>Smażyć do czasu zmięknięcia pomidorów</stage>
         <stage>Dodać pokrojoną paprykę, kukurydzę i fasolę</stage>
         <stage>Doprawić solą, pieprzem i przyprawą do dań maksykańskich</stage>
     </recepie>', 2)
;

INSERT INTO recipes_categories (recipe_id, category_id)
VALUES (1, 2),
       (2, 1),
       (3, 4),
       (4, 6);
INSERT INTO recipes_photos (recipe_id, photo_id)
VALUES (2, 3);
INSERT INTO units (name, abbr)
VALUES ('kilogram', 'kg'),
       ('dekagram', 'deg'),
       ('gram', 'g'),
       ('tona', 't'),
       ('sztuka', ''),
       ('szklanka', ''),
       ('mililitr', 'ml'),
       ('litr', 'l'),
       ( 'łyżka', ''),
       ( 'łyżeczka', '');
INSERT INTO units_ratio (bigger_unit_id, smaller_unit_id, ratio)
VALUES (1, 2, 100),
       (1, 3, 1000),
       (2, 3, 10),
       (4, 1, 1000);
INSERT INTO products_recipes (product_id, recipe_id, amount, unit_id)
VALUES (1, 2, 10, 2),
       (2, 1, 100, 3),
       (4, 3, 1, 5),
       (5, 3, 1, 5),
       (6, 3, 1, 5),
       (11, 3, 300, 3),
       (12, 3, 1, 6),
       (13, 3, 1, 5),
       (14, 3, 200, 3),
       (15, 3, 500, 7),
       (16, 3, 1.3, 6),
       (17, 3, 50, 3),
       (18, 3, 2, 9),
       (19, 3, 0.5, 10),
       (20, 3, 0.5, 10),
       (21, 3, 0.5, 10),
       (23, 4, 340, 3),
       (24, 4, 220, 3),
       (25, 4, 250, 7),
       (26, 4, 210, 7),
       (27, 4, 1, 10),
       (28, 4, 1.2, 10),
       (29, 4, 4, 9),
       (30, 4, 2, 5),
       (31, 4, 5, 5),
       (32, 4, 500, 7),
       (33, 4, 4, 9),
       (34, 4, 100, 3);