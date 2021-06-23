//.b=36

const fs = require("fs");

//.i
function parseData(curr){
	let fecha = new Date(curr.date);
	let tempArr = curr.name.split(" - Vol ");
	let obj = {
		name: curr.name,
		saga: tempArr[0],
		num: Number(tempArr[1]),
		date: fecha,
		day: fecha.getDate(),
		month: fecha.getMonth()+1,
		year: fecha.getFullYear()
	};
	return obj;
}

//.i
function subDate(d2, d1){
	let day2 = d2.fecha.getTime()/86400000;
	let day1 = d1.fecha.getTime()/86400000;

	return (day2-day1)
}

/* main */

let preData = JSON.parse(fs.readFileSync("books_raw.json"));
let data = preData.map(parseData);

let sagas = [];
let ordered = {}
data.forEach((curr) => {
	if(!sagas.includes(curr.saga)){
		ordered[curr.saga] = [curr];
		sagas.push(curr.saga);
	}
	else{
		ordered[curr.saga].push(curr);
	}
});

//.d=3
// let doc = {};
// sagas.forEach((curr) => {
// 	doc[curr] = [];
// });

let v2 = JSON.stringify(ordered, null, 4);
fs.writeFile('books_raw_v2.json', v2, (err) => {
	if(err){
		console.error(err);
	}
	else{
		console.log("Archivo actualizado");
	}
});




