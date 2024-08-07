/**
 * object.html에 포함.
 */

 // JSON(JavaScript Object Notation): 자바스크립트 객체 표기법.
 // { property: value, ... }
 
 const person = {
    name: '홍길동',
    age: 16,
    phone: ['010-0000-0000', '02-0000-0000']
 };
 console.log(person);
 
 // 객체가 가지고 있는 프로퍼티 접근: (1) 참조 연산자, (2) 인덱스 연산자
 console.log(person.name); // 참조 연산자: object.propertyName
 console.log(person['age']); // 인덱스 연산자: object.['propertyName']
 console.log(person.phone[0]); // person['phone'][0]
 
 person.age = 17; // 객체의 프로퍼티 값 변경.
 console.log(person);
 
 // 자바스크립트의 객체는, 객체가 생성된 이후에 프로퍼티를 추가할 수 O
 person.email = 'hgd@itwill.com';
 console.log(person);
 
 // 메서드를 갖는 객체:
 const score = {
    html: 100,
    css: 90,
    js: 82,
    sum: function () {
        // 객체의 프로퍼티를 접근할 때는 this 키워드를 사용.
        return this.html + this.css + this.js;
    },
    mean: function () {
        return this.sum() / 3;
    }
 };
 
 console.log(score);
 console.log(score.sum()); // 메서드 호출
 console.log(score.mean());
 
 // 생성자 함수(constructor function): this 키워드를 사용해서 프로퍼티를 선언한 함수.
 function Score(html, css, js) {
    // 필드
    this.html = html;
    this.css = css;
    this.js = js;
    
    // 메서드
    this.sum = function () {
        return this.html + this.css + this.js;
    };
    
    this.mean = function () {
        return this.sum() / 3;
    }
 }
 
 // 생성자 함수를 사용한 객체 생성: new 생성자함수();
 const score1 = new Score(1, 2, 31);
 console.log(score1);
 console.log(score1.sum());
 console.log(score1.mean());
  
 const score2 = new Score(); // -> 모든 필드는 undefined가 됨. 
 console.log(score2); // Score {html: undefined, css: undefined, js: undefined, sum: ƒ, mean: ƒ}
 console.log(score2.sum()); // -> undefined + undefined = Nan(Not a Number)
 
 // 자바스크립트 객체는 for-in 구문에서 사용할 수 O -> 프로퍼티 이름들을 iteration.
 const student = {
    no: 101,
    name: '오쌤',
    classNo: 10,
 };
 
 for (let x in student) {
    console.log(x, ':', student[x]); // student.x -> 안 됨.
 }
 
 // 클래스:
 class Rectangle {
    // 생성자: 필드 초기화
    constructor(width, height) {
        this.width = width;
        this.height = height;
    }
    
    // 메서드: function 키워드를 사용하지 X
    area() {
        return this.width * this.height;
    }
    
    perimeter() {
        return (this.width + this.height) * 2;
    }
 }
 
 // 클래스를 사용한 객체 생성:
 const rect1 = new Rectangle(2, 3);
 console.log(rect1);
 console.log(`넓이 = ${rect1.area()}`);
 console.log(`길이 = ${rect1.perimeter()}`);
 
 const rect2 = new Rectangle();
 console.log(rect2); // Rectangle {width: undefined, height: undefined}
 
 // 원(Circle) 클래스 선언:
 // - 필드: radius. 기본값 0.
 // - 메서드: area(넓이), perimeter(둘레 길이)
 
 class Circle {
    constructor(radius = 0) {
        this.radius = radius;
    }
    
    area() {
        return this.radius * this.radius * 3.14;
    }
    
    perimeter() {
        return this.radius * 2 * 3.14;
    }
 }
 
 const cir1 = new Circle(3);
 console.log(cir1); // Circle {radius: 3}
 console.log(`넓이 = ${cir1.area()}`);
 console.log(`길이 = ${cir1.perimeter()}`);
 
 const cir2 = new Circle(); // -> 생성자의 default parameter가 사용되는 경우.
 console.log(cir2); // Circle {radius: 0}
 console.log(`넓이 = ${cir2.area()}`); // 넓이 = 0
 console.log(`길이 = ${cir2.perimeter()}`); // 길이 = 0
 