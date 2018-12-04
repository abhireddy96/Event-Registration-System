insert into Employee(EMAIL_ID, JOIN_DATE, NAME, MID) values('M1041919@my.com','2017-07-22','Abhishek Reddy','M1041919');
insert into Employee(EMAIL_ID, JOIN_DATE, NAME, MID) values('M1031900@my.com','2014-06-24','Sunil Kimar','M1031900');
insert into Employee(EMAIL_ID, JOIN_DATE, NAME, MID) values('M1024900@my.com','2011-05-18','Rahul Mehta','M1024900');
insert into Employee(EMAIL_ID, JOIN_DATE, NAME, MID) values('M1020922@my.com','2009-04-08','Rakesh Bawne','M1020922');
insert into Employee(EMAIL_ID, JOIN_DATE, NAME, MID) values('M1001923@my.com','2007-10-14','Mahendra Modi','M1001923');

insert into Event(description, EVENT_TITLE,EVENT_ID) values('Held Every Tuesday and Friday.Calorie Burning Workouts','Boxing',1);
insert into Event(description, EVENT_TITLE,EVENT_ID) values('Held Every Saturday and Sunday.Old tradational excercise','Yoga',2);
insert into Event(description, EVENT_TITLE,EVENT_ID) values('Held Every Wednesday and Thursday.Good low intense cardio ','Cycling',3);
insert into Event(description, EVENT_TITLE,EVENT_ID) values('Held Every Monday and Friday.Time to get cool down','Music',4);


insert into EMPLOYEE_EVENT(MID_FK, EVENT_ID_FK) values('M1041919',1);
insert into EMPLOYEE_EVENT(MID_FK, EVENT_ID_FK) values('M1041919',3);
insert into EMPLOYEE_EVENT(MID_FK, EVENT_ID_FK) values('M1001923',2);
insert into EMPLOYEE_EVENT(MID_FK, EVENT_ID_FK) values('M1024900',4);
insert into EMPLOYEE_EVENT(MID_FK, EVENT_ID_FK) values('M1001923',4);
insert into EMPLOYEE_EVENT(MID_FK, EVENT_ID_FK) values('M1031900',1);