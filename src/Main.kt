fun main (){

    val produtos = mutableMapOf<String, Double>() //utilizado para criar um array dos produtos
    
    val filaProdutos: ArrayDeque<String> = ArrayDeque()

    val historico: ArrayDeque<String> = ArrayDeque() //utilizado para mostrar o histórico de operações
    
    var op : Int //variavel utilizada para selecionar a ação

    do {
        println("\n== Lista de Supermercado ==")
        println("Selecione: ")
        println("1 - Adicionar produto")
        println("2 - Remover produto")
        println("3 - Alterar preço")
        println("4 - Mostrar Lista")

        println("Operações Adicionais: ")
        println("5 - Processar fila de produtos") //fila
        println("6 - Apresentar Histórico") //pilha (stack)

        println("0 - Sair")
        val entrada = readln().toIntOrNull()
        //menu de seleção

        if (entrada == null){
            println("Digite apenas números.")
            continue
        }

        op = entrada

        if (op < 0 || op > 6 ) {
            //valição de entrada. Se for digitado algo fora do apresentado,
            //ele mostra mensagem de erro e apresenta o menu novamente.

            println("Seleção Inválida.")

        } else {

            when (op) { //executa o menu de selação de acordo com a operação selecionada
                1 -> {
                    println("Nome do produto: ")
                    val nome = readln()

                    println("Digite o preço: ")
                    val preco = readln().toDouble()

                    produtos[nome] = preco

                    filaProdutos.addLast(nome)

                    historico.addLast("Adicionado: $nome")

                    println("Produto adicionado!")

                } //ação que adiciona os dados do produto na lista Map
                 //fila -> primeiro que entra, primeiro que sai (quando o produto é adicionado, é colocado ele na fila.)

                2 -> {
                    println("Digite o produto para remover: ")
                    val nome = readln()

                    if (produtos.containsKey(nome)) {

                        produtos.remove(nome)

                        println("Produto removido com sucesso.")
                    } else {

                        println("Produto não encontrado.")
                    }

                } //permite remover o produto existente na lista

                3 -> {
                    println("Digite o nome do produto para alterar preço: ")
                    val nome = readln()

                    if (produtos.containsKey(nome)) {
                        println("Novo preço: ")
                        val novoPreco = readln().toDouble()

                        produtos[nome] = novoPreco

                        println("Preço do produto atualizado com sucesso.")
                    } else {

                        println("Produto não encontrado.")

                    }
                } //permite alterar o preço do produto existente na lista

                4 -> {
                    println("\nLista de Produtos:")

                    if (produtos.isEmpty()) {
                        print("Não há produtos para mostrar.")

                    } else {

                        val quantidade = produtos.size // size -> quantidade de produtos no map
                        val total = produtos.values.sum() //values -> lista de preços -> sum()/soma -> total

                        println("\nQuantidade de Produtos: $quantidade")
                        println("\nValor total dos produtos: $total")

                        for ((nome, preco) in produtos) {
                            println("\n$nome - R$ %.2f".format(preco))

                        }

                        println("Deseja ordenar produtos? (y/n)")
                        val resposta = readln()

                        if (resposta == "n") {

                            println("\nVoltando ao menu ...")

                        } else if (resposta == "y") {
                            println("\nProdutos ordenados por preço: ")

                            val produtosOrdenados = produtos.toList().sortedBy { it.second }

                            for ((nome, preco) in produtosOrdenados) {
                                println("$nome - $preco")
                            }
                        }//apresenta a lista e quantidade de produtos. Se estiver vazia, apresenta uma mensagem indicando. Caso contrário, apresenta a lista existente.
                    }

                }

                5 -> {
                    if (filaProdutos.isEmpty()) {
                        println("Nenhum produto na fila.")
                    } else {
                        val produto = filaProdutos.removeFirst()

                        Thread {
                            println("Processando $produto ...")

                            Thread.sleep(2000)

                            println("Processando produto: $produto")

                            historico.addLast("Processado: $produto")
                        }.start()
                        //O Thread permite executar algo ao mesmo tempo que o programa continua
                    }
                }

                6 -> {
                    if (historico.isEmpty()) {
                        println("Nenhuma ação foi registrada.")

                    } else {
                        println("Histórico de ações: ")

                        for (acao in historico.reversed()) {
                            println(acao)
                        } //o ".reversed() mostra a pilha como: ultima ação primeiro
                    }
                }

                0 -> {
                    println("Encerrando...")
                }

            }
        }

    } while (entrada != 0) //repete até o usuário escolher sair

}