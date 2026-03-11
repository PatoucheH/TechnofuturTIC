const numberSelect = document.getElementById("table");
const calcBtn = document.getElementById("calculateBtn");
const table = document.getElementById("displayTable");
const operatorSelect = document.getElementById("operator");

for (let i = 0; i <= 20; i++) {
    const opt = document.createElement("option");
    opt.value = i;
    opt.textContent = i;
    numberSelect.appendChild(opt);
}

calcBtn.addEventListener("click", displayTable);


function displayTable() {
    table.textContent = "";
    const div = document.createElement("div");
    const value = Number(numberSelect.value);
    const operatorValue = operatorSelect.value; 
    for (let i = 0; i <= 10; i++) {
        const p = document.createElement("p")
        switch (operatorValue) {
            case "*":
                p.textContent = `${i} ${operatorValue} ${value} = ${i * value}`;
                break;
            case "+":
                p.textContent = `${i} ${operatorValue} ${value} = ${i + value}`;
                break;
            case "-":
                p.textContent = `${i} ${operatorValue} ${value} = ${i - value}`;
                break;
            case "/":
                p.textContent = `${i} ${operatorValue} ${value} = ${i / value}`;
                break;
            default:
                break;
        }
        div.appendChild(p);
    }
    table.appendChild(div);
} 