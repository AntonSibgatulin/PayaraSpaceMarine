
CREATE TABLE chapter (
  id int NOT NULL,
  name text NOT NULL,
  parentLegion text NOT NULL,
  marinesCount int NOT NULL,
  world text NOT NULL
) ;


-- SQLINES DEMO *** ---------------------------------------

--
-- SQLINES DEMO *** � таблицы `coordinate`
--

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE coordinate (
  id int NOT NULL,
  x double precision NOT NULL,
  y double precision NOT NULL
) ;

--
-- SQLINES DEMO *** ых таблицы `coordinate`
--

-- SQLINES LICENSE FOR EVALUATION USE ONLY



-- SQLINES DEMO *** ---------------------------------------

--
-- SQLINES DEMO *** � таблицы `spacemarine`
--

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE spacemarine (
  id serial PRIMARY KEY,
  name text NOT NULL,
  creationDate text NOT NULL,
  creationDateUnix bigint NOT NULL,
  health double precision NOT NULL,
  category text NOT NULL,
  weaponType text NOT NULL,
  meleeWeapon text NOT NULL,
  starshipId bigint DEFAULT NULL
) ;

--
-- SQLINES DEMO *** ых таблицы `spacemarine`
--

-- SQLINES LICENSE FOR EVALUATION USE ONLY

-- SQLINES DEMO *** ---------------------------------------

--
-- SQLINES DEMO *** � таблицы `starship`
--

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE starship (
  id serial PRIMARY KEY,
  name text NOT NULL,
  campacity int NOT NULL,
  length double precision NOT NULL,
  width double precision NOT NULL,
  height double precision NOT NULL,
  starShipType text NOT NULL
) ;

--
-- SQLINES DEMO *** ых таблицы `starship`
--



--
-- SQLINES DEMO *** охранённых таблиц
--

--
-- SQLINES DEMO *** аблицы `chapter`
--
ALTER TABLE chapter
  ADD PRIMARY KEY (id);

--
-- SQLINES DEMO *** аблицы `coordinate`
--
ALTER TABLE coordinate
  ADD PRIMARY KEY (id);

--
-- SQLINES DEMO *** аблицы `spacemarine`
--

--
-- SQLINES DEMO *** аблицы `starship`
--

--
-- SQLINES DEMO *** ля сохранённых таблиц
--

--
-- SQLINES DEMO *** ля таблицы `spacemarine`
--

/* SQLINES DEMO *** ER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/* SQLINES DEMO *** ER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/* SQLINES DEMO *** ON_CONNECTION=@OLD_COLLATION_CONNECTION */;
