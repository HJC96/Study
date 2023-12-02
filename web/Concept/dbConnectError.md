# Mariadb 커넥션 오류

## 문제 상황

- DB 내에서 새로운 ID와 DB를 만들어 프로젝트를 진행하려 했으나 되지 않음.

## 해결 방법

- Mariadb 재설치 후, 백그라운드 실행

```yaml
brew services stop mariadb 
brew uninstall mariadb
brew install mariadb
brew services start mariadb
```

```yaml
# 에러발생 ERROR 1698 (28000): Access denied for user 'root'@'localhost'
mysql -u root 

# OK
sudo mysql -u root 
```

```yaml
# 계정생성
ALTER USER 'root'@'localhost' IDENTIFIED BY '[NEW_PASSWORD]';

# 권한부여
ALTER USER 'root'@'localhost' IDENTIFIED BY 'my_new_password';

# 즉시 실행
FLUSH PRIVILEGES;
```

→ 인텔리제이 DB navigator에는 연결이 안되었지만, 실제로는 연결이 되는 것을 확인.
