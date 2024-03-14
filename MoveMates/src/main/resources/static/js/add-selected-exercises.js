function addSelectedExercises(mymenuId) {
    // 選択されたエクササイズの値を取得
    const selectedExercises = $('input[name="selectedExercises"]:checked').map(function() {
        return $(this).val();
    }).get();
    
    let url = "/mymenu/" + mymenuId + "/add-exercises";
    
    // Ajaxリクエストを送信して選択されたエクササイズをサーバーに送信
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json", // JSON形式でデータを送信する
        data: JSON.stringify({ "exerciseIds": selectedExercises }), 	// データをJSON形式に変換して送信
        dataType: 'json', // サーバーからのレスポンスをJSONとして解析することを指定
        // 成功時の処理
        success: function(response) {
            console.log("エクササイズが追加されました");
            console.log("サーバーからのレスポンス:", response);
        },
        error: function(xhr, status, error) {
            // エラー時の処理
            console.error("エラーが発生しました:", error);
            console.log("ステータスコード:", xhr.status);
            console.log("レスポンス:", xhr.responseText);
        }
    });
}
