INSERT INTO user(id, email, github_token, nickname, password, profile_image, user_id)
VALUES (1, 'test1@idion.dev', '', 'test1', 'test123', '', 'test1'),
       (2, 'test2@idion.dev', '', 'test2', 'test213', '', 'test2');

INSERT INTO label(id, color, description, title)
VALUES (1, '#FFFFFF', 'Backend Issue', 'BE'),
       (2, '#FFFFFF', 'Frontend Issue', 'FE');

INSERT INTO milestone(id, description, due_date, is_opened, title)
VALUES (1, '1주차 진행과제', '2020-06-26', true, 'Phase1'),
       (2, '2주차 진행과제', '2020-07-03', true, 'Phase2'),
       (3, '3주차 진행과제', '2020-07-29', true, 'Phase3');

INSERT INTO issue(issue_number, created_at, is_opened, title, updated_at, author_id, milestone_id)
VALUES (1, '2020-06-21 22:56:31', true, '배고파서 밥을 먹어야합니다.', '2020-06-21 22:56:31', 1, 1);

INSERT INTO issue_label(id, issue_id, label_id)
VALUES (1, 1, 1);

INSERT INTO issue_assignee(id, assignee_id, issue_id)
VALUES (1, 1, 1),
       (2, 2, 1);

INSERT INTO comment(id, created_at, description, updated_at, issue_id, writer_id)
VALUES (1, '2020-06-21 22:56:31', '아 진짜 배고프다... 어떡하지...', '2020-06-21 22:56:31', 1, 1);
