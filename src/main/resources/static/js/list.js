//삭제 클릭시 confirm메시지 띄우고 url실행(getmapper)
function deleteCategory(e){
    const checkDelete = confirm("정말 삭제하시겠습니까?");
    const delUrl = e.target.getAttribute('data-url')

    if(checkDelete){
        window.location.href = delUrl;
    }
}