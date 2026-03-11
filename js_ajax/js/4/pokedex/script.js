const baseUrl = 'https://pokeapi.co/api/v2/';
const mainDiv = document.getElementById("mainDiv");
const nbInput = document.getElementById("nbInput");
nbInput.value = 151;

let pokeDisplay = [];

function createCard(pokemonArray) {
    mainDiv.innerHTML = ""; 

    for (const pokemon of pokemonArray) {
        const div = document.createElement("div");
        div.classList.add("cardPoke");
        const img = document.createElement("img");
        img.src = pokemon.url;
        const namePoke = document.createElement("p");
        namePoke.textContent = pokemon.name;

        const statDiv = document.createElement("div");
        statDiv.classList.add("statDiv");

        const hp = document.createElement("span");
        hp.textContent = `Health : ${pokemon.stats.hp}`;
        const attack = document.createElement("span");
        attack.textContent = `Attack : ${pokemon.stats.attack}`;
        const defense = document.createElement("span");
        defense.textContent = `Defense : ${pokemon.stats.defense}`;
        const specialAttack = document.createElement("span");
        specialAttack.textContent = `Special attack : ${pokemon.stats.specialAttack}`;
        const specialDefense = document.createElement("span");
        specialDefense.textContent = `Special defense : ${pokemon.stats.specialDefense}`;
        const speed = document.createElement("span");
        speed.textContent = `Speed : ${pokemon.stats.speed}`;

        statDiv.append(hp,attack, defense, specialAttack, specialDefense, speed);

        div.append(img, statDiv, namePoke);
        switch (pokemon.mainType) {
            case "grass":
                div.style.backgroundColor = "green";
                break;
            case "water":
                div.style.backgroundColor = "blue";
                break;
            case "fire":
                div.style.backgroundColor = "red";
                break;
            case "bug":
                div.style.backgroundColor = "	rgb(186 218 85)";
                break;
            case "normal":
                div.style.backgroundColor = "lightgray";
                break;
            case "electric":
                div.style.backgroundColor = "yellow";
                break;
            case "poison":
                div.style.backgroundColor = "rgb(158 26 219)";
                break;
            case "ground":
                div.style.backgroundColor = "rgb(206 170 39)";
                break;
            case "fairy":
                div.style.backgroundColor = "rgb(210 96 180)";
                break;
            case "fighting":
                div.style.backgroundColor = "rgb(208 144 27)";
                break;
            case "psychic":
                div.style.backgroundColor = "rgb(216 85 212)";
                break;
            case "rock":
                div.style.backgroundColor = "rgb(109 80 28)";
                break;
            case "ghost":
                div.style.backgroundColor = "rgb(100 21 138)";
                break;
            case "ice":
                div.style.backgroundColor = "rgb(21 130 138)";
                break;
            case "dragon":
                div.style.backgroundColor = "rgb(54 95 161)";
                break;
            default:
                break;
        }   
        mainDiv.append(div);
    }
}

async function main(nb) {
    const response = await fetch(baseUrl + `pokemon/?limit=${nb}`);
    const data = await response.json();
    const pokemonArray = data.results;

    const detailsPromises = pokemonArray.map(pokemon => fetch(pokemon.url).then(response => response.json()));
    const detailsArray = await Promise.all(detailsPromises);

    pokeDisplay = pokemonArray.map((pokemon, index) => ({
        name: pokemon.name,
        url: detailsArray[index].sprites.front_default,
        stats: {
            hp: detailsArray[index].stats[0].base_stat,
            attack: detailsArray[index].stats[1].base_stat,
            defense: detailsArray[index].stats[2].base_stat,
            specialAttack: detailsArray[index].stats[3].base_stat,
            specialDefense: detailsArray[index].stats[4].base_stat,
            speed: detailsArray[index].stats[5].base_stat
        },
        mainType: detailsArray[index].types[0].type.name
    }));
    createCard(pokeDisplay);
}

main(nbInput.value);

nbInput.addEventListener("input", () => {
    const nb = nbInput.value;
    main(nb);
});