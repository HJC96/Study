~~~linux
# limit_req_zone : 요청 수를 제한하기 위한 메모리 공간(zone)과 요청 속도(rate)를 정의
# $binary_remote_addr : 요청 수를 제한하는 기준을 클라이언트의 IP로 설정
# zone=mylimit:10m : 메모리 공간(zone)의 이름을 mylimit이라고 지정, 메모리 공간의 크기를 10MB 제한 (약 16만개의 IP 주소를 관리할 수 있음)
# rate=3r/s : 1초에 최대 3개의 요청만 허용

limit_req_zone $binary_remote_addr zone=mylimit:10m rate=3r/s;

server {
    # limit_req_zone에서 정의한 mylimit이라는건을 이 server 블럭에 적용
    limit_req zone=mylimit;
    # 요청이 제한됐을 때 429(Too Many Requests) 상태 코드를 반환
    limit_req_status 429;
    server_name api.jscode.p-e.kr;

    location / {
        proxy_pass http://localhost:8080;
    }

    listen 443 ssl; # managed by Certbot
    ssl_certificate /etc/letsencrypt/live/api.jscode.p-e.kr/fullchain.pem # managed by Certbot
    ssl_certificate_key /etc/letsencrypt/live/api.jscode.p-e.kr/privkey.pm; # managed by Certbot
    include /etc/letsencrypt/options-ssl-nginx.conf; # managed by Certbot
    ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem; # managed by Certbot
}

server {
    if ($host = api.jscode.p-e.kr) {
      return 301 https://$host$request_uri;
    } # managed by Certbot

    listen 80;
    server_name api.jscode.p-e.kr;
    return 404; # managed by Certbot
}
~~~


