/**
 * /post/details.html 파일에 포함.
 * 댓글 생성, 목록, 수정, 삭제.
 */
document.addEventListener('DOMContentLoaded', () => {
    let currentPageNo = 0; // 현재 댓글 목록의 페이지 번호
    // -> getAllComments() 함수에서 Ajax 요청을 보내고, 정상 응답이 오면 현재 페이지 번호가 바뀜.
    // -> currentPageNo의 값은 [더보기] 버튼에서 사용.
    
    // bootstrap 라이브러리의 Collapse 객체를 생성:
    const bsCollapse = new bootstrap.Collapse('div#collapseComments', {toggle: false});
    
    // [댓글 보기] 버튼을 찾아서, 클릭 이벤트 리스너를 설정:
    const btnToggle = document.querySelector('button#btnToggle');
    btnToggle.addEventListener('click', () => {
       bsCollapse.toggle(); // Collapse 객체를 보기/감추기 토글.
       
       const toggle = btnToggle.getAttribute('data-toggle');
       if (toggle === 'collapse') { // 현재 상태가 'collapse'라면
        btnToggle.innerHTML = '댓글 감추기'; // 버튼 텍스트를 변경.
        btnToggle.setAttribute('data-toggle', 'unfold'); // 상태를 'unfold'로 변경.
        
        // 댓글 목록 불러오기:
        getAllComments(0);
       } else { // 현재 상태가 'unfold'라면
        btnToggle.innerHTML = '댓글 보기'; // 버튼 텍스트를 변경.
        btnToggle.setAttribute('data-toggle', 'collapse'); // 상태를 'collapse'로 변경.
       }
    });
    
    // 댓글 [등록] 버튼을 찾아서, 클릭 이벤트 리스너를 설정:
    const btnRegisterComment = document.querySelector('button#btnRegisterComment');
    btnRegisterComment.addEventListener('click', registerComment);
    
    // 댓글 [더보기] 버튼을 찾아서, 클릭 이벤트 리스너를 설정:
    const btnMoreComments = document.querySelector('button#btnMoreComments');
    btnMoreComments.addEventListener('click', () => getAllComments(currentPageNo + 1));
    
    // ----- 함수 정의(선언) -----
    function registerComment() {
        // 댓글이 달린 포스트의 아이디
        const postId = document.querySelector('input#id').value;
        
        // 댓글 내용
        const ctext = document.querySelector('textarea#commentText').value;
        
        // 댓글 작성자
        const writer = document.querySelector('input#commentWriter').value;
        
        if (ctext.trim() === '') { // 댓글 내용이 비어 있는지 확인. (trim()은 공백도 빈값으로 인식함.)
            alert('댓글 내용을 입력하세요.');
            return;
        }
        
        // Ajax 요청에서 보낼 데이터
        const data = {postId, ctext, writer};
        
        // Ajax POST 방식 요청을 보내고, 응답/에러 처리 콜백 등록.
        axios.post('/api/comment', data)
            .then((response) => {
                console.log(response.data);
                alert('댓글 등록 성공!');
                
                // 댓글 내용 입력란을 비움.
                document.querySelector('textarea#commentText').value = '';
                
                // 댓글 목록 갱신
                getAllComments(0);
            })
            .catch((error) => console.log(error));
    }
    
    function getAllComments(pageNo) {
        // 댓글들이 달린 포스트 아이디:
        const postId = document.querySelector('input#id').value;
        
        // Ajax 요청을 보낼 주소:
        // path variable: 댓글이 달린 포스트 아이디. request param: 페이지 번호.
        const uri = `/api/comment/all/${postId}?p=${pageNo}`;
        
        // Ajax 요청을 보내고, 성공/실패 콜백 설정:
        axios.get(uri)
        .then((response) => {
            console.log(response);
            
            // 현재 페이지 번호의 값을 바꿈.
            currentPageNo = response.data.number;
            
            // 현재 페이지 번호보다 페이지 개수가 더 많으면 댓글 [더보기] 버튼을 보여줌.
            const divBtnMore  = document.querySelector('div#divBtnMore');
            // if (currentPageNo + 1 < response.data.totalPages) { // currentPageNo가 기본적으로 0이라 1을 더함.
            if (!response.data.last) {
                divBtnMore.classList.remove('d-none');
            } else {
                divBtnMore.classList.add('d-none');
            }
            
            // 댓글 목록을 HTML로 작성.
            makeCommentElements(response.data.content, response.data.number);
            // => content라는 프로퍼티가 목록의 배열을 가지고 있음.
            // => number라는 프로퍼티가 페이지 번호를 가지고 있음.
            
        })
        .catch((error) => console.log(error));
    }
    
    function makeCommentElements(data, pageNo) {
        // 로그인 사용자 정보 => 댓글 삭제/업데이트 버튼을 만들지 여부를 결정.
        // span 클래스 안에 value 속성이 없어서 value가 아니라 innerText 속성을 적음.
        const authUser = document.querySelector('span#authenticatedUser').innerText; // input 타입이 아니라서 innerText
        // innerHTML: html 안의 태그까지 포함함.
        // innerText: html 안의 태그 안의 문자열만 포함함.
        console.log(`authuser = ${authUser}`);
        
        // 댓글 목록을 추가할 div 요소
        const divComments = document.querySelector('div#divComments');
        
        let htmlStr = ''; // div에 삽입할 html 코드(댓글 목록)
        for (let comment of data) {
            // console.log(comment);
            const modifiedTime = new Date(comment.modifiedTime).toLocaleString();
            
            htmlStr += `
            <div class="card card-body mt-2">
                <div class="mt-2">
                    <span class="fw-bold">${comment.writer}</span>
                    <span class="text-secondary">${modifiedTime}</span>
                </div>
                <div class="mt-2">
                    <div class="mt-2">
                        <textarea class="commentText form-control" data-id="${comment.id}">${comment.ctext}</textarea>
                    </div>
            `;
            // 로그인 사용자와 댓글 작성자가 같은 경우에만 삭제/업데이트 버튼을 만듦.
            if (authUser === comment.writer) {
                htmlStr +=
                    `<div class="mt-2">
                        <button class="btnDelete btn btn-outline-danger btn-sm" data-id="${comment.id}">삭제</button>
                        <button class="btnUpdate btn btn-outline-primary btn-sm" data-id="${comment.id}">수정</button>
                    </div>
                </div>
            </div>
            `;
            } else {
                htmlStr += `
                    </div>
                </div>
                `;
            }
        }
        
        if (pageNo === 0) {
            // 댓글 목록 첫 번째 페이지이면, 기존 내용을 다 지우고 새로 작성.
            divComments.innerHTML = htmlStr;
        } else {
            // 댓글 목록에서 첫 번째 페이지가 아니면, 기존 내용 밑에 추가(append)
            divComments.innerHTML += htmlStr;
        }
        
        
        // 댓글 [삭제], [수정] 버튼들의 이벤트 리스너는 버튼들이 생겨난 이후에 등록.
        
        // 모든 button.btnDelete 버튼들을 찾아서 클릭 이벤트 리스너를 등록.
        const btnDeletes = document.querySelectorAll('button.btnDelete');
        btnDeletes.forEach((btn) => {
            btn.addEventListener('click', deleteComment);
        });
        
        // 모든 수정 버튼들을 찾아서 클릭 이벤트 리스너를 설정.
        const btnUpdates = document.querySelectorAll('button.btnUpdate');
        btnUpdates.forEach((btn) => {
           btn.addEventListener('click', updateComment); 
        });
        
    }
    
    // 댓글 [삭제] 버튼을 찾아서, 클릭 이벤트 리스너를 설정:
    function deleteComment(event) {
        // 삭제 여부 확인
        if (!confirm('댓글을 정말 삭제할까요?')) {
            return;
        }
        
         // 이벤트 리스너 콜백의 아규먼트 event 객체는 target 속성을 가지고 있음.
        const id = event.target.getAttribute('data-id'); // 삭제할 댓글 아이디
        
        // 삭제 Ajax 요청을 보낼 주소
        const uri = `/api/comment/${id}`; 
        
        // Ajax 요청을 보냄.
        axios
        .delete(uri)
        .then((response) => {
            console.log(response.data);
            
            alert(`댓글 #${id} 삭제 성공`);
            getAllComments(0); // 댓글 목록 갱신
         
        })
        .catch((error) => {
            console.log(error);
        });
    }
    
    // 댓글 [수정] 버튼의 클릭 이벤트 리스너
    function updateComment(event) {
        // 업데이트할 댓글 번호
        const id = event.target.getAttribute('data-id');
        
        const textarea = document.querySelector(`textarea.commentText[data-id="${id}"]`);
        
        // 업데이트할 댓글 내용
        const ctext = textarea.value;
        
        if (ctext.trim() === '') {
            alert('댓글 내용은 반드시 입력해야 합니다.');
            return; // 이벤트 리스너를 종료
        }
        
        // 업데이트 여부 확인
        if (!confirm('변경된 댓글을 저장할까요?')) {
            return;
        }
        
        // 댓글 업데이트 요청 REST API URI
        const uri = `/api/comment/${id}`;
        const data = { id, ctext }; // 업데이트 요청 데이터. {id: id, ctext: ctext}
        
        // Ajax 요청
        axios
        .put(uri, data )
        .then((response) => {
            console.log(response);
            
            alert(`댓글 #${id} 업데이트 성공`);
            
            getAllComments(0); // 댓글 목록 갱신
        })
        .catch((error) => console.log(error));
    }
    
});
