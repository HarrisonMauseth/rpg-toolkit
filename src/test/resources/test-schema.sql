START TRANSACTION;

DROP TABLE IF EXISTS item_charge_condition, item_rarity, charge_condition, item, rarity, category, users CASCADE;

CREATE TABLE users (
    user_id SERIAL,
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(200) NOT NULL,
    role varchar(50) NOT NULL,
    CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE category (
	category_id varchar(25),
	CONSTRAINT PK_category PRIMARY KEY(category_id)
);

CREATE TABLE rarity (
	rarity_id varchar(9) NOT NULL,
	min_level int NOT NULL,
	min_cost int NOT NULL,
	max_cost int NOT NULL,
	CONSTRAINT PK_rarity PRIMARY KEY(rarity_id),
	CONSTRAINT CK_min_cost CHECK (min_cost >= 0),
	CONSTRAINT CK_max_cost CHECK (max_cost >= 0)
);

CREATE TABLE item (
	item_id serial,
	item_name varchar(100) NOT NULL,
	category_id varchar(25),
	description text NULL,
	modifier int NOT NULL CONSTRAINT DF_not_null DEFAULT (0),
	modifier_info text NULL,
	requires_attunement boolean NOT NULL CONSTRAINT DF_is_attunable DEFAULT(FALSE),
	attunement_requirements text NULL,
	has_charges boolean NOT NULL CONSTRAINT DF_has_charges DEFAULT(FALSE),
	CONSTRAINT PK_item PRIMARY KEY(item_id),
	CONSTRAINT FK_category_id FOREIGN KEY (category_id) REFERENCES category (category_id)
);

CREATE TABLE charge_condition (
	charge_condition_id varchar(16) NOT NULL,
	CONSTRAINT PK_charge_condition PRIMARY KEY(charge_condition_id)
);

CREATE TABLE item_rarity (
	item_id int NOT NULL,
	rarity_id varchar(9) NOT NULL,
	CONSTRAINT PK_item_rarity PRIMARY KEY(item_id, rarity_id),
	CONSTRAINT FK_item_rarity_item FOREIGN KEY(item_id) REFERENCES item(item_id),
	CONSTRAINT FK_item_rarity_rarity FOREIGN KEY(rarity_id) REFERENCES rarity(rarity_id)
);

CREATE TABLE item_charge_condition (
	item_id int NOT NULL,
	charge_condition_id varchar(16) NOT NULL,
	number_charges int NOT NULL CONSTRAINT DF_number_charges DEFAULT(0),
	charge_reset_requirements text NULL,
	CONSTRAINT PK_item_charge_reset PRIMARY KEY(item_id, charge_condition_id),
	CONSTRAINT FK_item_charge_reset_item FOREIGN KEY(item_id) REFERENCES item(item_id),
	CONSTRAINT FK_item_charge_reset_charge_reset FOREIGN KEY(charge_condition_id) REFERENCES charge_condition(charge_condition_id)
);

COMMIT TRANSACTION;