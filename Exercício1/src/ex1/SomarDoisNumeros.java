package ex1;
import java.util.*;

class SomarDoisNumeros {
    
    public static void main (String args[]){
        // Declaração de variaveis
        int num1, num2, soma;
        
        // Criar objeto Scanner
        Scanner sc = new Scanner(System.in);
        
        // Leituras
        System.out.println("Digite um número");
        num1 = sc.nextInt();
        System.out.println("Digite outro número");
        num2 = sc.nextInt();
        
        // Fechar o objeto Scanner após a leitura
        sc.close();
        
        // Somar
        soma = num1 + num2;
        
        // Mostrar na tela
        System.out.println("Soma:" + soma);
    }
}