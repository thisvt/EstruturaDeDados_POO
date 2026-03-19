---

# 🧩 20 Perguntas — Torre de Hanói em Java

## 🧠 Parte 1 – Conceitos Gerais

1. O que representa o problema da Torre de Hanói? 
- Representa um  quebra-cabeça matematico e logico utilizado para desenvolver o pensamento estrategico, planejamento, memoria, e resolução de problemas. 
2. Quem foi o criador da Torre de Hanói e em que ano ela foi proposta?
- Introduzido pelo matematico frances Edouard Lucas em 1883
3. Quais são as três regras fundamentais do jogo da Torre de Hanói?
- Um por vez, o topo primeiro e maior nunca em cima do menor
4. Qual é o objetivo principal do algoritmo da Torre de Hanói?  
- O objetivo principal é encontrar a sequencia exata de movimentos para transferir todos os discos da haste de origem para a haste de destino, usando o minimo de movimentos possivel 
5. Qual é a fórmula que calcula o número mínimo de movimentos necessários para resolver o problema com `n` discos?  
- M = 2^n - 1
6. Quantos movimentos são necessários para resolver o problema com 3 discos?  
- 2^3 - 1 = 7 movimentos 
7. Qual o tempo de complexidade do algoritmo da Torre de Hanói?
- O tempo de complexidade do algoritmo da torre de hanoi é O(2^n) 
8. Por que o problema da Torre de Hanói é considerado um exemplo clássico de **recursão**?
- Porque a solução para n discos depende de resolver o mesmo problema para n - 1 discos 
9. O que significa “caso base” em um algoritmo recursivo, e qual é o caso base na Torre de Hanói?
- O caso base é a condição de parada de um algoritmo recursivo. É a unica parte do codigo que sabe resolver o problema diretamente, sem precisar chamar a si mesmo novamente 
10. O que acontece com o número de movimentos totais quando se adiciona mais um disco ao problema?
-  O tempo praticamente dobra 

---

## 💻 Parte 2 – Código Java

11. Qual é o papel dos parâmetros `origem`, `destino` e `auxiliar` no método `moverDiscos()`?  
- Servem para orientar o algoritmo sobre a função de cada haste em cada etapa da recursão. Origem: É a haste onde os discos estão empilhados no inicio. Destino: É a haste para onde você quer levar os discos nessa subetapa. Auxiliar: É a haste que serve de ponto de apoiop temporario, permitindo que você mova os discos menores para liberar caminho sem violar as regras 
12. O que acontece se o caso base `if (n == 1)` for removido do código?
- O algoritmo entrará em uma recursão infinita
13. No trecho abaixo, o que significa a linha `moverDiscos(n - 1, origem, auxiliar, destino);`?

    ```java
    moverDiscos(n - 1, origem, auxiliar, destino);
    System.out.println("Mover disco " + n + " de " + origem + " para " + destino);
    moverDiscos(n - 1, auxiliar, destino, origem);
    ```
    - Passo 1: mover n-1 discos da origem para a haste auxiliar
        // Isso libera o maior disco (n) para ser movido diretamente ao destino.
        moverDiscos(n - 1, origem, auxiliar, destino);

        // Passo 2: mover o disco maior (n) da origem para o destino
        System.out.println("Mover disco " + n + " de " + origem + " para " + destino);

        // Passo 3: mover os n-1 discos da haste auxiliar para o destino
        // Assim, todos os discos acabam empilhados corretamente no destino.
        moverDiscos(n - 1, auxiliar, destino, origem);

14. Por que o algoritmo chama o próprio método dentro dele (recursão)? 
- Porque a estrategia de soluçao para mover um bloco de discos é identica, independentemente de quantos discos existam na pilha 
15. O que a função `System.out.println()` exibe em cada iteração da função recursiva?  
- Pois ela exibe a origem da haste e o destino para qual o disco vai
16. Como o número de chamadas recursivas está relacionado ao número de discos (`n`)?
- O numero de chamadas recursivas cresce de forma exponencial em relação ao numeros de discos 
17. O que aconteceria se os parâmetros `destino` e `auxiliar` fossem trocados na primeira chamada recursiva? 
- Quebraria as regras impostas pelo algoritmo da torre 
18. Qual é o tipo de dado utilizado para representar as hastes (`A`, `B`, `C`) no código?  
-  moverDiscos(n, 'A', 'C', 'B');
19. No programa com contador de movimentos, qual é a finalidade da variável `contador`?  
- Contar o numero de movimentos feitos 
20. Se `n = 4`, quantos movimentos o programa imprimirá no total?
- 15 movimentos 

---
