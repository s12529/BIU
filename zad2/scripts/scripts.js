var viewModel = new PeopleTableViewModel({
	pageSize:25, count:data.length, context:document.getElementById('table')
});

var comparator = new Comparators();


function init(){
    /*var listOfPeople = new ListOfPeople();
    for(var i =0;i<data.length;i++){
            listOfPeople.addPerson(data[i]);
        }
    var context = document.getElementById('table');
    context.innerHTML = listOfPeople.toTable();*/
    viewModel.next();
}





















