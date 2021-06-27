# GestaoBancariaUnifacisa
API bancária simples.

## Instruções
1. Configurar o JDBC em \src\main\resources\application.properties
Após execução do projeto, o banco será criado automaticamente com o nome "unifacisa"

2. Executar o script do banco [sql\populaPessoas.sql](sql\populaPessoas.sql) para população da tabela Pessoas (como os campos de data das tabelas Conta e Transacao são preenchidos durante a chamada do JPA, elas deverão ser populadas pela API).

3. Os testes foram realizados com o auxílio do [Reqbin](https://reqbin.com)

4. A documentação está disponível em [\doc\index.html](\doc\index.html)