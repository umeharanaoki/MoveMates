// ページに応じてアイコンを切り替え
window.onload = function() {
    const currentPage = window.location.pathname; // 現在のページのURLを取得
    let imageSrcHome = "";
    let imageSrcSearch = "";
    let imageSrcMyMenu = "";

    // ページごとに異なる画像を設定
    // ホームアイコン
    if (currentPage.startsWith("/user")) {
        imageSrcHome = "/images/home-now.svg";
    } else {
        imageSrcHome = "/images/home.svg";
    }
    // 検索アイコン
    if (currentPage.startsWith("/exercises")) {
        imageSrcSearch = "/images/search-now.svg";
    } else {
        imageSrcSearch = "/images/search.svg";
    }
    // マイメニューアイコン
    if (currentPage.startsWith("/mymenu")) {
        imageSrcMyMenu = "/images/mymenu-now.svg";
    } else {
        imageSrcMyMenu = "/images/mymenu.svg"; // デフォルトの画像
    }
    
    // 画像を設定
    document.getElementById("nav-icon-img-home-pc").src = imageSrcHome;
    document.getElementById("nav-icon-img-home-sp").src = imageSrcHome;
    document.getElementById("nav-icon-img-search-pc").src = imageSrcSearch;
    document.getElementById("nav-icon-img-search-sp").src = imageSrcSearch;
    document.getElementById("nav-icon-img-mymenu-pc").src = imageSrcMyMenu;
    document.getElementById("nav-icon-img-mymenu-sp").src = imageSrcMyMenu;
}