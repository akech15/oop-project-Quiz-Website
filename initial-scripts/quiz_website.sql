use quiz_website;

SET FOREIGN_KEY_CHECKS = 1;
drop table if exists answer;
drop table if exists passed_quiz;
drop table if exists friends;
drop table if exists question;
drop table if exists quiz;

drop table if exists user;

CREATE TABLE user
(
    id       int         NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    CONSTRAINT unique_username UNIQUE (username),
    PRIMARY KEY (id)
);


create table friends
(
    user_id   int NOT NULL,
    friend_id int NOT NULL,
    status varchar(10) NOT NULL,
    FOREIGN KEY (user_id) references user (id),
    foreign key (friend_id) references user (id)
);

create table quiz
(
    id            int         NOT NULL,
    name          varchar(50) not null,
    creator_id    int         not null,
    creation_date DATETIME,
    primary key (id),
    foreign key (creator_id) references user (id)
);

CREATE TABLE question
(
    id             int          NOT NULL PRIMARY KEY,
    quiz_id        int          NOT NULL,
    category       VARCHAR(50)  NOT NULL,
    question       VARCHAR(200) NOT NULL,
    type           varchar(50)  not null,
    correct_answer varchar(200) not null,
    max_score      int          not null,
    FOREIGN KEY (quiz_id) references quiz (id)
);

CREATE TABLE passed_quiz
(
    id       int  not null primary key,
    user_id  int  NOT NULL,
    quiz_id  int  NOT NULL,
    score    int  not null,
    duration date not null,
    FOREIGN KEY (quiz_id) references quiz (id),
    FOREIGN KEY (user_id) references user (id)
);

CREATE TABLE answer
(
    id             int NOT NULL PRIMARY KEY,
    question_id    int NOT NULL,
    user_answer    VARCHAR(1000),
    passed_quiz_id int not null,
    FOREIGN KEY (question_id) references question (id),
    foreign key (passed_quiz_id) references passed_quiz (id)
);