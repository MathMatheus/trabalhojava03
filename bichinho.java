import java.util.Scanner;

class Animal {
    private String nome;
    private String classe;
    private String familia;
    private int idade;
    private boolean estadoVivo;
    private int caloria;
    private int forca;

    public Animal(String nome, String classe, String familia) {
        this.nome = nome;
        this.classe = classe;
        this.familia = familia;
        this.idade = 0;
        this.estadoVivo = true;
        this.caloria = 10;
        this.forca = 10;
    }

    public void exibirEstado() {
        System.out.println("\nParabéns! Você acabou de criar um novo amigo!");
        System.out.println("Este é " + nome + ". Ele é da classe " + classe + " e pertence à família " + familia + ".");
        System.out.println("Força: " + forca + ", Calorias: " + caloria + ", Idade: " + idade);
        System.out.println("A jornada de vocês juntos começa agora! Cuide bem dele.");
    }

    public void morrer() {
        this.forca = 0;
        this.estadoVivo = false;
        System.out.println("\nInfelizmente, " + nome + " não está mais entre nós.");
        System.out.println("Obrigado por brincar e cuidar dele até agora.");
        System.out.println("GAME OVER.");
    }

    public void comer() {
        if (!estadoVivo) {
            System.out.println("Infelizmente, o seu amiguinho se foi. Ele não pode comer agora.");
            return;
        }
        if (caloria >= 100) {
            System.out.println("Opa! Parece que o bichinho está cheio demais para comer mais.");
        } else {
            caloria += 10;
            forca -= 2;
            System.out.println(nome + " comeu com vontade! Agora ele está mais cheio e tem energia para brincar.");
            System.out.println("Força atual: " + forca + ", Calorias: " + caloria);
        }
    }

    public void correr() {
        if (!estadoVivo) {
            System.out.println("Seu amigo não pode mais correr. Ele já nos deixou.");
            return;
        }
        if (caloria <= 0) {
            System.out.println(nome + " está muito cansado e sem energia para correr agora.");
        } else {
            caloria -= 5;
            forca -= 5;
            System.out.println(nome + " está correndo pela sala, que energia!");
            System.out.println("Força atual: " + forca + ", Calorias: " + caloria);
        }
    }

    public void dormir() {
        if (!estadoVivo) {
            System.out.println("Seu amigo não pode mais dormir. Ele já nos deixou.");
            return;
        }
        forca += 10;
        caloria -= 2;
        System.out.println(nome + " está tirando uma soneca tranquila...");
        try {
            Thread.sleep(1000); // Pausa para simular "dormindo"
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(nome + " acordou renovado!");
        System.out.println("Força atual: " + forca + ", Calorias: " + caloria);
    }

    public boolean estaVivo() {
        return estadoVivo;
    }

    public boolean verificarCondicoesDeVida() {
        if (forca <= 0 || caloria <= 0) {
            System.out.println("\n" + nome + " está muito fraco para continuar...");
            morrer();
            return false;
        }
        return true;
    }
}

public class JogoBichinhoVirtual {
    public static Animal criarAnimal() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Vamos criar um novo amigo!");
        System.out.print("Digite o nome do seu bichinho: ");
        String nome = scanner.nextLine();
        System.out.print("Qual é a classe do seu bichinho? (Ex: Mamífero, Reptil): ");
        String classe = scanner.nextLine();
        System.out.print("E qual é a família dele? (Ex: Canídeos, Felídeos): ");
        String familia = scanner.nextLine();

        Animal animal = new Animal(nome, classe, familia);
        animal.exibirEstado();
        return animal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Animal animal = criarAnimal();

        while (animal.estaVivo()) {
            System.out.println("\nO que você gostaria que seu amiguinho fizesse agora?");
            System.out.println("1 - Comer");
            System.out.println("2 - Correr");
            System.out.println("3 - Dormir");
            System.out.println("4 - Morrer (Encerrar o jogo)");

            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    animal.comer();
                    break;
                case "2":
                    animal.correr();
                    break;
                case "3":
                    animal.dormir();
                    break;
                case "4":
                    animal.morrer();
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

            if (!animal.verificarCondicoesDeVida()) {
                break;
            }
        }

        scanner.close();
    }
}
