create table country
(
    id        bigint generated by default as identity
        primary key,
    name      varchar(255),
    continent varchar(255)
);

create table author
(
    id          bigint generated by default as identity
        primary key,
    name        varchar(255),
    surname     varchar(255),
    country_id  bigint references country
);

create table book
(
    id               bigint generated by default as identity
        primary key,
    name             varchar(255),
    category         varchar(255),
    author_id        bigint references author,
    available_copies integer
);

create table library_user
(
    username                   varchar(255)
        primary key,
    password                   varchar(255),
    name                       varchar(255),
    surname                    varchar(255),
    is_account_non_expired     boolean default true,
    is_account_non_locked      boolean default true,
    is_credentials_non_expired boolean default true,
    is_enabled                 boolean default true,
    role                       varchar(50)
);

create table wishlist
(
    id            bigint generated by default as identity
        primary key,
    date_created  timestamp,
    user_username varchar(255) references library_user,
    status        varchar(50)
);

create table wishlist_books
(
    wishlist_id bigint references wishlist on delete cascade,
    book_id     bigint references book on delete cascade,
    quantity    integer,
    primary key (wishlist_id, book_id)
);

create index idx_wishlist_user_status on wishlist (user_username, status);
create index idx_user_username_password on library_user (username, password);
create index idx_user_username on library_user (username);
create index idx_user_role on library_user (role);
