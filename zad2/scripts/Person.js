function Person(json){
    var self = this;
    self.id = json.id;
    self.name  = json.firstName;
    self.surname = json.lastName;
    self.gender = json.gender;
    self.email = json.email;
    self.age = json.age;
    self.birthsday = json.birthsday;
    self.income = json.income;
    
    
    self.toTableRow = function(lp){
        return '<tr><td>' 
            + lp
            +'</td><td>'
            +self.id
            +'</td><td>'
            +self.name
            +'</td><td>'
            +self.surname
            +'</td><td>'
            +self.gender
            +'</td><td>'
            +self.email
            +'</td><td>'
            +self.age
            +'</td><td>'
            +self.birthsday
            +'</td><td>'
            +self.income
            +'</td></tr>'
    }
    
    self.fullName = function(){
        return sum(self.name, self.surname);
    }
    
    var sum = function(a,b){
        return a+ " "+b;
    }
    
}