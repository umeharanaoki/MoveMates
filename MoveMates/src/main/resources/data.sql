/* rolesテーブル */
INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_GENERAL');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_ADMIN');

/* purposesテーブル */
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

/* bodypartsテーブル */
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

/* exercisesテーブル */
INSERT IGNORE INTO exercises (id, name, image_name, explanation, set_number, time_required, type)
VALUES
	(1, 'フロントランジ', '22652408.jpg', '1. 腰幅で、両足を前後に大きく開いて立つ。両手は腰にあてる。\r\n2. 上体をまっすぐキープしたまま、息を吸いながらゆっくり腰を下ろしていく。前足の膝の真下にかかとが来るよう、膝を90度まで曲げる。後ろ足は指の付け根で床を捉え、後ろ足の膝は床につかないようにする。\r\n3. 上体をキープしたまま、息を吐きながらゆっくり両ひざを伸ばしていく。\r\n4. 1～3を15～20回繰り返す　反対も同様に行う', '15～20回', 300, 'トレーニング'),
	(2, '肩甲骨をはがすストレッチ', '22652408.jpg', '1. 両ひじを曲げて腕をできるだけ上げ、手は軽く握って鎖骨のあたりに置く。\r\n2. 両ひじをゆっくりと後ろに引く。5秒かけて息を吐きながら、ひじの位置はできるだけ下げないようにする。肋骨から肩甲骨を「はがす」意識でぎゅっと強めに寄せる。\r\n3. 肩甲骨を寄せたままひじを下げ、脱力する。', '5回', 30, 'ストレッチ'),
	(3, '前ももストレッチ', '22652408.jpg', '1. 壁など支えがある場所で立ち、片方の足のかかとをお尻に近づける。この時お腹に力を入れて腰は反らないよう注意する。\r\n2. 太ももの前側が伸ばされるのを意識しながら、ひざを後ろに引く。\r\n3. 15〜30秒キープする。', '左右1回ずつ', 60, 'ストレッチ'),
	(4, 'お尻歩き', '22652408.jpg', '1. 両足を前に揃えて座る。この時、骨盤を立てる。\r\n2. 右のお尻を上げながら、左のひじを身体の内側に入れて上半身をねじる。\r\n3. 左のお尻を上げながら、右のひじを身体の内側に入れて上半身をねじる。\r\n4. 2と3を交互に行い、少しずつ前に20歩進む。脚ではなく、お尻を使って歩くイメージ。\r\n5. 同様に、少しずつ後ろに20歩下がる。', '2回', 60, 'トレーニング'),
	(5, 'キャットアンドカウ', '22652408.jpg', '１.両手、両膝を床に付けて、四つん這いになる。\r\n2. 両手を肩の真下に置く。股関節の真下に両膝を置き、両足を肩幅程度に開く。\r\n3. 息を一度吸って、吐くのと同時にゆっくり背中を丸める。\r\n4. 息を吸うのと同時にゆっくり背中を反らす。\r\n5. 3と4を繰り返す。', '10回', 30, 'ストレッチ'),
	(6, '腹部上部筋トレーニング', '22652408.jpg', '1. 平らな場所に寝転がり、膝を立てる。\r\n2. 手のひら・腕を真上に挙げて、おでこも同時に真上に持ち上げる。この時、斜め上にならないように気を付けて、真上になるように意識する。', '15回', 30, 'トレーニング'),
	(7, '腹部下部筋トレーニング', '22652408.jpg', '1. 平らな場所に寝転び、腰の下に手を入れる。\r\n2. 膝を直角に曲げた状態で浮かせる。\r\n3. 脚を地面につかないぎりぎりのところでキープする。\r\n4. 2と3の動作をゆっくりと繰り返す。', '15回', 30, 'トレーニング'),
	(8, '肩甲骨をほぐすストレッチ', '22652408.jpg', '1. 座り姿勢になり指先を肩につける。\r\n2. 両肘を胸の前で合わせ背中を開く。\r\n3. 息を吸いながら、ゆっくりと両肘を頭の横まで引き上げる。\r\n4. ゆっくりと息を吐きながら、両肘を背中で合わせるようなイメージで、胸を大きく開き肘を下ろす。\r\n5. 反対回しも同様に。', '前後10回ずつ', 30, 'ストレッチ'),
	(9, '背中・腰のストレッチ', '22652408.jpg', '1. 右腕を伸ばし、左腕で抱えるように胸へ引き寄せる。/r/n2. 顔は正面を向いたまま、腰から体をひねり、20秒程度静止。\r\n3. 体を正面に戻し、腕を下ろしたら、反対側も同様に行う。', '1回', 60, 'ストレッチ'),
	(10, '大胸筋エクササイズ', '22652408.jpg', '1. 椅子に座って軽く脚を開き、両手を太もも外側に添える。\r\n2. 両手は両側から脚を閉じる方向に、両脚は脚を開く方向に力を入れて10秒キープ。', '3回', 30, 'ストレッチ'),
	(11, 'プランク', '22652408.jpg', '1. 両肘を床につけ、うつ伏せになる。\r\n2. 腰を浮かせ、背筋をまっすぐに伸ばす。\r\n3. 体が一直線になるように姿勢をキープする。', '1回', 30, 'トレーニング');

/* exercise_bodypartsテーブル */
INSERT IGNORE INTO exercise_bodyparts (exercise_id, bodypart_id)
VALUES
	(1, 11),
	(1, 12),
	(1, 14),
	(2, 2),
	(2, 8),
	(3, 12),
	(4, 10),
	(4, 11),
	(5, 9),
	(6, 7),
	(7, 7),
	(8, 2),
	(8, 8),
	(9, 9),
	(10, 5),
	(11, 3);

/* exercise_purposesテーブル */
INSERT IGNORE INTO exercise_purposes (exercise_id, purpose_id) 
VALUES 
	(1, 9),
	(1, 10),
	(2, 1),
	(2, 16),
	(2, 21),
	(3, 10),
	(3, 13),
	(3, 22),
	(4, 13),
	(5, 12),
	(5, 22),
	(6, 11),
	(6, 22),
	(7, 11),
	(7, 22),
	(8, 1),
	(8, 17),
	(9, 1),
	(10, 4),
	(11, 11);

/* usersテーブル */
INSERT IGNORE INTO users (id, name, image_name, email, birthday, gender, password, role_id, enabled)
VALUES
	('goleiro66', 'うめしゃん', null, 'n6773938@gmail.com', '1995-06-06', '男性', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, 1),
	('aiueo', 'あいうえお', null, 'gupoke@owleyes.ch', '1999-09-09', '男性', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, 1),
	('user1', '田中たなか', 'image1.jpg', 'user1@example.com', '1990-01-01', '男性', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, 1),
	('user2', '山田やまだ', 'image2.jpg', 'user2@example.com', '1991-02-02', '女性', 'password2', 2, 1),
	('user3', '鈴木すずき', 'image3.jpg', 'user3@example.com', '1992-03-03', 'その他', 'password3', 1, 0),
	('user4', '佐藤さとう', 'image4.jpg', 'user4@example.com', '1993-04-04', '無回答', 'password4', 2, 1),
	('user5', '田中たなか', 'image5.jpg', 'user5@example.com', '1994-05-05', '男性', 'password5', 1, 0),
	('user6', '山田やまだ', 'image6.jpg', 'user6@example.com', '1995-06-06', '女性', 'password6', 2, 1),
	('user7', '鈴木すずき', 'image7.jpg', 'user7@example.com', '1996-07-07', 'その他', 'password7', 1, 0),
	('user8', '佐藤さとう', 'image8.jpg', 'user8@example.com', '1997-08-08', '無回答', 'password8', 2, 1),
	('user9', '田中たなか', 'image9.jpg', 'user9@example.com', '1998-09-09', '男性', 'password9', 1, 0),
	('user10', '山田やまだ', 'image10.jpg', 'user10@example.com', '1999-10-10', '女性', 'password10', 2, 1);
	


/* mymenusテーブル */
INSERT IGNORE INTO mymenus (id, name, user_id)
VALUES
	(1, 'メニュー1', 'goleiro66'),
	(2, 'メニュー2', 'goleiro66'),
	(3, 'メニュー3', 'goleiro66'),
	(4, 'メニュー1', 'aiueo'),
	(5, 'メニュー2', 'aiueo'),
	(6, 'メニュー3', 'aiueo'),
	(7, 'メニュー1', 'user1'),
	(8, 'メニュー2', 'user1'),
	(9, 'メニュー3', 'user1'),
	(10, 'メニュー1', 'user2'),
	(11, 'メニュー2', 'user2'),
	(12, 'メニュー3', 'user2'),
	(13, 'メニュー1', 'user3'),
	(14, 'メニュー2', 'user3'),
	(15, 'メニュー3', 'user3'),
	(16, 'メニュー1', 'user4'),
	(17, 'メニュー2', 'user4'),
	(18, 'メニュー3', 'user4'),
	(19, 'メニュー1', 'user5'),
	(20, 'メニュー2', 'user5'),
	(21, 'メニュー3', 'user5'),
	(22, 'メニュー1', 'user6'),
	(23, 'メニュー2', 'user6'),
	(24, 'メニュー3', 'user6'),
	(25, 'メニュー1', 'user7'),
	(26, 'メニュー2', 'user7'),
	(27, 'メニュー3', 'user7'),
	(28, 'メニュー1', 'user8'),
	(29, 'メニュー2', 'user8'),
	(30, 'メニュー3', 'user8'),
	(31, 'メニュー1', 'user9'),
	(32, 'メニュー2', 'user9'),
	(33, 'メニュー3', 'user9'),
	(34, 'メニュー1', 'user10'),
	(35, 'メニュー2', 'user10'),
	(36, 'メニュー3', 'user10');

/* mymenu_exercisesテーブル */
INSERT IGNORE INTO mymenu_exercises (id, mymenu_id, exercise_id, exercise_order) 
VALUES 
	(1, 4, 1, 1),
	(2, 4, 2, 2),
	(3, 4, 4, 3);

/* encouraging_messagesテーブル */
INSERT IGNORE INTO encouraging_messages (id, consecutive_weeks, message) 
VALUES
	(1, 0, '新しい自分に向けて頑張りましょう'),
	(2, 1, '最初の一歩を踏み出しましたね'),
	(3, 2, '継続は難しいですが、努力は実を結びます'),
	(4, 3, '最初は努力が見えにくいです。少しずつでも継続しましょう'),
	(5, 4, '変化が見えてきましたか？よく頑張っています'),
	(6, 9, '習慣化が進み、運動が日常の一部となってきたことでしょう。この習慣を大切にして、健康的な生活を維持しましょう。');
	
/* exercises_logsテーブル */
INSERT IGNORE INTO exercise_logs (id, user_id, mymenu_id, exercise_day, exercise_duration)
VALUES
	(1, 'user1', 7, '2024-02-21 00:00:00', 1800),
	(2, 'user1', 7, '2024-03-01 00:00:00', 1800),
	(3, 'user1', 7, '2024-03-04 00:00:00', 1800),
	(4, 'user1', 7, '2024-03-09 00:00:00', 1800),
	(5, 'user1', 7, '2024-03-15 00:00:00', 1800),
	(6, 'user1', 7, '2024-03-19 00:00:00', 1800),
	(7, 'user1', 7, '2024-03-23 00:00:00', 1800),
	(8, 'user1', 7, '2024-03-29 00:00:00', 1800),
	(9, 'user1', 7, '2024-04-02 00:00:00', 1800),
	(10, 'user1', 7, '2024-04-08 00:00:00', 1800);

/* favoritesテーブル */
INSERT IGNORE INTO favorites (id, user_id, exercise_id, status)
VALUES
	(1, 'user1', 1, 1),
	(2, 'user1', 2, 1),
	(3, 'user1', 4, 1),
	(4, 'user1', 6, 0),
	(5, 'user1', 7, 1),
	(6, 'user1', 10, 1);
	

	