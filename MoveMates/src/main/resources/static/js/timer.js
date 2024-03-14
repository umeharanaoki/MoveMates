let timer; // タイマー
let startTime; // 開始時間
let elapsedTime = 0; // 経過時間

const stopwatchDisplay = document.getElementById('stopwatch');
const startStopButton = document.getElementById('startStopButton');
const resetButton = document.getElementById('resetButton');
const endButton = document.getElementById('endButton');

function startStop() {
  if (timer) {
    // タイマーが動いている場合、停止する
    clearInterval(timer);
    timer = null;
    startStopButton.textContent = 'スタート';
  } else {
    // タイマーが停止している場合、開始する
    startTime = Date.now() - elapsedTime;
    timer = setInterval(updateStopwatch, 10);
    startStopButton.textContent = 'ストップ';
  }
}

function updateStopwatch() {
  const currentTime = Date.now();
  elapsedTime = currentTime - startTime;
  const formattedTime = formatTime(elapsedTime);
  stopwatchDisplay.textContent = formattedTime;
  
  // フォームのexerciseDurationフィールドに経過時間を反映する
  const exerciseDurationInput = document.getElementById('exerciseDuration');
  exerciseDurationInput.value = Math.floor(elapsedTime / 1000); // 秒単位で保存する
}

function formatTime(time) {
  const totalSeconds = Math.floor(time / 1000);
  const minutes = Math.floor(totalSeconds / 60);
  const seconds = totalSeconds % 60;
  return `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
}

function resetStopwatch() {
  clearInterval(timer);
  timer = null;
  elapsedTime = 0;
  stopwatchDisplay.textContent = '00:00';
  startStopButton.textContent = 'スタート';
}

startStopButton.addEventListener('click', startStop);
resetButton.addEventListener('click', resetStopwatch);
