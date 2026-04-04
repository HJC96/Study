/etc/nginx/conf.d/default.conf
~~~linux
server {
    listen 80;
    server_name jscode.p-e.kr;

    location / {
      root /usr/share/nginx/nginx-frontend-react/dist;
      index index.html;
    }
}

server {
    listen 80;
    server_name admin.jscode.p-e.kr;

    location / {
      root /usr/share/nginx/nginx-frontend-next/out;
      index index.html;
  }
}
~~~


~~~linux
# Nginx 설정 파일 중 문법 에러가 있는 지 체크
$ sudo nginx -t

# Nginx의 설정 파일이 바뀐 경우 아래 명령어를 입력해줘야 설정 파일이 반영된
$ sudo nginx -s reload
~~~
