# Smart Pointer
- 목적
   - RAII(Resource Acquisition Is Initialization)
      - 리소스와 오브젝트의 라이프 사이클을 일치 시킨다.
         - 리소스: Heap Memory, Thread, File access, Mutex 등
         - 오브젝트: Smart Ptr
- 효과
  - 메모리 누수 예방
- 종류
 1. Unique Pointer
    1. 특징 
       1. exclusive ownership
          1. 하나의 오브젝트를 하나의 포인터'만' 가리키는것 
          2. 따라서, 오브젝트의 라이프 사이클에 대해서 고민하지 않아도 된다.

2. Shared Pointer
   1. 특징
      1. shared ownership
         1. 하나의 오브젝트를 '여러개의 포인터'가 가리킨다.
         2. 레퍼런스 카운트를 이용해 몇개의 포인터가 할당되었는지 확인 가능하다.
 
# Virtual function
- 목적
   - Cpp에서 다형성(override)를 사용하기 위해서 사용.
- 예제코드
~~~C++
class Animal {
public:
    virtual void speak() {
        cout << "The animal makes a sound." << endl;
    }
};

class Dog : public Animal {
public:
    void speak() override {
        cout << "The dog barks." << endl;
    }
};
~~~
