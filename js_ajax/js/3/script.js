// --------------- //
// ----- DOM ----- //
// --------------- //
const tasksTable = document.getElementById('tasks-table');
const taskForm = document.getElementById('task-form');
const taskNameInput = document.getElementById('task-name-input');
const taskNameHeader = document.getElementById('task-name-header');
const searchInput = document.getElementById('search-input');
const taskDateInput = document.getElementById("task-date-input")

// ------------------- //
// ----- LOGIQUE ----- //
// ------------------- //
const sortIcons = ['fa-sort', 'fa-caret-down', 'fa-caret-up'];
const tasks = [
    { name: 'Faire la vaisselle', isClosed: false, limitDate: '2026-03-10' },
    { name: 'Étudier Javascript', isClosed: false, limitDate: '2026-03-11' },
    { name: 'Regarder la télévision', isClosed: true, limitDate: '2026-03-10' },
];
let nameSort = 0; // 0 -> pas de tri 1-> tri croissant 2 -> tri décroissant

function updateSort() {
    // modifier le tri
    nameSort = (nameSort + 1) % 3;
    renderHTML();
}

function removeTask(t) {
    // retirer une tache de la liste
    const i = tasks.indexOf(t);
    tasks.splice(i, 1);
    renderHTML();
}

function doneTask(t) {
    // mettre une tache de la liste en done 
    const i = tasks.indexOf(t);
    tasks[i].isClosed ? tasks[i].isClosed = false : tasks[i].isClosed = true;
    console.log(tasks[i].isClosed);
}

function addTask(e) {
    // empecher le rechargement de la page
    e.preventDefault();
    // vérifier que l'input n'est pas vide
    if(!taskNameInput.value.trim()) {
        return;
    }
    // ajouter dans la liste la nouvelle tache    
    tasks.push({name: taskNameInput.value.trim(), isClosed: false, limitDate: taskDateInput.value });
    taskNameInput.value = '';
    taskDateInput.value = "";
    renderHTML();
}

function applyFilters() {
    // trier en fct de l'ordre
    let toRender = tasks.toSorted(((item1, item2) => {
        if(nameSort === 0) {
            return 1;
        } 
        else if(nameSort === 1) {
            return item1.name.localeCompare(item2.name);
        }
        else {
            return item2.name.localeCompare(item1.name);
        }
    }));

    // filtrer en fonction de la valeur de l'input « search »
    toRender = toRender.filter(item => 
        item.name.toLocaleLowerCase()
            .startsWith(searchInput.value.toLocaleLowerCase())
    );
    
    return toRender;
}

// ------------------------------ //
// ----- RENDER / AFFICHAGE ----- //
// ------------------------------ //

function createRow(task) {
    // création d'une ligne
    const tr = document.createElement('tr');

    // création d'une colonne pour le nom
    const td = document.createElement('td');
    td.textContent = task.name;

    // création d'une colonne pour la date
    const tdDate = document.createElement('td');
    tdDate.textContent = task.limitDate;

    // création d'une colonne pour les actions
    const tdActions = document.createElement('td');
    tdActions.append(createDeleteButton(task));
    tdActions.append(createDoneButton(task));
    // ajout des 2 colonnes dans la ligne
    tr.append(td, tdDate, tdActions);
    return tr;
}

function createDeleteButton(task) {
    const button = document.createElement('button');
    button.classList.add('btn-remove');
    button.addEventListener('click', () => removeTask(task));
    const trashIcon = document.createElement('i');
    trashIcon.classList.add('fa', 'fa-trash');
    button.append(trashIcon);
    return button;
}

function createDoneButton(task){
    const checkbox = document.createElement('input')
    checkbox.type = "checkbox";
    checkbox.id = "check-done";
    checkbox.addEventListener("change", () => doneTask(task));
    return checkbox;
}

function renderHTML() {
    // vider la table
    tasksTable.querySelector('tbody').innerHTML = '';
    // appliquer les tris et filtres
    toRender = applyFilters();
    updateHeaderIcon();
    // ajouter les lignes dans la table
    tasksTable.querySelector('tbody').append(...toRender.map(createRow));
}

function updateHeaderIcon() {
    // modifier l'icone du header
    taskNameHeader.querySelector('i.fa').classList.remove(...sortIcons);
    taskNameHeader.querySelector('i.fa').classList.add(sortIcons[nameSort]);
}

// ---------------- //
// ----- INIT ----- //
// ---------------- //
renderHTML();


// ----------------------- //
// ----- INIT EVENTS ----- //
// ----------------------- //
taskForm.addEventListener('submit', addTask);
taskNameHeader.addEventListener('click', updateSort);
searchInput.addEventListener('input', renderHTML);