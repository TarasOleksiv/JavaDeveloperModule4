USE toleksiv_products;
# 01. Populate manufacturers;
INSERT INTO manufacturers (id, name) VALUES
  (unhex(replace(uuid(), '-', '')), 'Asics'),
  (unhex(replace(uuid(), '-', '')), 'Saucony'),
  (unhex(replace(uuid(), '-', '')), 'Nike'),
  (unhex(replace(uuid(), '-', '')), 'Craft');
# 02. Populate products (product could be produced only by 1 manufacturer);
SELECT @id:=id FROM manufacturers WHERE name = 'Asics';
INSERT INTO products (id, name, manufacturer_id, price) VALUES
  (unhex(replace(uuid(), '-', '')), 'Asics FUZEX SEAMLESS SS GRY M', @id,832),
  (unhex(replace(uuid(), '-', '')), 'Asics FUZEX PADDED VEST BLK M', @id,1664),
  (unhex(replace(uuid(), '-', '')), 'Asics LITE-SHOW VEST GRY/BLK M', @id,2392),
  (unhex(replace(uuid(), '-', '')), 'Asics FUZEX BOMBER JACKET BLK M', @id,1875);
SELECT @id:=id FROM manufacturers WHERE name = 'Saucony';
INSERT INTO products (id, name, manufacturer_id, price) VALUES
  (unhex(replace(uuid(), '-', '')), 'Saucony VELOCITY LONG SLEEVE', @id,840),
  (unhex(replace(uuid(), '-', '')), 'Saucony VITARUN JACKET', @id,2340),
  (unhex(replace(uuid(), '-', '')), 'Saucony SONIC REFLEX JACKET', @id,1782),
  (unhex(replace(uuid(), '-', '')), 'Saucony SIBERIUS JACKET', @id,2190);
SELECT @id:=id FROM manufacturers WHERE name = 'Nike';
INSERT INTO products (id, name, manufacturer_id, price) VALUES
  (unhex(replace(uuid(), '-', '')), 'Nike DF AEROREACT SS', @id,2369),
  (unhex(replace(uuid(), '-', '')), 'Nike 7 PHENOM 2-IN-1 SHORT', @id,1229),
  (unhex(replace(uuid(), '-', '')), 'Nike 5 PHENOM 2-IN-1 SHORT', @id,1169),
  (unhex(replace(uuid(), '-', '')), 'Nike DRI-FIT STRETCH WOVEN PANT', @id,1869);
SELECT @id:=id FROM manufacturers WHERE name = 'Craft';
INSERT INTO products (id, name, manufacturer_id, price) VALUES
  (unhex(replace(uuid(), '-', '')), 'Craft MIND SHORTSLEEVE TEE', @id,800),
  (unhex(replace(uuid(), '-', '')), 'Craft Essential short tights M', @id,1088),
  (unhex(replace(uuid(), '-', '')), 'Craft Classic Thermal Jersey M', @id,1945),
  (unhex(replace(uuid(), '-', '')), 'Craft EBC Aero Tri Top M', @id,2240);