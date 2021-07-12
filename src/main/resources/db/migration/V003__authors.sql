CREATE TABLE author
(
    id                   uuid   NOT NULL,
    firstname            character varying(255),
    lastname             character varying(255),
    revision             bigint NOT NULL DEFAULT nextval('server_revision_seq'),
    updated_by_client_id bigint NOT NULL DEFAULT 0,
    CONSTRAINT author_pkey PRIMARY KEY (id)
);

insert into author(id, firstname, lastname)
values ('db9d5fb6-d7f1-11eb-b8bc-0242ac130003', 'John', 'McCoy');
insert into author(id, firstname, lastname)
values ('e34d2066-d7f1-11eb-b8bc-0242ac130003', 'Mickey', 'Mouse');
insert into author(id, firstname, lastname)
values ('ec1a1d8e-d7f1-11eb-b8bc-0242ac130003', 'Donald', 'Duck');
