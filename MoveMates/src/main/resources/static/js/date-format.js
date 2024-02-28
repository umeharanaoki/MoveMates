// 入力フォームの要素を取得
var birthdayInput = document.getElementById('birthday');

// 入力値が変更されたときに呼び出されるイベントリスナーを追加
birthdayInput.addEventListener('input', function() {
    // 入力値を取得
    var inputValue = birthdayInput.value;

    // 入力値が'yyyy/MM/dd'形式になるように制御
    if (/^\d{4}\/\d{2}\/\d{2}$/.test(inputValue)) {
        return; // 正しい形式ならそのまま
    }

    // 数字以外を除去し、スラッシュで区切る
    var cleanedValue = inputValue.replace(/\D/g, '').replace(/(\d{4})(\d{2})(\d{2})/, '$1/$2/$3');

    // 入力フィールドに反映
    birthdayInput.value = cleanedValue;
});
