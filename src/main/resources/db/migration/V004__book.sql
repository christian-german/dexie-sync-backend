CREATE TABLE book
(
    id                   uuid   NOT NULL,
    title                character varying(255),
    author_id            uuid   NOT NULL,
    revision             bigint NOT NULL DEFAULT nextval('server_revision_seq'),
    updated_by_client_id bigint NOT NULL DEFAULT 0,
    CONSTRAINT book_pkey PRIMARY KEY (id)
);

insert into book(id, title, author_id)
values ('925db238-d7f1-11eb-b8bc-0242ac130003', 'titre1', 'db9d5fb6-d7f1-11eb-b8bc-0242ac130003');
insert into book(id, title, author_id)
values ('bbcd9778-d7f1-11eb-b8bc-0242ac130003', 'titre2', 'e34d2066-d7f1-11eb-b8bc-0242ac130003');
insert into book(id, title, author_id)
values ('c3d8feda-d7f1-11eb-b8bc-0242ac130003', 'titre3', 'ec1a1d8e-d7f1-11eb-b8bc-0242ac130003');
