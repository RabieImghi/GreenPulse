public class Person {
    String nom;
    String cin;
    static int id = 0;
    int age;

    public void addPerson(String cin ,String nom,int age){
        this.id ++;
        this.cin = cin;
        this.nom = nom;
        this.age = age;
    }
}
