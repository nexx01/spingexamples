--//Отметьте также, что этот компонент использует только файл sche-
--  ma.sql из корня пути к классам (в каталоге проекта src/main/resources).
--  Если для инициализации данных потребуется использовать другие
--  сценарии SQL, добавьте дополнительные объекты ResourceDatabas-
--  ePopulator вызовом populator.addPopulators().
drop table if exists Ingredient;
drop table if exists Taco;
drop table if exists Taco_Order;

-- tag::createTableIngredient[]
create table Ingredient(
  id identity,
  slug varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null
);
-- end::createTableIngredient[]

-- tag::createTableTaco[]
create table Taco (
  id identity,
  name varchar(50) not null,
  ingredient_ids long array
);
-- end::createTableTaco[]

-- tag::createTableTacoOrder[]
create table Taco_Order (
  id identity,
  delivery_name varchar(50) not null,
  delivery_street varchar(50) not null,
  delivery_city varchar(50) not null,
  delivery_state varchar(2) not null,
  delivery_zip varchar(10) not null,
  cc_number varchar(16) not null,
  cc_expiration varchar(5) not null,
  cc_cvv varchar(3) not null,
  taco_ids long array
);
-- end::createTableTacoOrder[]

--//Тип array столбца ingredient_ids специфичен для H2. Для Post-
--  greSQL  этот  столбец  можно  определить  с  типом integer[].  Чтобы
--  узнать, как определить столбцы-массивы в выбранной вами базе дан-
--  ных, обращайтесь к документации по ней. Но не забывайте, что не все
--  базы данных поддерживают столбцы-массивы, поэтому для модели-
--  рования отношений вам может потребоваться выбрать другой вари-
--  ант из перечисленных выше