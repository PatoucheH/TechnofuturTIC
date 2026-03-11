const myArray = [1,2,3,4,5,6,7,8,9,10];

function sort(array, callback){
    let iPredicate;
    
    for (let i = 0; i < array.length - 1; i++) {
        iPredicate = i;        
        for (let j = i + 1; j < array.length; j++) {
            if(!callback(array[iPredicate], array[j])){
                iPredicate = j;
            } 
        }

        if(iPredicate != i){
            const tmp = array[i];
            array[i] = array[iPredicate];
            array[iPredicate] = tmp;
        }
    }
}

console.log(myArray);

sort(myArray, (a, b) => a > b);

console.log(myArray);

sort(myArray, (a, b) => a < b);

console.log(myArray);