INSERT INTO user_type (user_type_name) VALUES ('ROLE_GENERAL_RECIPIENT');
INSERT INTO user_type (user_type_name) VALUES ('ROLE_CONSULTANT');
INSERT INTO user_type (user_type_name) VALUES ('ROLE_CLIENT');
INSERT INTO user_type (user_type_name) VALUES ('ROLE_ADMIN');

INSERT INTO `user` (username, password, user_type_user_type_id, enabled) VALUES ('gen', 'gen', 1, true);
INSERT INTO `user` (username, password, user_type_user_type_id, enabled) VALUES ('admin', 'admin', 4, true);
INSERT INTO `user` (username, password, user_type_user_type_id, enabled) VALUES ('client', 'client', 3, true);
INSERT INTO `user` (username, password, user_type_user_type_id, enabled) VALUES ('consultant', 'consultant', 2, true);
INSERT INTO `user` (username, password, user_type_user_type_id, enabled) VALUES ('d', '#', 4, true);
INSERT INTO `user` (username, password, user_type_user_type_id, enabled) VALUES ('x', '#', 4, true);
INSERT INTO `user` (username, password, user_type_user_type_id, enabled) VALUES ('y', '#', 4, true);


INSERT INTO status (status_name) VALUES ('NEW');
INSERT INTO status (status_name) VALUES ('OPEN');
INSERT INTO status (status_name) VALUES ('SOLVED');

INSERT INTO queue (queue_name) VALUES ('Test_Q_1');
INSERT INTO queue (queue_name) VALUES ('Test_Q_2');

INSERT INTO message_type (message_type_name) VALUES ('Internal Note');
INSERT INTO message_type (message_type_name) VALUES ('Reply');

INSERT INTO conversation (conversation_id) VALUES (1);
INSERT INTO conversation (conversation_id) VALUES (2);
INSERT INTO conversation (conversation_id) VALUES (3);
INSERT INTO conversation (conversation_id) VALUES (4);
INSERT INTO conversation (conversation_id) VALUES (5);
INSERT INTO conversation (conversation_id) VALUES (6);

INSERT INTO ticket (queue_queue_id, ticket_status_status_id, user_user_id, conversation_conversation_id) VALUES (1, 1, 1, 1);
INSERT INTO ticket (queue_queue_id, ticket_status_status_id, user_user_id, conversation_conversation_id) VALUES (1, 2, 2, 2);
INSERT INTO ticket (queue_queue_id, ticket_status_status_id, user_user_id, conversation_conversation_id) VALUES (1, 3, 2, 3);
INSERT INTO ticket (queue_queue_id, ticket_status_status_id, user_user_id, conversation_conversation_id) VALUES (2, 1, 1, 4);
INSERT INTO ticket (queue_queue_id, ticket_status_status_id, user_user_id, conversation_conversation_id) VALUES (2, 2, 3, 5);
INSERT INTO ticket (queue_queue_id, ticket_status_status_id, user_user_id, conversation_conversation_id) VALUES (2, 3, 3, 6);

-- Konwersacja 1
INSERT INTO message (conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (1, '2020-01-01 09:42:00', 1, 4, 1, 'Dzien dośbry. Chcialbym poznac');

-- Konwersacja 2
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (2, '2020-01-02 12:00:00', 1, 2, 4, 'Dzień dobry. Kontaktuje się w sprawie uszkodzenia mienia.');
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (2, '2020-01-02 12:30:00', 1, 4, 2, 'Nic mi o tym nie wiadomo!');
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (2, '2020-01-02 12:30:00', 1, 2, 4, 'Dziękuję za odpowiedź! Pozdrawiam.');
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (2, '2020-01-02 12:31:00', 2, 2, 2, 'SPRAWA ZAKNIĘTA');
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (2, '2020-01-02 12:45:00', 2, 6, 2, 'NIEPRAWDA - POPROŚ O WIĘCEJ SZCZEGÓŁÓW!!');

-- Konswersacja 3
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (3, '2020-01-05 20:00:00', 1, 4, 1, 'Dzień dobry. Potrzebuję pomocy!');
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (3, '2020-01-05 20:05:00', 1, 2, 4, 'W czym mogę pomóc?');
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (3, '2020-01-05 20:30:00', 1, 4, 2, 'Już zapomniałem... Nieważne.');
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (3, '2020-01-05 20:31:00', 2, 2, 1, 'SPRAWA ZAKNIĘTA');
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (3, '2020-01-06 08:15:00', 2, 6, 1, 'DZIWNY KLIENT... ALE DOBRA ROBOTA!');

--Konwersacja 4
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (4, '2020-02-12 15:37:00', 1, 5, 1, 'Dzień dobry. Chciałbym się dowiedzieć jaki jest postęp w sprawie reklamacji mojego sprzętu.');

--Konwersacja 5
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (5, '2020-02-16 14:02:00', 1, 5, 3, 'Dzień dobry. Jestem niezadowolony z waszych usług.');
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (5, '2020-02-16 14:05:00', 1, 3, 5, 'Proszę wyjaśnić.');
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (5, '2020-01-16 14:07:00', 1, 5, 3, 'Czekam na odpowiedź już kilka dni!!');
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (5, '2020-01-16 14:20:00', 2, 7, 1, 'ZAJMIJ SIĘ TYM - PILNIE!!!');

-- Konswersacja 6
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (6, '2020-02-20 22:00:00', 1, 5, 1, 'Halo! Jest tam kto?');
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (6, '2020-02-20 22:10:00', 1, 3, 5, 'Oczywiście. W czym mogę pomóc?');
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (6, '2020-02-20 22:12:00', 1, 5, 3, 'Tylko sprawdzałem czy ktoś tu pracuje.');
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (6, '2020-02-20 22:13:00', 2, 3, 1, 'SPAM');
INSERT INTO message(conversation_conversation_id, created, message_type_message_type_id, from_user_user_id, to_user_user_id, body) VALUES (6, '2020-02-21 08:15:00', 2, 7, 1, 'ZGADZA SIĘ');






commit;