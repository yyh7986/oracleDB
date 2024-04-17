-- 12_GroupFunction.sql

-- 테이블 내의 하나의 필드값들 전체로 하는 함수

-- 합계(SUM)
SELECT SUM(inprice) AS 입고가격합계 FROM booklist;
SELECT SUM(rentprice) AS 대여가격합계 FROM booklist WHERE inprice>=18000;

-- 갯수(COUNT)
SELECT COUNT(*) AS 회원수 FROM memberlist;
SELECT COUNT(*) AS 회원수 FROM memberlist WHERE bpoint>=100;

-- 평균(AVG)
SELECT ROUND(AVG(inprice), 0) AS 입고가격평균 FROM booklist;
-- TO_CHAR : 문자로 변환된 것이므로 숫자로서의 기능이 상실되어 다른 숫자와 연산이 불가능하다
SELECT TO_CHAR(AVG(inprice), '999,999,999') AS 입고가격평균 FROM booklist;

-- MAX : 최대값
-- SEQUENCE에 의해 방금 추가된 자동 증가번호를 조회할 때 많이 사용한다
SELECT MAX(inprice) FROM booklist;

-- MIN : 최소값
SELECT MIN(inprice) FROM booklist;


-- GROUP BY
-- 그룹함수의 결과들을 다른 필드의 그룹으로 재구성
SELECT COUNT(*) AS 총대여건수 FROM rentlist;

-- 도서별 대여건수
SELECT bnum AS "도서번호", COUNT(*) AS "도서별 대여건수", SUM(discount) AS "할인금액합계"
FROM rentlist GROUP BY bnum;

-- RENTLIST 테이블에서 대여일자(RENTDATE)별 대여건수와 할인금액 평균
SELECT rentdate AS 대여일자, COUNT(*) AS 대여건수
FROM rentlist GROUP BY rentdate ORDER BY rentdate DESC;


-- HAVING
-- 그룹핑된 내용들에 조건을 붙일때
SELECT rentdate AS "대여일자", COUNT(*) AS "대여건수", AVG(DISCOUNT) AS 할인금액평균
FROM RENTLIST
GROUP BY rentdate
HAVING AVG(discount)>=150
ORDER BY rentdate DESC;
