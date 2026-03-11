const input = document.getElementById("textTask");
const listTask = document.getElementById("listDiv");
const btnAdd = document.getElementById("btnAdd");
const btnClear = document.getElementById("btnClear");

btnAdd.addEventListener("click", () =>{
    const inputValue = input.value;
    if(input.value.trim() === "") return alert("NOPE");
    input.value = "";
    
    const itemList = document.createElement('li');
    const labelCheck = document.createElement("label");
    labelCheck.textContent = inputValue;
    
    const btnCheck = document.createElement("input");
    btnCheck.type = "checkbox";
    btnCheck.classList.add("itemLi");
    btnCheck.addEventListener("change", () => itemList.classList.toggle("textRed"));
    labelCheck.prepend(btnCheck);
    
    const btnDelete = document.createElement("button");
    btnDelete.textContent = "Delete";
    btnDelete.classList.add("itemLi");
    btnDelete.addEventListener("click", () => itemList.remove());
    
    itemList.appendChild(labelCheck);
    itemList.appendChild(btnDelete);

    listTask.appendChild(itemList);
});

btnClear.addEventListener("click", () => listTask.replaceChildren());

