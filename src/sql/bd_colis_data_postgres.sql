-- Création des séquences si elles n'existent pas
DO $$ 
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relkind = 'S' AND relname = 'utilisateur_id_utilisateur_seq') THEN
        CREATE SEQUENCE utilisateur_id_utilisateur_seq;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relkind = 'S' AND relname = 'alerte_id_alerte_seq') THEN
        CREATE SEQUENCE alerte_id_alerte_seq;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relkind = 'S' AND relname = 'annonce_id_annonce_seq') THEN
        CREATE SEQUENCE annonce_id_annonce_seq;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relkind = 'S' AND relname = 'comment_id_comment_seq') THEN
        CREATE SEQUENCE comment_id_comment_seq;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relkind = 'S' AND relname = 'image_id_image_seq') THEN
        CREATE SEQUENCE image_id_image_seq;
    END IF;

    IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relkind = 'S' AND relname = 'message_id_message_seq') THEN
        CREATE SEQUENCE message_id_message_seq;
    END IF;
END $$;

-- Table: public.utilisateur

-- DROP TABLE IF EXISTS public.utilisateur;

CREATE TABLE IF NOT EXISTS public.utilisateur
(
    id_utilisateur integer NOT NULL DEFAULT nextval('utilisateur_id_utilisateur_seq'::regclass),
    civilite character varying(2) NOT NULL,
    name text,
    login text,
    password text,
    email text NOT NULL,
    enabled boolean NOT NULL,
    derniere_connexion timestamp without time zone,
    annee_de_naissance integer NOT NULL,
    telephone text,
    date_inscription timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT utilisateur_pkey PRIMARY KEY (id_utilisateur)
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.utilisateur
    OWNER to postgres;

CREATE INDEX IF NOT EXISTS "UNIQ_8D93D64992FC23A8"
    ON public.utilisateur USING btree (login ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE UNIQUE INDEX IF NOT EXISTS "UNIQ_8D93D649A0D96FBF"
    ON public.utilisateur USING btree (email ASC NULLS LAST)
    TABLESPACE pg_default;

-- Table: public.alerte

-- DROP TABLE IF EXISTS public.alerte;

CREATE TABLE IF NOT EXISTS public.alerte
(
    id_alerte integer NOT NULL DEFAULT nextval('alerte_id_alerte_seq'::regclass),
    id_utilisateur integer,
    ville_depart text,
    ville_arrivee text,
    date_depot timestamp without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT alerte_pkey PRIMARY KEY (id_alerte),
    CONSTRAINT "FK_USER_DEPOSE_ALERTE" FOREIGN KEY (id_utilisateur)
        REFERENCES public.utilisateur (id_utilisateur) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.alerte
    OWNER to postgres;

CREATE INDEX IF NOT EXISTS "FK_USER_DEPOSE_ALERTE"
    ON public.alerte USING btree (id_utilisateur ASC NULLS LAST)
    TABLESPACE pg_default;

-- Table: public.annonce

-- DROP TABLE IF EXISTS public.annonce;

CREATE TABLE IF NOT EXISTS public.annonce
(
    id_annonce integer NOT NULL DEFAULT nextval('annonce_id_annonce_seq'::regclass),
    id_utilisateur integer NOT NULL,
    nature_colis text,
    poids numeric(10,0) NOT NULL,
    unite_poids integer NOT NULL,
    date_depot timestamp without time zone NOT NULL,
    date_depart timestamp without time zone NOT NULL,
    date_arrivee timestamp without time zone NOT NULL,
    ville_depart text,
    ville_arrivee text,
    prime numeric(10,0) NOT NULL,
    devise integer NOT NULL,
    statut character varying(10),
    type_annonce integer NOT NULL DEFAULT 1,
    detail text,
    CONSTRAINT annonce_pkey PRIMARY KEY (id_annonce),
    CONSTRAINT "FK_USER_DEPOSE_ANNONCE" FOREIGN KEY (id_utilisateur)
        REFERENCES public.utilisateur (id_utilisateur) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.annonce
    OWNER to postgres;

CREATE INDEX IF NOT EXISTS "FK_USER_DEPOSE_ANNONCE"
    ON public.annonce USING btree (id_utilisateur ASC NULLS LAST)
    TABLESPACE pg_default;

-- Table: public.comment

-- DROP TABLE IF EXISTS public.comment;

CREATE TABLE IF NOT EXISTS public.comment
(
    id_comment integer NOT NULL DEFAULT nextval('comment_id_comment_seq'::regclass),
    id_utilisateur integer NOT NULL,
    corps text,
    date_creation timestamp without time zone NOT NULL,
    CONSTRAINT comment_pkey PRIMARY KEY (id_comment),
    CONSTRAINT "FK_USER_DEPOSE_COMMENT" FOREIGN KEY (id_utilisateur)
        REFERENCES public.utilisateur (id_utilisateur) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.comment
    OWNER to postgres;

CREATE INDEX IF NOT EXISTS "FK_USER_DEPOSE_COMMENT"
    ON public.comment USING btree (id_utilisateur ASC NULLS LAST)
    TABLESPACE pg_default;

-- Table: public.image

-- DROP TABLE IF EXISTS public.image;

CREATE TABLE IF NOT EXISTS public.image
(
    id_image integer NOT NULL DEFAULT nextval('image_id_image_seq'::regclass),
    id_utilisateur integer NOT NULL,
    chemin text,
    date_creation timestamp without time zone,
    CONSTRAINT image_pkey PRIMARY KEY (id_image),
    CONSTRAINT "FK_USER_DEPOSE_IMAGE" FOREIGN KEY (id_utilisateur)
        REFERENCES public.utilisateur (id_utilisateur) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.image
    OWNER to postgres;

CREATE INDEX IF NOT EXISTS "FK_USER_DEPOSE_IMAGE"
    ON public.image USING btree (id_utilisateur ASC NULLS LAST)
    TABLESPACE pg_default;

-- Table: public.message

-- DROP TABLE IF EXISTS public.message;

CREATE TABLE IF NOT EXISTS public.message
(
    id_message integer NOT NULL DEFAULT nextval('message_id_message_seq'::regclass),
    id_utilisateur integer,
    id_annonce integer NOT NULL,
    corps text,
    date_creation timestamp without time zone NOT NULL,
    CONSTRAINT message_pkey PRIMARY KEY (id_message),
    CONSTRAINT "FK_ANNONCE_POSE_MESSAGE" FOREIGN KEY (id_annonce)
        REFERENCES public.annonce (id_annonce) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT "FK_USER_DEPOSE_MESSAGE" FOREIGN KEY (id_utilisateur)
        REFERENCES public.utilisateur (id_utilisateur) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.message
    OWNER to postgres;

CREATE INDEX IF NOT EXISTS "FK_ANNONCE_POSE_MESSAGE"
    ON public.message USING btree (id_annonce ASC NULLS LAST)
    TABLESPACE pg_default;

CREATE INDEX IF NOT EXISTS "FK_USER_DEPOSE_MESSAGE"
    ON public.message USING btree (id_utilisateur ASC NULLS LAST)
    TABLESPACE pg_default;


-- Insertion des données dans la table utilisateur
INSERT INTO utilisateur (id_utilisateur, civilite, name, login, password, email, enabled, derniere_connexion, annee_de_naissance, telephone, date_inscription) VALUES
(1, '1', 'Mbougueung', 'amb', 'amb', 'achille@achille.com', true, '2015-07-04 12:17:56', 1966, '0578678990', '2016-04-15 14:08:25'),
(2, '1', 'Le Guen', 'ppa', 'lgu', 'paule@paule.com', true, '2015-07-04 09:03:23', 2015, '0761705745', '2016-04-15 14:08:42'),
(3, '2', 'Le Maire', 'jje', 'lma', 'jean@jean.com', true, '2015-07-01 12:54:18', 1994, '0978678990', '2016-04-15 14:08:56'),
(5, '2', 'Marteau', 'mmo', 'mpo', 'moi@moi.com', true, '2015-07-04 09:47:30', 1992, '0676564389', '2016-04-15 14:09:08'),
(6, '1', 'ZOME', 'czome', 'czome', 'christophe.zome@indyli-services.com', true, '2015-07-29 15:57:41', 2000, '0624623647', '2016-04-15 14:00:20'),
(39, '2', 'Farmer', 'far', 'tito', 'mylene@solutec.com', true, NULL, 1990, '0635247895', '2016-04-15 14:09:15');

-- Insertion des données dans la table alerte
INSERT INTO alerte (id_alerte, id_utilisateur, ville_depart, ville_arrivee, date_depot) VALUES
(1, 1, 'Bordeaux, France', 'Nantes, France', '2016-04-11 16:15:05'),
(2, 1, 'YaoundÃ©, Centre, Cameroun', 'Douala, Littoral, Cameroun', '2016-04-10 08:30:49'),
(3, 3, 'YaoundÃ©, Centre, Cameroun|NEW|NEW|NEW|NEW|NEW|NEW|NEW', 'Douala, Littoral, Cameroun', '2016-04-10 08:30:49'),
(4, 3, 'YaoundÃ©, Centre, Cameroun', 'Douala, Littoral, Cameroun', '2016-04-10 08:30:49'),
(7, 5, 'Ndjamena, Tchad', 'Douala, Littoral, Cameroun', '2016-04-10 08:30:49'),
(8, 3, 'Dakar, SÃ©nÃ©gal', 'Abidjan, Lagunes, CÃ´te d''Ivoire', '2016-04-10 08:30:49'),
(9, 2, 'Paris', 'Bamako', '2016-04-10 08:30:49'),
(10, 6, 'Paris', 'Bamako', '2016-04-10 08:30:49'),
(11, 1, 'Paris;France', 'Bamako,Mali', '2016-04-10 08:30:49'),
(16, 1, 'MontMagny', 'Nanterre', '2016-04-10 08:30:49'),
(17, 6, 'MontMagny', 'Nanterre', '2016-04-10 08:30:49'),
(18, 6, 'Paris', 'Bamako', '2016-04-10 08:30:49'),
(19, 1, 'MontMagny', 'Nanterre', '2016-04-11 15:38:33'),
(20, 1, 'MontMagny', 'Nanterre', '2016-04-11 15:38:47'),
(21, 1, 'MontMagny', 'Nanterre', '2016-04-11 15:39:27'),
(25, 1, 'Montauban,France', 'Orleans,France', '2016-04-13 06:31:28'),
(27, 1, 'Montauban,France', 'Orleans,France', '2016-04-14 06:35:06'),
(28, 1, 'Montauban,France', 'Orleans,France', '2016-04-14 06:36:15'),
(29, 1, 'Mbouda-Cameroun', 'Nkongsamba-Cameroun', '2016-04-15 06:08:21');

-- Insertion des données dans la table annonce
INSERT INTO annonce (id_annonce, id_utilisateur, nature_colis, poids, unite_poids, date_depot, date_depart, date_arrivee, ville_depart, ville_arrivee, prime, devise, statut, type_annonce, detail) VALUES
(1, 1, '1', 50, 1, '2015-06-30 16:44:17', '2015-06-08 00:00:00', '2015-06-15 00:00:00', 'Nantes, France', 'Paris, France', 50, 1, '1', 2, 'loool'),
(2, 3, '2', 34, 2, '2015-07-01 15:14:41', '2015-07-16 00:00:00', '2015-07-26 00:00:00', 'Paris, France', 'Bamako, Mali', 456, 2, '1', 2, 'Merci de me contacter pour plus de détails'),
(3, 6, '2', 12, 1, '2015-07-29 15:38:54', '2015-05-12 00:00:00', '2015-04-10 00:00:00', 'Paris, France', 'Douala, Littoral, Cameroun', 45, 1, '1', 2, 'Je propose de vous le ramener'),
(4, 6, 'Chaussures', 2, 0, '2016-04-15 15:59:02', '2016-04-01 00:00:00', '2017-09-01 00:00:00', 'Louanda, Angola', 'Montreal,Canada', 0, 1, '1', 1, 'J''aimerai envoyer des chaussures à ma cousine residant au Canada.');

-- Insertion des données dans la table comment
INSERT INTO comment (id_comment, id_utilisateur, corps, date_creation) VALUES
(2, 1, 'Passager ne prevenant pas du retard', '2015-07-04 13:05:39'),
(3, 1, 'Le colis est arrivé nickel, merci beaucoup', '2015-07-04 13:06:10'),
(4, 1, 'N''est pas très communicant', '2015-07-04 13:06:34'),
(5, 6, 'N''a jamais répondu à mes messages', '2015-07-29 15:23:22'),
(6, 2, 'Transporteur tres ponctuel, je le recommande pour vos colis', '2016-01-12 23:04:45'),
(7, 2, 'Transporteur semi ponctuel', '2016-01-12 23:05:16'),
(8, 2, 'Transporteur tres ponctuel', '2016-04-11 17:32:32'),
(9, 2, 'Transporteur tres ponctuel', '2016-04-11 17:35:00'),
(10, 2, 'Transporteur tres ponctuel', '2016-04-11 17:37:39'),
(11, 2, 'Transporteur tres ponctuel', '2016-04-11 17:38:47'),
(12, 2, 'Transporteur tres ponctuel', '2016-04-11 17:39:27');

-- Insertion des données dans la table image
INSERT INTO image (id_image, id_utilisateur, chemin, date_creation) VALUES
(3, 1, 'b90f4e93674e6709a40740f4e4f9a8d3e7e3931f.jpeg', '2015-10-06 10:14:33'),
(4, 6, 'f787250fe425379056128efd746af51c2a8021d2.jpeg', '2015-12-10 10:14:33');

-- Insertion des données dans la table message
INSERT INTO message (id_message, id_utilisateur, id_annonce, corps, date_creation) VALUES
(1, 1, 1, 'dadadafafff', '2015-07-04 16:47:49'),
(2, 6, 2, 'dhfhdhdhfdhd', '2015-07-05 08:33:09');
