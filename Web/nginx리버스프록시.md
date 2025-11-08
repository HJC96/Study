Q) 포워드 프록시란
A) 회사에서 chatgpt로 요청 보낼때.

Q) 리버스 프록시란
A) 유저가 우리 서버로 오는 접근.


~~~linux
server {
    # api.jscode.p-e.kr:80로 들어온 요청을 이 server 블록이 처리
    listen 80;
    server_name api.jscode.p-e.kr;

    # / 으로 시작하는 모든 경로를 처리
    location / {
      # 들어온 요청을 전부 http://localhost:8080(Spring Boot 서버)로 전달
      proxy_pass http://localhost:8080;
    }
}
~~~



