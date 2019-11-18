/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/11/7 17:47:52                           */
/*==============================================================*/


drop table if exists ALBUM;

drop table if exists BOARD;

drop table if exists COMMENT;

drop table if exists LOGIN_LOG;

drop table if exists MESSAGE;

drop table if exists OPERATION;

drop table if exists PERMISSION;

drop table if exists PHOTO;

drop table if exists PRAISE;

drop table if exists RELATIONSHIP;

drop table if exists ROLE;

drop table if exists ROLE_PERMISSION;

drop table if exists USER;

/*==============================================================*/
/* Table: ALBUM                                                 */
/*==============================================================*/
create table ALBUM
(
   ID                   int not null auto_increment,
   ALBUM_ID             varchar(64),
   ALBUM_NAME           varchar(64),
   ALBUM_USER_ID        varchar(64),
   ALBUM_CREATETIME     date,
   primary key (ID)
);

/*==============================================================*/
/* Table: BOARD                                                 */
/*==============================================================*/
create table BOARD
(
   ID                   int not null auto_increment,
   BOARD_ID             varchar(64),
   BOARD_TEXT           text,
   BOARD_CREATEUSER_ID  varchar(64),
   BOARD_USERID         varchar(64),
   BOARD_CREATETIME     date,
   BOARD_STATE          int comment '0为未读状态，1为已读状态',
   primary key (ID)
);

/*==============================================================*/
/* Table: COMMENT                                               */
/*==============================================================*/
create table COMMENT
(
   ID                   int not null auto_increment,
   COMMENT_ID           varchar(64),
   COMMENT_MESSAGE_ID   varchar(64),
   COMMENT_TEXT         text,
   COMMENT_CREATETIME   date,
   COMMENT_USER_ID      varchar(64),
   primary key (ID)
);

/*==============================================================*/
/* Table: LOGIN_LOG                                             */
/*==============================================================*/
create table LOGIN_LOG
(
   ID                   int not null auto_increment,
   LOGIN_ID             varchar(64),
   LOGIN_USER_ID        varchar(64),
   LOGIN_OPERCATION     varchar(64),
   LOGIN_CREATETIME     date,
   primary key (ID)
);

/*==============================================================*/
/* Table: MESSAGE                                               */
/*==============================================================*/
create table MESSAGE
(
   ID                   int not null auto_increment,
   MESSAGE_ID           varchar(64),
   MESSAGE_TEXT         text,
   MESSAGE_CREATETIME   date,
   MESSAGE_USER_ID      varchar(64),
   MESSAGE_PHOTO_ID     varchar(64),
   primary key (ID)
);

/*==============================================================*/
/* Table: OPERATION                                             */
/*==============================================================*/
create table OPERATION
(
   ID                   int not null auto_increment,
   OPERATION_ID         varchar(64),
   OPERATION_USER_ID    varchar(64),
   OPERATION_ACT        varchar(64),
   OPERATION_CREATETIME date,
   primary key (ID)
);

/*==============================================================*/
/* Table: PERMISSION                                            */
/*==============================================================*/
create table PERMISSION
(
   ID                   int not null auto_increment,
   PERMISSION_ID        varchar(64) not null,
   PERMISSION_PARENTS_ID varbinary(64),
   PERMISSION_NAME      varbinary(64),
   PERMISSION_DESCRIPTION varbinary(64),
   PERMISSION_VALUE     varbinary(64),
   primary key (ID)
);

/*==============================================================*/
/* Table: PHOTO                                                 */
/*==============================================================*/
create table PHOTO
(
   ID                   int not null auto_increment,
   PHOTO_ID             varchar(64),
   PHOTO_NAME           varchar(64),
   PHOTO_URL            varchar(64),
   PHOTO_CREATETIME     date,
   PHOTO_ALBUM_ID       varchar(64),
   primary key (ID)
);

/*==============================================================*/
/* Table: PRAISE                                                */
/*==============================================================*/
create table PRAISE
(
   ID                   int not null auto_increment,
   PRAISE_ID            varchar(64),
   PRAISE_MESSAGE_ID    varchar(64),
   PRAISE_CREATETIME    date,
   PRAISE_USER_ID       varchar(64),
   primary key (ID)
);

/*==============================================================*/
/* Table: RELATIONSHIP                                          */
/*==============================================================*/
create table RELATIONSHIP
(
   ID                   INT not null auto_increment,
   RELATIONSHIP_USERID  varchar(64),
   RELATIONSHIP_FRIENDID varchar(64),
   RELATIONSHIP_STATE   INT comment '0为未同意，1为已同意，2为已拒绝',
   primary key (ID)
);

/*==============================================================*/
/* Table: ROLE                                                  */
/*==============================================================*/
create table ROLE
(
   ID                   INT not null auto_increment,
   ROLE_ID              varchar(64) not null,
   ROLE_NAME            varchar(64),
   ROLE_DESCRIPTION     varchar(64),
   ROLE_CREATETIME      varbinary(64),
   primary key (ID)
);

/*==============================================================*/
/* Table: ROLE_PERMISSION                                       */
/*==============================================================*/
create table ROLE_PERMISSION
(
   ID                   INT not null auto_increment,
   RP_ID                varchar(64) not null,
   ROLE_ID              varchar(64),
   PERMISSION_ID        varchar(64),
   primary key (ID)
);

/*==============================================================*/
/* Table: USER                                                  */
/*==============================================================*/
create table USER
(
   ID                   int not null auto_increment,
   USER_ID              varchar(64) not null,
   USER_NAME            varchar(64),
   USER_LOGIN_NAME      varchar(64) not null,
   USER_AGE             int,
   USER_EMAIL           varchar(64) not null,
   USER_PASSWORD        varchar(64),
   USER_SALT            varchar(64),
   USER_STATE           int comment '0为禁用，1为未禁用',
   USER_CREATETIME      date,
   USER_LASTTIME        date,
   USER_LOGIN_COUNT     int,
   USER_ROLE_ID         varchar(64) not null,
   primary key (ID),
   unique key UNQ (USER_ID, USER_EMAIL)
);

