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
 
 // input#itemInput2 요소에 'keydown' 이벤트 리스너를 등록:
 // 엔터키가 눌렸을 때, input에 입력된 내용을 ul#itemList2의 리스트 아이템으로 추가.
 const itemInput2 = document.querySelector('input#itemInput2');
 itemInput2.addEventListener('keydown', function (e) { // 'keydown'은 키보드를 입력할 때 이벤트 발생
    // const itemInput2 = document.querySelector('input#itemInput2');
    if (e.key === 'Enter') {
        const itemList2 = document.querySelector('ul#itemList2');
        itemList2.innerHTML += `<li>${itemInput2.value}</li>`;
        itemInput2.value = '';
    }
 }); 
 
 // input#username 요소에 'change' 이벤트 리스너를 등록:
 // input에 입력된 내용이 바뀔 때마다 div를 입력 내용으로 덮어씀.
const username = document.querySelector('input#username');
username.addEventListener('change', () => {
    // 'change'는 input이 편집상태가 아니고(포커스를 잃어버린 상태), input에 입력된 값이 이전과 달라진 경우에 발생함.
    const output = document.querySelector('div#output');
    output.innerHTML = username.value;
    username.value = '';
});

 // img#bulb 요소에 'mouseenter' 이벤트 리스너를 등록:
 // img의 src를 'images/bulb_on.gif'로 변경.
 const bulb_on = document.querySelector('img#bulb');
 bulb.addEventListener('mouseenter', function() {
    bulb_on.src = 'images/bulb_on.gif';
    bulb_on.alt = 'bulb_on';
 });
 
 // img#bulb 요소에 'mouseleave' 이벤트 리스너를 등록:
 // img의 src를 'images/bulb_off.gif'로 변경.
const bulb_off = document.querySelector('img#bulb');
 bulb.addEventListener('mouseleave', () => {
    bulb_off.src = 'images/bulb_off.gif';
    bulb_off.alt = 'bulb_off';
 });
 