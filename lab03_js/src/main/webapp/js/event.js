/**
 * event.html에 포함.
 */

 const output = document.querySelector('div#output');
 
 // btnInput에 클릭 이벤트 리스너를 설정:
 // input#itemInput에 입력된 내용을 ul 요소의 li로 추가.
 const btnInput = document.querySelector('button#btnInput');
 btnInput.addEventListener('click', function (e) {
//    console.log(e); // PointerEvent {isTrusted: true, pointerId: 1, width: 1, height: 1, pressure: 0, …}
    
    const itemInput = document.querySelector('input#itemInput');
    const itemList = document.querySelector('ul#itemList');
    itemList.innerHTML += `<li>${itemInput.value}</li>`;
    itemInput.value = '';
 });