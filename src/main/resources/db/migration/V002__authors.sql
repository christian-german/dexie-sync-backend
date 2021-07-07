CREATE TABLE author
(
    id                   uuid   NOT NULL,
    firstname            character varying(255),
    lastname             character varying(255),
    revision             bigint NOT NULL,
    updated_by_client_id bigint,
    CONSTRAINT author_pkey PRIMARY KEY (id)
);

insert into author(id, firstname, lastname, revision, updated_by_client_id)
values ('db9d5fb6-d7f1-11eb-b8bc-0242ac130003', 'John', 'McCoy', 1, 0);
insert into author(id, firstname, lastname, revision, updated_by_client_id)
values ('e34d2066-d7f1-11eb-b8bc-0242ac130003', 'Mickey', 'Mouse', 2, 0);
insert into author(id, firstname, lastname, revision, updated_by_client_id)
values ('ec1a1d8e-d7f1-11eb-b8bc-0242ac130003', 'Donald', 'Duck', 3, 0);
