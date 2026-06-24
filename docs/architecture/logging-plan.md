# Logging Plan

## Application Logs

Backend logs should include:

- request_id
- user_id when available
- method
- path
- status_code
- latency_ms
- error_code
- exception_name

Avoid logging:

- access tokens
- refresh tokens
- OAuth provider secrets
- exact private addresses
- phone numbers
- contract documents
- identity documents

## Event Logs

Initial event names:

- user_signed_in
- room_post_created
- room_post_viewed
- room_post_filtered
- handover_inquiry_clicked
- community_post_created
- comment_created
- report_submitted
- region_viewed
- public_api_called
- public_api_failed
- ai_risk_check_requested

## External API Logs

Track:

- provider
- endpoint
- region_code
- status_code
- latency_ms
- success
- error_message
- requested_at

## Admin Metrics

Show:

- daily active users
- new room handover posts
- new community posts
- report count
- top viewed regions
- external API failure rate
- average external API latency
- AI request count and estimated cost
