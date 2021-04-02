create table Bank (bankId integer not null auto_increment, amount float(0), concept varchar(255), parentCreditId_ integer, parentDebitId_ integer, parentHoodId_ integer, parentHouseId_ integer, incomeDate datetime(0), creditId integer, primary key (bankId)) engine=InnoDB
create table Buddy (buddyId integer not null auto_increment, email varchar(255), parentHoodId_ integer, parentHouseId_ integer, name varchar(255), phone varchar(255), houseId integer, primary key (buddyId)) engine=InnoDB
create table Credit (creditId integer not null auto_increment, amount float(0), concept varchar(255), creditDate datetime(0), parentDebitId_ integer, parentHoodId_ integer, parentHouseId_ integer, debitId integer, primary key (creditId)) engine=InnoDB
create table Debit (debitId integer not null auto_increment, amount float(0), concept varchar(255), debitDate datetime(0), parentHoodId_ integer, parentHouseId_ integer, paid bit(255), houseId integer, primary key (debitId)) engine=InnoDB
create table Expense (expenseId integer not null auto_increment, amount float(0), concept varchar(255), expenseDate datetime(0), parentHoodId_ integer, hoodId integer, primary key (expenseId)) engine=InnoDB
create table Hood (hoodId integer not null auto_increment, balance float(0), name varchar(255), primary key (hoodId)) engine=InnoDB
create table House (houseId integer not null auto_increment, balance float(0), parentHoodId_ integer, houseCode varchar(255), hoodId integer, primary key (houseId)) engine=InnoDB
alter table Bank add constraint FK591bgsg8op7gi3vrish1wgfhn foreign key (creditId) references Credit (creditId)
alter table Buddy add constraint FKdqtg8u4nryqn3oichels5mq20 foreign key (houseId) references House (houseId)
alter table Credit add constraint FKojt2xa1b2jeatp0bdbtdeytu6 foreign key (debitId) references Debit (debitId)
alter table Debit add constraint FK2j812lvl0jmone4wb6rhccu2l foreign key (houseId) references House (houseId)
alter table Expense add constraint FK8ohaeedcm85nky90f4xbtmqf7 foreign key (hoodId) references Hood (hoodId)
alter table House add constraint FKd0xshh7aq8wh1xhm9cllcaod0 foreign key (hoodId) references Hood (hoodId)
create table Bank (bankId integer not null auto_increment, amount float(0), concept varchar(255), parentCreditId_ integer, parentDebitId_ integer, parentHoodId_ integer, parentHouseId_ integer, incomeDate datetime(0), creditId integer, primary key (bankId)) engine=InnoDB
create table Buddy (buddyId integer not null auto_increment, email varchar(255), parentHoodId_ integer, parentHouseId_ integer, name varchar(255), phone varchar(255), houseId integer, primary key (buddyId)) engine=InnoDB
create table Credit (creditId integer not null auto_increment, amount float(0), concept varchar(255), creditDate datetime(0), parentDebitId_ integer, parentHoodId_ integer, parentHouseId_ integer, debitId integer, primary key (creditId)) engine=InnoDB
create table Debit (debitId integer not null auto_increment, amount float(0), concept varchar(255), debitDate datetime(0), parentHoodId_ integer, parentHouseId_ integer, paid integer, houseId integer, primary key (debitId)) engine=InnoDB
create table Expense (expenseId integer not null auto_increment, amount float(0), concept varchar(255), expenseDate datetime(0), parentHoodId_ integer, hoodId integer, primary key (expenseId)) engine=InnoDB
create table Hood (hoodId integer not null auto_increment, balance float(0), name varchar(255), primary key (hoodId)) engine=InnoDB
create table House (houseId integer not null auto_increment, balance float(0), parentHoodId_ integer, houseCode varchar(255), hoodId integer, primary key (houseId)) engine=InnoDB
alter table Bank add constraint FK591bgsg8op7gi3vrish1wgfhn foreign key (creditId) references Credit (creditId)
alter table Buddy add constraint FKdqtg8u4nryqn3oichels5mq20 foreign key (houseId) references House (houseId)
alter table Credit add constraint FKojt2xa1b2jeatp0bdbtdeytu6 foreign key (debitId) references Debit (debitId)
alter table Debit add constraint FK2j812lvl0jmone4wb6rhccu2l foreign key (houseId) references House (houseId)
alter table Expense add constraint FK8ohaeedcm85nky90f4xbtmqf7 foreign key (hoodId) references Hood (hoodId)
alter table House add constraint FKd0xshh7aq8wh1xhm9cllcaod0 foreign key (hoodId) references Hood (hoodId)
create table Bank (bankId integer not null auto_increment, amount float(0), concept varchar(255), parentCreditId_ integer, parentDebitId_ integer, parentHoodId_ integer, parentHouseId_ integer, incomeDate datetime(0), creditId integer, primary key (bankId)) engine=InnoDB
create table Buddy (buddyId integer not null auto_increment, email varchar(255), parentHoodId_ integer, parentHouseId_ integer, name varchar(255), phone varchar(255), houseId integer, primary key (buddyId)) engine=InnoDB
create table Credit (creditId integer not null auto_increment, amount float(0), concept varchar(255), creditDate datetime(0), parentDebitId_ integer, parentHoodId_ integer, parentHouseId_ integer, debitId integer, primary key (creditId)) engine=InnoDB
create table Debit (debitId integer not null auto_increment, amount float(0), concept varchar(255), debitDate datetime(0), parentHoodId_ integer, parentHouseId_ integer, paid integer, houseId integer, primary key (debitId)) engine=InnoDB
create table Expense (expenseId integer not null auto_increment, amount float(0), concept varchar(255), expenseDate datetime(0), parentHoodId_ integer, hoodId integer, primary key (expenseId)) engine=InnoDB
create table Hood (hoodId integer not null auto_increment, balance float(0), name varchar(255), primary key (hoodId)) engine=InnoDB
create table House (houseId integer not null auto_increment, balance float(0), parentHoodId_ integer, houseCode varchar(255), hoodId integer, primary key (houseId)) engine=InnoDB
alter table Bank add constraint FK591bgsg8op7gi3vrish1wgfhn foreign key (creditId) references Credit (creditId)
alter table Buddy add constraint FKdqtg8u4nryqn3oichels5mq20 foreign key (houseId) references House (houseId)
alter table Credit add constraint FKojt2xa1b2jeatp0bdbtdeytu6 foreign key (debitId) references Debit (debitId)
alter table Debit add constraint FK2j812lvl0jmone4wb6rhccu2l foreign key (houseId) references House (houseId)
alter table Expense add constraint FK8ohaeedcm85nky90f4xbtmqf7 foreign key (hoodId) references Hood (hoodId)
alter table House add constraint FKd0xshh7aq8wh1xhm9cllcaod0 foreign key (hoodId) references Hood (hoodId)
create table Bank (bankId integer not null auto_increment, amount float(0), concept varchar(255), parentCreditId_ integer, parentDebitId_ integer, parentHoodId_ integer, parentHouseId_ integer, incomeDate datetime(0), creditId integer, primary key (bankId)) engine=InnoDB
create table Buddy (buddyId integer not null auto_increment, email varchar(255), parentHoodId_ integer, parentHouseId_ integer, name varchar(255), phone varchar(255), houseId integer, primary key (buddyId)) engine=InnoDB
create table Credit (creditId integer not null auto_increment, amount float(0), concept varchar(255), creditDate datetime(0), parentDebitId_ integer, parentHoodId_ integer, parentHouseId_ integer, debitId integer, primary key (creditId)) engine=InnoDB
create table Debit (debitId integer not null auto_increment, amount float(0), concept varchar(255), debitDate datetime(0), parentHoodId_ integer, parentHouseId_ integer, paid integer, houseId integer, primary key (debitId)) engine=InnoDB
create table Expense (expenseId integer not null auto_increment, amount float(0), concept varchar(255), expenseDate datetime(0), parentHoodId_ integer, hoodId integer, primary key (expenseId)) engine=InnoDB
create table Hood (hoodId integer not null auto_increment, balance float(0), name varchar(255), primary key (hoodId)) engine=InnoDB
create table House (houseId integer not null auto_increment, balance float(0), parentHoodId_ integer, houseCode varchar(255), hoodId integer, primary key (houseId)) engine=InnoDB
alter table Bank add constraint FK591bgsg8op7gi3vrish1wgfhn foreign key (creditId) references Credit (creditId)
alter table Buddy add constraint FKdqtg8u4nryqn3oichels5mq20 foreign key (houseId) references House (houseId)
alter table Credit add constraint FKojt2xa1b2jeatp0bdbtdeytu6 foreign key (debitId) references Debit (debitId)
alter table Debit add constraint FK2j812lvl0jmone4wb6rhccu2l foreign key (houseId) references House (houseId)
alter table Expense add constraint FK8ohaeedcm85nky90f4xbtmqf7 foreign key (hoodId) references Hood (hoodId)
alter table House add constraint FKd0xshh7aq8wh1xhm9cllcaod0 foreign key (hoodId) references Hood (hoodId)
