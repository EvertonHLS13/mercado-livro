package fundamentos

import com.fasterxml.jackson.databind.node.DoubleNode

class Carro(var cor: String, val anoFabricacao: Int, val dono: Dono) {

    // var variavel mutavel

}

data class Dono (var nome: String, var idade: Int){

}


fun main(){
    var carro = Carro("Branca", 1994, Dono("Gustavo", 24))

    println(carro.cor)

    carro.cor ="Preto"

    println(carro.cor)

    println(carro.dono.nome)

    carro.dono.nome = "Daniel"

    println(carro.dono)
}