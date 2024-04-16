-- 11_Function.sql

-- [1] 샘플 테이블인 dual 테이블
SELECT * FROM tab;
SELECT * FROM dual;

-- [2] 임시 데이터 출력
SELECT 1234 * 1234 FROM dual;

-- *** 문자열 처리 관련 함수 ***
-- lower(), upper(), initcap()
SELECT lower('Hong Gil Dong') AS 소문자 FROM dual;
SELECT upper('Hong Gil Dong') AS 대문자 FROM dual;
SELECT initcap('hong gil dong') AS "첫글자만 대문자" FROM dual;

SELECT lower(ename) AS 이름 FROM emp;
SELECT empno, job, upper(ename) AS 이름 FROM emp;

-- 이어 붙이기 concat() : 문자열 연결
SELECT concat('하이미디어IT', '아카데미'), '하이미디어IT'||'아카데미' FROM dual;
SELECT concat(empno, ename) FROM emp;

-- substr() : 문자열 추출(데이터, 인덱스(1), 카운트)
SELECT substr('홍길동 만세', 2, 4) FROM dual;		--길동 만
-- substr 의 경우 자바의 substring 처럼 시작번째부터 끝번째 +1이 아니라
-- 시작번째부터 글자수를 나타낸다. 위 예의 경우 2번째 글자부터 네글자 표시


-- *** 날짜 처리 관련 함수 ***
-- sysdate : 날짜
SELECT sysdate FROM dual;  -- 오늘 날짜와 현재 시간
-- INSERT 명령 그리고 그 외에 오늘 날짜가 필요한 곳에 폭넓게 사용된다
INSERT INTO rentlist(rentdate, numseq) VALUES(sysdate, rent_seq.nextVal);

-- months_between(): 개월 수 차 구하기
SELECT floor(months_between('2021-12-31', '2024-04-16')) AS "개월수" FROM dual;

-- add_months() : 개월 수 더하기
SELECT add_months(sysdate, 20) FROM dual;

-- next_day() : 다가올 요일에 해당하는 날짜 - 오늘 날짜(sysdate)에서 가장 가까운 일요일
SELECT next_day(sysdate, '일요일') FROM dual;

-- last_day() : 해당 달의 마지막 일 수
SELECT last_day(sysdate) FROM dual;
SELECT last_day('2020-12-15') FROM dual;





-- to_char() : 문자(String)로 변환 Date -> varchar2
SELECT to_char(sysdate, 'yyyy-mm-dd') AS "Date->String" FROM dual;
-- select 와 from 사이에서 많이 사용되는 함수

-- to_date() : 날짜형(Date)으로 변환 varchar2 -> Date
SELECT to_date('2019/12/31', 'yyyy/mm/dd') AS "String->Date" FROM dual;
-- insert 명령에서 values() 괄호안에 많이 사용되는 함수

-- 숫자 -> 문자 세자리마다 컴마를 넣어서 변환
SELECT TO_CHAR(123456789, '999,999,999') FROM DUAL;		-- 123,456,789

-- 여섯자리 문자를 숫자로 변환
SELECT TO_NUMBER('123456') FROM DUAL;

-- YYYY -> 연도 표시
SELECT TO_CHAR(SYSDATE, 'YY"년"') FROM DUAL;	--> 23년
SELECT TO_CHAR(SYSDATE, 'Y"년"') FROM DUAL;	--> 3년

-- month, mon -> 월표시
SELECT TO_CHAR(SYSDATE, 'MONTH') FROM DUAL;	--> 4월
SELECT TO_CHAR(SYSDATE, 'MON') FROM DUAL;	--> 4월
SELECT TO_CHAR(SYSDATE, 'MM') FROM DUAL;	--> 04
SELECT TO_CHAR(SYSDATE, 'MM"월"') FROM DUAL;	--> 04월

-- DD 일자를 01~31 로 표시
SELECT TO_CHAR(SYSDATE, 'DD') FROM DUAL;	--> 27
-- DDD 일자를 001~365 형태로 표시
SELECT TO_CHAR(SYSDATE, 'DDD') FROM DUAL;	--> 300
-- DL : 오늘 날짜를 요일까지 표시
SELECT TO_CHAR(SYSDATE, 'DL') FROM DUAL; --> 2023년 10월 27일 금요일

-- round() : 반올림(음수 : 소수점 이상 자리)
SELECT round(12.3456, 3) FROM dual;
-- 12.3456 : 반올림하려는 대상 숫자	3 : 반올림하여 표시하고자하는 마지막 자리수


