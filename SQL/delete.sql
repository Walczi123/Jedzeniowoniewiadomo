TRUNCATE TABLE public.categories
    RESTART IDENTITY
    CASCADE;
TRUNCATE TABLE public.category_groups
    RESTART IDENTITY
    CASCADE;
TRUNCATE TABLE public.photos
    RESTART IDENTITY
    CASCADE;
TRUNCATE TABLE public.prices
    RESTART IDENTITY
    CASCADE;
TRUNCATE TABLE public.products
    RESTART IDENTITY
    CASCADE;
TRUNCATE TABLE public.products_photos
    RESTART IDENTITY
    CASCADE;
TRUNCATE TABLE public.products_recipes
    RESTART IDENTITY
    CASCADE;
TRUNCATE TABLE public.recipes
    RESTART IDENTITY
    CASCADE;
TRUNCATE TABLE public.recipes_categories
    RESTART IDENTITY
    CASCADE;
TRUNCATE TABLE public.recipes_photos
    RESTART IDENTITY
    CASCADE;
TRUNCATE TABLE public.units
    RESTART IDENTITY
    CASCADE;
TRUNCATE TABLE public.units_ratio
    RESTART IDENTITY
    CASCADE;
TRUNCATE TABLE public.users
    RESTART IDENTITY
    CASCADE;