INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT IGNORE INTO bodyparts (id, name) VALUES (1, '首');
INSERT IGNORE INTO bodyparts (id, name) VALUES (2, '肩');
INSERT IGNORE INTO bodyparts (id, name) VALUES (3, '二の腕');
INSERT IGNORE INTO bodyparts (id, name) VALUES (4, '前腕');
INSERT IGNORE INTO bodyparts (id, name) VALUES (5, '胸');
INSERT IGNORE INTO bodyparts (id, name) VALUES (6, '手・手首');
INSERT IGNORE INTO bodyparts (id, name) VALUES (7, 'お腹');
INSERT IGNORE INTO bodyparts (id, name) VALUES (8, '肩甲骨');
INSERT IGNORE INTO bodyparts (id, name) VALUES (9, '背中');
INSERT IGNORE INTO bodyparts (id, name) VALUES (10, '股関節');
INSERT IGNORE INTO bodyparts (id, name) VALUES (11, 'お尻');
INSERT IGNORE INTO bodyparts (id, name) VALUES (12, '前もも');
INSERT IGNORE INTO bodyparts (id, name) VALUES (13, 'すね');
INSERT IGNORE INTO bodyparts (id, name) VALUES (14, 'もも裏');
INSERT IGNORE INTO bodyparts (id, name) VALUES (15, 'ふくらはぎ');
INSERT IGNORE INTO bodyparts (id, name) VALUES (16, '足・足首');

INSERT IGNORE INTO purposes (id, name) VALUES (1, '肩こり解消');
INSERT IGNORE INTO purposes (id, name) VALUES (2, 'ストレートネック改善');
INSERT IGNORE INTO purposes (id, name) VALUES (3, '開脚');
INSERT IGNORE INTO purposes (id, name) VALUES (4, 'バストアップ');
INSERT IGNORE INTO purposes (id, name) VALUES (5, '二の腕痩せ');
INSERT IGNORE INTO purposes (id, name) VALUES (6, '背中痩せ');
INSERT IGNORE INTO purposes (id, name) VALUES (7, '小顔');
INSERT IGNORE INTO purposes (id, name) VALUES (8, 'くびれ');
INSERT IGNORE INTO purposes (id, name) VALUES (9, 'お尻引き締め');
INSERT IGNORE INTO purposes (id, name) VALUES (10, '脚瘦せ');
INSERT IGNORE INTO purposes (id, name) VALUES (11, '体幹強化');
INSERT IGNORE INTO purposes (id, name) VALUES (12, '腰痛改善');
INSERT IGNORE INTO purposes (id, name) VALUES (13, '骨盤矯正');
INSERT IGNORE INTO purposes (id, name) VALUES (14, '産後ケア');
INSERT IGNORE INTO purposes (id, name) VALUES (15, 'リンパマッサージ');
INSERT IGNORE INTO purposes (id, name) VALUES (16, '眼精疲労解消');
INSERT IGNORE INTO purposes (id, name) VALUES (17, '猫背改善');
INSERT IGNORE INTO purposes (id, name) VALUES (18, '前屈');
INSERT IGNORE INTO purposes (id, name) VALUES (19, 'むくみ解消');
INSERT IGNORE INTO purposes (id, name) VALUES (20, '冷え性改善');
INSERT IGNORE INTO purposes (id, name) VALUES (21, '肩甲骨はがし');
INSERT IGNORE INTO purposes (id, name) VALUES (22, '反り腰改善');

INSERT IGNORE INTO exercises (id, name, image_name, explanation, set_number, time_required, type) VALUES (1, 'フロントランジ', '22652408.jpg', '&lt;p&gt;1. 腰幅で、両足を前後に大きく開いて立つ。両手は腰にあてる。&lt;/p&gt;&lt;p&gt;2. 上体をまっすぐキープしたまま、息を吸いながらゆっくり腰を下ろしていく。前足の膝の真下にかかとが来るよう、膝を90度まで曲げる。後ろ足は指の付け根で床を捉え、後ろ足の膝は床につかないようにする。&lt;/p&gt;&lt;p&gt;3．上体をキープしたまま、息を吐きながらゆっくり両ひざを伸ばしていく。&lt;/p&gt;&lt;p&gt;4．1～3を15～20回繰り返す　反対も同様に行う', '15～20回', 300, 'WORKOUT');

INSERT IGNORE INTO exercise_bodyparts (exercise_id, bodypart_id) VALUES (1, 11);
INSERT IGNORE INTO exercise_bodyparts (exercise_id, bodypart_id) VALUES (1, 12);
INSERT IGNORE INTO exercise_bodyparts (exercise_id, bodypart_id) VALUES (1, 14);

INSERT IGNORE INTO exercise_purposes (exercise_id, purpose_id) 
VALUES 
	(1, 9),
	(1, 10);

INSERT IGNORE INTO users (id, name, image_name, email, birthday, gender, password, role_id, enabled) VALUES ('goleiro66', 'うめしゃん', null, 'n6773938@gmail.com', '1995-06-06', '男性', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, 1);

INSERT IGNORE INTO mymenu_exercises (id, mymenu_id, exercise_id, exercise_order) 
VALUES 
	(1, 4, 1, 1),
	(2, 4, 2, 2),
	(3, 4, 4, 3);
	
INSERT IGNORE INTO encouraging_messages (id, consecutive_weeks, message) 
VALUES
	(1, 0, '新しい自分に向けて頑張りましょう'),
	(2, 1, '最初の一歩を踏み出しましたね'),
	(3, 2, '継続は難しいですが、努力は実を結びます'),
	(4, 3, '最初は努力が見えにくいです。少しずつでも継続しましょう'),
	(5, 4, '変化が見えてきましたか？よく頑張っています'),
	(6, 9, '習慣化が進み、運動が日常の一部となってきたことでしょう。この習慣を大切にして、健康的な生活を維持しましょう。');
	