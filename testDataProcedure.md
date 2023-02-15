# Create_testData

테스트 데이터 프로시저 만들기

```mariadb
DELIMITER $$

CREATE PROCEDURE testDataInsert()
BEGIN
	DECLARE | INT DEFAULT 1;
	
	WHILE | <= 120 DO
		INSERT INTO board(title, content)
		VALUES(concat('제목', i), concat('내용' i));
		SET i = i +1;
	END WHILE;
END$$
DELIMITER $$
```

