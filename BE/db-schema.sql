ALTER TABLE comment
    DROP FOREIGN KEY comment_issue;
ALTER TABLE comment
    DROP FOREIGN KEY comment_writer;
ALTER TABLE image
    DROP FOREIGN KEY image_comment;
ALTER TABLE issue
    DROP FOREIGN KEY issue_author;
ALTER TABLE issue
    DROP FOREIGN KEY issue_milestone;
ALTER TABLE issue_assignee
    DROP FOREIGN KEY issue_assignee_user;
ALTER TABLE issue_assignee
    DROP FOREIGN KEY issue_assignee_issue;
ALTER TABLE issue_label
    DROP FOREIGN KEY issue_label_issue;
ALTER TABLE issue_label
    DROP FOREIGN KEY issue_label_label;

DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS image;
DROP TABLE IF EXISTS issue;
DROP TABLE IF EXISTS issue_assignee;
DROP TABLE IF EXISTS issue_label;
DROP TABLE IF EXISTS label;
DROP TABLE IF EXISTS milestone;
DROP TABLE IF EXISTS user;

CREATE TABLE comment
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    created_at  DATETIME(6),
    description VARCHAR(5000),
    updated_at  DATETIME(6),
    issue_id    BIGINT,
    writer_id   BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE image
(
    id         BIGINT NOT NULL,
    url        VARCHAR(2083),
    comment_id BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE issue
(
    issue_number BIGINT NOT NULL AUTO_INCREMENT,
    created_at   DATETIME(6),
    is_opened    BIT    NOT NULL,
    title        VARCHAR(255),
    updated_at   DATETIME(6),
    author_id    BIGINT,
    milestone_id BIGINT,
    PRIMARY KEY (issue_number)
) ENGINE = InnoDB;

CREATE TABLE issue_assignee
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    assignee_id BIGINT,
    issue_id    BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE issue_label
(
    id       BIGINT NOT NULL AUTO_INCREMENT,
    issue_id BIGINT,
    label_id BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE label
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    color       VARCHAR(7),
    description VARCHAR(255),
    title       VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE milestone
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(255),
    due_date    DATE,
    is_opened   BIT    NOT NULL,
    title       VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE user
(
    id            BIGINT NOT NULL AUTO_INCREMENT,
    email         VARCHAR(255),
    github_token  VARCHAR(255),
    nickname      VARCHAR(100),
    password      VARCHAR(255),
    profile_image VARCHAR(2083),
    user_id       VARCHAR(100),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE comment
    ADD CONSTRAINT comment_issue FOREIGN KEY (issue_id) REFERENCES issue (issue_number);
ALTER TABLE comment
    ADD CONSTRAINT comment_writer FOREIGN KEY (writer_id) REFERENCES user (id);
ALTER TABLE image
    ADD CONSTRAINT image_comment FOREIGN KEY (comment_id) REFERENCES comment (id);
ALTER TABLE issue
    ADD CONSTRAINT issue_author FOREIGN KEY (author_id) REFERENCES user (id);
ALTER TABLE issue
    ADD CONSTRAINT issue_milestone FOREIGN KEY (milestone_id) REFERENCES milestone (id);
ALTER TABLE issue_assignee
    ADD CONSTRAINT issue_assignee_user FOREIGN KEY (assignee_id) REFERENCES user (id);
ALTER TABLE issue_assignee
    ADD CONSTRAINT issue_assignee_issue FOREIGN KEY (issue_id) REFERENCES issue (issue_number);
ALTER TABLE issue_label
    ADD CONSTRAINT issue_label_issue FOREIGN KEY (issue_id) REFERENCES issue (issue_number);
ALTER TABLE issue_label
    ADD CONSTRAINT issue_label_label FOREIGN KEY (label_id) REFERENCES label (id);
