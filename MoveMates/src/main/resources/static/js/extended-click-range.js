function extendedClickRange(event) {
	console.log("クリックされました。");
    // 親要素のクリックイベントをトリガー
    window.location.href = event.currentTarget.querySelector('a').getAttribute('href');
}
