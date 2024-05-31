/**
 * /user/signup.jsp에 포함.
 */

// HTML DOM(Document Object Model) 컨텐트 로딩이 끝났을 때, 이벤트 리스너를 실행.
document.addEventListener('DOMContentLoaded', () => {

    const SignUpForm = document.querySelector('form#SignUpForm');

    const inputUserid = document.querySelector('input#userid');
    const btnCheckDuplicateUserId = document.querySelector('button#btnCheckDuplicateUserId');

    const inputPassword = document.querySelector('input#password');

    const inputEmail = document.querySelector('input#email');
    const btnCheckDuplicateEmail = document.querySelector('button#btnCheckDuplicateEmail');

    const btnSignUp = document.querySelector('button#btnSignUp');

    const btnCancel = document.querySelector('button#btnCancel');

    btnCheckDuplicateUserId.addEventListener('click', () => {

    });

    btnCheckDuplicateEmail.addEventListener('click', () => {

    });

    btnSignUp.addEventListener('click', () => {
        const userid = inputUserid.value;
        const password = inputPassword.value;
        const email = inputEmail.value;

        if (!userid) {
            alert('아이디를 입력하세요!');
            return; // 함수 종료
        } else if (!password) {
            alert('비밀번호를 입력하세요!');
            return; // 함수 종료
        } else if (!email) {
            alert('이메일을 입력하세요!');
            return; // 함수 종료
        }

        const result = confirm('가입하시겠습니까?');
        if (result) {
            // 삭제(GET 방식) 요청을 서버로 보냄.
            SignUpForm.method = 'post'; // 폼 제출 방식 설정.(post 방식)
            SignUpForm.action = 'signup'; // 폼 제출 요청 주소 설정.
            SignUpForm.submit(); // 폼 제출(서버로 요청을 보냄).

            alert("회원가입이 성공적으로 완료되었습니다.");
        }
    });

    btnCancel.addEventListener('click', () => {
        location.href = `/lab05/`;
    });



});