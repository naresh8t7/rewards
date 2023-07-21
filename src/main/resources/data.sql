
  
INSERT INTO customers (id, name) VALUES
  (1000, 'Spectrum customer 1'),
  (1001, 'Spectrum customer 2');
  
INSERT INTO transactions (id, notes, transaction_date, amount, customer_id) VALUES
  (1000, 'cable', now() , 120.56, 1000),
  (1001, 'office wifi', now(), 300, 1000),
  (1002, 'internet', now(), 200, 1001),
  (1003, 'office wifi',  current_date - 1, 120, 1000),
  (1004, 'office wifi',  current_date - 2, 10.07, 1000),
  (1005, 'office wifi',  current_date - 3, 50.96, 1000),
  (1006, 'office wifi',  current_date - 4, 200, 1000);