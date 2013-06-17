# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table project (
  id                        bigint not null,
  project_name              varchar(255),
  project_description       varchar(255),
  project_vcs_url           varchar(255),
  constraint pk_project primary key (id))
;

create table project_member (
  id                        bigint not null,
  user_user_id              varchar(255),
  project_id                bigint,
  role                      integer,
  vcs_id                    varchar(255),
  vcs_pw                    varchar(255),
  constraint pk_project_member primary key (id))
;

create table user (
  user_id                   varchar(255) not null,
  user_name                 varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  profile_img               varchar(255),
  grade                     varchar(255),
  status                    varchar(255),
  constraint pk_user primary key (user_id))
;

create sequence project_seq;

create sequence project_member_seq;

create sequence user_seq;

alter table project_member add constraint fk_project_member_user_1 foreign key (user_user_id) references user (user_id) on delete restrict on update restrict;
create index ix_project_member_user_1 on project_member (user_user_id);
alter table project_member add constraint fk_project_member_project_2 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_project_member_project_2 on project_member (project_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists project;

drop table if exists project_member;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists project_seq;

drop sequence if exists project_member_seq;

drop sequence if exists user_seq;

