-- 04_LIMITOFFSET.sql

-- SELECT 명령의 결과 레코드가 갯수가 많을 때, 일부만 지목해서 열람할 수 있다.
-- 오라클에서는 rownum 이라는 키워드를 이용합니다.
-- SELECT ROWNUM, 테이블명.* FROM 테이블명;
-- SELECT * FROM 테이블명 WHERE ROWNUM>=1 AND ROWNUM<=10;


-- LIMIT
-- SELECT로 데이터 조회할 때 조회할 레코드의 갯수를 조절, 제한 할 수 있다
SELECT * FROM memberlist ORDER BY membernum DESC LIMIT 5;
-- 회원 정보 조회하여 5개의 레코드만 리턴

-- OFFSET
-- SELECT로 데이터 조회할 때, 맨위부터 offset에 지정한 번째까지는 뛰어 넘고 그 다음부터 리턴
SELECT * FROM memberlist ORDER BY membernum DESC;
SELECT * FROM memberlist ORDER BY membernum DESC LIMIT 5 OFFSET 3;
-- 3번째 데이터까지 뛰어넘고 그 다음 데이터부터 5개의 레코드 리턴 - 개수가 모자르면 있는곳까지 리턴














