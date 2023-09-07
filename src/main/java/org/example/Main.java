package org.example;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Llamar al menú principal
        menu(scanner);
    }

    public static void menu(Scanner scanner) {
        int opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1. Crear ArrayList, Dividir y Sumar.");
            System.out.println("2. Salir del programa.");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Obtener el largo del array y asegurarse de que sea válido
                    int largoArray = obtenerLargoValido(scanner);

                    // Crear un ArrayList de números enteros con el largo especificado
                    ArrayList<Integer> miArrayList = crearArrayListConLargo(scanner, largoArray);

                    try {
                        // Realizar las divisiones consecutivas
                        ArrayList<Integer> resultadosDivision = dividirElementosConsecutivos(miArrayList);

                        // Sumar los resultados de la división
                        int sumaResultados = sumarResultados(resultadosDivision);

                        System.out.println("La suma de las divisiones consecutivas es: " + sumaResultados);
                    } catch (DivisionPorCeroException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 2);
    }

    public static int obtenerLargoValido(Scanner scanner) {
        int largo;
        do {
            System.out.print("Ingrese el largo del array (debe ser mayor o igual a 4 y par): ");
            largo = scanner.nextInt();
            if (!esLargoValido(largo)) {
                System.out.println("El rango ingresado no es válido. Debe ser mayor o igual a 4 y par.");
            }
        } while (!esLargoValido(largo));
        return largo;
    }

    public static boolean esLargoValido(int largo) {
        return largo >= 4 && largo % 2 == 0;
    }

    public static ArrayList<Integer> crearArrayListConLargo(Scanner scanner, int largo) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < largo; i++) {
            System.out.print("Ingrese un número entero: ");
            int numero = scanner.nextInt();
            arrayList.add(numero);
        }
        return arrayList;
    }

    public static ArrayList<Integer> dividirElementosConsecutivos(ArrayList<Integer> arrayList) throws DivisionPorCeroException {
        int longitud = arrayList.size();
        ArrayList<Integer> resultados = new ArrayList<>();
        for (int i = 0; i < longitud / 2; i++) {
            int primerNumero = arrayList.get(i);
            int ultimoNumero = arrayList.get(longitud - i - 1);
            if (ultimoNumero == 0) {
                throw new DivisionPorCeroException("División por cero detectada.");
            }
            int resultado = primerNumero / ultimoNumero;
            resultados.add(resultado);
        }
        return resultados;
    }

    public static int sumarResultados(ArrayList<Integer> resultados) {
        int suma = 0;
        for (int resultado : resultados) {
            suma += resultado;
        }
        return suma;
    }
}

class DivisionPorCeroException extends Exception {
    public DivisionPorCeroException(String mensaje) {
        super(mensaje);
    }
}



