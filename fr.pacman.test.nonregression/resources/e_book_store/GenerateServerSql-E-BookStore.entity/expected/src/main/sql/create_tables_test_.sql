/****************************************************************/
/* Base de donnees:                                             */
/* Application:          test                                   */
/* Date de creation:     01/10/2024 11:49:16                    */
/****************************************************************/
/****************************************************************/
/* Sequences                                                    */
/****************************************************************/
create sequence AUTHOR_SEQ start with 1000;
create sequence BOOK_SEQ start with 1000;
create sequence PURCHASEORDER_SEQ start with 1000;
create sequence SHOOPINGCARTLINE_SEQ start with 1000;
create sequence SHOPPINGCART_SEQ start with 1000;
create sequence USER_SEQ start with 1000;




/****************************************************************/
/* Table: AUTHOR                                                */
/****************************************************************/
create table AUTHOR
(
    AUTHOR_ID NUMBER(19) not null,
    NAME VARCHAR(100) not null,

    BOOK_ID NUMBER(19),
    constraint AUTHOR_PK1_1 primary key (AUTHOR_ID)
);
create index AUTHOR_IDX1_1 on AUTHOR (BOOK_ID);


/****************************************************************/
/* Table: BOOK                                                  */
/****************************************************************/
create table BOOK
(
    BOOK_ID NUMBER(19) not null,
    TITLE VARCHAR(100) not null,
    PRICE NUMBER(10) not null,
    IMAGE VARCHAR(100) not null,
    TYPE VARCHAR(100),

    AUTHOR_ID NUMBER(19),
    constraint BOOK_PK1_1 primary key (BOOK_ID)
);
create index BOOK_IDX1_1 on BOOK (AUTHOR_ID);


/****************************************************************/
/* Table: PURCHASEORDER                                         */
/****************************************************************/
create table PURCHASEORDER
(
    PURCHASEORDER_ID NUMBER(19) not null,
    CARDNUMBER VARCHAR(100) not null,
    TOTALAMOUNT NUMBER(10) not null,

    SHOPPINGCART_ID NUMBER(19) not null,
    USER_ID NUMBER(19) not null,
    constraint PURCHASEORDER_PK1_1 primary key (PURCHASEORDER_ID)
);
create index PURCHASEORDER_IDX1_1 on PURCHASEORDER (SHOPPINGCART_ID);
create index PURCHASEORDER_IDX1_2 on PURCHASEORDER (USER_ID);


/****************************************************************/
/* Table: SHOOPINGCARTLINE                                      */
/****************************************************************/
create table SHOOPINGCARTLINE
(
    SHOOPINGCARTLINE_ID NUMBER(19) not null,
    QUANTITY NUMBER(10) not null,

    BOOK_ID NUMBER(19) not null,
    SHOPPINGCART_ID NUMBER(19),
    constraint SHOOPINGCARTLINE_PK1_1 primary key (SHOOPINGCARTLINE_ID)
);
create index SHOOPINGCARTLINE_IDX1_1 on SHOOPINGCARTLINE (BOOK_ID);
create index SHOOPINGCARTLINE_IDX1_2 on SHOOPINGCARTLINE (SHOPPINGCART_ID);


/****************************************************************/
/* Table: SHOPPINGCART                                          */
/****************************************************************/
create table SHOPPINGCART
(
    SHOPPINGCART_ID NUMBER(19) not null,
    STATUS VARCHAR(7) not null,

    USER_ID NUMBER(19),
    constraint SHOPPINGCART_PK1_1 primary key (SHOPPINGCART_ID)
);
create index SHOPPINGCART_IDX1_1 on SHOPPINGCART (USER_ID);


/****************************************************************/
/* Table: USER                                                  */
/****************************************************************/
create table USER
(
    USER_ID NUMBER(19) not null,
    NAME VARCHAR(100) not null,
    EMAIL VARCHAR(100) not null,
    PASSWORD VARCHAR(100) not null,

    constraint USER_PK1_1 primary key (USER_ID)
);



/****************************************************************/
/* Constraints                                                  */
/****************************************************************/
alter table AUTHOR add constraint AUTHOR_FK1_1 foreign key (BOOK_ID) references BOOK (BOOK_ID);
alter table BOOK add constraint BOOK_FK1_1 foreign key (AUTHOR_ID) references AUTHOR (AUTHOR_ID);
alter table PURCHASEORDER add constraint PURCHASEORDER_FK1_1 foreign key (SHOPPINGCART_ID) references SHOPPINGCART (SHOPPINGCART_ID);
alter table PURCHASEORDER add constraint PURCHASEORDER_FK1_2 foreign key (USER_ID) references USER (USER_ID);
alter table SHOOPINGCARTLINE add constraint SHOOPINGCARTLINE_FK1_1 foreign key (BOOK_ID) references BOOK (BOOK_ID);
alter table SHOOPINGCARTLINE add constraint SHOOPINGCARTLINE_FK1_2 foreign key (SHOPPINGCART_ID) references SHOPPINGCART (SHOPPINGCART_ID);
alter table SHOPPINGCART add constraint SHOPPINGCART_CK1_1 CHECK (STATUS IN ('pending','shipped'));
alter table SHOPPINGCART add constraint SHOPPINGCART_FK1_1 foreign key (USER_ID) references USER (USER_ID);

