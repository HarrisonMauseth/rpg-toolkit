BEGIN TRANSACTION;

-- Users
INSERT INTO users (username,password_hash,role) VALUES ('user1','password1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','password2','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('user3','password3','ROLE_USER');

-- Category
INSERT INTO category (category_id) VALUES ('category1');
INSERT INTO category (category_id) VALUES ('category2');
INSERT INTO category (category_id) VALUES ('category3');

-- Rarity
INSERT INTO rarity (rarity_id, min_level, min_cost, max_cost) VALUES ('rarity1', 1, 0, 5);
INSERT INTO rarity (rarity_id, min_level, min_cost, max_cost) VALUES ('rarity2', 2, 5, 10);
INSERT INTO rarity (rarity_id, min_level, min_cost, max_cost) VALUES ('rarity3', 3, 10, 15);

-- Charge Conditions
INSERT INTO charge_condition (charge_condition_id) VALUES ('charge1');
INSERT INTO charge_condition (charge_condition_id) VALUES ('charge2');
INSERT INTO charge_condition (charge_condition_id) VALUES ('charge3');

-- Items
INSERT INTO item (item_name, category_id, description, modifier, modifier_info, requires_attunement, has_charges)
    VALUES ('Item1', 'category1', 'description1', 0, null, false, false);
INSERT INTO item (item_name, category_id, description, modifier, modifier_info, requires_attunement, attunement_requirements, has_charges)
    VALUES ('Item2', 'category2', 'description2', 1, 'modifies 1', true, 'requirements' , false);
INSERT INTO item (item_name, category_id, description, modifier, modifier_info, requires_attunement, has_charges)
    VALUES ('Item3', 'category3', 'description3', 2, 'modifies 2', false, true);

-- Item Rarity
INSERT INTO item_rarity (item_id, rarity_id) VALUES (1, 'rarity1');
INSERT INTO item_rarity (item_id, rarity_id) VALUES (2, 'rarity2');
INSERT INTO item_rarity (item_id, rarity_id) VALUES (3, 'rarity3');

-- Item Charge Condition
INSERT INTO item_charge_condition (item_id, charge_condition_id, number_charges, charge_reset_requirements)
    VALUES (3, 'charge3', 3, 'charge reset requirement for test item 3');

COMMIT TRANSACTION;