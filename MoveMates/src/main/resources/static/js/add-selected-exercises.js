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
//        data: JSON.stringify(selectedExercises), // データをJSON形式に変換して送信
        // 成功時の処理
        success: function(response) {
            console.log("エクササイズが追加されました");
            // JSON形式のレスポンスをオブジェクトに変換
            let responseObject = JSON.parse(response);
            console.log("サーバーからのレスポンス:", responseObject);
        },
        error: function(xhr, status, error) {
            // エラー時の処理
            console.error("エラーが発生しました:", error);
            console.log("ステータスコード:", xhr.status);
            console.log("レスポンス:", xhr.responseText);
        }
    });
}
