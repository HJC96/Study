#  자바에서의 입출력
스트림

ㄴ바이트기반 스트림

ㄴ보조 스트림

ㄴ문자기반 스트림

## 스트림
- 개념
  - 자바 입출력을 위한 연결 통로
- 종류
  - input stream
  - output stream
- 구조
  - FIFO 구조

## 바이트기반 스트림 - InputStream, OutputStream
**입출력스트림**
- 입력스트림
  - File InputStream
    - 파일 대상 입출력 
  - ByteArray InputStream
    - 메모리(byte배열) 대상 입출력
  - Piped InputStream
    - 프로세스(프로세스간의 통신) 
  - Audio InputStream
    - 오디오장치 

- 출력스트림
  - File OutputStream
    - 파일 대상 입출력 
  - ByteArray OutputStream
    - 메모리(byte배열) 대상 입출력
  - Piped OutputStream
    - 프로세스(프로세스간의 통신) 
  - Audio OutputStream
    - 오디오장치 
  
## 보조 스트림
~~~java
// 먼저 기반스트림을 생성한다.
FileInputStream fis = new FileInputStream("test.txt");
// 기반스트림을 이용해서 보조스트림을 생성한다.
BufferedInputStream bis = new BufferedInputStream(fis);
bis.read(); // 보조스트림인 BufferedInputStream으로부터 데이터를 읽는다.
~~~
- 추가설명
  - 코드 상으로는 보조스트림이 입력기능을 수행하는 것처럼 보이지만, 실제 입력기능은 FileInputStream이 수행한다.
  - 보조스트림은 버퍼만 제공한다.


## 문자기반 스트림
java에서는 한 문자를 의미하는 char형이 1 byte가 아니라 2byte이다.

<img width="1528" alt="image" src="https://user-images.githubusercontent.com/87226129/236828142-9db1dcd8-e4d1-44d5-98ce-65c7b5a246f3.png">

<img width="1448" alt="image" src="https://user-images.githubusercontent.com/87226129/236828201-2fa33176-0d22-4645-8d7c-4a323833aabd.png">

<img width="1499" alt="image" src="https://user-images.githubusercontent.com/87226129/236828285-92968e7c-c1ec-485a-ad83-724398c7cc7e.png">

<img width="1490" alt="image" src="https://user-images.githubusercontent.com/87226129/236828344-e50a6408-8ea1-485b-bd81-1cbb232ef9c3.png">



출처: 자바의 정석
