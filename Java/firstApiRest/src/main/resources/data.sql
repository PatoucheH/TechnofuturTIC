-- ============================================================
-- Données de test - my_shop
-- Remise à zéro à chaque démarrage (mode développement)
-- ============================================================

TRUNCATE TABLE t_user_role, t_command_line, t_stock_move, t_order, t_product, t_stock, t_category, t_user
    RESTART IDENTITY CASCADE;

-- ------------------------------------------------------------
-- Catégories
-- ------------------------------------------------------------
INSERT INTO t_category (id, created_at, updated_at, name) VALUES
  (1, NOW(), NOW(), 'Électronique'),
  (2, NOW(), NOW(), 'Vêtements'),
  (3, NOW(), NOW(), 'Livres');

-- ------------------------------------------------------------
-- Utilisateurs
-- ------------------------------------------------------------
INSERT INTO t_user (id, created_at, updated_at, email, username, password) VALUES
  ('00000000-0000-0000-0000-000000000001', NOW(), NOW(), 'admin@shop.com', 'admin', 'password123'),
  ('00000000-0000-0000-0000-000000000002', NOW(), NOW(), 'alice@shop.com', 'alice', 'password123'),
  ('00000000-0000-0000-0000-000000000003', NOW(), NOW(), 'bob@shop.com',   'bob',   'password123');

INSERT INTO t_user_role (user_id, roles) VALUES
  ('00000000-0000-0000-0000-000000000001', 'ADMIN'),
  ('00000000-0000-0000-0000-000000000001', 'USER'),
  ('00000000-0000-0000-0000-000000000002', 'USER'),
  ('00000000-0000-0000-0000-000000000003', 'USER');

-- ------------------------------------------------------------
-- Stocks
-- ------------------------------------------------------------
INSERT INTO t_stock (id, created_at, updated_at, quantity, alert_quantity) VALUES
  (1, NOW(), NOW(), 100, 10),
  (2, NOW(), NOW(),  50,  5),
  (3, NOW(), NOW(), 200, 20);

-- ------------------------------------------------------------
-- Produits (category_id + stock_id)
-- ------------------------------------------------------------
INSERT INTO t_product (id, created_at, updated_at, name, description, price, category_id, stock_id) VALUES
  ('00000000-0000-0000-0002-000000000001', NOW(), NOW(), 'iPhone 15',    'Smartphone Apple 128 Go, puce A16 Bionic',   99999, 1, 1),
  ('00000000-0000-0000-0002-000000000002', NOW(), NOW(), 'T-Shirt Bleu', 'T-shirt coton bio, coupe regular, taille M',  1999, 2, 2),
  ('00000000-0000-0000-0002-000000000003', NOW(), NOW(), 'Clean Code',   'Guide du code propre par Robert C. Martin',   3999, 3, 3);

-- ------------------------------------------------------------
-- Mouvements de stock (stock_id FK dans t_stock_move)
-- ------------------------------------------------------------
INSERT INTO t_stock_move (id, created_at, updated_at, quantity, type, description, stock_id) VALUES
  (1, NOW(), NOW(), 100, 'IN',  'Approvisionnement initial', 1),
  (2, NOW(), NOW(),  10, 'OUT', 'Ventes semaine 1',          1),
  (3, NOW(), NOW(),  50, 'IN',  'Approvisionnement initial', 2),
  (4, NOW(), NOW(),   5, 'OUT', 'Ventes semaine 1',          2),
  (5, NOW(), NOW(), 200, 'IN',  'Approvisionnement initial', 3),
  (6, NOW(), NOW(),  15, 'OUT', 'Ventes semaine 1',          3);

-- ------------------------------------------------------------
-- Commandes (user_id + adresse embedded)
-- ------------------------------------------------------------
INSERT INTO t_order (id, created_at, updated_at, order_status, total_price, user_id,
                     country, zip_code, city, street, street_number) VALUES
  ('00000000-0000-0000-0001-000000000001', NOW(), NOW(), 'DELIVERED',  999.99, '00000000-0000-0000-0000-000000000001', 'Belgique', '4000',  'Liège',     'Rue de la Paix',   12),
  ('00000000-0000-0000-0001-000000000002', NOW(), NOW(), 'CONFIRMED',   59.97, '00000000-0000-0000-0000-000000000002', 'Belgique', '1000',  'Bruxelles', 'Boulevard du Midi',  5),
  ('00000000-0000-0000-0001-000000000003', NOW(), NOW(), 'PENDING',     39.99, '00000000-0000-0000-0000-000000000003', 'France',   '75001', 'Paris',     'Rue de Rivoli',     42);

-- ------------------------------------------------------------
-- Lignes de commande (product_id + order_id FK dans t_command_line)
-- ------------------------------------------------------------
INSERT INTO t_command_line (id, created_at, updated_at, quantity, product_id, order_id) VALUES
  (1, NOW(), NOW(), 1, '00000000-0000-0000-0002-000000000001', '00000000-0000-0000-0001-000000000001'),
  (2, NOW(), NOW(), 3, '00000000-0000-0000-0002-000000000002', '00000000-0000-0000-0001-000000000002'),
  (3, NOW(), NOW(), 1, '00000000-0000-0000-0002-000000000003', '00000000-0000-0000-0001-000000000003');
