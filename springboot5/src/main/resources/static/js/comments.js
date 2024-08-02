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
    
    // 부트스트랩 모달(다이얼로그) 객체 생성.
    const commentModal = new bootstrap.Modal('div#commentModal', {backdrop: true});
    
    // 모달의 저장 버튼을 찾고, 클릭 이벤트 리스너를 설정.
    const btnUpdateComment = document.querySelector('button#btnUpdateComment');
    btnUpdateComment.addEventListener('click', updateComment);
    
    // [댓글 보기] 버튼을 찾아서, 클릭 이벤트 리스너를 설정:
    const btnToggle = document.querySelector('button#btnToggle');
    btnToggle.addEventListener('click', () => {
       bsCollapse.toggle(); // Collapse 객체를 보기/감추기 토글.
       
       const toggle = btnToggle.getAttribute('data-toggle');
       if (toggle === 'collapse') {
        btnToggle.innerHTML = '댓글 감추기';
        btnToggle.setAttribute('data-toggle', 'unfold');
        
        // 댓글 목록 불러오기:
        getAllComments(0);
       } else {
        btnToggle.innerHTML = '댓글 보기';
        btnToggle.setAttribute('data-toggle', 'collapse');
       }
    });
    
    // 댓글 [등록] 버튼을 찾아서, 클릭 이벤트 리스너를 설정:
    const btnRegisterComment = document.querySelector('button#btnRegisterComment');
    btnRegisterComment.addEventListener('click', registerComment);
    
    // 댓글 [더보기] 버튼을 찾아서, 클릭 이벤트 리스너를 설정:
    const btnMoreComments = document.querySelector('button#btnMoreComments');
    const moreCommentsContainer = document.querySelector('#moreCommentsContainer');
    btnMoreComments.addEventListener('click', () => getAllComments(currentPageNo + 1));
    
    // ----- 함수 정의(선언) -----
    function registerComment() {
        // 댓글이 달린 포스트의 아이디
        const postId = document.querySelector('input#id').value;
        
        // 댓글 내용
        const ctext = document.querySelector('textarea#commentText').value;
        
        // 댓글 작성자
        const writer = document.querySelector('input#commentWriter').value;
        
        if (ctext.trim() === '') {
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
            
            // 댓글 목록을 HTML로 작성.
            makeCommentElements(response.data.content, response.data.number);
            // => content라는 프로퍼티가 목록의 배열을 가지고 있음.
            // => number라는 프로퍼티가 페이지 번호를 가지고 있음.
                
            // 더보기 버튼 표시 여부 결정
            updateMoreCommentsButtonVisibility(response.data);
            
        })
        .catch((error) => console.log(error));
    }
    
    function makeCommentElements(data, pageNo) {
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
                        <textarea class="form-control">${comment.ctext}</textarea>
                    </div>
                    <div class="mt-2">
                        <button class="btnDelete btn btn-outline-danger btn-sm" data-id="${comment.id}">삭제</button>
                        <button class="btnUpdate btn btn-outline-primary btn-sm" data-id="${comment.id}">수정</button>
                    </div>
                </div>
            </div>
            `;
        }
        
        if (pageNo === 0) {
            // 댓글 목록 첫 번째 페이지이면, 기존 내용을 다 지우고 새로 작성.
            divComments.innerHTML = htmlStr;
        } else {
            // 댓글 목록에서 첫 번째 페이지가 아니면, 기존 내용 밑에 추가(append)
            divComments.innerHTML += htmlStr;
        }
        
        // 모든 삭제 버튼들을 찾아서 클릭 이벤트 리스너를 설정. (html 코드가 div 영역에 삽입된 이후에 이벤트 발생)
        const btnDeletes = document.querySelectorAll('button.btnDelete');
        for (let btn of btnDeletes) {
            btn.addEventListener('click', deleteComment);
        }
        
        // 모든 수정 버튼들을 찾아서 클릭 이벤트 리스너를 설정.
        const btnUpdates = document.querySelectorAll('button.btnUpdate');
        for (let btn of btnUpdates) {
            btn.addEventListener('click', showCommentModal);
        }
        
    }
    
    // 더보기 버튼 표시 여부를 업데이트하는 함수
    function updateMoreCommentsButtonVisibility(pageData) {
        const btnMoreComments = document.querySelector('button#btnMoreComments');
        
        if (pageData.last) {
            // 마지막 페이지면 버튼을 숨김
            btnMoreComments.style.display = 'none';
        } else {
            // 더 볼 페이지가 있으면 버튼을 표시
            btnMoreComments.style.display = 'block';
        }
    }
    
    // 댓글 [삭제] 버튼을 찾아서, 클릭 이벤트 리스너를 설정:
    function deleteComment(event) {
        // 이벤트 리스너 콜백의 아규먼트 event 객체는 target 속성을 가지고 있음.
        console.log(event.target); // 이벤트가 발생한 요소(타겟)
        const id = event.target.getAttribute('data-id'); // HTML 요소의 속성 값 찾기
        
        // 삭제 여부 확인
        const result = confirm('댓글을 정말 삭제할까요?');
        if (!result) { // 사용자가 [취소]를 선택했을 때
            return; // 함수 종료
        }
        
        // Ajax로 삭제 요청을 보낼 REST API URI
        const uri = `/api/comment/${id}`;
        
        // Ajax 요청을 보냄.
        axios
        .delete(uri)
        .then((response) => {
            console.log(response.data);
            
            alert(`댓글(${id}) 삭제 성공`);
            getAllComments(0); // 댓글 목록 갱신
         
        })
        .catch((error) => {
            console.log(error);
        });
    }
    
    // 댓글 수정 버튼의 클릭 이벤트 리스너
    function showCommentModal(event) {
        // 이벤트 타겟(수정 버튼)의 data-id 속성 값을 읽음.
        const id = event.target.getAttribute('data-id');
        
        // Ajax 요청을 보내서 댓글 아이디로 검색.
        const uri = `/api/comment/${id}`;
        axios
        .get(uri)
        .then((response) => {
            // console.log(response);
            console.log(response.data);
            // console.log(response.data.id);
            
            // 모달의 input(댓글 번호), textarea(댓글 내용)의 value를 채움.
            document.querySelector('input#modalCommentId').value = id;
            document.querySelector('textarea#modalCommentText').value = response.data.ctext;
            
            // 모달을 보여줌.
            commentModal.show();
        })
        .catch((error) => console.log(error)); // 실행문이 하나일 때 중괄호 생략 가능함.
    }
    
    // 댓글 업데이트 모달의 [저장] 버튼의 클릭 이벤트 리스너
    function updateComment() {
        // 업데이트할 댓글 번호
        const id = document.querySelector('input#modalCommentId').value;
        
        // 업데이트할 댓글 내용
        const ctext = document.querySelector('textarea#modalCommentText').value;
        if (ctext === '') {
            alert('업데이트할 댓글 내용을 입력하세요.');
            return; // 이벤트 리스너를 종료
        }
        
        // 댓글 업데이트 요청 REST API URI
        const uri = `/api/comment/${id}`;
        
        // Ajax 요청
        axios
        .put(uri, { ctext }) // { ctext } = {ctext: ctext}, {id, ctext} = {id: id, ctext: ctext} id는 CommentRestController에서 dto.setId(id);가 있어서 안 적음.
        .then((response) => {
            console.log(response);
            
            getAllComments(0); // 댓글 목록 갱신
            commentModal.hide(); // 모달 숨김
        })
        .catch((error) => console.log(error));
    }
    
});
