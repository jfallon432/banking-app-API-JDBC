show search_path;
set search_path to banking_app;

create table users(
	user_id serial,
	username varchar not null unique,
	password varchar not null,
	email varchar not null unique,
	first_name varchar not null,
	last_name varchar not null,
	date_of_birth date not null,
	
	constraint pk_users primary key(user_id)
);

create table user_accounts(
	user_id int not null,
	account_id int not null,
	
	constraint fk_user foreign key(user_id) references users(user_id)
);

create table account_info(
	account_id serial ,
	type varchar
);

alter table account_info
	add nickname varchar
	
alter table account_info 
add constraint pk_account_info primary key(account_id)

alter table user_accounts 
	add constraint fk_accounts foreign key(account_id) references account_info(account_id)

create table savings_accounts(
	account_id int,
	balance decimal(19,2) check(balance>=200) not null,
	constraint pk_savings_accounts primary key(account_id),
	constraint fk_account_info foreign key(account_id) references account_info(account_id) 
	
)

create table checking_accounts(
	account_id int,
	balance decimal(19,2) check(balance >= 0) not null,
	constraint pk_checking_accounts primary key(account_id),
	constraint fk_account_info foreign key(account_id) references account_info(account_id)
)

alter table account_info
alter column type set not null

create table transactions(
	transaction_id serial primary key,
	account_id int not null references account_info(account_id),
	type varchar not null,
	transaction_amount decimal(19,2) not null
	
)
