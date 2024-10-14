/****************************************************************/
/* Base de donnees:      H2                                     */
/* Application:          application                            */
/* Date de creation:     01/10/2024 11:49:31                    */
/****************************************************************/
/****************************************************************/
/* Sequences                                                    */
/****************************************************************/
create sequence AW_GRADE_SEQ start with 1000;

create sequence AW_COMPETENCE_SEQ start with 1000;
create sequence AW_PERSONNE_SEQ start with 1000;
create sequence AW_ADRESSE_POSTALE_SEQ start with 1000;
create sequence AW_PAYS_SEQ start with 1000;




/****************************************************************/
/* Table: AW_GRADE                                              */
/****************************************************************/
create table AW_GRADE
(
    GRADE_ID NUMBER(19) not null,
    LIBELLE VARCHAR(100) not null,
    TRIGRAMME VARCHAR(100) not null,

    XDMAJ TIMESTAMP default current_date not null,
    XTOPSUP VARCHAR(1) default 0 not null,
    constraint AW_GRADE_PK1_1 primary key (GRADE_ID)
);

comment on column AW_GRADE.LIBELLE is 'Le libellé du grade';
comment on column AW_GRADE.TRIGRAMME is 'Le trigramme du grade';



/****************************************************************/
/* Table: AW_COMPETENCE                                         */
/****************************************************************/
create table AW_COMPETENCE
(
    COMPETENCE_ID NUMBER(19) not null,
    LIBELLE VARCHAR(100) not null,
    TYPECOMPETENCE VARCHAR(9) not null,

    XDMAJ TIMESTAMP default current_date not null,
    XTOPSUP VARCHAR(1) default 0 not null,
    constraint AW_COMPETENCE_PK1_1 primary key (COMPETENCE_ID)
);

comment on column AW_COMPETENCE.LIBELLE is 'Le libellé de la compétence';

/****************************************************************/
/* Table: AW_COMPETENCEDISPOSE                                  */
/****************************************************************/
create table AW_COMPETENCEDISPOSE
(
    COMPETENCE_ID NUMBER(19) not null,
    PERSONNE_ID NUMBER(19) not null,
    XDMAJ TIMESTAMP default current_date not null,
    XTOPSUP VARCHAR(1) default 0 not null,
    constraint AW_COMPETENCEDISPOSE_PK2_1 primary key (COMPETENCE_ID, PERSONNE_ID)
);

/****************************************************************/
/* Table: AW_PERSONNE                                           */
/****************************************************************/
create table AW_PERSONNE
(
    PERSONNE_ID NUMBER(19) not null,
    NOM VARCHAR(30) not null,
    PRENOM VARCHAR(100) not null,
    CIVIL NUMBER(1) not null,
    DATENAISSANCE TIMESTAMP not null,
    SALAIRE NUMBER(14,2) not null,

    GRADE_ID NUMBER(19),
    PERSONNE_1_ID NUMBER(19),
    PERSONNE_2_ID NUMBER(19) not null,
    XDMAJ TIMESTAMP default current_date not null,
    XTOPSUP VARCHAR(1) default 0 not null,
    constraint AW_PERSONNE_PK1_1 primary key (PERSONNE_ID)
);
create unique index AW_PERSONNE_UN1_1 on AW_PERSONNE (NOM asc);
create index AW_PERSONNE_IDX1_2 on AW_PERSONNE (GRADE_ID);
create index AW_PERSONNE_IDX1_3 on AW_PERSONNE (PERSONNE_1_ID);
create index AW_PERSONNE_IDX1_4 on AW_PERSONNE (PERSONNE_2_ID);


/****************************************************************/
/* Table: AW_ADRESSE_POSTALE                                    */
/****************************************************************/
create table AW_ADRESSE_POSTALE
(
    ADRESSE_POSTALE_ID NUMBER(19) not null,
    RUE VARCHAR(100) not null,
    VILLE VARCHAR(100) not null,
    CP VARCHAR(5) not null,

    PERSONNE_ID NUMBER(19),
    XDMAJ TIMESTAMP default current_date not null,
    XTOPSUP VARCHAR(1) default 0 not null,
    constraint AW_ADRESSE_POSTALE_PK1_1 primary key (ADRESSE_POSTALE_ID)
);
create index AW_ADRESSE_POSTALE_IDX1_1 on AW_ADRESSE_POSTALE (PERSONNE_ID);


/****************************************************************/
/* Table: AW_PAYS                                               */
/****************************************************************/
create table AW_PAYS
(
    PAYS_ID NUMBER(19) not null,
    NOM VARCHAR(100) not null,
    CAPITALE VARCHAR(100) not null,

    PERSONNE_ID NUMBER(19),
    XDMAJ TIMESTAMP default current_date not null,
    XTOPSUP VARCHAR(1) default 0 not null,
    constraint AW_PAYS_PK1_1 primary key (PAYS_ID)
);
create index AW_PAYS_IDX1_1 on AW_PAYS (PERSONNE_ID);



/****************************************************************/
/* Constraints                                                  */
/****************************************************************/
alter table AW_COMPETENCE add constraint AW_COMPETENCE_CK1_1 CHECK (TYPECOMPETENCE IN ('TYPECOMP1','TYPECOMP2','TYPECOMP3'));
alter table AW_COMPETENCEDISPOSE add constraint AW_COMPETENCEDISPOSE_FK1_1 foreign key (COMPETENCE_ID) references AW_COMPETENCE (COMPETENCE_ID);
alter table AW_COMPETENCEDISPOSE add constraint AW_COMPETENCEDISPOSE_FK1_2 foreign key (PERSONNE_ID) references AW_PERSONNE (PERSONNE_ID);
alter table AW_PERSONNE add constraint PERSONNE_FK1_1 foreign key (GRADE_ID) references AW_GRADE (GRADE_ID);
alter table AW_PERSONNE add constraint PERSONNE_FK1_2 foreign key (PERSONNE_1_ID) references AW_PERSONNE (PERSONNE_ID);
alter table AW_PERSONNE add constraint PERSONNE_FK1_3 foreign key (PERSONNE_2_ID) references AW_PERSONNE (PERSONNE_ID);
alter table AW_ADRESSE_POSTALE add constraint ADRESSE_POSTALE_FK1_1 foreign key (PERSONNE_ID) references AW_PERSONNE (PERSONNE_ID);
alter table AW_PAYS add constraint PAYS_FK1_1 foreign key (PERSONNE_ID) references AW_PERSONNE (PERSONNE_ID);

