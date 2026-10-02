/*
 * [문제 정보] 조건에 맞는 사용자와 총 거래금액 조회하기
 * [사용 SQL] JOIN, WHERE, GROUP BY, HAVING, SUM, ORDER BY
 *
 * [풀이 핵심]
 * 1. 게시글의 작성자 ID(WRITER_ID)와 사용자 ID(USER_ID)를 기준으로 두 테이블을 JOIN.
 * 2. 거래 상태가 DONE인 게시글만 WHERE로 필터링.
 * 3. 사용자별 총 거래금액을 구하기 위해 USER_ID, NICKNAME으로 GROUP BY.
 * 4. SUM(PRICE)가 700000 이상인 사용자만 HAVING으로 조회.
 * 5. 총 거래금액(TOTAL_SALES)을 기준으로 오름차순 정렬.
 */
 
SELECT U.USER_ID, U.NICKNAME, SUM(B.PRICE) AS TOTAL_SALES
FROM USED_GOODS_BOARD B JOIN USED_GOODS_USER U ON B.WRITER_ID = U.USER_ID
WHERE STATUS = "DONE"
GROUP BY U.USER_ID, U.NICKNAME
HAVING SUM(B.PRICE) >= 700000
ORDER BY TOTAL_SALES;