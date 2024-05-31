/**
 * /user/signin.jsp에 포함.
 */

console.log("gg");

// HTML DOM(Document Object Model) 컨텐트 로딩이 끝났을 때, 이벤트 리스너를 실행.
document.addEventListener('DOMContentLoaded', () => {

    const SignInForm = document.querySelector('form#SignInForm');

    const inputUserid = document.querySelector('input#userid');

    const inputPassword = document.querySelector('input#password');

    const inputSignIn = document.querySelector('input#SignIn');
    
    const btnCancel = document.querySelector('button#btnCancel');
    
    inputSignIn.addEventListener('click', () => {
        userId = inputUserid.value;
        password = inputPassword.value;
        
    });

    btnCancel.addEventListener('click', () => {
        location.href = `/lab05/`;
    });
    
});



