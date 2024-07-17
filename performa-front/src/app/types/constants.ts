export class TelnetType{

ID: number;
Name : string;

constructor(id:number, name:string){
    this.ID=id;
    this.Name=name;
}
}

export const gradeCategories : TelnetType[]=[
    {"ID":1 ,"Name":"Foundation"},
    {"ID":2 ,"Name":"Management"},
    {"ID":3 ,"Name":"Leadership"},
]


export const managementCategories : TelnetType[]=[
    {"ID":1 ,"Name":"People Management Path"},
    {"ID":2 ,"Name":"Technical Management Path"},
]