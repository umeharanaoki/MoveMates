// カレンダーを作成するメソッドを定義
// 実行はhtml側で引数を渡しつつ行う
function generateCalendar(year, month, exerciseDays) {
    const daysInMonth = new Date(year, month + 1, 0).getDate();
    const firstDayOfWeek = new Date(year, month, 1).getDay();
    
    console.log(exerciseDays);
    
    const today = new Date(); // 今日の日付を取得

    const calendarDiv = document.getElementById('my-calendar');
    calendarDiv.innerHTML = '';

	document.getElementById('currentMonthYear').textContent = `${year}/${month + 1}`;

    const table = document.createElement('table');
    const headerRow = table.insertRow();
    const weekdays = ['Sun.', 'Mon.', 'Tue.', 'Wed.', 'Thu.', 'Fri.', 'Sat.'];
    weekdays.forEach(weekday => {
        const th = document.createElement('th');
        th.textContent = weekday;
        headerRow.appendChild(th);
    });

    let date = 1;
    for (let i = 0; i < 6; i++) {
        const row = table.insertRow();
        for (let j = 0; j < 7; j++) {
            if (i === 0 && j < firstDayOfWeek) {
                const cell = row.insertCell();
                cell.textContent = '';
            } else if (date > daysInMonth) {
                if (j === 0) {
                    break; // カレンダーの右下がかけるのを防ぐ
				}
				const cell = row.insertCell();
				cell.textContent = '';
            } else {
                const cell = row.insertCell();
                // 日付を表示する要素を作成	
				const dateSpan = document.createElement('span');	
				dateSpan.textContent = date;
                
                // 今日の日付と一致する場合、セルにクラスを追加してスタイルを変更
				if (year === today.getFullYear() && month === today.getMonth() && date === today.getDate()) {
					cell.classList.add('today');
				}
				
				// 日付を囲む円を作成し、日付の周りに配置
				const circle = document.createElement('div');	
				circle.classList.add('calendar-circle');	
				circle.appendChild(dateSpan);	
				cell.appendChild(circle);
				
				// date型のexerciseDaysと比較できるように日付を文字列に変換
                const dateString = `${year}-${(month + 1).toString().padStart(2, '0')}-${date.toString().padStart(2, '0')}`;
                if (exerciseDays.includes(dateString)) {
				    // 運動の記録がある場合、画像を表示する要素を作成し、日付セルに追加
				    const exerciseDiv = document.createElement('div');
				    exerciseDiv.classList.add('exercise-day');
				    cell.appendChild(exerciseDiv);
				}

                date++;
            }
        }
    }

    calendarDiv.appendChild(table);
}


// 前の月に移動
function moveToPreviousMonth() {
	currentMonth--;
	if (currentMonth < 0) {
	currentMonth = 11;
	currentYear--;
	}
	generateCalendar(currentYear, currentMonth);
}
	
// 次の月に移動
function moveToNextMonth() {
	currentMonth++;
	if (currentMonth > 11) {
	currentMonth = 0;
	currentYear++;
	}
	generateCalendar(currentYear, currentMonth);
}