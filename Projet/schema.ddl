
    alter table Album_Artiste 
        drop constraint FK_1aolh6d67unjjdm0q61mbtec2

    alter table Album_Artiste 
        drop constraint FK_37tt2o7kqrsjx3ytvkfuxjdq5

    alter table Album_Genre 
        drop constraint FK_8fnn4wjrhvsdrepb50e3r7u47

    alter table Album_Genre 
        drop constraint FK_np3mu30ht8u5s8c10h3janqij

    alter table Album_Musique 
        drop constraint FK_71hli5ih05o8h56ric8bfxn77

    alter table Album_Musique 
        drop constraint FK_exohhb5m5mqtt1ek7x7h1bcnd

    drop table Album if exists

    drop table Album_Artiste if exists

    drop table Album_Genre if exists

    drop table Album_Musique if exists

    drop table Artiste if exists

    drop table Genre if exists

    drop table Musique if exists

    drop sequence hibernate_sequence

    create table Album (
        id bigint not null,
        titre varchar(255),
        annee integer,
        primary key (id)
    )

    create table Album_Artiste (
        albums_id bigint not null,
        artists_id bigint not null,
        primary key (albums_id, artists_id)
    )

    create table Album_Genre (
        albums_id bigint not null,
        types_id bigint not null,
        primary key (albums_id, types_id)
    )

    create table Album_Musique (
        albums_id bigint not null,
        songs_id bigint not null,
        primary key (albums_id, songs_id)
    )

    create table Artiste (
        id bigint not null,
        groupe boolean,
        nom varchar(255),
        prenom varchar(255),
        primary key (id)
    )

    create table Genre (
        id bigint not null,
        description varchar(255),
        primary key (id)
    )

    create table Musique (
        id bigint not null,
        titre varchar(255),
        primary key (id)
    )

    alter table Album_Artiste 
        add constraint FK_1aolh6d67unjjdm0q61mbtec2 
        foreign key (artists_id) 
        references Artiste

    alter table Album_Artiste 
        add constraint FK_37tt2o7kqrsjx3ytvkfuxjdq5 
        foreign key (albums_id) 
        references Album

    alter table Album_Genre 
        add constraint FK_8fnn4wjrhvsdrepb50e3r7u47 
        foreign key (types_id) 
        references Genre

    alter table Album_Genre 
        add constraint FK_np3mu30ht8u5s8c10h3janqij 
        foreign key (albums_id) 
        references Album

    alter table Album_Musique 
        add constraint FK_71hli5ih05o8h56ric8bfxn77 
        foreign key (songs_id) 
        references Musique

    alter table Album_Musique 
        add constraint FK_exohhb5m5mqtt1ek7x7h1bcnd 
        foreign key (albums_id) 
        references Album

    create sequence hibernate_sequence start with 1
