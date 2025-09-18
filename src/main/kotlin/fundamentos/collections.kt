package fundamentos

fun main(){

    var mapNomeIdade = mutableMapOf("Gustavo" to 24, "Daniel" to 20)
    println(mapNomeIdade)

    mapNomeIdade["Bruno"] = 30

    println(mapNomeIdade)

    mapNomeIdade.remove("Bruno") // Funciona aémas com a chave

    println(mapNomeIdade)

//    mapNomeIdade["Gustavo"] = 30 // Sobrescreve o valor
//
//    println(mapNomeIdade)

    mapNomeIdade.putIfAbsent("Bruno", 24) // Não  sobrescreve valor de chave suplicada apenas o valor

    println(mapNomeIdade)

//    println(lista)
//
//    lista.sort() // ordena a lista inclusive em ordem alfabetica
//
//    println(lista)
//
//    lista.shuffle() // Embaralha a lista
//
//    println(lista)

//    var listaNomes = mutableListOf("Gustavo", "Daniel")

//    println(listaNomes)
//
//    listaNomes.sort()
//
//    println(listaNomes)



//    lista.add(8)
//
//    lista.removeAt(0) // remover pelo indice
//    lista.remove(3) // remover pelo própio elemento

    //lista[3] = 20



//    for ( numero in lista){
//        println(numero)
//
//    }


//    println(lista[0])
//    println(lista.get(0))
//    println(lista.size)
//    println(lista.indexOf(6))


}