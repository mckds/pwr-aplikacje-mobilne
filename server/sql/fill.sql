insert into account(password, username) values ('$2a$10$8XnWkXoae/V3U7W4hN97F.xqCVMRxFbDK5GAJp20HLySrtcCWjl2q', 'user2');
insert into account(password, username)  values ( '$2a$10$8XnWkXoae/V3U7W4hN97F.xqCVMRxFbDK5GAJp20HLySrtcCWjl2q', 'George');
insert into account(password, username)  values ( '$2a$10$8XnWkXoae/V3U7W4hN97F.xqCVMRxFbDK5GAJp20HLySrtcCWjl2q', 'Johnny');

insert into budget(name, end_date, expenditure_limit, start_date) values ('fun',sysdate(), 3000, sysdate() + 9);
insert into budget(name, end_date, expenditure_limit, start_date) values ('home', sysdate() + 10, 3000, sysdate() + 20);
insert into category(name, budget_id) values ('drinks', 1);
insert into category(name, budget_id) values ('food', 1);
insert into category(name, budget_id) values ('tickets', 1);
insert into category(name, budget_id) values ('food', 2);
insert into category(name, budget_id) values ('stuff', 2);
insert into budget_account(budget_id, account_id) values (1,1);
insert into budget_account(budget_id, account_id) values (1,2);
insert into budget_account(budget_id, account_id) values (2,1);
insert into expense(amount, date, account_id, budget_id, category_id) values (70, sysdate(), 1, 1, 1);
insert into expense(amount, date, account_id, budget_id, category_id) values (30, sysdate(), 1, 1, 2);
insert into expense(amount, date, account_id, budget_id, category_id) values (90, sysdate(), 2, 1, 3);
insert into expense(amount, date, account_id, budget_id, category_id) values (12, sysdate(), 2, 1, 1);


select * from budget_account;
select * from budget;
select * from category;
select * from expense;
delete from budget where budget_id = 37;

