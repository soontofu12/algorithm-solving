/*
 * [문제 정보] 헤비 유저가 소유한 장소
 * [사용 SQL] 서브쿼리, GROUP BY, HAVING, IN
 *
 * [풀이 핵심]
 * 1. HOST_ID별로 장소 개수를 세기 위해 GROUP BY 사용.
 * 2. COUNT(*)가 2 이상인 HOST_ID만 HAVING으로 추출.
 * 3. 서브쿼리 결과가 여러 HOST_ID일 수 있으므로 IN으로 원본 테이블에서 해당 장소들을 조회.
 */

SELECT *
FROM PLACES
WHERE HOST_ID IN (SELECT HOST_ID FROM PLACES
                    GROUP BY HOST_ID
                    HAVING COUNT(*) >= 2);