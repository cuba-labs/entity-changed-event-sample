alter table SALES_ORDER add constraint FK_SALES_ORDER_DETAILS foreign key (DETAILS_ID) references SALES_ORDER_DETAILS(ID);
