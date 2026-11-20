
CREATE DATABASE IF NOT EXISTS advanced_library_db;

USE advanced_library_db;

CREATE TABLE IF NOT EXISTS admin (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS libraries (
    library_id INT AUTO_INCREMENT PRIMARY KEY,
    library_name VARCHAR(150) NOT NULL,
    area VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    contact_number VARCHAR(20),
    email VARCHAR(100),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(150) NOT NULL,
    isbn VARCHAR(30) UNIQUE,
    book_code VARCHAR(30) UNIQUE,
    category VARCHAR(100),
    edition VARCHAR(50),
    publisher VARCHAR(150),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_title (title),
    INDEX idx_author (author),
    INDEX idx_category (category)
);

CREATE TABLE IF NOT EXISTS library_inventory (
    inventory_id INT AUTO_INCREMENT PRIMARY KEY,
    library_id INT NOT NULL,
    book_id INT NOT NULL,
    total_copies INT NOT NULL DEFAULT 0,
    available_copies INT NOT NULL DEFAULT 0,
    book_price DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_inventory_library
        FOREIGN KEY (library_id)
        REFERENCES libraries(library_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_inventory_book
        FOREIGN KEY (book_id)
        REFERENCES books(book_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT unique_library_book
        UNIQUE (library_id, book_id),

    CONSTRAINT chk_total_copies
        CHECK (total_copies >= 0),

    CONSTRAINT chk_available_copies
        CHECK (available_copies >= 0
        AND available_copies <= total_copies),

    CONSTRAINT chk_book_price
        CHECK (book_price >= 0)
);

INSERT INTO libraries
(library_name, area, address, contact_number, email)
SELECT 'Central Library', 'Zindabazar',
'Zindabazar, Sylhet', '01711111111',
'central@example.com'
WHERE NOT EXISTS (
    SELECT 1 FROM libraries
    WHERE library_name = 'Central Library'
);

INSERT INTO libraries
(library_name, area, address, contact_number, email)
SELECT 'City Library', 'Ambarkhana',
'Ambarkhana, Sylhet', '01722222222',
'city@example.com'
WHERE NOT EXISTS (
    SELECT 1 FROM libraries
    WHERE library_name = 'City Library'
);

INSERT INTO libraries
(library_name, area, address, contact_number, email)
SELECT 'Knowledge Library', 'Subidbazar',
'Subidbazar, Sylhet', '01733333333',
'knowledge@example.com'
WHERE NOT EXISTS (
    SELECT 1 FROM libraries
    WHERE library_name = 'Knowledge Library'
);

INSERT INTO books
(title, author, isbn, book_code, category, edition, publisher)
SELECT 'Introduction to Algorithms',
'Thomas H. Cormen', '9780262046305',
'BK101', 'Computer Science', '4th Edition',
'MIT Press'
WHERE NOT EXISTS (
    SELECT 1 FROM books WHERE book_code = 'BK101'
);

INSERT INTO books
(title, author, isbn, book_code, category, edition, publisher)
SELECT 'Database System Concepts',
'Abraham Silberschatz', '9780078022159',
'BK102', 'Database', '7th Edition',
'McGraw Hill'
WHERE NOT EXISTS (
    SELECT 1 FROM books WHERE book_code = 'BK102'
);

INSERT INTO books
(title, author, isbn, book_code, category, edition, publisher)
SELECT 'Clean Code',
'Robert C. Martin', '9780132350884',
'BK103', 'Programming', '1st Edition',
'Prentice Hall'
WHERE NOT EXISTS (
    SELECT 1 FROM books WHERE book_code = 'BK103'
);

INSERT INTO books
(title, author, isbn, book_code, category, edition, publisher)
SELECT 'Java: The Complete Reference',
'Herbert Schildt', '9781260440232',
'BK104', 'Programming', '12th Edition',
'McGraw Hill'
WHERE NOT EXISTS (
    SELECT 1 FROM books WHERE book_code = 'BK104'
);

INSERT INTO library_inventory
(library_id, book_id, total_copies, available_copies, book_price)
SELECT l.library_id, b.book_id, 10, 7, 850.00
FROM libraries l, books b
WHERE l.library_name = 'Central Library'
AND b.book_code = 'BK101'
AND NOT EXISTS (
    SELECT 1 FROM library_inventory i
    WHERE i.library_id = l.library_id
    AND i.book_id = b.book_id
);

INSERT INTO library_inventory
(library_id, book_id, total_copies, available_copies, book_price)
SELECT l.library_id, b.book_id, 8, 5, 750.00
FROM libraries l, books b
WHERE l.library_name = 'Central Library'
AND b.book_code = 'BK102'
AND NOT EXISTS (
    SELECT 1 FROM library_inventory i
    WHERE i.library_id = l.library_id
    AND i.book_id = b.book_id
);

INSERT INTO library_inventory
(library_id, book_id, total_copies, available_copies, book_price)
SELECT l.library_id, b.book_id, 5, 3, 650.00
FROM libraries l, books b
WHERE l.library_name = 'Central Library'
AND b.book_code = 'BK103'
AND NOT EXISTS (
    SELECT 1 FROM library_inventory i
    WHERE i.library_id = l.library_id
    AND i.book_id = b.book_id
);

INSERT INTO library_inventory
(library_id, book_id, total_copies, available_copies, book_price)
SELECT l.library_id, b.book_id, 6, 4, 900.00
FROM libraries l, books b
WHERE l.library_name = 'City Library'
AND b.book_code = 'BK101'
AND NOT EXISTS (
    SELECT 1 FROM library_inventory i
    WHERE i.library_id = l.library_id
    AND i.book_id = b.book_id
);

INSERT INTO library_inventory
(library_id, book_id, total_copies, available_copies, book_price)
SELECT l.library_id, b.book_id, 10, 8, 700.00
FROM libraries l, books b
WHERE l.library_name = 'City Library'
AND b.book_code = 'BK103'
AND NOT EXISTS (
    SELECT 1 FROM library_inventory i
    WHERE i.library_id = l.library_id
    AND i.book_id = b.book_id
);

INSERT INTO library_inventory
(library_id, book_id, total_copies, available_copies, book_price)
SELECT l.library_id, b.book_id, 7, 5, 800.00
FROM libraries l, books b
WHERE l.library_name = 'City Library'
AND b.book_code = 'BK104'
AND NOT EXISTS (
    SELECT 1 FROM library_inventory i
    WHERE i.library_id = l.library_id
    AND i.book_id = b.book_id
);

INSERT INTO library_inventory
(library_id, book_id, total_copies, available_copies, book_price)
SELECT l.library_id, b.book_id, 12, 10, 780.00
FROM libraries l, books b
WHERE l.library_name = 'Knowledge Library'
AND b.book_code = 'BK102'
AND NOT EXISTS (
    SELECT 1 FROM library_inventory i
    WHERE i.library_id = l.library_id
    AND i.book_id = b.book_id
);

INSERT INTO library_inventory
(library_id, book_id, total_copies, available_copies, book_price)
SELECT l.library_id, b.book_id, 5, 2, 820.00
FROM libraries l, books b
WHERE l.library_name = 'Knowledge Library'
AND b.book_code = 'BK104'
AND NOT EXISTS (
    SELECT 1 FROM library_inventory i
    WHERE i.library_id = l.library_id
    AND i.book_id = b.book_id
);

SELECT * FROM admin;
SELECT * FROM libraries;
SELECT * FROM books;
SELECT * FROM library_inventory;

SELECT
    b.title,
    b.author,
    b.category,
    l.library_name,
    l.area,
    l.address,
    i.total_copies,
    i.available_copies,
    i.book_price
FROM library_inventory i
JOIN books b ON i.book_id = b.book_id
JOIN libraries l ON i.library_id = l.library_id
WHERE b.title LIKE '%Java%'
   OR b.author LIKE '%Java%'
   OR b.category LIKE '%Java%'
   OR b.isbn LIKE '%Java%'
   OR b.book_code LIKE '%Java%';

SELECT
    SUM(total_copies) AS total_books,
    SUM(available_copies) AS available_books,
    SUM(total_copies - available_copies) AS unavailable_books
FROM library_inventory;

SELECT
    l.library_name,
    COUNT(i.inventory_id) AS different_book_types,
    COALESCE(SUM(i.total_copies), 0) AS total_copies,
    COALESCE(SUM(i.available_copies), 0) AS available_copies
FROM libraries l
LEFT JOIN library_inventory i
ON l.library_id = i.library_id
GROUP BY l.library_id, l.library_name;
