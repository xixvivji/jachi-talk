# Public API Plan

## MVP APIs

### Real Estate Transaction Data

Use for neighborhood-level rent and transaction context.

- 국토교통부 아파트 실거래가
- 국토교통부 연립다세대 실거래가
- 국토교통부 오피스텔 실거래가

### Region Codes

Use for mapping user-facing region names to legal dong codes.

- 법정동 코드
- 행정구역 코드
- 도로명주소 API if address search is needed later

### Store and Facility Data

Use for living convenience metrics.

- 소상공인시장진흥공단 상가업소정보
- 서울 열린데이터광장 local facility datasets if starting from Seoul

## Later APIs

- 서울시 지하철역/버스정류소 data
- 서울시 생활인구 data
- 한국환경공단 대기오염정보
- 기상청 단기예보

## Collection Strategy

- Cache public API responses by region and date.
- Store raw snapshots in PostgreSQL first.
- Move large raw payloads to S3 when volume grows.
- Track latency, status code, timeout, and failure reason for each external API call.

## Important Constraints

- Public data can be incomplete or delayed.
- Legal dong and administrative dong mappings must be handled carefully.
- The service should show data source and collection date.
- Avoid language that sounds like investment advice.
