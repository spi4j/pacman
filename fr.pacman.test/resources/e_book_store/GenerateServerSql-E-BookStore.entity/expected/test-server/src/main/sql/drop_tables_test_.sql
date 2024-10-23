/****************************************************************/
/* Base de donnees:                                             */
/* Application:          test                                   */
/* Date de creation:     18/10/2024 11:04:58                    */
/****************************************************************/

/****************************************************************/
/* Sequences                                                    */
/****************************************************************/
drop sequence AUTHOR_SEQ;
drop sequence BOOK_SEQ;
drop sequence PURCHASEORDER_SEQ;
drop sequence SHOOPINGCARTLINE_SEQ;
drop sequence SHOPPINGCART_SEQ;
drop sequence USER_SEQ;


/****************************************************************/
/* Constraints                                                  */
/****************************************************************/
alter table AUTHOR drop constraint AUTHOR_FK1_1;
alter table BOOK drop constraint BOOK_FK1_1;
alter table PURCHASEORDER drop constraint PURCHASEORDER_FK1_1;
alter table PURCHASEORDER drop constraint PURCHASEORDER_FK1_2;
alter table SHOOPINGCARTLINE drop constraint SHOOPINGCARTLINE_FK1_1;
alter table SHOOPINGCARTLINE drop constraint SHOOPINGCARTLINE_FK1_2;
alter table SHOPPINGCART drop constraint SHOPPINGCART_CK1_1;
alter table SHOPPINGCART drop constraint SHOPPINGCART_FK1_1;

/****************************************************************/
/* Indexs                                                       */
/****************************************************************/
drop index AUTHOR_IDX1_1;
drop index BOOK_IDX1_1;
drop index PURCHASEORDER_IDX1_1;
drop index PURCHASEORDER_IDX1_2;
drop index SHOOPINGCARTLINE_IDX1_1;
drop index SHOOPINGCARTLINE_IDX1_2;
drop index SHOPPINGCART_IDX1_1;


/****************************************************************/
/* Table: AUTHOR                                                */
/****************************************************************/
drop table AUTHOR;

/****************************************************************/
/* Table: BOOK                                                  */
/****************************************************************/
drop table BOOK;

/****************************************************************/
/* Table: PURCHASEORDER                                         */
/****************************************************************/
drop table PURCHASEORDER;

/****************************************************************/
/* Table: SHOOPINGCARTLINE                                      */
/****************************************************************/
drop table SHOOPINGCARTLINE;

/****************************************************************/
/* Table: SHOPPINGCART                                          */
/****************************************************************/
drop table SHOPPINGCART;

/****************************************************************/
/* Table: USER                                                  */
/****************************************************************/
drop table USER;



