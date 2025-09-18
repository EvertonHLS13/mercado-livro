package fundamentos

fun retornaNumeroPorExtenso (numero: Int): String {
    if (numero == 5){
        return "Cinco"
    } else if (numero == 6){
        return "Seis"
    }

    return "Numero nao mapeado"
}

fun main(){
   println(retornaNumeroPorExtenso(0))
}