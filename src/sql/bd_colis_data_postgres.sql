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
