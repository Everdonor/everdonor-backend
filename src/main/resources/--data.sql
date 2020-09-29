INSERT INTO user (name, is_active,email, password, phone_number, address, longitude, latitude,image,report_Quantity)
    VALUES ('Centro de donaciones Donarte',true,'donarte@gmail.com','pass123', 1131112345,'Address 123', -58.3,-34.72,"https://i0.wp.com/updatemexico.com/wp-content/uploads/2020/06/WhatsApp-Image-2020-06-11-at-16.44.11.jpeg?fit=1125%2C633&ssl=1",0);
INSERT INTO user (name, is_active,email, password, phone_number, address, longitude, latitude,image,report_Quantity)
    VALUES ('Fundación personas',true,'fundacion_personas@gmail.com','pass123', 1131167890,'Address 123', -58.334,-34.71,"https://media.lacapital.com.ar/adjuntos/203/imagenes/028/670/0028670283.jpg",0);
INSERT INTO user (name,is_active, email, password, phone_number, address, longitude, latitude,image,report_Quantity)
    VALUES ('Comedor Quilmes',true,'comedor_quilmes@gmail.com','pass123', 1131110293, 'Address 123', -58.298,-34.75,"https://lh3.googleusercontent.com/proxy/LeWdvf6LdjYBabt-26pYxYOGVkO2xoYPrFwlEeg78d2FHm88OI_Zfho6b1-qYvcjp8tGQJ9ihsk2hxvuPkb_44CzgCWzbD1VlNGxSO_Or5tF1FRA-Wl9X879JXPNz_cbAEdoD2RQ64Uuh5Aphg",0);

INSERT INTO user_donation_types (user_id, donation_type)
    VALUES (1,'FUNDING');
INSERT INTO user_donation_types (user_id, donation_type)
    VALUES (2,'CLOTHES');
INSERT INTO user_donation_types (user_id, donation_type)
    VALUES (3,'FOOD');
INSERT INTO user_donation_types (user_id, donation_type)
    VALUES (3,'CLOTHES');

--     DEPRECATED
--     Replaced by actual user registration in EverdonorApplication (for propper password encryption)