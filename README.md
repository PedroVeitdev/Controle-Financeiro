# Controle Financeiro
Uma aplicação Java estruturada para console projetada para o gerenciamento eficiente de finanças pessoais. O sistema permite o cadastro, listagem e remoção de transações financeiras (receitas e despesas), além do cálculo em tempo real do saldo consolidado. O principal objetivo deste projeto é demonstrar a aplicação prática de padrões de projeto, Programação Orientada a Objetos (POO) avançada e escrita de código limpo (*Clean Code*) em um ambiente Java puro, simulando as exigências técnicas de um ambiente corporativo real

## Conceitos e Tecnologias Aplicadas

*   **Linguagem:** Java (Compatível com JDK 17 ou superior)

*   **Arquitetura:** MVC (Model-View-Controller) simplificado para garantir o isolamento completo de responsabilidades.

*   **Padrões de Projeto (Design Patterns):** 
    *   *Singleton:* Implementado na classe `GerenciadorFinanceiro` para garantir uma única instância global de controle de dados durante a execução.

*   **Programação Orientada a Objetos (POO):**
    *   *Encapsulamento Defensivo:* O método que expõe a lista de transações retorna uma cópia independente (`new ArrayList<>(transacoes)`), blindando o estado interno contra manipulações externas.
    *   *Polimorfismo e Herança:* A subclasse `TransacaoMensal` herda de `Transacao` e sobrescreve o método (`@Override`) `getValorImpacto()` para projetar o peso financeiro acumulado ao longo do tempo de forma dinâmica.
    *   *Enumerações (Enums):* Tipagem forte através do `TipoTransacao` para evitar o uso de strings literais soltas no código.

*   **Tratamento de Erros e Robustez:**
    *   Exceções customizadas com a classe `TransacaoException` para centralizar falhas e regras de negócio.
    *   Prevenção de quebra de buffer do terminal tratando entradas numéricas com `Integer.parseInt(scanner.nextLine())`.

 ##  Funcionalidades do Sistema

O sistema foi construído do zero utilizando recursos nativos da linguagem para cobrir os seguintes requisitos técnicos e de negócio:

*   **Cadastrar Entradas e Saídas:** Permite a inserção de receitas e despesas com validação rigorosa de dados (valores menores ou iguais a zero e descrições vazias são rejeitados).
*   **Transações Recorrentes (Mensais):** Diferencial que estende o comportamento padrão do sistema, calculando o impacto financeiro total com base no número de meses informado pelo usuário.
*   **Listagem de Movimentações:** Exibe de forma organizada todas as transações salvas em memória, acompanhadas de um ID autoincremental exclusivo para controle.
*   **Exibição de Saldo Consolidado:** Realiza o cálculo dinâmico do saldo atualizado. O valor é exibido com formatação de moeda brasileira (`R$`) e cores reativas (Verde para saldo positivo, Vermelho para saldo devedor).
*   **Remoção de Transação por ID:** Exclusão segura de registros da memória. O sistema valida se o ID informado de fato existe e, caso contrário, dispara um alerta sem interromper o programa.
*   **Menu Interativo em Loop:** Interface CLI (Interface de Linha de Comando) controlada por estruturas de repetição que guiam o usuário de forma contínua até que ele decida encerrar o software.
