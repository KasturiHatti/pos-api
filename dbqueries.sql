CREATE DATABASE POS;

USE POS;

create table if not exists POS.SALES
(
    ID                varchar(36)   primary key,
    TSZ_CREATED       datetime(6)    NOT NULL,
    FINAL_PRICE       decimal(38, 2) NOT NULL,
    ORIGINAL_PRICE    decimal(38, 2) NOT NULL,
    PAYMENT_METHOD    smallint       NOT NULL,
    POINTS            int            NOT NULL,
    PRICE_MODIFIER    decimal(38, 2) NOT NULL
);

