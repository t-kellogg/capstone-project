create table user_accounts
(
ua_id			serial			primary key,
active			boolean			default true,
username		varchar(64)		not null,
token			varchar(64)		not null,
email			varchar(64)		not null,
f_name			varchar(32)		not null,
l_name			varchar(32)		not null,
p_no			varchar(12),
role			int				not NULL
);

create table bank_accounts
(
ba_id			serial			primary key,
type			int				not null,
balance			numeric(13,2)	not null,
active			boolean			default true,
user_account_id	int				references user_accounts(ua_id) on delete restrict
);

create table user_accounts_bank_accounts
(
user_account_id	int				references user_accounts(ua_id) on delete restrict,
bank_account_id	int				references bank_accounts(ba_id) on delete restrict
);

create table logs(
l_id			serial			primary key,
action			int				not null,
amount			numeric(13,2)	not null,
timestamp		bigint			not null,
user_account_id	int				references user_accounts(ua_id) on delete restrict,
from_bank_id	int				references bank_accounts(ba_id) on delete restrict,
to_bank_id		int				references bank_accounts(ba_id) on delete restrict
);