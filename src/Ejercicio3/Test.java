package Ejercicio3;

import java.util.Date;

public class Test {

    public static void main(String args[]){
        //Creo algunas dates
        Date d1 = new Date(1996,1,3);
        Date d2 = new Date(1980,5,20);
        Date d3 = new Date();


        //Creo un par de empleados algunos con mas de 10 años otros no

        Tecnico tecnico1 = new Tecnico("Pepe", "22222222", "direccionFalsa", d1, 'M', "0001", 12, "Electricista", d3);

        Tecnico tecnico2 = new Tecnico("Alberto", "123123", "dirPPPPP", d2, 'M', "0002", 9, "Electricista", d3);

        Tecnico tecnico3 = new Tecnico("Jose", "69696969", "8484848484", d1, 'M', "0003", 11, "Electricista", d3);

        Administrativo admin1 = new Administrativo("Alicia","11111111","dire2", d2, 'F', "0004", 20, "2");

        Administrativo admin2 = new Administrativo("Domi","55555555","dir4141", d2, 'F', "0005", 5, "2");

        Administrativo admin3 = new Administrativo("Alicia","11111111","dire2", d2, 'F', "0006", 8, "2");

        Persona per = new Persona("elVer", "654654","lalala", d1, 'F');

        //Creo un array de personas para instaciar a la empresa
        Persona [] arrPer = new Persona[] {tecnico1,tecnico2,tecnico3,admin1,admin2,admin3, per};

        Empresa emp = new Empresa("LaEmpresa",arrPer);
        Persona [] nuevoArr;
        nuevoArr = emp.obtArrAntiguedad10();

        for (Persona persona: nuevoArr){
            if(persona != null)
                System.out.println(persona + "\n");
        }

    }
}
