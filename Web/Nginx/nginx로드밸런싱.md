~~~linux
limit_req_zone $binary_remote_addr zone=mylimit:10m rate=3r/s;
# 로드 밸런싱 대상 서버들을 upstream이라는 그룹으로 묶음
# upstream 그룹의 이름은 backend라고 지정
upstream backend {
    server localhost:8080;
    server localhost:8081;
}

server {
    limit_req zone=mylimit;
    limit_req_status 429;
    server_name api.jscode.p-e.kr;

    # upstream 그룹에서 지정한 서버들로 요청이 분산됨
    location / {
        proxy_pass http://backend;
    }

    listen 443 ssl; # managed by Certbot
    ssl_certificate /etc/letsencrypt/live/api.jscode.p-e.kr/fullchain.pem; # managed by Certbot
    ssl_certificate_key /etc/letsencrypt/live/api.jscode.p-e.kr/privkey.pem; # managed by Certbot
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
