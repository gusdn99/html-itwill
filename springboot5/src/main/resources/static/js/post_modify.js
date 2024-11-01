/**
 * /post/modify.html 파일에 포함.
 * 포스트 업데이트, 삭제 버튼 이벤트 처리.
 */

 document.addEventListener('DOMContentLoaded', () => {
    
    // 삭제 버튼을 찾고, 클릭 이벤트 리스너를 설정.
    const btnDelete = document.querySelector('button#btnDelete');
    btnDelete.addEventListener('click', () => {
        const check = confirm('정말 삭제할까요?');
        if (check) { // 사용자가 [확인]을 선택했을 때
            // GET 방식의 delete 요청을 서버로 보냄.
            const id = document.querySelector('input#id').value;
            location.href = `/post/delete?id=${id}`;
        }
    });

    // 업데이트 버튼을 찾고, 클릭 이벤트 리스너를 설정.
    const btnUpdate = document.querySelector('button#btnUpdate');
    btnUpdate.addEventListener('click', () => {
        // 제목과 내용이 비어있는지 체크:
        // trim(): 문자열 시작과 끝에 있는 모든 공백을 제거.
        const title = document.querySelector('input#title').value.trim();
        const content = document.querySelector('textarea#content').value.trim();
        if (title === '' || content === '') {
            alert('제목과 내용은 반드시 입력해야 합니다!');
            return;
        }
        
        // 업데이트 내용 저장 확인:
        const check = confirm('변경된 내용을 저장할까요?');
        if (check) {
            const updateForm = document.querySelector('form#updateForm');
            updateForm.submit(); // 폼 양식 데이터 제출(서버로 요청 보냄).
        } 
        
   });
   
});
