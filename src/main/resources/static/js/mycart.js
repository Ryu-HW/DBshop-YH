document.querySelectorAll('#decreaseBtn, #increaseBtn').forEach(button => {
  button.addEventListener('click', (event) => {
    // 버튼 클릭 시 상품 ID와 수량을 가져옴
    const button = event.target;
    const productId = button.getAttribute('data-product-id');
    //버튼에서 가장 가까운 '.d-flex'클래스를 갖고있는 태그의 자식 중 type="number"인 아이.
    const quantityInput = button.closest('.d-flex').querySelector('input[type="number"]');
    let quantity = parseInt(quantityInput.value, 10);

    // 수량 증가 또는 감소 처리
    if (button.dataset.action === 'decrease') {
      quantity--;
    } else if (button.dataset.action === 'increase') {
      quantity++;
    }


    // 새로운 수량을 서버로 보내기 위한 PATCH 요청
    updateCartItemQuantity(productId, quantity, quantityInput);


  });
});

// 수량 업데이트 함수 (PATCH 요청 보내기)
function updateCartItemQuantity(productId, quantity, quantityInput) {

//다른 버튼 등 비활성화
  document.querySelectorAll('button, input, select, textarea').forEach(element => {
    element.disabled = true;
  });

  // PATCH 요청을 보낼 URL 설정
  const url = `/cart/update/${productId}`;

  // 요청에 보낼 데이터 설정
//  const data = {
//    quantity: quantity
//  };

  // Fetch API를 사용하여 PATCH 요청 보내기
  fetch(url, {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json',
      'X-CSRF-TOKEN': document.querySelector('input[name="_csrf"]').value  // CSRF 토큰 추가
    },
    body: JSON.stringify(quantity)  // 수량을 포함한 데이터 전송
  })
  .then(response => {
    // 성공적으로 수량이 업데이트된 경우
    if (response.ok) {
      // 화면에 반영된 수량 값 업데이트

      if(quantity < 1){
        deleteCartItem(productId);
      }else{
        quantityInput.value = quantity;
        const inputEvent = new Event('input');
        quantityInput.dispatchEvent(inputEvent);
      }

      // 추가적으로 DB나 화면에 총액 등 다른 데이터도 업데이트 할 수 있음
    } else {
      alert("수량 업데이트 실패!");
    }
  })  // 응답 JSON 처리
  .catch(error => {
    console.error('Error:', error);
    alert("수량 업데이트 중 오류가 발생했습니다.");
  });

//다시 활성화
    document.querySelectorAll('button, input, select, textarea').forEach(element => {
      element.disabled = false;
    });


}

document.querySelectorAll('#deleteProductBtn').forEach(button => {
  button.addEventListener('click', (event) => {
    event.preventDefault();  // 기본 동작(폼 제출)을 방지
    //제일 가까운 productId
    const productId = button.closest('form').querySelector('input[name="productId"]').value;

    // fetch로 DELETE 요청 보내기
    deleteCartItem(productId);
  });
});

// 장바구니 항목 삭제 함수 (DELETE 요청 보내기)
function deleteCartItem(productId) {
  const url = `/cart/delete/${productId}`;

  fetch(url, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json',
      'X-CSRF-TOKEN': document.querySelector('input[name="_csrf"]').value  // CSRF 토큰 추가
    }
  })
  .then(response => {
    if (response.ok) {
      // 삭제가 성공하면 해당 항목을 화면에서 제거
      const itemElement = document.querySelector(`#cartItem-${productId}`);
      if (itemElement) {
        itemElement.remove();

      }
      alert('상품이 삭제되었습니다.');
      location.reload();
    } else {
      alert('상품 삭제 실패!');
    }
  })
  .catch(error => {
    console.error('Error:', error);
    alert('상품 삭제 중 오류가 발생했습니다.');
  });


}

function updateTotalPrice() {
    let totalPrice = 0;

    // 모든 상품의 총액을 찾아서 더하기
    document.querySelectorAll('.item-total-value').forEach(input => {
        totalPrice += parseInt(input.value, 10);
    });

    // 총액을 화면에 반영
    document.getElementById('totalPrice').textContent = `총액: ${totalPrice}원`;
}
updateTotalPrice();

document.querySelectorAll('.item-total-value').forEach(input => {
    input.addEventListener('change', function () {

        updateTotalPrice();
    });
});

document.querySelectorAll('#quantityInput').forEach(input => {
    input.addEventListener('input', function () {
        // 수량이 변경된 후 자동으로 해당 상품의 총액을 갱신하는 코드
        const quantity = parseInt(this.value, 10);  // 변경된 수량 값
        console.log(parseInt(this.closest('.d-flex').querySelector('.item-value').value, 10));
        const price = parseInt(this.closest('.d-flex').querySelector('.item-value').value, 10);  // 해당 상품의 가격

        // 총액 계산
        const newTotal = price * quantity;

        // 총액이 표시되는 span을 찾아서 텍스트를 업데이트
        const totalSpan = this.closest('.justify-content-between').querySelector('.item-total');
        const totalInput = this.closest('.justify-content-between').querySelector('.item-total-value');
        totalSpan.textContent = `${newTotal}원`;
        totalInput.value = `${newTotal}`;

        let totalPrice = 0;

        // 모든 .item-total-value를 찾아서 총액 계산
        document.querySelectorAll('.item-total-value').forEach(input => {
        console.log(input.value);
        totalPrice += parseInt(input.value, 10);
        });

        document.querySelector('#totalPrice').textContent = `총액: ${totalPrice}원`

    });
});