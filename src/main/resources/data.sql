INSERT INTO CARRIER(name) VALUES('Tim');
INSERT INTO CARRIER(name) VALUES('vivo');
INSERT INTO CARRIER(name) VALUES('oi');
INSERT INTO CARRIER(name) VALUES('claro');

INSERT INTO DDD(code, name) VALUES(47, 'SC Norte');
INSERT INTO DDD(code, name) VALUES(51, 'RS POA');
INSERT INTO DDD(code, name) VALUES(41, 'PR CTB');
INSERT INTO DDD(code, name) VALUES(11, 'SP Capital');

INSERT INTO PLAN(franchise, minutes, value, type, carrier_id) VALUES('200MB', 100, 89.90,'CONTROLE', 1);
INSERT INTO DDD_PLAN(ddd_code, plan_id)  VALUES(47,1);

INSERT INTO PLAN(franchise, minutes, value, type, carrier_id) VALUES('100MB', 100, 59.90,'PRE', 2);
INSERT INTO DDD_PLAN(ddd_code, plan_id)  VALUES(51,2);

INSERT INTO PLAN(franchise, minutes, value, type, carrier_id) VALUES('1500MB', 100, 69.90,'POS', 3);
INSERT INTO DDD_PLAN(ddd_code, plan_id)  VALUES(11,3);
INSERT INTO DDD_PLAN(ddd_code, plan_id)  VALUES(47,3);
INSERT INTO DDD_PLAN(ddd_code, plan_id)  VALUES(41,3);

INSERT INTO PLAN(franchise, minutes, value, type, carrier_id) VALUES('200MB', 100, 89.90,'CONTROLE', 3);
INSERT INTO DDD_PLAN(ddd_code, plan_id)  VALUES(47,4);

INSERT INTO PLAN(franchise, minutes, value, type, carrier_id) VALUES('100MB', 100, 59.90,'PRE', 4);
INSERT INTO DDD_PLAN(ddd_code, plan_id)  VALUES(47,5);

INSERT INTO PLAN(franchise, minutes, value, type, carrier_id) VALUES('200MB', 100, 89.90,'CONTROLE', 3);
INSERT INTO DDD_PLAN(ddd_code, plan_id)  VALUES(47,1);



