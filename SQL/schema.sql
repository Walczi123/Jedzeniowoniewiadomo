/*UPDATED 25.03.2019*/

create table if not exists users
(
  user_id serial      not null
    constraint users_pkey
    primary key,
  login   varchar(64) not null
);
create unique index if not exists users_login_uindex
  on users (login);
create table if not exists recipes
(
  recipe_id  serial       not null
    constraint recipes_pkey
    primary key,
  name        varchar(128) not null,
  description text,
  user_id     integer
    constraint recipes_users_user_id_fk
    references users
);
create table if not exists category_groups
(
  category_group_id serial      not null
    constraint category_groups_pkey
    primary key,
  name              varchar(64) not null
);
create unique index if not exists category_groups_name_uindex
  on category_groups (name);
create table if not exists categories
(
  category_id       serial      not null
    constraint categories_pkey
    primary key,
  name              varchar(64) not null,
  category_group_id integer     not null
    constraint categories_category_groups_category_group_id_fk
    references category_groups
);
create table if not exists recipes_categories
(
  recipe_id  integer not null
    constraint recipes_categories_recipes_recipe_id_fk
    references recipes,
  category_id integer not null
    constraint recipes_categories_categories_category_id_fk
    references categories,
  constraint recipes_categories_pk
  primary key (category_id, recipe_id)
);
create table if not exists photos
(
  photo_id serial       not null
    constraint photos_pkey
    primary key,
  url      varchar(256) not null
);
create table if not exists recipes_photos
(
  recipe_id integer not null
    constraint recipes_photos_recipes_recipe_id_fk
    references recipes,
  photo_id   integer not null
    constraint recipes_photos_photos_photo_id_fk
    references photos,
  constraint recipes_photos_pk
  primary key (photo_id, recipe_id)
);
create table if not exists products
(
  product_id serial      not null
    constraint products_pkey
    primary key,
  name       varchar(64) not null
);
create table if not exists products_photos
(
  product_id integer not null
    constraint products_photos_products_product_id_fk
    references products,
  photo_id   integer not null
    constraint products_photos_photos_photo_id_fk
    references photos,
  constraint products_photos_pk
  primary key (product_id, photo_id)
);
create table if not exists prices
(
  price_id   serial        not null,
  value      numeric(6, 2) not null
    constraint prices_pkey
    primary key,
  product_id integer       not null
    constraint prices_products_product_id_fk
    references products,
  date       timestamp     not null
);
create table if not exists units
(
  unit_id serial      not null
    constraint units_pkey
    primary key,
  name    varchar(32) not null,
  abbr    varchar(4)
);
create table if not exists products_recipes
(
  product_id integer       not null
    constraint products_recipes_products_product_id_fk
    references products,
  recipe_id integer       not null
    constraint products_recipes_recipes_recipe_id_fk
    references recipes,
  amount     numeric(6, 3) not null,
  unit_id    integer       not null
    constraint products_recipes_units_unit_id_fk
    references units,
  constraint products_recipes_pk
  primary key (product_id, recipe_id)
);
create table if not exists units_ratio
(
  bigger_unit_id  integer not null
    constraint units_ratio_units_unit_id_fk
    references units,
  smaller_unit_id integer not null
    constraint units_ratio_units_unit_id_fk_2
    references units,
  ratio           integer not null,
  constraint units_ratio_pk
  primary key (smaller_unit_id, bigger_unit_id)
);

INSERT INTO category_groups (category_group_id, name) VALUES
(1,'dania'),(2,'kuchnie')ON CONFLICT DO NOTHING;
INSERT INTO categories ( category_id,name, category_group_id) VALUES
(1,'śniadania',1),(2,'drugie śniadania',1),(3,'zupy',1),(4,'obiady',1),(5,'desery',1),(6,'kolacje',1),
(7,'włoska',2),(8,'francuska',2),(9,'polska',2)ON CONFLICT DO NOTHING;
INSERT INTO photos(photo_id, url) VALUES
(1,'photoAddress1'),(2,'photoAddress2'),(3,'photoAddress3')ON CONFLICT DO NOTHING;
INSERT INTO products(product_id,name) VALUES
(1,'pomidor'),(2,'ziemniak'),(3,'marchew'),(4,'cebula'),(5,'czosnek'),(6,'kurczak'),(7,'mięso mielone'),(8,'mięso wołowe'),(9,'mieso wieprzowe'),(10,'frytki')ON CONFLICT DO NOTHING;
INSERT INTO products_photos(product_id,photo_id) VALUES
(1,2),(5,3)ON CONFLICT DO NOTHING;
INSERT INTO prices(price_id, value, product_id, date) VALUES
(1,7.53, 2,NOW()),(2,0.65, 10,NOW())ON CONFLICT DO NOTHING;
INSERT INTO users(user_id, login) VALUES
(0,'SuperUser'),(1,'AlaAdmin'),(2,'KubaAdmin'),(6,'PatrykAdmin')ON CONFLICT DO NOTHING;
INSERT INTO recipes(recipe_id, name, description, user_id) VALUES
(1,'przepis 1','
 <recipe>
    <stages>
        <stage>Udka umyj, <b>posyp</b> <i>przyprawą<i> do kurczaka, podsmaż na smalcu lub oleju na większym ogniu po kilka minut na każdej stronie.</stage>
        <stage>Następnie zmniejsz ogień i posmaż jeszcze 15-20 min pod przykrywką.</stage>
        <stage>Gotowe.</stage>
    </stages>
</recipe>',1),
(2,'przepis2','<recipe>
    <stages>
        <stage>Makaron ugotuj w solonej wodzie (zgodnie z przepisem na opakowaniu).</stage>
        <stage>Jak kiełbasa będzie prawie podsmażona, wrzuć do nie jurowego pokrojonego w kostkę fileta i podsmaż do miękkości (Kilka minut).</stage>
        <stage>Sos wymieszaj z zimną wodą i śmietaną zgodnie z przepisem na opakowaniu: wlej na patelnie do podsmaonych składników i zagotuj 3-4 min. Ugotowany makaron wsym na patelnie, wymieszaj i podgrzej w sosie.</stage>
		<stage>Gotowe.</stage>
    </stages>
</recipe>',2)ON CONFLICT DO NOTHING;
INSERT INTO recipes_categories(recipe_id, category_id) VALUES
(1,2),(2,1)ON CONFLICT DO NOTHING;
INSERT INTO recipes_photos(recipe_id, photo_id) VALUES(2,3) ON CONFLICT DO NOTHING;
INSERT INTO units(unit_id, name, abbr) VALUES
(1,'kilogram','kg'),(2,'dekagram','deg'),(3,'gram','g'),(4,'tona','t')ON CONFLICT DO NOTHING;
INSERT INTO units_ratio(bigger_unit_id, smaller_unit_id, ratio) VALUES
(1,2,100),(1,3,1000),(2,3,10),(4,1,1000)ON CONFLICT DO NOTHING;
INSERT INTO products_recipes(product_id, recipe_id, amount, unit_id) VALUES
(1,2,10,2),(2,1,100,3) ON CONFLICT DO NOTHING;
