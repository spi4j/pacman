/****************************************************************/
/* Base de donnees:      H2                                     */
/* Application:          application                            */
/* Date de creation:     18/10/2024 11:05:16                    */
/****************************************************************/

/****************************************************************/
/* Sequences                                                    */
/****************************************************************/
drop sequence AW_GRADE_SEQ;

drop sequence AW_COMPETENCE_SEQ;
drop sequence AW_PERSONNE_SEQ;
drop sequence AW_ADRESSE_POSTALE_SEQ;
drop sequence AW_PAYS_SEQ;


/****************************************************************/
/* Constraints                                                  */
/****************************************************************/
alter table AW_COMPETENCE drop constraint AW_COMPETENCE_CK1_1;
alter table AW_COMPETENCEDISPOSE drop constraint AW_COMPETENCEDISPOSE_FK1_1;
alter table AW_COMPETENCEDISPOSE drop constraint AW_COMPETENCEDISPOSE_FK1_2;
alter table AW_PERSONNE drop constraint PERSONNE_FK1_1;
alter table AW_PERSONNE drop constraint PERSONNE_FK1_2;
alter table AW_PERSONNE drop constraint PERSONNE_FK1_3;
alter table AW_ADRESSE_POSTALE drop constraint ADRESSE_POSTALE_FK1_1;
alter table AW_PAYS drop constraint PAYS_FK1_1;

/****************************************************************/
/* Indexs                                                       */
/****************************************************************/

drop index AW_PERSONNE_UN1_1;
drop index AW_PERSONNE_IDX1_2;
drop index AW_PERSONNE_IDX1_3;
drop index AW_PERSONNE_IDX1_4;
drop index AW_ADRESSE_POSTALE_IDX1_1;
drop index AW_PAYS_IDX1_1;


/****************************************************************/
/* Table: AW_GRADE                                              */
/****************************************************************/
drop table AW_GRADE;


/****************************************************************/
/* Table: AW_COMPETENCE                                         */
/****************************************************************/
drop table AW_COMPETENCE;

/****************************************************************/
/* Table: AW_COMPETENCEDISPOSE                                  */
/****************************************************************/
drop table AW_COMPETENCEDISPOSE;

/****************************************************************/
/* Table: AW_PERSONNE                                           */
/****************************************************************/
drop table AW_PERSONNE;

/****************************************************************/
/* Table: AW_ADRESSE_POSTALE                                    */
/****************************************************************/
drop table AW_ADRESSE_POSTALE;

/****************************************************************/
/* Table: AW_PAYS                                               */
/****************************************************************/
drop table AW_PAYS;



