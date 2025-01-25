
function deleteCategory(e){
    const checkDelete = confirm("정말 삭제하시겠습니까?");
    const delUrl = e.target.getAttribute('data-url')

    if(checkDelete){
        window.location.href = delUrl;
    }
}