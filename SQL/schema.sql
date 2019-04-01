/*UPDATED 26.03.2019*/

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
    references users,
  is_deleted boolean       default false not null
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
    references category_groups,
  is_deleted boolean   default false not null
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
  url      varchar(256) not null,
  is_deleted boolean   default false not null
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
  name       varchar(64) not null,
  is_deleted boolean     default false not null
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
  abbr    varchar(4),
  is_deleted boolean   default false not null
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