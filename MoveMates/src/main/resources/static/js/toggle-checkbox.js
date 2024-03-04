function toggleCheckbox(card) {
    var checkbox = card.querySelector('input[type="checkbox"]');
    checkbox.checked = !checkbox.checked;
	card.classList.toggle('checked', checkbox.checked);
}