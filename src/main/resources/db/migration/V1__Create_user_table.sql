CREATE TABLE USER
(
    ID           INT AUTO_INCREMENT primary key not null,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(50),
    TOKEN        CHAR(36),
    GTM_CREATE   BIGINT,
    GTM_MODIFIED BIGINT
);

