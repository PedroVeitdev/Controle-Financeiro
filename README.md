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

## Estrutura de Pacotes

A organização dos arquivos segue estritamente a divisão de responsabilidades em engenharia de software:

controle_financeiro/

├── app/          Classe de entrada (Main) e gerenciamento da interface CLI (View)

├── controller/   Lógica central e coordenação dos dados (GerenciadorFinanceiro)

├── model/        Entidades de dados, subclasses polimórficas e Enums

├── exceptions/   Exceções customizadas do domínio da aplicação

└── utils/        Classes utilitárias (Formatação de moeda padrão pt-BR)
