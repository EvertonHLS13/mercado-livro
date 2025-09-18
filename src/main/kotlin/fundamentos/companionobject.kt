package fundamentos

class MinhaClasse(
    var nome: String,
    var endereco: String,
    var idade: Int

) {
    companion object { // Utilizar um método sem precisar instanciar a classe
        fun criarComValoresPadrao(): MinhaClasse {
            return MinhaClasse("Gustavo", "Rua teste", 24)
        }

        fun criarApartirDeSegundaClasse(segundaClasse: SegundaClasse): MinhaClasse{
            return MinhaClasse(segundaClasse.nome, segundaClasse.endereco, segundaClasse.idade)
        }

    }
}

class SegundaClasse(
    var nome: String,
    var endereco: String,
    var idade: Int

) {
    fun criarComValoresPadrao(): SegundaClasse {
        return SegundaClasse("Gustavo", "Rua teste", 24)
    }
}

fun main(){

    var segundaClasse = SegundaClasse("nome", "endereco", 10).criarComValoresPadrao()

    var minhaLoader = MinhaClasse.criarApartirDeSegundaClasse(segundaClasse) // chama o objeto manipulando a classe e a função



}