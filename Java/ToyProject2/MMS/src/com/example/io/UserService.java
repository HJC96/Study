package com.example.io;

import java.util.Iterator;
import java.util.List;

public interface UserService {
    //회원 정보를 등록하다.
    public void addUser(User user);
    // 회원정보를 수정한다. user.getEmail()에 해당하는 회원정보 수정
    public boolean updateUser(User user);
    // 회원정보를 삭제한다.
    public boolean deleteUser(String email);
    // 모든 회원정보를 반환한다.
    public Iterator<User> getUsers();
    // email에 해당하는 회원정보가 있을경우 0보다 큰 값을 반환.
    public boolean exists(String email);
}
