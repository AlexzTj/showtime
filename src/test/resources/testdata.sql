delete from PROGRESS;
delete from  TEST_CASES;
delete from  TASK;
delete from CATEGORY;
delete from USERS;


INSERT INTO CATEGORY(CATEGORY_ID,TITLE) VALUES (1,'LEVEL1');
INSERT INTO CATEGORY(CATEGORY_ID,TITLE) VALUES (2,'LEVEL2');

INSERT INTO TASK(TASK_ID,TITLE,DESCRIPTION,LAST_MODIFIED,CREATED_ON,CATEGORY_ID) VALUES (1,'MYTITLE','DESCR',NULL,'2017-09-17 18:47:52.69',1);
INSERT INTO TASK(TASK_ID,TITLE,DESCRIPTION,LAST_MODIFIED,CREATED_ON,CATEGORY_ID) VALUES (2,'MYTITL22E','DESC22R',NULL,'2017-09-18 18:47:52.69',1);
INSERT INTO USERS(USERNAME,PASSWORD,ENABLED) VALUES ('admin.user','admin',true);
INSERT INTO PROGRESS(TASK_ID,USER_ID,SOLUTION,ATTEMPTS,LANGUAGE) VALUES (2,'admin.user','print 1',1,1);
INSERT INTO TEST_CASES(TEST_CASE_ID,TASK_ID,INPUT,OUTPUT) VALUES (1,1,'1','2');
INSERT INTO TEST_CASES(TEST_CASE_ID,TASK_ID,INPUT,OUTPUT) VALUES (2,1,'3','2');
INSERT INTO TEST_CASES(TEST_CASE_ID,TASK_ID,INPUT,OUTPUT) VALUES (3,2,'1','2');

