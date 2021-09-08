package Ejercicio3;

public class Empresa {

    private String nombre;
    private Persona[] arrayEmpleados;  //Declarar el tipoDeVariable[] para un array de ese tipo de variable

    public Empresa(String nombre, Persona[] arrayEmpleados) {
        this.nombre = nombre;
        this.arrayEmpleados = arrayEmpleados;
    }

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.arrayEmpleados = null;
    }

    public Persona[] obtArrAntiguedad10(){
        //Genera un array con los empleados con antiguedad mayor a 10
        int j = 0, arrEmpLong = this.arrayEmpleados.length;
        Persona[] empleados = new Persona[arrEmpLong];

        for (Persona arrayEmpleado : this.arrayEmpleados) {     //for each loop
            if (arrayEmpleado.antiguedadMayor10()) {
                empleados[j] = arrayEmpleado;
                j++;
            }
        }
        return empleados;
    }
}
