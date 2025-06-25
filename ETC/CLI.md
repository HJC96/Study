# 환경 설정
### 이름 지정하기
~~~git
git config --global user.name "Name"
~~~
### 이메일 지정하기
~~~git
git config --global user.email "Email Address"
~~~
### UI 컬러 설정하기
~~~git
git config --global color.ui auto
~~~
# 스테이징 커밋
### 현재 저장소 상태 보기
~~~git
git status
~~~
### 작업 파일 스테이지에 올리기
~~~git
git add [파일명]
~~~
### 스테이지에 올라간 작업 내용을 스테이지에서 내리기
~~~git
git reset [파일명]
~~~
### 변경 내용을 완전히 삭제하기
~~~git
git reset --hard [파일명]
~~~
### 스테이지에 없는 변경 사항 보기
~~~git
git diff
~~~
### 스테이지에 있지만, 커밋되지 않은 변경 사항 보기
~~~git
git diff --staged
~~~
### 스테이지에 올라간 내용 커밋하기
~~~git
git commit -m "커밋 메시지"
~~~
# 저장소 초기화
### 현재 디렉토리를 Git 저장소로 초기화하기
~~~git
git init
~~~
### 원격 저장소 복사해 오기
~~~git
git clone [저장소 URL]
~~~
# 브랜치와 병합
### 로컬 브랜치 목록 보기
~~~git
git branch
~~~
### 현재 커밋에서 브랜치 만들기
~~~git
git branch [새로운 브랜치 이름]
~~~
### 브랜치 변경하기
~~~git
git switch [브랜치 이름]
~~~
### 현재 브랜치와 지정된 브랜치 병합하기
~~~git
git merge [브랜치]
~~~
### 현재 브랜치를 지정된 브랜치 위로 리베이스하기
~~~git
git rebase [브랜치]
~~~
### 특정 커밋으로 현재 브랜치 리셋하기
~~~git
git reset --hard [커밋 체크섬]
~~~
# 로그 보기
### 현재 로그 보기
~~~git
git log
~~~
### 두 브랜치의 차이점 보기
~~~git
git log branchB..branchA
git diff branchB..branchA
~~~
### 한 파일의 히스토리 살펴보기
~~~git
git log --follow [파일명]
~~~
### 깃 오브젝트 정보 보기
~~~git
git show [오브젝트 체크섬]
~~~
# 임시 저장
### 작업 내용 임시로 저장하기
~~~git
git stash
~~~
### 임시 저장 목록 보기
~~~git
git stash list
~~~
### 가장 최근의 임시 저장 꺼내오기
~~~git
git stash pop
~~~
### 임시 저장 내용을 반영하고 스태시 목록에 남겨놓기
~~~git
git stash apply
~~~
### 임시 저장 내용 반영 없이 삭제하기
~~~git
git stash drop
~~~
# 업로드 및 업데이트
### 별칭 이름으로 원겨 저장소 추가하기
~~~git
git remote add [별칭][URL]
~~~
### 특정 원격 저장소만 업데이트 하기
~~~git
git fetch [별칭]
~~~
### 원격 저장소의 브랜치를 현재 브랜치에 병합하기
~~~git
git merge [별칭]/[브랜치 이름]
~~~
### 원격 저장소에 브랜치 업로드하기
~~~git
git push [별칭] [브랜치 이름]
~~~
### 원격 저장소에서 커밋을 가져와 동시에 병합하기
~~~git
git pull
~~~



출처: https://education.github.com/git-cheat-sheet-education.pdf
출처: 팀 개발을 위한 Git, GitHub 시작하기
