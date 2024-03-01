function addSelectedExercises(mymenuId) {
    // 選択されたエクササイズの値を取得
    var selectedExercises = $('input[name="selectedExercises"]:checked').map(function() {
        return $(this).val();
    }).get();
    
    var url = "/mymenu/" + mymenuId + "/add-exercises";
    
    // Ajaxリクエストを送信して選択されたエクササイズをサーバーに送信
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json", // JSON形式でデータを送信する
        data: JSON.stringify(selectedExercises), // データをJSON形式に変換して送信
        success: function(response) {
            // 成功時の処理
            console.log("エクササイズが追加されました");
        },
        error: function(xhr, status, error) {
            // エラー時の処理
            console.error("エラーが発生しました:", error);
        }
    });
}
