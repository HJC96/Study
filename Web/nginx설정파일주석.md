/etc/nginx/conf.d/default.conf

~~~linux
# server : '하나의 웹 사이트에 관련된 설정'을 관리하는 단위 ('server 블럭'이라고 부름)
server {
  # localhost:80으로 들어오는 요청을 이 server 블럭에서 처리하도록 설정
  # (server_name이 일치하는 server 블럭이 없는 경우 첫 번째 정의되어 있는 server 블럭을 기반으로 처리)
  # (아직은 정확히 몰라도 된다. 나중에 '멀티 도메인' 기능을 배우면 쉽게 이해할 수 있다.)
  listen 80;
  server_name localhost;
  
  # / 으로 시작하는 모든 경로를 처리 (ex. /index.html)
  location / {
    # /jscode.html로 요청이 들어오면 /usr/share/nginx/html/jscode.html 파일로 응답
    root /usr/share/nginx/html;
    
    # /로 요청이 들어오면 /usr/share/nginx/html/index.html로 응답
    # 만약 /usr/share/nginx/html/index.html이 없을 경우, /usr/share/nginx/html/index.htm으로 응답
    index index.html index.htm;
  }

    # Nginx에서 500, 502, 503, 504의 상태 코드가 발생했을 때 /50x.html로 redirect
    error_page 500 502 503 504 /50x.html;
    
    # /50x.html과 완전히 일치하는 경로를 처리
    location = /50x.html {
      # /50x.html로 요청이 들어오면 /usr/share/nginx/html/50x.html 파일로 응답
      root /usr/share/nginx/html;
  }
}
~~~


