START TRANSACTION;

DROP TABLE IF EXISTS item_charge_condition, item_rarity, item, charge_condition, rarity, category, users CASCADE;

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

-- populate users
INSERT INTO users (username, password_hash, role) VALUES ('user', '$2a$10$a.giCRrhivgRdo2pNAVlwuSoOH2XMr4E/wgZPu8EsBWtKIdkyN/Fe', 'ROLE_USER');

-- populate category
INSERT INTO category (category_id) VALUES ('Unknown');
INSERT INTO category (category_id) VALUES ('Armor');
INSERT INTO category (category_id) VALUES ('Breastplate');
INSERT INTO category (category_id) VALUES ('Chain Mail');
INSERT INTO category (category_id) VALUES ('Chain Shirt');
INSERT INTO category (category_id) VALUES ('Half Plate');
INSERT INTO category (category_id) VALUES ('Hide');
INSERT INTO category (category_id) VALUES ('Leather');
INSERT INTO category (category_id) VALUES ('Padded');
INSERT INTO category (category_id) VALUES ('Plate');
INSERT INTO category (category_id) VALUES ('Potion');
INSERT INTO category (category_id) VALUES ('Pride Silk Outfit');
INSERT INTO category (category_id) VALUES ('Ring');
INSERT INTO category (category_id) VALUES ('Ring Mail');
INSERT INTO category (category_id) VALUES ('Rod');
INSERT INTO category (category_id) VALUES ('Scale Mail');
INSERT INTO category (category_id) VALUES ('Scroll');
INSERT INTO category (category_id) VALUES ('Shield');
INSERT INTO category (category_id) VALUES ('Spiked Armor');
INSERT INTO category (category_id) VALUES ('Splint');
INSERT INTO category (category_id) VALUES ('Staff');
INSERT INTO category (category_id) VALUES ('Studded Leather');
INSERT INTO category (category_id) VALUES ('Wand');
INSERT INTO category (category_id) VALUES ('Wondrous Item');

-- populate rarity
INSERT INTO rarity (rarity_id, min_level, min_cost, max_cost) VALUES ('Unknown', 0, 0, 999999);
INSERT INTO rarity (rarity_id, min_level, min_cost, max_cost) VALUES ('Common', 1, 50, 100);
INSERT INTO rarity (rarity_id, min_level, min_cost, max_cost) VALUES ('Uncommon', 1, 101, 500);
INSERT INTO rarity (rarity_id, min_level, min_cost, max_cost) VALUES ('Rare', 5, 501, 5000);
INSERT INTO rarity (rarity_id, min_level, min_cost, max_cost) VALUES ('Very Rare', 11, 5001, 50000);
INSERT INTO rarity (rarity_id, min_level, min_cost, max_cost) VALUES ('Legendary', 15, 50001, 500000);
INSERT INTO rarity (rarity_id, min_level, min_cost, max_cost) VALUES ('Artifact', 17, 500001, 999999);

-- -- populate charge_condition
INSERT INTO charge_condition (charge_condition_id) VALUES ('Short Rest');
INSERT INTO charge_condition (charge_condition_id) VALUES ('Long Rest');
INSERT INTO charge_condition (charge_condition_id) VALUES ('Dawn');
INSERT INTO charge_condition (charge_condition_id) VALUES ('Consumable');
INSERT INTO charge_condition (charge_condition_id) VALUES ('Other');

-- populate item
INSERT INTO item (item_name, category_id, description, modifier, modifier_info, requires_attunement, has_charges)
    VALUES ('Bracers of Defense', 'Wondrous Item', 'While wearing these bracers, you gain a +2 bonus to AC if you are not wearing armor or using a shield.', 2, 'AC', true, false);
INSERT INTO item (item_name, category_id, description, modifier, modifier_info, requires_attunement, has_charges)
    VALUES ('Wand of Magic Detection', 'Wand', 'While holding this wand, you can use an action to expend 1 charge to cast *Detect Magic*.', 0, null, false, true);

-- -- populate item_rarity
INSERT INTO item_rarity (item_id, rarity_id) VALUES (1, 'Rare');
INSERT INTO item_rarity (item_id, rarity_id) VALUES (2, 'Uncommon');

-- populate item_charge_condition
INSERT INTO item_charge_condition (item_id, charge_condition_id, number_charges, charge_reset_requirements)
    VALUES (2, 'Dawn', 7, 'This wand has 7 charges and regains 1d6+1 expended charges each dawn. When the last charge is expended, roll a d20. On a 1, the wand violently shakes and sparks with magic then disappears.');

COMMIT;