/*
Ne pas décommenter ce code. Copier ce code d'exemple dans les balises User Code en fin de fichier.


alter table AUTHOR drop constraint AUTHOR_FK1_1;
alter table BOOK drop constraint BOOK_FK1_1;
alter table PURCHASEORDER drop constraint PURCHASEORDER_FK1_1;
alter table PURCHASEORDER drop constraint PURCHASEORDER_FK1_2;
alter table SHOOPINGCARTLINE drop constraint SHOOPINGCARTLINE_FK1_1;
alter table SHOOPINGCARTLINE drop constraint SHOOPINGCARTLINE_FK1_2;
alter table SHOPPINGCART drop constraint SHOPPINGCART_CK1_1;
alter table SHOPPINGCART drop constraint SHOPPINGCART_FK1_1;



insert into AUTHOR (AUTHOR_ID, NAME, BOOK_ID)
values (1, 'S', 1);
insert into BOOK (BOOK_ID, TITLE, PRICE, IMAGE, TYPE, AUTHOR_ID)
values (1, 'S', 0, 'S', 'S', 1);
insert into PURCHASEORDER (PURCHASEORDER_ID, CARDNUMBER, TOTALAMOUNT, SHOPPINGCART_ID, USER_ID)
values (1, 'S', 0, 1, 1);
insert into SHOOPINGCARTLINE (SHOOPINGCARTLINE_ID, QUANTITY, BOOK_ID, SHOPPINGCART_ID)
values (1, 0, 1, 1);
insert into SHOPPINGCART (SHOPPINGCART_ID, STATUS, USER_ID)
values (1, 'pending', 1);
insert into USER (USER_ID, NAME, EMAIL, PASSWORD)
values (1, 'S', 'S', 'S');

alter table AUTHOR add constraint AUTHOR_FK1_1 foreign key (BOOK_ID) references BOOK (BOOK_ID);
alter table BOOK add constraint BOOK_FK1_1 foreign key (AUTHOR_ID) references AUTHOR (AUTHOR_ID);
alter table PURCHASEORDER add constraint PURCHASEORDER_FK1_1 foreign key (SHOPPINGCART_ID) references SHOPPINGCART (SHOPPINGCART_ID);
alter table PURCHASEORDER add constraint PURCHASEORDER_FK1_2 foreign key (USER_ID) references USER (USER_ID);
alter table SHOOPINGCARTLINE add constraint SHOOPINGCARTLINE_FK1_1 foreign key (BOOK_ID) references BOOK (BOOK_ID);
alter table SHOOPINGCARTLINE add constraint SHOOPINGCARTLINE_FK1_2 foreign key (SHOPPINGCART_ID) references SHOPPINGCART (SHOPPINGCART_ID);
alter table SHOPPINGCART add constraint SHOPPINGCART_CK1_1 CHECK (STATUS IN ('pending','shipped'));
alter table SHOPPINGCART add constraint SHOPPINGCART_FK1_1 foreign key (USER_ID) references USER (USER_ID);


*/

/*
Start of user code init data
*/


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



insert into AUTHOR (AUTHOR_ID, NAME, BOOK_ID)
values (1, 'S', 1);
insert into BOOK (BOOK_ID, TITLE, PRICE, IMAGE, TYPE, AUTHOR_ID)
values (1, 'S', 0, 'S', 'S', 1);
insert into PURCHASEORDER (PURCHASEORDER_ID, CARDNUMBER, TOTALAMOUNT, SHOPPINGCART_ID, USER_ID)
values (1, 'S', 0, 1, 1);
insert into SHOOPINGCARTLINE (SHOOPINGCARTLINE_ID, QUANTITY, BOOK_ID, SHOPPINGCART_ID)
values (1, 0, 1, 1);
insert into SHOPPINGCART (SHOPPINGCART_ID, STATUS, USER_ID)
values (1, 'pending', 1);
insert into USER (USER_ID, NAME, EMAIL, PASSWORD)
values (1, 'S', 'S', 'S');

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


/*
End of user code
*/
