-- 1
CREATE OR REPLACE FUNCTION format_contact(contact_name TEXT, contact_title TEXT)
RETURNS TEXT AS 
$$
	BEGIN
	    RETURN UPPER(contact_name) || ' (' || contact_title || ')';
	END;
$$ LANGUAGE plpgsql;

-- 1 test 
SELECT format_contact(last_name, title)
FROM employees;




-- 2 
CREATE OR REPLACE FUNCTION calculate_shipping_cost(order_id INT)
RETURNS NUMERIC AS
$$
	DECLARE
	    v_freight NUMERIC;
	BEGIN
	    SELECT freight
	    INTO v_freight
	    FROM orders o
	    WHERE o.order_id = calculate_shipping_cost.order_id;
	    IF v_freight > 100 THEN
	        RETURN v_freight * 1.10;
	    ELSE
	        RETURN v_freight;
	    END IF;
	END;
$$ LANGUAGE plpgsql;

-- 2 test

SELECT calculate_shipping_cost(order_id)
FROM orders;






-- 3
CREATE OR REPLACE FUNCTION get_order_total_discount(p_order_id INT)
RETURNS NUMERIC AS 
$$
	DECLARE
	    total_discount NUMERIC;
	BEGIN
	    SELECT SUM(unit_price * quantity * discount)
	    INTO total_discount
	    FROM order_details
	    WHERE order_id = p_order_id;
	
	    RETURN COALESCE(total_discount, 0);
	END;
$$ LANGUAGE plpgsql;


--3 test
SELECT order_id, get_order_total_discount(order_id)
FROM orders;





-- 4
CREATE OR REPLACE FUNCTION get_shipping_status(
    p_order_id INT
)
RETURNS TEXT AS 
$$
	DECLARE
	    v_required DATE;
	    v_shipped DATE;
	BEGIN
	    SELECT required_date, shipped_date
	    INTO v_required, v_shipped
	    FROM orders
	    WHERE order_id = p_order_id;
	
	    IF v_shipped IS NULL THEN
	        RETURN 'Non expédié';
	
	    ELSIF v_shipped > v_required THEN
	        RETURN 'En retard';
	
	    ELSE
	        RETURN 'À temps';
	    END IF;
	END;
$$ LANGUAGE plpgsql;


--4 test 
SELECT order_id, shipped_date, required_date, get_shipping_status(order_id)
FROM orders;






-- 5
CREATE OR REPLACE FUNCTION is_product_available(prod_id INT, desired_quantity INT)
RETURNS BOOLEAN AS 
$$
	DECLARE
	    stock INT;
	BEGIN
	    SELECT units_in_stock
	    INTO stock
	    FROM products
	    WHERE product_id = prod_id;
	
	    RETURN stock >= desired_quantity;
	END;
$$ LANGUAGE plpgsql;


-- 5 test
SELECT is_product_available(product_id, units_in_stock)
FROM products;




-- 6
CREATE OR REPLACE FUNCTION get_customer_average_basket(
    p_customer_id VARCHAR
)
RETURNS NUMERIC AS 
$$
	DECLARE
	    avg_total NUMERIC;
	BEGIN
	    SELECT AVG(order_total)
	    INTO avg_total
	    FROM (
	        SELECT o.order_id,
	               SUM(od.unit_price * od.quantity * (1 - od.discount)) AS order_total
	        FROM orders o
	        JOIN order_details od
	            ON o.order_id = od.order_id
	        WHERE o.customer_id = p_customer_id
	        GROUP BY o.order_id
	    ) sub;
	
	    RETURN COALESCE(avg_total, 0);
	END;
$$ LANGUAGE plpgsql;


-- test 6
SELECT 
    customer_id,
    get_customer_average_basket(customer_id)
FROM customers;






-- 7
CREATE OR REPLACE FUNCTION get_category_average_price(cat_name TEXT)
RETURNS NUMERIC AS 
$$
	DECLARE
	    avg_price NUMERIC;
	BEGIN
	    SELECT AVG(p.unit_price)
	    INTO avg_price
	    FROM products p
	    JOIN categories c
	        ON p.category_id = c.category_id
	    WHERE c.category_name = cat_name;
	
	    RETURN avg_price;
	END;
$$ LANGUAGE plpgsql;


-- 7 test
SELECT get_category_average_price(category_name)
FROM categories;




-- 8 corrigé final
CREATE OR REPLACE FUNCTION get_top_customers(min_orders INT)
RETURNS TABLE(company_name TEXT, total_orders INT) AS 
$$
BEGIN
    RETURN QUERY
    SELECT c.company_name::TEXT,
           COUNT(o.order_id)::INT AS total_orders
    FROM customers c
    JOIN orders o
        ON c.customer_id = o.customer_id
    GROUP BY c.company_name
    HAVING COUNT(o.order_id) >= min_orders
    ORDER BY total_orders DESC;
END;
$$ LANGUAGE plpgsql;


-- 8 test 
SELECT * FROM get_top_customers(5);







-- TRIGGER

--1
CREATE OR REPLACE FUNCTION check_quantity()
RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.quantity <= 0 THEN
        RAISE EXCEPTION 'Quantity must be greater than 0';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_check_quantity
BEFORE INSERT ON order_details
FOR EACH ROW
EXECUTE FUNCTION check_quantity();


--1 test
INSERT INTO order_details(order_id, product_id, unit_price, quantity, discount)
VALUES (10248, 1, 10, 0, 0);

INSERT INTO order_details(order_id, product_id, unit_price, quantity, discount)
VALUES (10248, 1, 10, 5, 0);




--2
CREATE OR REPLACE FUNCTION default_order_date()
RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.order_date IS NULL THEN
        NEW.order_date := CURRENT_DATE;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_default_order_date
BEFORE INSERT ON orders
FOR EACH ROW
EXECUTE FUNCTION default_order_date();


--2 test
SELECT order_id, order_date
FROM orders
ORDER BY order_id DESC
LIMIT 1;







--3
CREATE OR REPLACE FUNCTION prevent_price_drop()
RETURNS TRIGGER AS
$$
BEGIN
    IF NEW.unit_price < OLD.unit_price THEN
        RAISE EXCEPTION
        'Price cannot be decreased (old %, new %)',
        OLD.unit_price,
        NEW.unit_price;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_prevent_price_drop
BEFORE UPDATE ON products
FOR EACH ROW
EXECUTE FUNCTION prevent_price_drop();

--3 test
UPDATE products
SET unit_price = 1
WHERE product_id = 1;

UPDATE products
SET unit_price = unit_price + 1
WHERE product_id = 1;






--4
CREATE TABLE product_audit (
    product_id INT,
    product_name VARCHAR(100),
    deleted_at TIMESTAMP
);
CREATE OR REPLACE FUNCTION audit_product_delete()
RETURNS TRIGGER AS
$$
BEGIN
    INSERT INTO product_audit
    VALUES (
        OLD.product_id,
        OLD.product_name,
        NOW()
    );

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER trg_audit_product_delete
AFTER DELETE ON products
FOR EACH ROW
EXECUTE FUNCTION audit_product_delete();

--test 4
DELETE FROM order_details
WHERE product_id = 10;
DELETE FROM products
WHERE product_id = 10;

SELECT *
FROM product_audit
ORDER BY deleted_at DESC;








--5
CREATE OR REPLACE FUNCTION check_employee_customer_city()
RETURNS TRIGGER AS
$$
DECLARE
    emp_city TEXT;
    cust_city TEXT;
BEGIN

    SELECT city INTO emp_city
    FROM employees
    WHERE employee_id = NEW.employee_id;

    SELECT city INTO cust_city
    FROM customers
    WHERE customer_id = NEW.customer_id;

    IF emp_city = cust_city THEN
        RAISE EXCEPTION
        'Employee and customer cannot be in the same city (%).',
        emp_city;
    END IF;

    RETURN NEW;

END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_check_employee_customer_city
BEFORE INSERT ON orders
FOR EACH ROW
EXECUTE FUNCTION check_employee_customer_city();

--test 5 
INSERT INTO orders(order_id, customer_id, employee_id, ship_name)
VALUES (11078, 'ALFKI', 1, 'Test city rule');




--6
CREATE OR REPLACE FUNCTION update_stock_on_modification()
RETURNS TRIGGER AS
$$
BEGIN

    UPDATE products
    SET units_in_stock =
        units_in_stock + OLD.quantity - NEW.quantity
    WHERE product_id = NEW.product_id;

    RETURN NEW;

END;
$$ LANGUAGE plpgsql;	
CREATE TRIGGER trg_update_stock_on_modification
AFTER UPDATE ON order_details
FOR EACH ROW
EXECUTE FUNCTION update_stock_on_modification();

--test 6 
-- 1. Choisir un order_details existant
SELECT * FROM order_details LIMIT 1;

-- 2. Vérifier stock produit
SELECT product_id, units_in_stock FROM products WHERE product_id = 1;

-- 3. Modifier quantity
UPDATE order_details
SET quantity = 7
WHERE order_id = 10248 AND product_id = 1;

-- 4. Vérifier stock mis à jour
SELECT units_in_stock FROM products WHERE product_id = 1;






--7
CREATE OR REPLACE FUNCTION prevent_out_of_stock_order()
RETURNS TRIGGER AS
$$
DECLARE
    stock INT;
BEGIN

    SELECT units_in_stock
    INTO stock
    FROM products
    WHERE product_id = NEW.product_id;

    IF NEW.quantity > stock THEN
        RAISE EXCEPTION
        'Not enough stock. Requested %, available %',
        NEW.quantity,
        stock;
    END IF;

    RETURN NEW;

END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER trg_prevent_out_of_stock_order
BEFORE INSERT ON order_details
FOR EACH ROW
EXECUTE FUNCTION prevent_out_of_stock_order();

--7 test
INSERT INTO orders( order_id, customer_id, employee_id, order_date, ship_name)
VALUES ( 11079, 'ALFKI', 1, CURRENT_DATE, 'Trigger test');

INSERT INTO order_details( order_id, product_id, unit_price, quantity, discount)
VALUES (11079,1,10,999,0);


INSERT INTO order_details
VALUES (11079, 1, 10, 2, 0);